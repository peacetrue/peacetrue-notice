package com.github.peacetrue.notice;

import com.github.peacetrue.core.OperatorCapableImpl;
import com.github.peacetrue.validation.constraints.in.In;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


/**
 * @author xiayx
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeAdd extends OperatorCapableImpl<Long> {

    private static final long serialVersionUID = 0L;

    /** 标题 */
    @NotNull
    @Size(min = 1, max = 255)
    private String title;
    /** 推荐标题 */
    @NotNull
    @Size(min = 1, max = 255)
    private String commendTitle;
    /** 简介 */
    @NotNull
    @Size(min = 1, max = 255)
    private String intro;
    /** 详情 */
    @NotNull
    @Size(min = 1, max = 51200)
    private String detail;
    /** 信息来源 */
    @NotNull
    @Size(min = 1, max = 32)
    private String source;
    /** 文字 */
    @NotNull
    @Size(min = 1, max = 32)
    private String textWriter;
    /** 摄影 */
    @NotNull
    @Size(min = 1, max = 32)
    private String photographer;
    /** 编辑 */
    @NotNull
    @Size(min = 1, max = 32)
    private String editor;
    /** 状态. 1、草稿；2、发布 */
    @In({"1", "2"})
    private Integer stateId;
    /** 发布时间 */
    private LocalDateTime publishedTime;
    /** 浏览次数 */
    private Integer viewCount;
    /** 序号 */
    private Long serialNumber;
    /** 备注 */
    @Size(min = 1, max = 255)
    private String remark;

}
