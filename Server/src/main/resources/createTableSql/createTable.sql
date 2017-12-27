这个现在只是复制用的草稿

drop table MemberPromotion;
drop table TotalPromotion;
drop table CombinePromotion;

create table MemberPromotion(
  dayId integer,

  createTime bigint,
  lastModifiedTime bigint,
  beginTime bigint,
  endTime bigint,

  comment varchar(150),

  requiredLevel integer,
  discountFraction double,
  tokenAmount double,
  gifts varchar(1000)
) character set = utf8;


create table TotalPromotion(
  dayId integer,

  createTime bigint,
  lastModifiedTime bigint,
  beginTime bigint,
  endTime bigint,

  comment varchar(150),

  requiredTotal double,
  tokenAmount double,
  gifts varchar(1000)
) character set = utf8;



create table CombinePromotion(
  dayId integer,

  createTime bigint,
  lastModifiedTime bigint,
  beginTime bigint,
  endTime bigint,

  comment varchar(150),

  discountAmount double,
  goodsCombination varchar(1000)
) character set = utf8;

create table SalesSellReceipt(
  dayId integer,
  operatorId integer,
  createTime bigint,
  lastModifiedTime bigint,
  receiptState integer,

  comment varchar(150),

  memberId integer,
  clerkName varchar(30),
  stockName varchar(30),
  goodsList varchar(1000),
  discountAmount double,
  tokenAmount double,
  originSum double,

  gifts varchar(1000),
  giveTokenAmount double
) character set = utf8;


create table account(
    ID int auto_increment primary key,
    name varchar(255),
    balance double
);


create table PaymentBillReceipt(
    dayId integer,
    operatorId integer,
    createTime bigint,
    lastModifiedTime bigint,
    receiptState integer,

    clerkName varchar(30),
    clientId integer,
    transferList varchar(1000),
    sum double
);

create table ChargeBillReceipt(
    dayId integer,
    operatorId integer,
    createTime bigint,
    lastModifiedTime bigint,
    receiptState integer,

    clerkName varchar(30),
    clientId integer,
    transferList varchar(1000),
    sum double
);

create table CashBillReceipt(
    dayId integer,
    operatorId integer,
    createTime bigint,
    lastModifiedTime bigint,
    receiptState integer,

    clerkName varchar(30),
    accountID INTEGER,
    total double,
    itemList varchar(1000)

);


create table SalesDetail(
    date bigint,
    goodsName varchar(30),
    goodsID integer,
    number integer,
    price double,
    total double,

    clientID integer,
    clerkName varchar(30),
    stockID integer
);


create table BusinessCondition(
    income double,
    expense double
);