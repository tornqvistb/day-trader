ALTER TABLE error_record CHANGE message message VARCHAR(10000) default NULL;

insert into system_property (id, string_value, number_value) values ('mailjob-frequency', '', 30);
insert into system_property (id, string_value, number_value) values ('mail-host', 'pop.gmail.com', 0);
insert into system_property (id, string_value, number_value) values ('mail-username', 'daytrader.arctisys@gmail.com', 0);
insert into system_property (id, string_value, number_value) values ('mail-password', 'Arctisys1000', 0);
insert into system_property (id, string_value, number_value) values ('mail-smtps-host', 'smtp.gmail.com', 0);

insert into system_property (id, string_value, number_value) values ('long-moving-average', 'long', 200);
insert into system_property (id, string_value, number_value) values ('medium-moving-average', 'medium', 50);
insert into system_property (id, string_value, number_value) values ('short-moving-average', 'short', 20);


insert into system_property (id, string_value, number_value) values ('mainjob-frequency', '', 30);
insert into system_property (id, string_value, number_value) values ('default-rate-frequency', '', 4);
insert into system_property (id, string_value, number_value) values ('default-buy-amount', '', 10000);
insert into system_property (id, string_value, number_value) values ('default-max-holding-amount', '', 10000);
insert into system_property (id, string_value, number_value) values ('file-incoming-folder', 'C:/Projekt/arctisys/filedirs/incoming', 0);
insert into system_property (id, string_value, number_value) values ('file-processed-folder', 'C:/Projekt/arctisys/filedirs/processed', 0);
insert into system_property (id, string_value, number_value) values ('file-error-folder', 'C:/Projekt/arctisys/filedirs/error', 0);
commit;

//UPDATE DATABASECHANGELOGLOCK SET LOCKED=FALSE, LOCKGRANTED=null, LOCKEDBY=null where ID=1;