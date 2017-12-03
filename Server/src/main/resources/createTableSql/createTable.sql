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

  requiredLevel integer,
  discountFraction double,
  tokenAmount double
);


create table TotalPromotion(
  dayId integer,

  createTime bigint,
  lastModifiedTime bigint,
  beginTime bigint,
  endTime bigint,

  requiredTotal double,
  tokenAmount double,
  gifts varchar(1000)
);



create table CombinePromotion(
  dayId integer,

  createTime bigint,
  lastModifiedTime bigint,
  beginTime bigint,
  endTime bigint,

  discountAmount double,
  goodsCombination varchar(1000)
);

create table SalesSellReceipt(
  dayId integer,
  operatorId integer,
  createTime bigint,
  lastModifiedTime bigint,
  receiptState integer,

  clientId integer,
  clerkName varchar(30),
  stockName varchar(30),
  goodsList varchar(1000),
  discountAmount double,
  tokenAmount double,
  originSum double,
  comment varchar(150)
);