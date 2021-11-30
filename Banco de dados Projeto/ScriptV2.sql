
-- -----------------------------------------------------
-- Table `endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rua` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `cep` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `endereco_idendereco` INT NOT NULL,
  PRIMARY KEY (`id`, `endereco_idendereco`),
  INDEX `fk_cliente_endereco1_idx` (`endereco_idendereco` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_endereco1`
    FOREIGN KEY (`endereco_idendereco`)
    REFERENCES `endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `raca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `raca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_raca` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `especie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `especie` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_especie` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pet` (
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
    REFERENCES `cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pet_raca1`
    FOREIGN KEY (`raca_id`)
    REFERENCES `raca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pet_especie1`
    FOREIGN KEY (`especie_id`)
    REFERENCES `especie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `servicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `servico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipoServico` VARCHAR(45) NOT NULL,
  `horarioData` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cliente_has_servicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cliente_has_servico` (
  `cliente_idcliente` INT NOT NULL,
  `servico_idservico` INT NOT NULL,
  PRIMARY KEY (`cliente_idcliente`, `servico_idservico`),
  INDEX `fk_cliente_has_servico_servico1_idx` (`servico_idservico` ASC) VISIBLE,
  INDEX `fk_cliente_has_servico_cliente1_idx` (`cliente_idcliente` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_has_servicos_cliente1`
    FOREIGN KEY (`cliente_idcliente`)
    REFERENCES `cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_has_servicos_servico1`
    FOREIGN KEY (`servico_idservico`)
    REFERENCES `servico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


