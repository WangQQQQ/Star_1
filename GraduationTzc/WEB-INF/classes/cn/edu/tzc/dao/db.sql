/*设置连接编码为utf8*/
set names utf8;
/*如果数据库存在, 则删除*/
drop database if exists graduation_tzc;
/*创建数据库 并设置编码为utf8*/
create database graduation_tzc charset utf8;
/*使用数据库*/
use graduation_tzc;

drop table if exists tb_student;
create table tb_student (
	/*编号 主键*/
	id bigint primary key auto_increment,
	/*用户名*/
	username varchar(50) not null,
	/*解决题目数*/
	solve_problem int(50),
	/*总提交数*/
	submit_count int(50),
	/*学生学号*/
	student_num varchar(50),
	/*真实姓名*/
	real_name varchar(100),
	/*班级*/
	class_id varchar(50),
	/*难度分*/
	problem_source bigint(50),
	/*昨日已完成题目量*/
	yesterday_solve_problem int
)engine=InnoDB default charset=utf8;

/*学生备份表*/
drop table if exists tb_student_info;
create table tb_student_info (
	/*编号 主键*/
	id bigint primary key auto_increment,
	/*用户名*/
	username varchar(50) not null,
	/*解决题目数*/
	solve_problem int(50),
	/*总提交数*/
	submit_count int(50),
	/*学生学号*/
	student_num varchar(50),
	/*真实姓名*/
	real_name varchar(100),
	/*班级*/
	class_id varchar(50),
	/*难度分*/
	problem_source bigint(50),
	/*更新数据时当前系统时间*/
	sysdate date
)engine=InnoDB default charset=utf8;

drop table if exists tb_class;
create table tb_class (
	/*编号 主键*/
	id bigint primary key auto_increment,
	/*班级ID*/
	class_id bigint,
	/*班级名称*/
	class_name varchar(50)
)engine=InnoDB default charset=utf8;


drop table if exists tb_class_student;
create table tb_class_student (
	/*编号 主键*/
	id bigint primary key auto_increment,
	/*班级ID*/
	class_id bigint,
	/*班级学生*/
	student_name varchar(50)
)engine=InnoDB default charset=utf8;

drop table if exists tb_admin;
create table tb_admin (
	/*编号主键*/
	id bigint primary key auto_increment,
	/*用户名*/
	admin_code varchar(50),
	/*密码*/
	password varchar(50)
)engine=InnoDB default charset=utf8;
