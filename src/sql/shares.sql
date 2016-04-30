delete from share_day_rate;

delete from share;

update share_on_market set status = 'new';

update share_on_market set status = 'startimport' where id in ('HM-B.ST','SEB-A.ST','LIAB.ST','ATCO-A.ST','NOLA-B.ST','SAND.ST','INVE-A.ST','ALIV-SDB.ST','BILI-A.ST','UNIB-SDB.ST');


commit;
