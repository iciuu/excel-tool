CREATE TABLE ng_test.comments
(
    comment_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id int(11) NOT NULL,
    weibo_id int(11) NOT NULL,
    comment_text varchar(500),
    comment_date date NOT NULL,
    CONSTRAINT FKqi14bvepnwtjbbaxm7m4v44yg FOREIGN KEY (user_id) REFERENCES user (user_id),
    CONSTRAINT comments_weibo_weibo_id_fk FOREIGN KEY (weibo_id) REFERENCES weibo (weibo_id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE INDEX FKqi14bvepnwtjbbaxm7m4v44yg ON ng_test.comments (user_id);
CREATE INDEX comments_weibo_weibo_id_fk ON ng_test.comments (weibo_id);
INSERT INTO ng_test.comments (comment_id, user_id, weibo_id, comment_text, comment_date) VALUES (1, 2, 1, 'pinlun1', '2018-12-04');
INSERT INTO ng_test.comments (comment_id, user_id, weibo_id, comment_text, comment_date) VALUES (2, 3, 1, 'pinlun2', '2018-12-04');