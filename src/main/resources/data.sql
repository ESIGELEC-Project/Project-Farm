PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
INSERT INTO "category" VALUES(1,'APP','APP');
INSERT INTO "category" VALUES(2,'Robotics','Robotics');
INSERT INTO "category" VALUES(3,'Information system','Information system');
INSERT INTO "category" VALUES(4,'Hardware','Hardware');
INSERT INTO "user" VALUES('pingrunhuang@126.com
','runping',123,'owner');
INSERT INTO "user" VALUES('john@acme.com','john',123,'owner');
INSERT INTO "user" VALUES('sarah@geek.com','Sarah Logan',456,'evaluator');
INSERT INTO "user" VALUES('thibault@geek.com','Thibault Moulin',456,'evaluator');
INSERT INTO "user" VALUES('george@geek.com','George Papalodeminus',456,'evaluator');
INSERT INTO "user" VALUES('mary@acme.com','Mary Moon',123,'owner');
INSERT INTO "user" VALUES('paul@acme.com','Paul McDonalds',123,'owner');
INSERT INTO "document" VALUES('123','\where.js','04/18/2016 14:25:22');
	`description`	TEXT,
	`created_date`	TEXT,
INSERT INTO "project" VALUES(12,'123','123',123,123,'04/18/2016 08:40:50','john@acme.com','Apps',NULL);
INSERT INTO "project" VALUES(13,'1234','1',1,1,'04/18/2016 12:46:40','john@acme.com','Apps',NULL);
INSERT INTO "project" VALUES(14,'4455','55',55,55,'04/18/2016 13:22:58','john@acme.com','APP',NULL);
INSERT INTO "evaluation" VALUES('123','sarah@geek.com',1,3);
INSERT INTO "evaluation" VALUES('123','sarah@geek.com',3,1);
INSERT INTO "evaluation" VALUES('123','sarah@geek.com',1,1);
INSERT INTO "evaluation" VALUES('123','sarah@geek.com',1,1);
INSERT INTO "evaluation" VALUES('1234','sarah@geek.com',1,1);
INSERT INTO "evaluation" VALUES('123','sarah@geek.com',1,1);
INSERT INTO "evaluation" VALUES('4455','sarah@geek.com',1,5);
INSERT INTO "evaluation" VALUES('4455','sarah@geek.com',1,4);
INSERT INTO "evaluation" VALUES('4455','thibault@geek.com',5,4);
DELETE FROM sqlite_sequence;
INSERT INTO "sqlite_sequence" VALUES('category',4);
INSERT INTO "sqlite_sequence" VALUES('project',14);
COMMIT;
