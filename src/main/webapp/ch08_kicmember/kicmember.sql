drop table kicmember;
   
-- 회원가입용 
create table kicmember (
	id varchar(20) primary key,
	pass varchar(20),
	name varchar(20),
	gender number(1),
	tel varchar(20),
	email varchar(50),
	picture varchar(200)
);