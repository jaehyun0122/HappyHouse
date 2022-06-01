-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema happyhouse
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema happyhouse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `happyhouse` DEFAULT CHARACTER SET utf8 ;
USE `happyhouse` ;

-- -----------------------------------------------------
-- Table `happyhouse`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`member` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(16) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `email` VARCHAR(20) NULL,
  `regtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `happyhouse`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`board` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `writer` VARCHAR(16) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `hit` INT NOT NULL DEFAULT 0,
  `regtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `writer_idx` (`writer` ASC) VISIBLE,
  CONSTRAINT `board_writer_FK_member_nickname`
    FOREIGN KEY (`writer`)
    REFERENCES `happyhouse`.`member` (`nickname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `happyhouse`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `writer` VARCHAR(16) NOT NULL,
  `boardId` INT NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `regtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `comment_writer_FK_member_nickname_idx` (`writer` ASC) VISIBLE,
  INDEX `comment_boardId_FK_board_id_idx` (`boardId` ASC) VISIBLE,
  CONSTRAINT `comment_writer_FK_member_nickname`
    FOREIGN KEY (`writer`)
    REFERENCES `happyhouse`.`member` (`nickname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `comment_boardId_FK_board_id`
    FOREIGN KEY (`boardId`)
    REFERENCES `happyhouse`.`board` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `happyhouse`.`boardFile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`boardFile` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `boardId` INT NOT NULL,
  `savefolder` VARCHAR(45) NOT NULL,
  `originname` VARCHAR(50) NOT NULL,
  `savename` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `boardFile_boardId_FK_board_id_idx` (`boardId` ASC) VISIBLE,
  CONSTRAINT `boardFile_boardId_FK_board_id`
    FOREIGN KEY (`boardId`)
    REFERENCES `happyhouse`.`board` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `happyhouse`.`sidocode`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`sidocode` (
  `sidoCode` VARCHAR(10) NOT NULL,
  `sidoName` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`sidoCode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `happyhouse`.`guguncode`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`guguncode` (
  `gugunCode` VARCHAR(10) NOT NULL,
  `gugunName` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`gugunCode`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
