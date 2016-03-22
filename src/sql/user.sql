insert into trading_user (user_id, email, name) values ('tornqvistb', 'tornqvistb@gmail.com', 'Björn Törnqvist');

insert into account (trading_user_id, id, actual_balance, minmum_balance) values ('tornqvistb', 1, 50000, 0);

insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'Beijer Alma B', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'Bilia A', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'DGC One', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'eWork Scandinavia', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'H&M B', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'Haldex', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'MQ Holding', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'MTG B', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'Peab B', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'SJR B', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'Skanska B', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'Tele2 A', 5000, 4);

insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'AAK.ST', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'ACAN-B.ST', 5000, 4);
insert into user_share (id, trading_user_userId, share_id, buy_amount, frequency) values (1, 'tornqvistb', 'ALFA.ST', 5000, 4);

commit;
