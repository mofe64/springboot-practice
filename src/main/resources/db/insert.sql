SET FOREIGN_KEY_CHECKS = 0;
truncate table blog_post;
truncate table author;
truncate table comment;
truncate table author_posts;

INSERT INTO blog_post(id, title, content)
VALUES  (41, 'post1', 'content1'),
        (42, 'post2', 'content2'),
        (43, 'post3', 'content3'),
        (44, 'post4', 'content4'),
        (45, 'post5', 'content5');


SET FOREIGN_KEY_CHECKS  = 1;