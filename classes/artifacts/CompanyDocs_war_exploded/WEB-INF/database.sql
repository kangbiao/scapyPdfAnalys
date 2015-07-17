#User
#auth  权限  0-->最高管理员  1--->普通用户
#status 账号状态 0-->未审核 1-->已审核  -1-->已废弃  
DROP TABLE IF EXISTS User;
create table User(
   id int not null auto_increment,
   account varchar(20) not null,
   passwd varchar(20) not null,
   name varchar(20) not null,
   auth int not null,
   status int not null,
   qq varchar(20) ,
   phone varchar(20),
   email varchar(30),
   PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#Company
#num 公司证券编号
#name 公司名字
DROP TABLE IF EXISTS Company;
create table Company (
  id  int  not null auto_increment,
  num varchar(10) not null,
  name varchar(100) not null,
  kind varchar(100) DEFAULT NULL,
  trade varchar(50) DEFAULT NULL, 
  PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#PDF File
#pdfpath Pdf 文件路径
#htmlpath html文件路径
#time 文件发表的时间
#status 文件当前状态  0-->未处理 1-->处理成功  -1-->处理失败
DROP TABLE IF EXISTS File;
create table File(
   id int not null auto_increment,
   filename varchar(100) not null,
   pdfpath varchar(500) not null,
   htmlpath varchar(500) ,
   time datetime not null,
   status int not null,
   PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#File to Company
DROP TABLE IF EXISTS FileCompany;
create table FileCompany(
   id int not null auto_increment,
   fileid int not null,
   companyid int not null,
   PRIMARY KEY(id),
   CONSTRAINT FileCom_fileid FOREIGN KEY(fileid) REFERENCES File(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
   CONSTRAINT  FileCom_comid FOREIGN KEY(companyid) REFERENCES Company(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#Tag
#tag 标签 如 年度报告 财务报告
DROP TABLE IF EXISTS Tag;
create table Tag(
   id int not null auto_increment,
   tag varchar(100) not null,
   PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#File to Tag 
DROP TABLE IF  EXISTS FileTag;
create table FileTag(
   id int not null auto_increment,
   fileid int not null,
   tagid int not null,
   PRIMARY KEY(id),
   CONSTRAINT FileTag_fileid FOREIGN KEY(fileid) REFERENCES File(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
   CONSTRAINT FileTag_tagid FOREIGN KEY(tagid) REFERENCES Tag(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table LiuDongZiC(
   id int not null auto_increment,
   item1 varchar(50) DEFAULT NULL,
   item2 varchar(50) DEFAULT NULL,
   item3 varchar(50) DEFAULT NULL,
   item4 varchar(50) DEFAULT NULL,
   item5 varchar(50) DEFAULT NULL,
   item6 varchar(50) DEFAULT NULL,
   item7 varchar(50) DEFAULT NULL,
   item8 varchar(50) DEFAULT NULL,
   item9 varchar(50) DEFAULT NULL,
   item10 varchar(50) DEFAULT NULL,
   item11 varchar(50) DEFAULT NULL,
   item12 varchar(50) DEFAULT NULL,
   PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table NoLiuDongZC(
  id int not null auto_increment,
   item1 varchar(50) DEFAULT NULL,
   item2 varchar(50) DEFAULT NULL,
   item3 varchar(50) DEFAULT NULL,
   item4 varchar(50) DEFAULT NULL,
   item5 varchar(50) DEFAULT NULL,
   item6 varchar(50) DEFAULT NULL,
   item7 varchar(50) DEFAULT NULL,
   item8 varchar(50) DEFAULT NULL,
   item9 varchar(50) DEFAULT NULL,
   item10 varchar(50) DEFAULT NULL,
   item11 varchar(50) DEFAULT NULL,
   item12 varchar(50) DEFAULT NULL,
   item13 varchar(50) DEFAULT NULL,
   item14 varchar(50) DEFAULT NULL,
   item15 varchar(50) DEFAULT NULL,
   item16 varchar(50) DEFAULT NULL,
   item17 varchar(50) DEFAULT NULL,
   item18 varchar(50) DEFAULT NULL,
   PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table LiuDongFuZ(
  id int not null auto_increment,
   item1 varchar(50) DEFAULT NULL,
   item2 varchar(50) DEFAULT NULL,
   item3 varchar(50) DEFAULT NULL,
   item4 varchar(50) DEFAULT NULL,
   item5 varchar(50) DEFAULT NULL,
   item6 varchar(50) DEFAULT NULL,
   item7 varchar(50) DEFAULT NULL,
   item8 varchar(50) DEFAULT NULL,
   item9 varchar(50) DEFAULT NULL,
   item10 varchar(50) DEFAULT NULL,
   item11 varchar(50) DEFAULT NULL,
   item12 varchar(50) DEFAULT NULL,
   item13 varchar(50) DEFAULT NULL,
   PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table NLiuDongFuZ(
   id int not null auto_increment,
   item1 varchar(50) DEFAULT NULL,
   item2 varchar(50) DEFAULT NULL,
   item3 varchar(50) DEFAULT NULL,
   item4 varchar(50) DEFAULT NULL,
   item5 varchar(50) DEFAULT NULL,
   item6 varchar(50) DEFAULT NULL,
   item7 varchar(50) DEFAULT NULL,
   item8 varchar(50) DEFAULT NULL,
   PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table SuoYouZQY(
   id int not null auto_increment,
   item1 varchar(50) DEFAULT NULL,
   item2 varchar(50) DEFAULT NULL,
   item3 varchar(50) DEFAULT NULL,
   item4 varchar(50) DEFAULT NULL,
   item5 varchar(50) DEFAULT NULL,
   item6 varchar(50) DEFAULT NULL,
   item7 varchar(50) DEFAULT NULL,
   item8 varchar(50) DEFAULT NULL,
   item9 varchar(50) DEFAULT NULL,
   item10 varchar(50) DEFAULT NULL,
   item11 varchar(50) DEFAULT NULL,
   PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table YinYeShouY(
  id int not null auto_increment,
   item1 varchar(50) DEFAULT NULL,
   item2 varchar(50) DEFAULT NULL,
   item3 varchar(50) DEFAULT NULL,
   item4 varchar(50) DEFAULT NULL,
   item5 varchar(50) DEFAULT NULL,
   item6 varchar(50) DEFAULT NULL,
   item7 varchar(50) DEFAULT NULL,
   item8 varchar(50) DEFAULT NULL,
   item9 varchar(50) DEFAULT NULL,
   item10 varchar(50) DEFAULT NULL,
   item11 varchar(50) DEFAULT NULL,
   PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table YinYeLiRun(
   id int not null auto_increment,
   item1 varchar(50) DEFAULT NULL,
   item2 varchar(50) DEFAULT NULL,
   item3 varchar(50) DEFAULT NULL,
   item4 varchar(50) DEFAULT NULL,
   item5 varchar(50) DEFAULT NULL,
   item6 varchar(50) DEFAULT NULL,
   item7 varchar(50) DEFAULT NULL,
   item8 varchar(50) DEFAULT NULL,
   item9 varchar(50) DEFAULT NULL,
   item10 varchar(50) DEFAULT NULL,
   item11 varchar(50) DEFAULT NULL,
   item12 varchar(50) DEFAULT NULL,
   item13 varchar(50) DEFAULT NULL,
   item14 varchar(50) DEFAULT NULL,
   item15 varchar(50) DEFAULT NULL,
   item16 varchar(50) DEFAULT NULL,
   PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table XianJinLL(
   id int not null auto_increment,
   item1 varchar(50) DEFAULT NULL,
   item2 varchar(50) DEFAULT NULL,
   item3 varchar(50) DEFAULT NULL,
   item4 varchar(50) DEFAULT NULL,
   item5 varchar(50) DEFAULT NULL,
   item6 varchar(50) DEFAULT NULL,
   item7 varchar(50) DEFAULT NULL,
   item8 varchar(50) DEFAULT NULL,
   item9 varchar(50) DEFAULT NULL,
   item10 varchar(50) DEFAULT NULL,
   item11 varchar(50) DEFAULT NULL,
   item12 varchar(50) DEFAULT NULL,
   item13 varchar(50) DEFAULT NULL,
   item14 varchar(50) DEFAULT NULL,
   item15 varchar(50) DEFAULT NULL,
   item16 varchar(50) DEFAULT NULL,
   item17 varchar(50) DEFAULT NULL,
   item18 varchar(50) DEFAULT NULL,
   item19 varchar(50) DEFAULT NULL,
   item20 varchar(50) DEFAULT NULL,
   item21 varchar(50) DEFAULT NULL,
   item22 varchar(50) DEFAULT NULL,
   item23 varchar(50) DEFAULT NULL,
   item24 varchar(50) DEFAULT NULL,
   item25 varchar(50) DEFAULT NULL,
   item26 varchar(50) DEFAULT NULL,
   item27 varchar(50) DEFAULT NULL,
   item28 varchar(50) DEFAULT NULL,
   PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table SuoYouZQYMain(
   id int not null auto_increment,
   item1 varchar(50) DEFAULT NULL,
   item2 varchar(50) DEFAULT NULL,
   item3 varchar(50) DEFAULT NULL,
   item4 varchar(50) DEFAULT NULL,
   item5 varchar(50) DEFAULT NULL,
   item6 varchar(50) DEFAULT NULL,
   item7 varchar(50) DEFAULT NULL,
   item8 varchar(50) DEFAULT NULL,
   item9 varchar(50) DEFAULT NULL,
   item10 varchar(50) DEFAULT NULL,
   item11 varchar(50) DEFAULT NULL,
   item12 varchar(50) DEFAULT NULL,
   item13 varchar(50) DEFAULT NULL,
   item14 varchar(50) DEFAULT NULL,
   item15 varchar(50) DEFAULT NULL,
   item16 varchar(50) DEFAULT NULL,
   item17 varchar(50) DEFAULT NULL,
   item18 varchar(50) DEFAULT NULL,
   item19 varchar(50) DEFAULT NULL,
   item20 varchar(50) DEFAULT NULL,
   item21 varchar(50) DEFAULT NULL,
   item22 varchar(50) DEFAULT NULL,
   item23 varchar(50) DEFAULT NULL,
   item24 varchar(50) DEFAULT NULL,
   item25 varchar(50) DEFAULT NULL,
   item26 varchar(50) DEFAULT NULL,
   item27 varchar(50) DEFAULT NULL,
   item28 varchar(50) DEFAULT NULL,
   PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table CompanyTable (
    id int not null auto_increment,
    status int not null,
    companyid int not null ,
    ldzjId int DEFAULT NULL,
    fldzjId int DEFAULT NULL,
    ldfzId int DEFAULT NULL,
    fldfzId int DEFAULT NULL,
    suoyzId int DEFAULT NULL,
    yysrId int DEFAULT NULL,
    yylyId int DEFAULT NULL,
    xjlyId int DEFAULT NULL,
    syzqyId int DEFAULT NULL,
    year varchar(10) DEFAULT NULL,
    CONSTRAINT Company_table FOREIGN KEY(companyid) REFERENCES Company(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
   CONSTRAINT One_ldzj FOREIGN KEY(ldzjId) REFERENCES LiuDongZiC(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
    CONSTRAINT two_lfldzj FOREIGN KEY(fldzjId) REFERENCES NoLiuDongZC(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
    CONSTRAINT One3_ldzj FOREIGN KEY(ldfzId) REFERENCES LiuDongFuZ(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
    CONSTRAINT One4_ldzj FOREIGN KEY(fldfzId) REFERENCES NLiuDongFuZ(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
    CONSTRAINT One5_ldzj FOREIGN KEY(suoyzId) REFERENCES SuoYouZQY(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
    CONSTRAINT One6_ldzj FOREIGN KEY(yysrId) REFERENCES YinYeShouY(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
    CONSTRAINT One7_ldzj FOREIGN KEY(yylyId) REFERENCES YinYeLiRun(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
    CONSTRAINT One8_ldzj FOREIGN KEY(xjlyId) REFERENCES XianJinLL(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
    CONSTRAINT One9_ldzj FOREIGN KEY(syzqyId) REFERENCES SuoYouZQYMain(id)
   ON UPDATE CASCADE
   ON DELETE CASCADE,
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
