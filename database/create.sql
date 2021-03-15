CREATE SCHEMA IF NOT EXISTS `ARS` DEFAULT CHARACTER SET utf8 ;
USE `ARS` ;

-- -----------------------------------------------------
-- Table `ARS`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ARS`.`users` ;

CREATE TABLE IF NOT EXISTS `ARS`.`users` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ARS`.`reviews`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ARS`.`reviews` ;

CREATE TABLE IF NOT EXISTS `ARS`.`reviews` (
  `review_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `url` VARCHAR(45) NOT NULL,
  `summary` TEXT NULL,
  `posneg` TEXT NOT NULL,
  `majorPoints` TEXT NOT NULL,
  `minorPoints` TEXT NOT NULL,
  `rec` TEXT NOT NULL,
  PRIMARY KEY (`review_id`),
  INDEX `person_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ARS`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ARS`.`scores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ARS`.`scores` ;

CREATE TABLE IF NOT EXISTS `ARS`.`scores` (
  `score_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `score` TINYINT UNSIGNED NOT NULL,
  `review_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`score_id`),
  INDEX `review_id_idx` (`review_id` ASC) VISIBLE,
  INDEX `person_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `review_id`
    FOREIGN KEY (`review_id`)
    REFERENCES `ARS`.`reviews` (`review_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_id_score`
    FOREIGN KEY (`user_id`)
    REFERENCES `ARS`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
