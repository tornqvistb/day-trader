delete from user_share where user_user_id = 1;

delete from account where user_user_id = 1;

delete from share_day_rate;

delete from share_transaction;

delete from user_share;

insert into user (user_id, email, first_name, last_name, active, username, password) values (1, 'tornqvistb@gmail.com', 'Björn', 'Törnqvist', 1, 'tornqvistb', 'stensture');

insert into role (role_id, role) values (1, 'USER');
insert into role (role_id, role) values (2, 'ADMIN');

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (1, 2);

insert into account (user_user_id, id, actual_balance, minimum_balance) values (1, 1, 50000, 0);

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

insert into share (id, description, share_on_market_id) values ('AAK.ST', 'AAK', 'AAK.ST');
insert into share (id, description, share_on_market_id) values ('ABB.ST', 'ABB', 'ABB.ST');

insert into user_share (id, user_user_id, share_id, buy_amount, strategy_id) values (1, 1, 'AAK.ST', 5000, 1);
insert into user_share (id, user_user_id, share_id, buy_amount, strategy_id) values (2, 1, 'ABB.ST', 5000, 1);

-- test users
insert into user (user_id, email, first_name, last_name, active, username, password) values (2, 'user@gmail.com', 'Common', 'User', 1, 'commonuser', 'stensture');
insert into user_role (user_id, role_id) values (2, 1);
insert into account (user_user_id, id, actual_balance, minimum_balance) values (2, 2, 50000, 0);

insert into user (user_id, email, first_name, last_name, active, username, password) values (3, 'admin@gmail.com', 'Admin', 'User', 1, 'adminuser', 'stensture');
insert into user_role (user_id, role_id) values (3, 2);
insert into account (user_user_id, id, actual_balance, minimum_balance) values (3, 3, 50000, 0);

