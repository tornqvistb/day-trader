ALTER TABLE error_record CHANGE message VARCHAR(10000) default NULL;
ALTER TABLE email CHANGE content VARCHAR(4000) default NULL;

insert into system_property (id, string_value, number_value) values ('mail-username', 'trader.arctisys@gmail.com', 0);
insert into system_property (id, string_value, number_value) values ('mail-password', 'Arctisys1000', 0);
insert into system_property (id, string_value, number_value) values ('mail-smtps-host', 'smtp.gmail.com', 0);
insert into system_property (id, string_value, number_value) values ('mainjob-frequency', '', 30);
insert into system_property (id, string_value, number_value) values ('trading-time', '17:00', 0);
insert into system_property (id, string_value, number_value) values ('last-trading-date', '2016-01-01', 0);
insert into system_property (id, string_value, number_value) values ('stock-site-url', 'http://www.nasdaqomxnordic.com/aktier/listed-companies/stockholm', 0);
insert into system_property (id, string_value, number_value) values ('stock-site-url-start', 'http://www.nasdaqomxnordic.com', 0);
insert into system_property (id, string_value, number_value) values ('quotes-base-url', 'https://query2.finance.yahoo.com/v8/finance/chart/', 0);
insert into system_property (id, string_value, number_value) values ('years-to-collect-history', '', 1);
insert into system_property (id, string_value, number_value) values ('moving-average-short', '', 5);
insert into system_property (id, string_value, number_value) values ('moving-average-medium', '', 20);
insert into system_property (id, string_value, number_value) values ('moving-average-long', '', 50);

insert into strategy()

INSERT INTO strategy (id, compare_long_to_medium, compare_medium_to_short, compare_short_to_current, description, multiple_long_to_medium, multiple_medium_to_short, multiple_short_to_current) 
VALUES ('1', b'0', b'0', b'1', 'kort_102', '1.02', '1.02', '1.02');
INSERT INTO strategy (id, compare_long_to_medium, compare_medium_to_short, compare_short_to_current, description, multiple_long_to_medium, multiple_medium_to_short, multiple_short_to_current) 
VALUES ('2', b'0', b'1', b'0', 'mellan_102', '1.02', '1.02', '1.02');
INSERT INTO strategy (id, compare_long_to_medium, compare_medium_to_short, compare_short_to_current, description, multiple_long_to_medium, multiple_medium_to_short, multiple_short_to_current) 
VALUES ('3', b'1', b'0', b'0', 'lång_102', '1.02', '1.02', '1.02');

INSERT INTO strategy (id, compare_long_to_medium, compare_medium_to_short, compare_short_to_current, description, multiple_long_to_medium, multiple_medium_to_short, multiple_short_to_current) 
VALUES ('4', b'0', b'0', b'1', 'kort_1015', '1.015', '1.015', '1.015');
INSERT INTO strategy (id, compare_long_to_medium, compare_medium_to_short, compare_short_to_current, description, multiple_long_to_medium, multiple_medium_to_short, multiple_short_to_current) 
VALUES ('5', b'0', b'1', b'0', 'mellan_1015', '1.015', '1.015', '1.015');
INSERT INTO strategy (id, compare_long_to_medium, compare_medium_to_short, compare_short_to_current, description, multiple_long_to_medium, multiple_medium_to_short, multiple_short_to_current) 
VALUES ('6', b'1', b'0', b'0', 'lång_1015', '1.015', '1.015', '1.015');

INSERT INTO strategy (id, compare_long_to_medium, compare_medium_to_short, compare_short_to_current, description, multiple_long_to_medium, multiple_medium_to_short, multiple_short_to_current) 
VALUES ('7', b'0', b'1', b'1', 'kort_mellan_102', '1.02', '1.02', '1.02');

INSERT INTO strategy (id, compare_long_to_medium, compare_medium_to_short, compare_short_to_current, description, multiple_long_to_medium, multiple_medium_to_short, multiple_short_to_current) 
VALUES ('8', b'1', b'1', b'0', 'mellan_lång_102', '1.02', '1.02', '1.02');

commit;