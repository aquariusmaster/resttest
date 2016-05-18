-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='';

-- -----------------------------------------------------
-- Schema resttest
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `resttest` ;

-- -----------------------------------------------------
-- Schema resttest
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `resttest` DEFAULT CHARACTER SET utf8 ;
USE `resttest` ;

-- -----------------------------------------------------
-- Table `resttest`.`contatcs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resttest`.`contacts` ;

CREATE TABLE IF NOT EXISTS `resttest`.`contacts` (
  `id` BIGINT(45) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

