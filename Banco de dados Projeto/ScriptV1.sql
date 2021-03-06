-- MySQL Script generated by MySQL Workbench
-- Wed Nov 24 14:09:34 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rua` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `cep` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `endereco_idendereco` INT NOT NULL,
  PRIMARY KEY (`id`, `endereco_idendereco`),
  INDEX `fk_cliente_endereco1_idx` (`endereco_idendereco` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_endereco1`
    FOREIGN KEY (`endereco_idendereco`)
    REFERENCES `mydb`.`endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`raca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`raca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_raca` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`especie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`especie` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_especie` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`pet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `peso` INT NOT NULL,
  `data_nasc` DATE NOT NULL,
  `genero` VARCHAR(45) NOT NULL,
  `cliente_idcliente` INT NOT NULL,
  `raca_id` INT NOT NULL,
  `especie_id` INT NOT NULL,
  PRIMARY KEY (`id`, `cliente_idcliente`),
  INDEX `fk_pet_cliente_idx` (`cliente_idcliente` ASC) VISIBLE,
  INDEX `fk_pet_raca1_idx` (`raca_id` ASC) VISIBLE,
  INDEX `fk_pet_especie1_idx` (`especie_id` ASC) VISIBLE,
  CONSTRAINT `fk_pet_cliente`
    FOREIGN KEY (`cliente_idcliente`)
    REFERENCES `mydb`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pet_raca1`
    FOREIGN KEY (`raca_id`)
    REFERENCES `mydb`.`raca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pet_especie1`
    FOREIGN KEY (`especie_id`)
    REFERENCES `mydb`.`especie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`servicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`servicos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipoServicos` VARCHAR(45) NOT NULL,
  `horarioData` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cliente_has_servicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cliente_has_servicos` (
  `cliente_idcliente` INT NOT NULL,
  `servicos_idservicos` INT NOT NULL,
  PRIMARY KEY (`cliente_idcliente`, `servicos_idservicos`),
  INDEX `fk_cliente_has_servicos_servicos1_idx` (`servicos_idservicos` ASC) VISIBLE,
  INDEX `fk_cliente_has_servicos_cliente1_idx` (`cliente_idcliente` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_has_servicos_cliente1`
    FOREIGN KEY (`cliente_idcliente`)
    REFERENCES `mydb`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_has_servicos_servicos1`
    FOREIGN KEY (`servicos_idservicos`)
    REFERENCES `mydb`.`servicos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
