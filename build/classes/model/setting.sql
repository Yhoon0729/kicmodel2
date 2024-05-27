-- 회원가입
create table kciMember (
    id varchar2(20) primary key,
    pass varchar2(20),
    name varchar2(20),
    gender number(1),
    tel varchar2(15),
    birth number(8),
    address varchar2(20),
    tel varchar2(20),
    email varchar2(50)
);


-- 게시판
create table kicboard (
num int primary key,
name varchar(30),
pass varchar(20),
subject varchar(100),
content varchar(4000),
file1 varchar(100),
regdate date,
readcnt number(10),
boardid varchar(1)
);

create sequence kicboardseq;

select *
from (select rownum rn, a.* from kicboard a where boardid='1' order by num desc)
where rn between 4 and 6;