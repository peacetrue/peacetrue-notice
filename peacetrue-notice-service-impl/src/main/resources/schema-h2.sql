DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    title          VARCHAR(255)   NOT NULL COMMENT '标题',
    commend_title  VARCHAR(255)   NOT NULL COMMENT '推荐标题',
    intro          VARCHAR(255)   NOT NULL COMMENT '简介',
    detail         VARCHAR(20480) NOT NULL COMMENT '详情',
    source         VARCHAR(32)    NOT NULL COMMENT '信息来源',
    text_writer    VARCHAR(32)    NOT NULL COMMENT '文字',
    photographer   VARCHAR(32)    NOT NULL COMMENT '摄影',
    editor         VARCHAR(32)    NOT NULL COMMENT '编辑',
    state_id       tinyint        NOT NULL COMMENT '状态. 1、草稿；2、发布',
    published_time datetime       not null comment '发布时间',
    view_count     int            NOT NULL COMMENT '浏览次数',
    remark         VARCHAR(255)   NOT NULL COMMENT '备注',
    serial_number  bigint         NOT NULL COMMENT '序号',
    creator_id     bigint         not null comment '创建者主键',
    created_time   datetime       not null comment '创建时间',
    modifier_id    bigint         not null comment '修改者主键',
    modified_time  datetime       not null comment '最近修改时间'
);

COMMENT ON TABLE `notice` IS '通知公告';
COMMENT ON COLUMN `notice`.id IS '主键';

