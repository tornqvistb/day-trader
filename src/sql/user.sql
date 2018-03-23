delete from user_share where trading_user_userId = 'tornqvistb';

delete from account where trading_user_userId = 'tornqvistb';

delete from share_day_rate;

delete from share_transaction;

delete from user_share;

insert into trading_user (user_id, email, name) values ('tornqvistb', 'tornqvistb@gmail.com', 'Björn Törnqvist');

insert into account (trading_user_userId, id, actual_balance, minimum_balance) values ('tornqvistb', 1, 50000, 0);

INSERT INTO strategy
(id,
compare_long_to_medium,
compare_medium_to_short,
compare_short_to_current,
description,
multiple_long_to_medium,
multiple_medium_to_short,
multiple_short_to_current)
VALUES
(1, b'0', b'0', b'1', 'Kortsiktig', 1, 1, 1.01);



insert into user_share (id, trading_user_userId, trading_user_user_id, share_id, buy_amount, strategy_id) values (1, 'tornqvistb', 'tornqvistb', 'AAK.ST', 5000, 1);
insert into user_share (id, trading_user_userId, trading_user_user_id, share_id, buy_amount, strategy_id) values (1, 'tornqvistb', 'tornqvistb', 'ABB.ST', 5000, 1);
insert into user_share (id, trading_user_userId, trading_user_user_id, share_id, buy_amount, strategy_id) values (1, 'tornqvistb', 'tornqvistb', 'ACAD.ST', 5000, 1);
insert into user_share (id, trading_user_userId, trading_user_user_id, share_id, buy_amount, strategy_id) values (1, 'tornqvistb', 'tornqvistb', 'ACAN-B.ST', 5000, 1);
insert into user_share (id, trading_user_userId, trading_user_user_id, share_id, buy_amount, strategy_id) values (1, 'tornqvistb', 'tornqvistb', 'ACTI.ST', 5000, 1);
insert into user_share (id, trading_user_userId, trading_user_user_id, share_id, buy_amount, strategy_id) values (1, 'tornqvistb', 'tornqvistb', 'ADDT-B.ST', 5000, 1);
insert into user_share (id, trading_user_userId, trading_user_user_id, share_id, buy_amount, strategy_id) values (1, 'tornqvistb', 'tornqvistb', 'AF-B.ST', 5000, 1);
insert into user_share (id, trading_user_userId, trading_user_user_id, share_id, buy_amount, strategy_id) values (1, 'tornqvistb', 'tornqvistb', 'AGRO.ST', 5000, 1);
insert into user_share (id, trading_user_userId, trading_user_user_id, share_id, buy_amount, strategy_id) values (1, 'tornqvistb', 'tornqvistb', 'AHSL.ST', 5000, 1);
insert into user_share (id, trading_user_userId, trading_user_user_id, share_id, buy_amount, strategy_id) values (1, 'tornqvistb', 'tornqvistb', 'ALFA.ST', 5000, 1);
