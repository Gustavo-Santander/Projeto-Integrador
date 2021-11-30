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