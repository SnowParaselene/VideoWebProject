DROP DATABASE IF EXISTS video_web_project;
CREATE DATABASE video_web_project;
USE video_web_project;

-- 管理员表
DROP TABLE IF EXISTS t_admin;
CREATE TABLE t_admin(
	t_admin_id INT PRIMARY KEY AUTO_INCREMENT,
    -- 管理员id
    admin_id VARCHAR(20) ,
    -- 管理员名称
	admin_name VARCHAR(20),
    -- 创建时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 最后更新时间
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 管理员账号
    admin_account VARCHAR(20),
    -- 管理员密码
	admin_password VARCHAR(20),
    -- 角色
    admin_character INT,
    -- 账号状态
    admin_status INT,
    -- 头像
    admin_head_img VARCHAR(100)
);

-- 管理员角色表
DROP TABLE IF EXISTS t_character;
CREATE TABLE t_character(
    -- 角色id
	t_character_id INT PRIMARY KEY AUTO_INCREMENT,
    -- 创建时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 最后更新时间
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 角色名
    character_label VARCHAR(20),
    -- 角色值
    character_value VARCHAR(20)
);

-- 管理员权限表
DROP TABLE IF EXISTS t_authority;
CREATE TABLE t_authority(
    -- 权限id
	t_authority_id INT PRIMARY KEY AUTO_INCREMENT,
    -- 创建时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 最后更新时间
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 权限名
    authority_label VARCHAR(20),
    -- 权限值
    authority_value VARCHAR(20)
);

-- 角色权限关系表
DROP TABLE IF EXISTS t_character_authority;
CREATE TABLE t_character_authority(
    -- 角色权限关系id
	t_character_authority_id INT PRIMARY KEY AUTO_INCREMENT,
    -- 创建时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 最后更新时间
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 角色id
    character_id INT,
    -- 权限id
    authority_id INT
);

-- 账号状态表
DROP TABLE IF EXISTS t_account_status;
CREATE TABLE t_account_status(
    -- 账号状态id
	t_account_status_id INT PRIMARY KEY AUTO_INCREMENT,
    -- 创建时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 最后更新时间
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 账号状态名
    status_label VARCHAR(20),
    -- 账号状态值
    status_value VARCHAR(20)
);

-- 用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
	t_user_id INT PRIMARY KEY AUTO_INCREMENT,
    -- 用户id
    user_id VARCHAR(20) ,
    -- 创建时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 最后更新时间
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 用户名
    user_name VARCHAR(20),
    -- 用户账号
    user_account VARCHAR(20),
    -- 用户密码
    user_password VARCHAR(20),
    -- 用户状态
    user_status INT,
    -- 用户头像
    user_head_img VARCHAR(100)
);

-- 用户余额表
DROP TABLE IF EXISTS t_user_balance;
CREATE TABLE t_user_balance(
    -- 用户余额id
	t_user_balance_id INT PRIMARY KEY AUTO_INCREMENT,
    -- 创建时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 最后更新时间
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 用户id
    user_id VARCHAR(20),
    -- 用户余额
    remaining_sum DOUBLE
);

-- 会员表
DROP TABLE IF EXISTS t_vip;
CREATE TABLE t_vip(
    -- 会员id
	t_vip_id INT PRIMARY KEY AUTO_INCREMENT,
    -- 创建时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 最后更新时间
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 会员类型
    vip_label VARCHAR(20),
    -- 会员值
    vip_value INT,
    -- 会员价格
    vip_price DOUBLE
);

-- 用户会员关系表
DROP TABLE IF EXISTS t_user_vip;
CREATE TABLE t_user_vip(
    -- 用户会员关系id
	t_user_vip_id INT PRIMARY KEY AUTO_INCREMENT,
    -- 创建时间
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 最后更新时间
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- 用户id
    user_id INT,
    -- 会员id
    vip_id INT,
    -- 会员到期时间
    expiration_time TIMESTAMP
);
