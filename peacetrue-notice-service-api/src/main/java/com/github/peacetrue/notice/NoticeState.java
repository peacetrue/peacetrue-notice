package com.github.peacetrue.notice;

import com.github.peacetrue.core.IdCapable;
import com.github.peacetrue.core.NameCapable;
import lombok.Getter;

/**
 * @author : xiayx
 * @since : 2020-11-24 13:53
 **/
@Getter
public enum NoticeState implements IdCapable<Integer>, NameCapable {

    draft(1, "草稿"),
    publish(2, "发布"),
    ;

    private final Integer id;
    private final String name;

    NoticeState(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
