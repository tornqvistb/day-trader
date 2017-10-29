ALTER TABLE error_record CHANGE message message VARCHAR(10000) default NULL;

insert into system_property (id, string_value, number_value) values ('mailjob-frequency', '', 30);
insert into system_property (id, string_value, number_value) values ('mail-host', 'pop.gmail.com', 0);
insert into system_property (id, string_value, number_value) values ('mail-username', 'trader.arctisys@gmail.com', 0);
insert into system_property (id, string_value, number_value) values ('mail-password', 'Arctisys1000', 0);
insert into system_property (id, string_value, number_value) values ('mail-smtps-host', 'smtp.gmail.com', 0);

insert into system_property (id, string_value, number_value) values ('long-moving-average', 'long', 200);
insert into system_property (id, string_value, number_value) values ('medium-moving-average', 'medium', 50);
insert into system_property (id, string_value, number_value) values ('short-moving-average', 'short', 20);


insert into system_property (id, string_value, number_value) values ('mainjob-frequency', '', 30);
insert into system_property (id, string_value, number_value) values ('default-rate-frequency', '', 4);
insert into system_property (id, string_value, number_value) values ('default-buy-amount', '', 10000);
insert into system_property (id, string_value, number_value) values ('default-max-holding-amount', '', 10000);

insert into system_property (id, string_value, number_value) values ('file-import-url', 'http://real-chart.finance.yahoo.com/table.csv?s=#ID&a=#FR_MON&b=#FR_DAY&c=#FR_YEAR&d=#TO_MON&e=#TO_DA&f=#TO_YEAR&g=d&ignore=.csv',0);

insert into system_property (id, string_value, number_value) values ('trading-time', '17:00', 0);
insert into system_property (id, string_value, number_value) values ('last-trading-date', '2016-01-01', 0);
insert into system_property (id, string_value, number_value) values ('stock-site-url', 'http://bors.six.se/ttspektra-web/gpse/equities?entry=stockholm-omx-all', 0);
insert into system_property (id, string_value, number_value) values ('years-to-collect-history', '', 1);

insert into system_property (id, string_value, number_value) values ('moving-average-short', '', 5);
insert into system_property (id, string_value, number_value) values ('moving-average-medium', '', 20);
insert into system_property (id, string_value, number_value) values ('moving-average-long', '', 50);

commit;



//UPDATE DATABASECHANGELOGLOCK SET LOCKED=FALSE, LOCKGRANTED=null, LOCKEDBY=null where ID=1;