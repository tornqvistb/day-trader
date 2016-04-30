delete from user_share where trading_user_userId = 'tornqvistb';

delete from account where trading_user_userId = 'tornqvistb';

delete from share_day_rate;

delete from share_transaction;

delete from user_share;

insert into trading_user (user_id, email, name) values ('tornqvistb', 'tornqvistb@gmail.com', 'Björn Törnqvist');

insert into account (trading_user_userId, id, actual_balance, minimum_balance) values ('tornqvistb', 1, 50000, 0);

delete from share_day_rate;

delete from share;

update share_on_market set status = 'new';

update share_on_market set status = 'startimport' where id in ('HM-B.ST','SEB-A.ST','LIAB.ST','ATCO-A.ST','NOLA-B.ST','SAND.ST','INVE-A.ST','ALIV-SDB.ST','BILI-A.ST','UNIB-SDB.ST');

insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'HM-B.ST', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'SEB-A.ST', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'LIAB.ST', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'ATCO-A.ST', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'NOLA-B.ST', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'SAND.ST', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'INVE-A.ST', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'ALIV-SDB.ST', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'BILI-A.ST', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'UNIB-SDB.ST', 5000, 4);

commit;
