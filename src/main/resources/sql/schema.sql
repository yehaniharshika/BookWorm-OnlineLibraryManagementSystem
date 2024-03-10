drop database if exists BookWarm;
create database if not exists BookWarm;
use BookWarm;

create table admin(
    adminID varchar(20) primary key ,
    firstName varchar(200) not null,
    lastName varchar(250) not null,
    nic varchar(20) not null ,
    emailAddress text not null ,
    username varchar(30) not null ,
    password varchar(20) not null
);


create table user(
    userID varchar(15) primary key ,
    firstName varchar(200) not null,
    lastName varchar(250) not null,
    nic varchar(20) not null ,
    emailAddress text not null ,
    username varchar(30) not null ,
    password varchar(20) not null
);


create table libraryBranch(
    branchId varchar(15) primary key,
    branchName varchar(200) not null ,
    location varchar(200) not null ,
    description text not null ,
    constraint foreign key(adminID) references admin(adminID) on update cascade on DELETE cascade
);


create  table book(
     bookID varchar(15) primary key ,
     bookName varchar(100) not null ,
     category varchar(20) not null,
     qtyOnHand int not null,


);

