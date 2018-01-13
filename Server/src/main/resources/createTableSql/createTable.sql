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

create table Log(
logId integer,
createTime bigint,
username varchar(100),
userCategory integer,
eventCategory integer,
event varchar(100)
);

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

    clientId integer,

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
)character set = utf8;

create table CashBillReceipt(
    dayId integer,
    operatorId integer,
    createTime bigint,
    lastModifiedTime bigint,
    receiptState integer,


    accountId INTEGER,
    total double,
    itemList varchar(1000)
)character set = utf8;


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
memberId integer,
stockName varchar(100),
goodsList varchar(1000),
originSum integer,
comment varchar(150)
)character set = utf8;


create table StockRetReceipt(
dayId integer,
createTime bigint,
lastModifiedTime bigint,
operatorId integer,
receiptState integer,
memberId integer,
stockName varchar(100),
goodsList varchar(1000),
originSum integer,
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
  usertype integer DEFAULT 0,
  createTime bigint,
  facebook varchar(50),
  github varchar(100),
  twitter varchar(100),
  email varchar(100),
  phone varchar(100),
  comment varchar(100),
  password varchar(100),
  isDeleted integer DEFAULT 0
)character set = utf8;

create table Member(
memberId integer,
memberCategory integer DEFAULT 0,
VIPgrade integer DEFAULT 0,
memberName varchar(100) DEFAULT '',
clerkName varchar(100) DEFAULT '',
phoneNumber varchar(100) DEFAULT '',
address varchar(100) DEFAULT '',
zipCode varchar(100) DEFAULT  '',
emailAddress varchar(100) DEFAULT '',
debtCeiling double DEFAULT 0,
debt double DEFAULT 0,
credit double DEFAULT 0,
isDeleted integer DEFAULT 0,
comment varchar(100) DEFAULT ''
);

insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (5,0,4,'林冰','阳光仓库','139000013313','四明街525号','00013','lb131311@qq.com',1004,300,273,0,'常客');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (6,0,2,'连奚','阳光仓库','139206612365','四明街613号','00013','lx131rrr@sina.com',521,210,101,0,'无');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (7,0,1,'若欣','阳光仓库','139000013313','贝尔街525号','00014','rx1390013@qq.com',136,200,4,0,'无');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (8,0,4,'李若冰','李星仓库','139000013313','贝尔街5号','00014','lrb--139000@qq.com',204,300,123,0,'无');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (9,1,4,'盛利冰','阳光仓库','139000013313','贝尔街13号','00014','lsbqqq11@qq.com',114,300,912,0,'无');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (10,1,4,'王水','阳光仓库','139000013313','思明区小明街32号','00032','lb1zxc311@qq.com',190,300,166,0,'无');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (11,1,4,'王青花','阳光仓库','139000013313','思明区小明街55号','00032','wqh1311@qq.com',588,300,213,0,'无');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (12,1,5,'陈小','李星仓库','139000013313','若林街99号','00010','cx131311@qq.com',912,300,271,0,'常客');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (13,0,4,'伏请','阳光仓库','139000013313','四明街25号','00013','fq1adw311@qq.com',1500,300,531,0,'常客');
