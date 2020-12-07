package com.github.peacetrue.notice;

import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;

/**
 * 通知公告客户端
 *
 * @author xiayx
 */
@ReactiveFeignClient(name = "peacetrue-notice", url = "${peacetrue.Notice.url:${peacetrue.server.url:}}")
public interface NoticeServiceClient {

    @PostMapping(value = "/notices")
    Mono<NoticeVO> add(NoticeAdd params);

    @GetMapping(value = "/notices", params = "page")
    Mono<Page<NoticeVO>> query(@Nullable @SpringQueryMap NoticeQuery params, @Nullable Pageable pageable, @SpringQueryMap String... projection) ;

    @GetMapping(value = "/notices", params = "sort")
    Flux<NoticeVO> query(@SpringQueryMap NoticeQuery params, Sort sort, @SpringQueryMap String... projection) ;

    @GetMapping(value = "/notices")
    Flux<NoticeVO> query(@SpringQueryMap NoticeQuery params, @SpringQueryMap String... projection) ;

    @GetMapping(value = "/notices/get")
    Mono<NoticeVO> get(@SpringQueryMap NoticeGet params, @SpringQueryMap String... projection) ;

    @PutMapping(value = "/notices")
    Mono<Integer> modify(NoticeModify params) ;

    @DeleteMapping(value = "/notices/delete")
    Mono<Integer> delete(@SpringQueryMap NoticeDelete params);

}
