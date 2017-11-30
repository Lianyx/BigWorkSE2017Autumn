这个目前只是复制用的草稿

create table MemberPromotion(
  id integer,

  createTime bigint,
  lastModifedTime bigint,
  beginTime bigint,
  endTime bigint,

  requiredLevel integer,
  discountFraction double,
  tokenAmount double
);


create table TotalPromotion(
  id integer,

  createTime bigint,
  lastModifed bigint,
  beginTime bigint,
  endTime bigint,

  totalAmount double,
  tokenAmount double,
  gifts varchar(1000)
);



create table CombinePromotion(
  id integer,

  createTime bigint,
  lastModifed bigint,
  beginTime bigint,
  endTime bigint,

  discountAmount double,
  goodsCombination varchar(1000)
);



drop table MemberPromotion;
drop table TotalPromotion;
drop table CombinePromotion;