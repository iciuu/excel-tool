CREATE TABLE ng_test.fans
(
    user_id int(11),
    weibo_id int(11),
    fan_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT
);
INSERT INTO ng_test.fans (user_id, weibo_id, fan_id) VALUES (2, 1, 1);
INSERT INTO ng_test.fans (user_id, weibo_id, fan_id) VALUES (3, 1, 2);
INSERT INTO ng_test.fans (user_id, weibo_id, fan_id) VALUES (4, 1, 3);