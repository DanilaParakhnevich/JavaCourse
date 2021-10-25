-- MySQL Script generated by MySQL Workbench
-- Mon Oct 25 20:36:02 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema reddit_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema reddit_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `reddit_db` DEFAULT CHARACTER SET utf8 ;
USE `reddit_db` ;

-- -----------------------------------------------------
-- Table `reddit_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reddit_db`.`users` (
  `idusr` INT UNSIGNED NOT NULL,
  `role` VARCHAR(10) NOT NULL,
  `nickname` VARCHAR(20) NOT NULL,
  `mail` VARCHAR(45) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `date` VARCHAR(45) NOT NULL,
  `photo` VARCHAR(100) NULL,
  PRIMARY KEY (`idusr`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reddit_db`.`communities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reddit_db`.`communities` (
  `idcm` INT UNSIGNED NOT NULL,
  `idusr` INT UNSIGNED NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `date` VARCHAR(45) NOT NULL,
  `photo` VARCHAR(100) NULL,
  PRIMARY KEY (`idcm`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reddit_db`.`publications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reddit_db`.`publications` (
  `idpb` INT UNSIGNED NOT NULL,
  `idusr` INT UNSIGNED NOT NULL,
  `idcm` INT UNSIGNED NULL,
  `date` VARCHAR(45) NOT NULL,
  `photos` VARCHAR(1000) NULL,
  `tag` VARCHAR(20) NULL,
  PRIMARY KEY (`idpb`),
  INDEX `userspb_idx` (`idusr` ASC) VISIBLE,
  INDEX `communitiespb_idx` (`idcm` ASC) VISIBLE,
  CONSTRAINT `userspb`
    FOREIGN KEY (`idusr`)
    REFERENCES `reddit_db`.`users` (`idusr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `communitiespb`
    FOREIGN KEY (`idcm`)
    REFERENCES `reddit_db`.`communities` (`idcm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reddit_db`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reddit_db`.`comments` (
  `idcm` INT UNSIGNED NOT NULL,
  `idusr` INT UNSIGNED NOT NULL,
  `idpb` INT UNSIGNED NOT NULL,
  `text` VARCHAR(100) NOT NULL,
  `photo` VARCHAR(100) NULL,
  PRIMARY KEY (`idcm`),
  INDEX `userscomments_idx` (`idusr` ASC) VISIBLE,
  INDEX `publicationscomments_idx` (`idpb` ASC) VISIBLE,
  CONSTRAINT `userscomments`
    FOREIGN KEY (`idusr`)
    REFERENCES `reddit_db`.`users` (`idusr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `publicationscomments`
    FOREIGN KEY (`idpb`)
    REFERENCES `reddit_db`.`publications` (`idpb`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reddit_db`.`followers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reddit_db`.`followers` (
  `idfl` INT UNSIGNED NOT NULL,
  `idusr` INT UNSIGNED NOT NULL,
  `idcm` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idfl`),
  INDEX `usersfol_idx` (`idusr` ASC) VISIBLE,
  INDEX `communitiesfol_idx` (`idcm` ASC) VISIBLE,
  CONSTRAINT `communitiesfol`
    FOREIGN KEY (`idcm`)
    REFERENCES `reddit_db`.`communities` (`idcm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `usersfol`
    FOREIGN KEY (`idusr`)
    REFERENCES `reddit_db`.`users` (`idusr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reddit_db`.`dislikes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reddit_db`.`dislikes` (
  `iddlk` INT UNSIGNED NOT NULL,
  `idusr` INT UNSIGNED NOT NULL,
  `idpb` INT UNSIGNED NULL,
  `idcm` INT UNSIGNED NULL,
  PRIMARY KEY (`iddlk`),
  INDEX `commentsdlk_idx` (`idcm` ASC) VISIBLE,
  INDEX `publicationsdlk_idx` (`idpb` ASC) VISIBLE,
  INDEX `usersdlk_idx` (`idusr` ASC) VISIBLE,
  CONSTRAINT `usersdlk`
    FOREIGN KEY (`idusr`)
    REFERENCES `reddit_db`.`users` (`idusr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `publicationsdlk`
    FOREIGN KEY (`idpb`)
    REFERENCES `reddit_db`.`publications` (`idpb`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `commentsdlk`
    FOREIGN KEY (`idcm`)
    REFERENCES `reddit_db`.`comments` (`idcm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `reddit_db`.`likes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reddit_db`.`likes` (
  `idlk` INT UNSIGNED NOT NULL,
  `idusr` INT UNSIGNED NOT NULL,
  `idpb` INT UNSIGNED NULL,
  `idcm` INT UNSIGNED NULL,
  PRIMARY KEY (`idlk`),
  INDEX `userslk_idx` (`idusr` ASC) VISIBLE,
  INDEX `publicationslk_idx` (`idpb` ASC) VISIBLE,
  INDEX `commentslk_idx` (`idcm` ASC) VISIBLE,
  CONSTRAINT `userslk`
    FOREIGN KEY (`idusr`)
    REFERENCES `reddit_db`.`users` (`idusr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `publicationslk`
    FOREIGN KEY (`idpb`)
    REFERENCES `reddit_db`.`publications` (`idpb`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `commentslk`
    FOREIGN KEY (`idcm`)
    REFERENCES `reddit_db`.`comments` (`idcm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
