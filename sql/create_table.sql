-- 用户表
create table if not exists user
(
    id           bigint auto_increment primary key,
    username     varchar(8)                           null comment '用户昵称',
    avatarUrl    varchar(64)                          null comment '用户头像',
    gender       tinyint(1)                           null comment '性别',
    userPassword varchar(32)                          null comment '密码',
    email        varchar(32)                          null comment '邮箱',
    tags         varchar(64)                          null comment '标签列表',
    phone        varchar(16)                          null comment '电话',
    profile      varchar(64)                          null comment '个人简介---',
    userSpeak    varchar(512)                         null comment '用户反馈',
    planetCode   varchar(8)                           null comment '编号',
    createTime   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    userStatus   tinyint(1) default 0                 null comment '状态-正常---',
    userRole     tinyint(1) default 0                 null comment '普通用户-0 管理员-1---',
    isDelete     tinyint(1) default 0                 null comment '是否删除'
)
    comment '用户表';


-- 队伍表
create table if not exists team
(
    id          bigint auto_increment comment 'id' primary key,
    name        varchar(8)                           null comment '队伍名称',
    description varchar(64)                          null comment '描述',
    avatarUrl   varchar(64)                          null comment '队伍头像---',
    maxNum      tinyint(2) default 1                 null comment '最大人数',
    userId      bigint                               null comment '队长id',
    password    varchar(32)                          null comment '密码',
    expireTime  datetime                             null comment '过期时间',
    createTime  datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime  datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    status      tinyint(1) default 0                 null comment '0:公开，1:私有，2:加密',
    isDelete    tinyint(1) default 0                 null comment '是否删除'
)
    comment '队伍表';


-- 用户队伍关系表
create table if not exists user_team
(
    id         bigint auto_increment comment 'id' primary key,
    userId     bigint                               null comment '用户id',
    teamId     bigint                               null comment '队伍id',
    createTime datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    ties       tinyint(1) default 0                 null comment '0:未加入/已退出 1:已加入 2:队长/创建者',
    isDelete   tinyint(1) default 0                 not null comment '是否删除'
)
    comment '用户队伍关系表';


-- auto-generated definition
create table article
(
    id          bigint                                 not null comment '文章id',
    title       varchar(256)                           not null comment '文章标题',
    description varchar(256)                           not null comment '文章摘要',
    content     varchar(2048)                          not null comment '文章内容',
    author      bigint                                 not null comment '创作者',
    view        int          default 0                 null comment '浏览量',
    likes       int          default 0                 null comment '点赞量',
    comments    varchar(256) default '0'               null comment '评论量',
    create_time datetime     default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime     default CURRENT_TIMESTAMP null comment '更新时间',
    is_delete   tinyint      default 0                 null comment '逻辑删除'
)
    comment '博文';

-- auto-generated definition
create table friends
(
    id          bigint auto_increment comment 'id'
        primary key,
    user_id     bigint                               null comment '用户id',
    friend_id   bigint                               null comment '好友id',
    note        varchar(8)                           null comment '好友备注',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    is_delete   tinyint(1) default 0                 not null comment '是否删除'
)
    comment '好友表';


-- 访客表
create table if not exists visitor
(
    id         bigint auto_increment comment 'id' primary key,
    toId       bigint                               null comment '被访者id',
    fromId     bigint                               null comment '来访者id',
    fromUrl    varchar(64)                          null comment '来访者头像',
    message    varchar(128)                         null comment '来访者留言',
    amount     int        default 1                 null comment '来访次数',
    createTime datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    stealth    tinyint(1) default 0                 null comment '是否为隐身访问',
    isDelete   tinyint(1) default 0                 not null comment '是否删除'
)
    comment '访客表';


-- 消息表
create table if not exists chat
(
    id         bigint auto_increment primary key,
    teamId     bigint                               null comment '队伍id(群聊)',
    toId       bigint                               null comment '接收者id(私聊)',
    fromId     bigint                               null comment '发送者id',
    fromName   varchar(8)                           null comment '发送者昵称',
    fromUrl    varchar(64)                          null comment '发送者头像',
    text       varchar(128)                         null comment '内容',
    createTime datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    status     tinyint(1) default 0                 null comment '接收状态 0:未读 1:已读(私聊)',
    type       tinyint(1) default 0                 null comment '消息类型 0:队伍创建 1:队伍解散 2:成员加入 3:成员退出 4:队伍过期(系统消息)',
    scope      tinyint(1) default 1                 null comment '消息类 0:私聊 1:群聊',
    isDelete   tinyint(1) default 0                 null comment '是否删除'
)
    comment '消息表';
