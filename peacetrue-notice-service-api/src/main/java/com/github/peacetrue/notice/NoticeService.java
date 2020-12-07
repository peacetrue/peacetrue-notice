package com.github.peacetrue.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;

/**
 * 通知公告服务接口
 *
 * @author xiayx
 */
public interface NoticeService {

    /** 新增 */
    Mono<NoticeVO> add(NoticeAdd params);

    /** 分页查询 */
    Mono<Page<NoticeVO>> query(@Nullable NoticeQuery params, @Nullable Pageable pageable, String... projection);

    /** 全量查询 */
    Flux<NoticeVO> query(NoticeQuery params, @Nullable Sort sort, String... projection);

    /** 全量查询 */
    default Flux<NoticeVO> query(NoticeQuery params, String... projection) {
        return this.query(params, (Sort) null, projection);
    }

    /** 获取 */
    Mono<NoticeVO> get(NoticeGet params, String... projection);

    /** 修改 */
    Mono<Integer> modify(NoticeModify params);

    /** 删除 */
    Mono<Integer> delete(NoticeDelete params);

    /** 发布 */
    Mono<Integer> publish(NoticePublish params);

    /** 浏览 */
    Mono<Integer> view(NoticeView params);

}
