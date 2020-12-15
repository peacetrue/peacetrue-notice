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
    /** 标题 */
    @Size(min = 1, max = 255)
    private String title;
    /** 内容 */
    @Size(min = 1, max = 1024)
    private String content;
    /** 推荐标题 */
    @Size(min = 1, max = 255)
    private String commendTitle;
    /** 信息来源 */
    @Size(min = 1, max = 32)
    private String source;
    /** 文字 */
    @Size(min = 1, max = 32)
    private String textWriter;
    /** 摄影 */
    @Size(min = 1, max = 32)
    private String photographer;
    /** 编辑 */
    @Size(min = 1, max = 32)
    private String editor;
    /** 浏览次数 */
    @Min(0)
    private Integer viewCount;
    /** 备注 */
    @Size(min = 1, max = 255)
    private String remark;

}
