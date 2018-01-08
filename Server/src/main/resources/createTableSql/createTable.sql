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
  promotionState integer,

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
  promotionState integer,

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
  promotionState integer,

  discountAmount double,
  goodsCombination varchar(1000)
) character set = utf8;

create table SalesSellReceipt(
  dayId integer,
  operatorId integer,
  createTime bigint,
  lastModifiedTime bigint,
  receiptState integer,
  memberId integer,
  clerkName varchar(30),
  stockName varchar(30),
  goodsList varchar(1000),
  discountAmount double,
  tokenAmount double,
  originSum double,

  gifts varchar(1000),
  giveTokenAmount double,
  comment varchar(150)

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

    accountID integer,
    transferList varchar(1000),
    sum double
)character set = utf8;

create table ChargeBillReceipt(
    dayId integer,
    operatorId integer,
    createTime bigint,
    lastModifiedTime bigint,
    receiptState integer,

    clientId integer,
    transferList varchar(1000),
    sum double
);character set = utf8

create table CashBillReceipt(
    dayId integer,
    operatorId integer,
    createTime bigint,
    lastModifiedTime bigint,
    receiptState integer,


    accountId INTEGER,
    total double,
    itemList varchar(1000)
);character set = utf8


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
    date bigint,
    salesIncome double,
    overFlowIncome double,
    purPriceAdjustIncome double,
    priceDiffIncome double,
    tokenIncome double,

    discount double,

    purCost double,
    damageCost double,
    giftCost double
);

create table StockPurReceipt(
dayId integer,
createTime bigint,
lastModifiedTime bigint,
operatorId integer,
receiptState integer,
memberid integer,
stockName varchar(100),
goodsList varchar(1000),
originalSum integer,
comment varchar(150)
)character set = utf8;


create table StockRetReceipt(
dayId integer,
createTime bigint,
lastModifiedTime bigint,
operatorId integer,
receiptState integer,
memberid integer,
stockName varchar(100),
goodsList varchar(1000),
originalSum integer,
comment varchar(150)
)character set = utf8;

create table SalesRetReceipt(
  dayId integer,
  operatorId integer,
  createTime bigint,
  lastModifiedTime bigint,
  receiptState integer,



  memberId integer,
  clerkName varchar(30),
  stockName varchar(30),
  goodsList varchar(1000),
  discountAmount double,
  tokenAmount double,
  originSum double,
comment varchar(150)
) character set = utf8;

create table User(
  userId integer,
  username varchar(30),
  usertype integer,
  createTime bigint,
  facebook varchar(50),
  github varchar(100),
  twitter varchar(100),
  email varchar(100),
  phone varchar(100),
  comment varchar(100),
  date varchar(100),
  password varchar(100)
); character set = utf8;
