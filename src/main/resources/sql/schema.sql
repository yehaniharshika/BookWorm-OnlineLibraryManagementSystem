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

insert into admin values("AD001","Gagani","Bandara","200279602914","gagani@gmail.com","gagani","12");
select * from admin where adminID="AD001" and  username="gagani" and password="12";

create table libraryBranch(
    branchID varchar(15) primary key,
    branchName varchar(200) not null ,
    location varchar(200) not null ,
    description text not null ,
    adminID varchar(15),
    constraint foreign key(adminID) references admin(adminID) on update cascade on DELETE cascade
);


create  table book(
     bookID varchar(15) primary key ,
     bookName varchar(300) not null ,
     authorName varchar(300) not null,
     bookGenre varchar(200) not null,
     qty int not null,
     branchID varchar(15),
     constraint foreign key(branchID) references libraryBranch(branchID) on update cascade on delete cascade

);

