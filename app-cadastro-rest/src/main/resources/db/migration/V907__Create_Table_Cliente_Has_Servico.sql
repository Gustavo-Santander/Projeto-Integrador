CREATE TABLE IF NOT EXISTS `cliente_has_servico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data` VARCHAR(45) NOT NULL,
  `horario` VARCHAR(45) NOT NULL,
  `cliente_idcliente` INT NOT NULL,
  `servico_idservico` INT NOT NULL,
  INDEX `fk_cliente_has_servico_servico1_idx` (`servico_idservico` ASC) VISIBLE,
  INDEX `fk_cliente_has_servico_cliente1_idx` (`cliente_idcliente` ASC) VISIBLE,
  PRIMARY KEY (`id`, `cliente_idcliente`, `servico_idservico`),
  CONSTRAINT `fk_cliente_has_servico_cliente1`
    FOREIGN KEY (`cliente_idcliente`)
    REFERENCES `cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_has_servico_servico1`
    FOREIGN KEY (`servico_idservico`)
    REFERENCES `servico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
