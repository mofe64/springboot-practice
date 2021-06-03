SET FOREIGN_KEY_CHECKS = 0;
truncate table blog_post;
truncate table author;
truncate table comment;
truncate table author_posts;

INSERT INTO blog_post(id, title, content, date_created)
VALUES  (41, 'post1', 'content1', '2021-06-03T11:33:56' ),
        (42, 'post2', 'content2', '2021-06-03T11:34:56'),
        (43, 'post3', 'content3', '2021-06-03T11:35:56'),
        (44, 'post4', 'content4', '2021-06-03T11:36:56'),
        (45, 'post5', 'content5', '2021-06-03T11:37:56');


SET FOREIGN_KEY_CHECKS  = 1;