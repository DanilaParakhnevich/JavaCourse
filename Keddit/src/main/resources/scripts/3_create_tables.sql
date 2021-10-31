
CREATE TABLE IF NOT EXISTS `keddit`.`users` (
                                                `id` INT UNSIGNED NOT NULL,
                                                `mail` VARCHAR(254) NOT NULL,
    `password` VARCHAR(60) NOT NULL,
    `nickname` VARCHAR(20) NOT NULL,
    `date` DATETIME NOT NULL,
    `photo` VARCHAR(62) NULL,
    `role` TINYINT(3) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `keddit`.`communities` (
                                                      `id` INT UNSIGNED NOT NULL,
                                                      `id_user` INT UNSIGNED NOT NULL,
                                                      `name` VARCHAR(35) NOT NULL,
    `photo` VARCHAR(77) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `keddit`.`publications` (
                                                       `id` INT UNSIGNED NOT NULL,
                                                       `id_user` INT UNSIGNED NOT NULL,
                                                       `head` VARCHAR(140) NOT NULL,
    `body` VARCHAR(1000) NOT NULL,
    `photos` VARCHAR(630) NULL,
    `date` DATETIME NOT NULL,
    `id_community` INT NULL,
    PRIMARY KEY (`id`),
    INDEX `users_publications_idx` (`id_user` ASC) VISIBLE,
    CONSTRAINT `users_publications`
    FOREIGN KEY (`id_user`)
    REFERENCES `keddit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `keddit`.`comments` (
                                                   `id` INT UNSIGNED NOT NULL,
                                                   `id_user` INT UNSIGNED NOT NULL,
                                                   `id_publications` INT UNSIGNED NOT NULL,
                                                   `photo` VARCHAR(62) NULL,
    `content` VARCHAR(300) NOT NULL,
    `date` DATETIME NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `users_comments_idx` (`id_user` ASC) VISIBLE,
    INDEX `publications_comments_idx` (`id_publications` ASC) VISIBLE,
    CONSTRAINT `users_comments`
    FOREIGN KEY (`id_user`)
    REFERENCES `keddit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `publications_comments`
    FOREIGN KEY (`id_publications`)
    REFERENCES `keddit`.`publications` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `keddit`.`like_publications` (
                                                            `id_user` INT UNSIGNED NOT NULL,
                                                            `id_publication` INT UNSIGNED NOT NULL,
                                                            `is_like` TINYINT NOT NULL,
                                                            PRIMARY KEY (`id_user`, `id_publication`),
    INDEX `publications_like_publications_idx` (`id_publication` ASC) VISIBLE,
    CONSTRAINT `users_like_publications`
    FOREIGN KEY (`id_user`)
    REFERENCES `keddit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `publications_like_publications`
    FOREIGN KEY (`id_publication`)
    REFERENCES `keddit`.`publications` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `keddit`.`like_comments` (
                                                        `id_user` INT UNSIGNED NOT NULL,
                                                        `id_comment` INT UNSIGNED NOT NULL,
                                                        `is_like` TINYINT NOT NULL,
                                                        PRIMARY KEY (`id_user`, `id_comment`),
    INDEX `comments_like_comments_idx` (`id_comment` ASC) VISIBLE,
    CONSTRAINT `users_like_comments`
    FOREIGN KEY (`id_user`)
    REFERENCES `keddit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `comments_like_comments`
    FOREIGN KEY (`id_comment`)
    REFERENCES `keddit`.`comments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `keddit`.`followers` (
                                                    `id_user` INT UNSIGNED NOT NULL,
                                                    `id_community` INT UNSIGNED NOT NULL,
                                                    PRIMARY KEY (`id_user`, `id_community`),
    INDEX `communities_followers_idx` (`id_community` ASC) VISIBLE,
    CONSTRAINT `users_followers`
    FOREIGN KEY (`id_user`)
    REFERENCES `keddit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `communities_followers`
    FOREIGN KEY (`id_community`)
    REFERENCES `keddit`.`communities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `keddit`.`tags` (
    `id_publications` INT UNSIGNED NOT NULL,
    `tag` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id_publications`, `tag`),
    INDEX `communities_followers_idx` (`id_publications` ASC) VISIBLE,
    CONSTRAINT `publications_tags`
    FOREIGN KEY (`id_publications`)
    REFERENCES `keddit`.`publications` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;
