package com.github.peacetrue.notice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDateTime;


import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

/**
 * 通知公告实体类
 *
 * @author xiayx
 */
@Getter
@Setter
@ToString
public class Notice implements Serializable {

    private static final long serialVersionUID = 0L;

    /** 主键 */
    @Id
    private Long id;
    /** 标题 */
    private String title;
    /** 推荐标题 */
    private String commendTitle;
    /** 简介 */
    private String intro;
    /** 详情 */
    private String detail;
    /** 信息来源 */
    private String source;
    /** 文字 */
    private String textWriter;
    /** 摄影 */
    private String photographer;
    /** 编辑 */
    private String editor;
    /** 状态. 1、草稿；2、发布 */
    private Integer stateId;
    /** 发布时间 */
    private LocalDateTime publishedTime;
    /** 浏览次数 */
    private Integer viewCount;
    /** 备注 */
    private String remark;
    /** 序号 */
    private Long serialNumber;
    /** 创建者主键 */
    private Long creatorId;
    /** 创建时间 */
    private LocalDateTime createdTime;
    /** 修改者主键 */
    private Long modifierId;
    /** 最近修改时间 */
    private LocalDateTime modifiedTime;

}
