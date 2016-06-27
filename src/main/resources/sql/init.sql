
use app;

SELECT * FROM app.user;

SET SQL_SAFE_UPDATES = 0;

UPDATE `app`.`user` SET `deleted`=0 WHERE `deleted`=1;

commit ;

