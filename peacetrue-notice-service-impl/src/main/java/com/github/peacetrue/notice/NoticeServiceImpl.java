package com.github.peacetrue.notice;

import com.github.peacetrue.core.IdCapable;
import com.github.peacetrue.core.OperatorCapable;
import com.github.peacetrue.core.Range;
import com.github.peacetrue.spring.data.relational.core.query.CriteriaUtils;
import com.github.peacetrue.spring.data.relational.core.query.UpdateUtils;
import com.github.peacetrue.spring.util.BeanUtils;
import com.github.peacetrue.util.DateUtils;
import com.github.peacetrue.util.StreamUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.data.domain.*;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 通知公告服务实现
 *
 * @author xiayx
 */
@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private R2dbcEntityOperations entityOperations;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public static Criteria buildCriteria(NoticeQuery params) {
        return CriteriaUtils.and(
                CriteriaUtils.nullableCriteria(CriteriaUtils.smartIn("id"), params::getId),
                CriteriaUtils.nullableCriteria(Criteria.where("title")::like, value -> "%" + value + "%", params::getTitle),
                CriteriaUtils.nullableCriteria(Criteria.where("source")::is, params::getSource),
                CriteriaUtils.nullableCriteria(Criteria.where("stateId")::is, params::getStateId),
                CriteriaUtils.nullableCriteria(Criteria.where("publishedTime")::greaterThanOrEquals, params.getPublishedTime()::getLowerBound),
                CriteriaUtils.nullableCriteria(Criteria.where("publishedTime")::lessThan, DateUtils.DATE_CELL_EXCLUDE, params.getPublishedTime()::getUpperBound),
                CriteriaUtils.nullableCriteria(Criteria.where("creatorId")::is, params::getCreatorId),
                CriteriaUtils.nullableCriteria(Criteria.where("createdTime")::greaterThanOrEquals, params.getCreatedTime()::getLowerBound),
                CriteriaUtils.nullableCriteria(Criteria.where("createdTime")::lessThan, DateUtils.DATE_CELL_EXCLUDE, params.getCreatedTime()::getUpperBound),
                CriteriaUtils.nullableCriteria(Criteria.where("modifierId")::is, params::getModifierId),
                CriteriaUtils.nullableCriteria(Criteria.where("modifiedTime")::greaterThanOrEquals, params.getModifiedTime()::getLowerBound),
                CriteriaUtils.nullableCriteria(Criteria.where("modifiedTime")::lessThan, DateUtils.DATE_CELL_EXCLUDE, params.getModifiedTime()::getUpperBound)
        );
    }

    @Override
    @Transactional
    public Mono<NoticeVO> add(NoticeAdd params) {
        log.info("新增通知公告信息[{}]", params);
        if (params.getRemark() == null) params.setRemark("");
        if (params.getViewCount() == null) params.setViewCount(0);
        if (params.getStateId() == null) params.setStateId(NoticeState.draft.getId());
        if (params.getPublishedTime() == null) params.setPublishedTime(LocalDateTime.now());
        Notice entity = BeanUtils.map(params, Notice.class);
        entity.setCreatorId(params.getOperatorId());
        entity.setCreatedTime(LocalDateTime.now());
        entity.setModifierId(entity.getCreatorId());
        entity.setModifiedTime(entity.getCreatedTime());
        return entityOperations.insert(entity)
                .map(item -> BeanUtils.map(item, NoticeVO.class))
                .doOnNext(item -> eventPublisher.publishEvent(new PayloadApplicationEvent<>(item, params)));
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Page<NoticeVO>> query(@Nullable NoticeQuery params, @Nullable Pageable pageable, String... projection) {
        log.info("分页查询通知公告信息[{}]", params);
        if (params == null) params = NoticeQuery.DEFAULT;
        if (params.getPublishedTime() == null) params.setPublishedTime(Range.LocalDateTime.DEFAULT);
        if (params.getCreatedTime() == null) params.setCreatedTime(Range.LocalDateTime.DEFAULT);
        if (params.getModifiedTime() == null) params.setModifiedTime(Range.LocalDateTime.DEFAULT);
        Pageable finalPageable = pageable == null ? PageRequest.of(0, 10) : pageable;
        Criteria where = buildCriteria(params);

        return entityOperations.count(Query.query(where), Notice.class)
                .flatMap(total -> total == 0L ? Mono.empty() : Mono.just(total))
                .<Page<NoticeVO>>flatMap(total -> {
                    Query query = Query.query(where).with(finalPageable).sort(finalPageable.getSortOr(Sort.by("createdTime").descending()));
                    return entityOperations.select(query, Notice.class)
                            .map(item -> BeanUtils.map(item, NoticeVO.class))
                            .reduce(new ArrayList<>(), StreamUtils.reduceToCollection())
                            .map(item -> new PageImpl<>(item, finalPageable, total));
                })
                .switchIfEmpty(Mono.just(new PageImpl<>(Collections.emptyList(), finalPageable, 0L)));
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<NoticeVO> query(@Nullable NoticeQuery params, @Nullable Sort sort, String... projection) {
        log.info("全量查询通知公告信息[{}]", params);
        if (params == null) params = NoticeQuery.DEFAULT;
        if (params.getPublishedTime() == null) params.setPublishedTime(Range.LocalDateTime.DEFAULT);
        if (params.getCreatedTime() == null) params.setCreatedTime(Range.LocalDateTime.DEFAULT);
        if (params.getModifiedTime() == null) params.setModifiedTime(Range.LocalDateTime.DEFAULT);
        if (sort == null) sort = Sort.by("createdTime").descending();
        Criteria where = buildCriteria(params);
        Query query = Query.query(where).sort(sort).limit(100);
        return entityOperations.select(query, Notice.class)
                .map(item -> BeanUtils.map(item, NoticeVO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<NoticeVO> get(NoticeGet params, String... projection) {
        log.info("获取通知公告信息[{}]", params);
//        Criteria where = CriteriaUtils.and(
//                CriteriaUtils.nullableCriteria(Criteria.where("id")::is, params::getId),
//        );
        Criteria where = Criteria.where("id").is(params.getId());
        return entityOperations.selectOne(Query.query(where), Notice.class)
                .map(item -> BeanUtils.map(item, NoticeVO.class));
    }

    @Override
    @Transactional
    public Mono<Integer> modify(NoticeModify params) {
        log.info("修改通知公告信息[{}]", params);
        return this.modifyGeneric(params);
    }

    private <T extends IdCapable<Long> & OperatorCapable<Long>> Mono<Integer> modifyGeneric(T params) {
        Criteria where = Criteria.where("id").is(params.getId());
        Query idQuery = Query.query(where);
        return entityOperations.selectOne(idQuery, Notice.class)
                .zipWhen(entity -> {
                    Notice modify = BeanUtils.map(params, Notice.class);
                    modify.setModifierId(params.getOperatorId());
                    modify.setModifiedTime(LocalDateTime.now());
                    Update update = UpdateUtils.selectiveUpdateFromExample(modify);
                    return entityOperations.update(idQuery, update, Notice.class);
                })
                .map(tuple2 -> {
                    NoticeVO vo = BeanUtils.map(tuple2.getT1(), NoticeVO.class);
                    BeanUtils.copyProperties(params, vo, BeanUtils.EMPTY_PROPERTY_VALUE);
                    eventPublisher.publishEvent(new PayloadApplicationEvent<>(vo, params));
                    return tuple2.getT2();
                })
                .switchIfEmpty(Mono.just(0));
    }

    @Override
    @Transactional
    public Mono<Integer> delete(NoticeDelete params) {
        log.info("删除通知公告信息[{}]", params);
        Criteria where = Criteria.where("id").is(params.getId());
        Query idQuery = Query.query(where);
        return entityOperations.selectOne(idQuery, Notice.class)
                .map(item -> BeanUtils.map(item, NoticeVO.class))
                .zipWhen(region -> entityOperations.delete(idQuery, Notice.class))
                .doOnNext(tuple2 -> eventPublisher.publishEvent(new PayloadApplicationEvent<>(tuple2.getT1(), params)))
                .map(Tuple2::getT2)
                .switchIfEmpty(Mono.just(0));
    }

    @Override
    public Mono<Integer> publish(NoticePublish params) {
        log.info("发布通知公告信息[{}]", params);
        Criteria where = Criteria.where("id").is(params.getId());
        Query idQuery = Query.query(where);

        Notice modify = new Notice();
        modify.setId(params.getId());
        modify.setStateId(NoticeState.publish.getId());
        modify.setPublishedTime(LocalDateTime.now());
        modify.setModifierId(params.getOperatorId());
        modify.setModifiedTime(LocalDateTime.now());
        Update update = UpdateUtils.selectiveUpdateFromExample(modify);
        return entityOperations.update(idQuery, update, Notice.class)
                .doOnNext(count -> {
                    NoticeVO vo = BeanUtils.map(modify, NoticeVO.class);
                    eventPublisher.publishEvent(new PayloadApplicationEvent<>(vo, params));
                })
                .switchIfEmpty(Mono.just(0));
    }

    @Override
    public Mono<Integer> view(NoticeView params) {
        log.info("浏览通知公告信息[{}]", params);
        Criteria where = Criteria.where("id").is(params.getId());
        Query idQuery = Query.query(where);
        return entityOperations.selectOne(idQuery, Notice.class)
                .zipWhen(entity -> {
                    //TODO 并发BUG
                    Update viewCount = Update.update("viewCount", entity.getViewCount() + 1);
                    return entityOperations.update(idQuery, viewCount, Notice.class);
                })
                .map(tuple2 -> {
                    NoticeVO vo = BeanUtils.map(tuple2.getT1(), NoticeVO.class);
                    BeanUtils.copyProperties(params, vo, BeanUtils.EMPTY_PROPERTY_VALUE);
                    eventPublisher.publishEvent(new PayloadApplicationEvent<>(vo, params));
                    return tuple2.getT2();
                })
                .switchIfEmpty(Mono.just(0));
    }
}
