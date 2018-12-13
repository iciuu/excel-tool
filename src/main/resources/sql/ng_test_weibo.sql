CREATE TABLE ng_test.weibo
(
    weibo_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id int(11) NOT NULL,
    weibo_text varchar(500),
    create_date date NOT NULL,
    CONSTRAINT weibo_user_id_fk FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE INDEX weibo_user_id_fk ON ng_test.weibo (user_id);
INSERT INTO ng_test.weibo (weibo_id, user_id, weibo_text, create_date) VALUES (1, 1, 'hahaha', '2018-07-18');