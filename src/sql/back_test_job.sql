back_test_result_transactions;
delete from transaction;
delete from back_test_result;
delete from back_test_input;
delete from back_test_job;

insert into back_test_job(id, name, status) values (1, 'My test job', 'start');

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(1,'8TRA.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(2,'8TRA.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(3,'8TRA.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(4,'8TRA.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(5,'8TRA.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(6,'8TRA.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(7,'8TRA.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(8,'8TRA.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(11,'AAK.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(12,'AAK.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(13,'AAK.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(14,'AAK.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(15,'AAK.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(16,'AAK.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(17,'AAK.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(18,'AAK.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(21,'ABB.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(22,'ABB.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(23,'ABB.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(24,'ABB.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(25,'ABB.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(26,'ABB.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(27,'ABB.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(28,'ABB.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(31,'ACAD.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(32,'ACAD.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(33,'ACAD.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(34,'ACAD.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(35,'ACAD.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(36,'ACAD.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(37,'ACAD.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(38,'ACAD.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(41,'ACE.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(42,'ACE.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(43,'ACE.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(44,'ACE.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(45,'ACE.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(46,'ACE.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(47,'ACE.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(48,'ACE.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(51,'ACTI.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(52,'ACTI.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(53,'ACTI.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(54,'ACTI.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(55,'ACTI.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(56,'ACTI.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(57,'ACTI.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(58,'ACTI.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(61,'ADAPT.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(62,'ADAPT.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(63,'ADAPT.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(64,'ADAPT.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(65,'ADAPT.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(66,'ADAPT.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(67,'ADAPT.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(68,'ADAPT.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(71,'ADDT-B.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(72,'ADDT-B.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(73,'ADDT-B.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(74,'ADDT-B.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(75,'ADDT-B.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(76,'ADDT-B.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(77,'ADDT-B.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(78,'ADDT-B.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(81,'AF-B.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(82,'AF-B.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(83,'AF-B.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(84,'AF-B.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(85,'AF-B.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(86,'AF-B.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(87,'AF-B.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(88,'AF-B.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(91,'AGRO.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(92,'AGRO.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(93,'AGRO.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(94,'AGRO.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(95,'AGRO.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(96,'AGRO.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(97,'AGRO.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(98,'AGRO.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(101,'ALFA.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(102,'ALFA.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(103,'ALFA.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(104,'ALFA.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(105,'ALFA.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(106,'ALFA.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(107,'ALFA.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(108,'ALFA.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(111,'ALIF-B.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(112,'ALIF-B.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(113,'ALIF-B.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(114,'ALIF-B.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(115,'ALIF-B.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(116,'ALIF-B.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(117,'ALIF-B.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(118,'ALIF-B.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(121,'ALIG.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(122,'ALIG.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(123,'ALIG.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(124,'ALIG.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(125,'ALIG.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(126,'ALIG.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(127,'ALIG.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(128,'ALIG.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(131,'ALIV-SDB.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(132,'ALIV-SDB.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(133,'ALIV-SDB.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(134,'ALIV-SDB.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(135,'ALIV-SDB.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(136,'ALIV-SDB.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(137,'ALIV-SDB.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(138,'ALIV-SDB.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(141,'AM1S.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(142,'AM1S.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(143,'AM1S.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(144,'AM1S.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(145,'AM1S.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(146,'AM1S.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(147,'AM1S.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(148,'AM1S.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(151,'AMBEA.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(152,'AMBEA.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(153,'AMBEA.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(154,'AMBEA.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(155,'AMBEA.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(156,'AMBEA.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(157,'AMBEA.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(158,'AMBEA.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(161,'ANOD-B.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(162,'ANOD-B.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(163,'ANOD-B.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(164,'ANOD-B.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(165,'ANOD-B.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(166,'ANOD-B.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(167,'ANOD-B.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(168,'ANOD-B.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(171,'ANOT.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(172,'ANOT.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(173,'ANOT.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(174,'ANOT.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(175,'ANOT.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(176,'ANOT.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(177,'ANOT.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(178,'ANOT.ST',10000, '2019-04-27', '2020-02-21', 8, 1);

insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(181,'AOI.ST',10000, '2019-04-27', '2020-02-21', 1, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(182,'AOI.ST',10000, '2019-04-27', '2020-02-21', 2, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(183,'AOI.ST',10000, '2019-04-27', '2020-02-21', 3, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(184,'AOI.ST',10000, '2019-04-27', '2020-02-21', 4, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(185,'AOI.ST',10000, '2019-04-27', '2020-02-21', 5, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(186,'AOI.ST',10000, '2019-04-27', '2020-02-21', 6, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(187,'AOI.ST',10000, '2019-04-27', '2020-02-21', 7, 1);
insert into back_test_input (id, share_id, amount, start_date, end_date, strategy_id, back_test_job_id) 
values(188,'AOI.ST',10000, '2019-04-27', '2020-02-21', 8, 1);


//startdatum, slutydatum, aktie(id), aktie(klartext), strategi, startvärde, slutvärde(konto), slutvärde(aktier), förändring (%)

select btj.name, bti.start_date, bti.end_date, sh.id, sh.description, st.description, bti.amount, btr.end_value_available, btr.end_value_in_stocks, (btr.end_value_available + btr.end_value_in_stocks - btr.start_value)/btr.start_value*100 profit from back_test_job btj, back_test_input bti, share sh, strategy st, back_test_result btr
where btj.id = bti.back_test_job_id and btr.id = bti.back_test_result_id and bti.strategy_id = st.id and bti.share_id = sh.id order by profit desc;