package com.github.peacetrue.notice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 通知公告控制器
 *
 * @author xiayx
 */
@Slf4j
@RestController
@RequestMapping(value = "/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<NoticeVO> addByForm(NoticeAdd params) {
        log.info("新增通知公告信息(请求方法+表单参数)[{}]", params);
        return noticeService.add(params);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<NoticeVO> addByJson(@RequestBody NoticeAdd params) {
        log.info("新增通知公告信息(请求方法+JSON参数)[{}]", params);
        return noticeService.add(params);
    }

    @GetMapping(params = "page")
    public Mono<Page<NoticeVO>> query(NoticeQuery params, Pageable pageable, String... projection) {
        log.info("分页查询通知公告信息(请求方法+参数变量)[{}]", params);
        return noticeService.query(params, pageable, projection);
    }

    @GetMapping
    public Flux<NoticeVO> query(NoticeQuery params, Sort sort, String... projection) {
        log.info("全量查询通知公告信息(请求方法+参数变量)[{}]", params);
        return noticeService.query(params, sort, projection);
    }

    @GetMapping("/{id}")
    public Mono<NoticeVO> getByUrlPathVariable(@PathVariable Long id, String... projection) {
        log.info("获取通知公告信息(请求方法+路径变量)详情[{}]", id);
        return noticeService.get(new NoticeGet(id), projection);
    }

    @RequestMapping("/get")
    public Mono<NoticeVO> getByPath(NoticeGet params, String... projection) {
        log.info("获取通知公告信息(请求路径+参数变量)详情[{}]", params);
        return noticeService.get(params, projection);
    }

    @PutMapping(value = {"", "/*"}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<Integer> modifyByForm(NoticeModify params) {
        log.info("修改通知公告信息(请求方法+表单参数)[{}]", params);
        return noticeService.modify(params);
    }

    @PutMapping(value = {"", "/*"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Integer> modifyByJson(@RequestBody NoticeModify params) {
        log.info("修改通知公告信息(请求方法+JSON参数)[{}]", params);
        return noticeService.modify(params);
    }

    @DeleteMapping("/{id}")
    public Mono<Integer> deleteByUrlPathVariable(@PathVariable Long id) {
        log.info("删除通知公告信息(请求方法+URL路径变量)[{}]", id);
        return noticeService.delete(new NoticeDelete(id));
    }

    @DeleteMapping(params = "id")
    public Mono<Integer> deleteByUrlParamVariable(NoticeDelete params) {
        log.info("删除通知公告信息(请求方法+URL参数变量)[{}]", params);
        return noticeService.delete(params);
    }

    @RequestMapping(path = "/delete")
    public Mono<Integer> deleteByPath(NoticeDelete params) {
        log.info("删除通知公告信息(请求路径+URL参数变量)[{}]", params);
        return noticeService.delete(params);
    }

    @PutMapping("/publish")
    public Mono<Integer> publish(NoticePublish params) {
        log.info("发布通知公告信息[{}]", params);
        return noticeService.publish(params);
    }

    @PutMapping("/view")
    public Mono<Integer> view(NoticeView params) {
        log.info("浏览通知公告信息[{}]", params);
        return noticeService.view(params);
    }

}
