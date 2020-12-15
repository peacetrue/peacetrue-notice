package com.github.peacetrue.notice;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

/**
 * @author xiayx
 */
@Data
public class NoticeVO implements Serializable {

    private static final long serialVersionUID = 0L;

    /** 主键 */
    private Long id;
    /** 标题 */
    private String title;
    /** 内容 */
    private String content;
    /** 状态. 1、草稿；2、发布 */
    private Integer stateId;
    /** 发布时间 */
    private LocalDateTime publishedTime;
    /** 浏览次数 */
    private Integer viewCount;
    /** 备注 */
    private String remark;
    /** 创建者主键 */
    private Long creatorId;
    /** 创建时间 */
    private LocalDateTime createdTime;
    /** 修改者主键 */
    private Long modifierId;
    /** 最近修改时间 */
    private LocalDateTime modifiedTime;
}
