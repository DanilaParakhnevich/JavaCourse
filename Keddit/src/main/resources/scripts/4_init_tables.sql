USE keddit;
INSERT INTO `users` VALUES (1,'beylon228822@gmail.com','$2a$10$NJBA5.rLveCO6tQN5uu27OCjUGKnbUG4By9l5bXucdUfttYrDUhUS',
                            'dendilll','2021-10-10 16:54:43','82f560b4-1943-4153-b037-395a18404c4edendilll.jpg',3,0),
                           (2,'roman2005@mail.ru','$2a$10$FCQVpuSuYKuHso76fOr5BucnSGlt6xWFP62AVArO1Egqg9qGAQmU6',
                            'roman','2021-10-10 18:23:13','3841e17b-6acc-4a11-af26-b2f40c66a1bbroman.jpg.jpg',2,0),
                           (3,'evgen228@gmail.com','$2a$10$cjhZalkZBWTAZT2TzFDSXOrx6ecDxQ0GqyCmJMbPAlhW4XrSxoOXe',
                            'evgen','2021-10-10 19:23:46',NULL,1,0);
INSERT INTO `publications` VALUES (1,2,'Анекдот про улитку',
                                   'Улитка заходит в бар, но бармен заявляет: \"У нас строгая политика в отношении улиток!\" — и ногой выпихивает ее на улицу. Через неделю улитка возвращается в бар и говорит бармену: \"Ну и зачем ты это сделал!?\"',NULL,'2021-10-31 19:13:20',1,0);
INSERT INTO `tags` VALUES (1,'Анекдот'),(1,'УлиткаВБаре');
INSERT INTO `like_publications` VALUES (2,1,0),(3,1,1);
INSERT INTO `comments` VALUES (1,3,1,NULL,'ору','2021-10-31 19:38:35');
INSERT INTO `like_comments` VALUES (1,1,1),(3,1,1);
INSERT INTO `communities` VALUES (1,1,'Keddit_Official','efd1d8ef-daee-4160-b38a-d166ce728330Keddit_Official.png');
INSERT INTO `followers` VALUES (2,1),(3,1);