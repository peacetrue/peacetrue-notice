package com.github.peacetrue.notice;

import com.github.peacetrue.core.OperatorCapableImpl;
import com.github.peacetrue.core.Range;
import lombok.*;


/**
 * @author xiayx
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeQuery extends OperatorCapableImpl<Long> {

    public static final NoticeQuery DEFAULT = new NoticeQuery();

    private static final long serialVersionUID = 0L;

    /** 主键 */
    private Long[] id;
    /** 标题 */
    private String title;
    /** 内容 */
    private String content;
    /** 状态. 1、草稿；2、发布 */
    private Integer stateId;
    /** 发布时间 */
    private Range.LocalDateTime publishedTime;
    /** 浏览次数 */
    private Integer viewCount;
    /** 备注 */
    private String remark;
    /** 创建者主键 */
    private Long creatorId;
    /** 创建时间 */
    private Range.LocalDateTime createdTime;
    /** 修改者主键 */
    private Long modifierId;
    /** 最近修改时间 */
    private Range.LocalDateTime modifiedTime;

    public NoticeQuery(Long[] id) {
        this.id = id;
    }

}
