package com.github.peacetrue.notice;

import com.github.peacetrue.core.IdCapable;
import com.github.peacetrue.core.OperatorCapableImpl;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author xiayx
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeModify extends OperatorCapableImpl<Long> implements IdCapable<Long> {

    private static final long serialVersionUID = 0L;

    /** 主键 */
    @NotNull
    private Long id;
    /** 来源. 书院 */
    private Long sourceId;
    /** 标题 */
    @Size(min = 1, max = 255)
    private String title;
    /** 内容 */
    @Size(min = 1, max = 1024)
    private String content;
    /** 浏览次数 */
    @Min(0)
    private Integer viewCount;
    /** 备注 */
    @Size(min = 1, max = 255)
    private String remark;

}
