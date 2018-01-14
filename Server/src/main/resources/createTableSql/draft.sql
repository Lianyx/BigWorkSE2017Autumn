drop table PaymentBillReceipt;
drop table ChargeBillReceipt;
drop table CashBillReceipt;


create table initialMember(
year char(11),
memberId integer,
memberCategory integer ,
VIPgrade integer ,
memberName varchar(100),
clerkName varchar(100) ,
phoneNumber varchar(100) ,
address varchar(100),
zipCode varchar(100) ,
emailAddress varchar(100),
debtCeiling double,
debt double ,
credit double ,
isDeleted integer ,
comment varchar(100)
);