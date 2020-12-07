package com.github.peacetrue.notice;

import com.github.peacetrue.core.OperatorCapableImpl;
import com.github.peacetrue.validation.constraints.in.In;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


/**
 * @author xiayx
 */
@Getter
@Setter
@ToString
public class NoticeAdd extends OperatorCapableImpl<Long> {

    private static final long serialVersionUID = 0L;

    /** 来源. 书院 */
    @NotNull
    private Long sourceId;
    /** 标题 */
    @NotNull
    @Size(min = 1, max = 255)
    private String title;
    /** 内容 */
    @NotNull
    @Size(min = 1, max = 1024)
    private String content;
    /** 状态. 1、草稿；2、发布 */
    @In({"1", "2"})
    private Integer stateId;
    /** 发布时间 */
    private LocalDateTime publishedTime;
    /** 浏览次数 */
    private Integer viewCount;
    /** 备注 */
    @Size(min = 1, max = 255)
    private String remark;

}
