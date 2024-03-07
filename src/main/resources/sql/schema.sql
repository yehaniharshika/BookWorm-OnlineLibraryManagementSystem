drop database if exists BookWarm;
create database if not exists BookWarm;
use BookWarm;

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

);

create  table book(
     ISBN varchar(10) primary key ,
     bookName varchar(100) not null ,
     category varchar(20) not null,
     qtyOnHand int not null,
     rackCode varchar(10),
     authorId varchar(10),
     constraint foreign key(rackCode) references bookRack(rackCode) on update cascade on delete cascade,
     constraint foreign key(authorId) references  author(authorId) on update cascade on delete cascade
);

