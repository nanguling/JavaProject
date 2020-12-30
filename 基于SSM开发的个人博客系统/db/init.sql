drop database if exists myboke;
create database myboke character set utf8mb4;

use myboke;

drop table if exists user;
create table user (
    id int primary key auto_increment,
    username varchar(20) not null unique,
    password varchar(20) not null,
    nickname varchar(20),
    birthday date,
    phone_number varchar(11),
    email varchar(20),
    head varchar(50)
);

drop table if exists  article;
create table article(
    id int primary key auto_increment,
    title varchar(50) not null,
    content mediumtext not null,
    user_id int,
    create_time timestamp default now(),
    read_times int default 0,
    foreign key (user_id) references user(id)
);
insert into user (username,password) values ('abcd','1234');
insert into user (username,password) values ('efgh','1234');
insert into user (username,password) values ('ijkl','1234');
insert into user (username,password) values ('mnop','1234');

insert into article(title,content,user_id) values ('年轻人不讲武德','内容1',1);
insert into article(title,content,user_id) values ('耗子尾汁','内容2',1);
insert into article(title,content,user_id) values ('我大E了啊','内容3',3);
insert into article(title,content,user_id) values ('发生甚么事了','内容4',2);

select id, username, password, nickname, birthday, phone_number, email, head from user where username = ?;