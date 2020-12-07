DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    source_id      bigint        NOT NULL COMMENT '来源. 书院',
    title          VARCHAR(255)  NOT NULL COMMENT '标题',
    content        VARCHAR(1024) NOT NULL COMMENT '内容',
    state_id       tinyint       NOT NULL COMMENT '状态. 1、草稿；2、发布',
    published_time datetime      not null comment '发布时间',
    view_count     int           NOT NULL COMMENT '浏览次数',
    remark         VARCHAR(255)  NOT NULL COMMENT '备注',
    creator_id     bigint        not null comment '创建者主键',
    created_time   datetime      not null comment '创建时间',
    modifier_id    bigint        not null comment '修改者主键',
    modified_time  datetime      not null comment '最近修改时间'
);

COMMENT ON TABLE `notice` IS '通知公告';
COMMENT ON COLUMN `notice`.id IS '主键';

