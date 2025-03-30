CREATE TABLE IF NOT EXISTS `studiozero`.`Funcionario` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `tel` VARCHAR(15) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `token` VARCHAR(45) NOT NULL,
  `ativo` BOOLEAN NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  UNIQUE INDEX `idFuncionario_UNIQUE` (`idFuncionario` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `studiozero`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cpf` CHAR(14) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `tel` CHAR(15) NOT NULL,
  `tipoCliente` VARCHAR(45) NOT NULL,
  `ativo` BOOLEAN NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `idCliente_UNIQUE` (`idCliente` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `studiozero`.`ServicoOuPacotes` (
  `idServico` INT NOT NULL AUTO_INCREMENT,
  `cliente` INT NOT NULL,
  `valorTotal` FLOAT NOT NULL,
  `categoria` VARCHAR(42) NOT NULL,
  `status` VARCHAR(42) NOT NULL,
  `tipoServico` VARCHAR(42) NOT NULL,
  PRIMARY KEY (`idServico`),
  INDEX `fk_Servico_Cliente1_idx` (`cliente` ASC) VISIBLE,
  CONSTRAINT `fk_Servico_Cliente1`
    FOREIGN KEY (`cliente`)
    REFERENCES `studiozero`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `studiozero`.`Tarefa` (
  `idTarefa` INT NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(250) NOT NULL,
  `data_inicio` DATE NULL,
  `data_limite` DATE NULL,
  `responsavel` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTarefa`),
  INDEX `fk_tarefa_Funcionario1_idx` (`responsavel` ASC) VISIBLE,
  CONSTRAINT `fk_tarefa_Funcionario1`
    FOREIGN KEY (`responsavel`)
    REFERENCES `studiozero`.`Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `studiozero`.`SubServico` (
  `idSubServico` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tituloSubServico` VARCHAR(45) NOT NULL,
  `descricaoSubServico` VARCHAR(250) NULL,
  `valorSubServico` DOUBLE NOT NULL,
  `data` DATE NOT NULL,
  `horaInicio` TIME NOT NULL,
  `horaFim` TIME NULL,
  `status` VARCHAR(42) NOT NULL,
  `servico` INT NOT NULL,
  PRIMARY KEY (`idSubServico`, `servico`),
  INDEX `fk_Servico_Atendimento1_idx` (`servico` ASC) VISIBLE,
  CONSTRAINT `fk_Servico_Atendimento1`
    FOREIGN KEY (`servico`)
    REFERENCES `studiozero`.`ServicoOuPacotes` (`idServico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `studiozero`.`Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `nomeProduto` VARCHAR(45) NOT NULL,
  `qtdProduto` INT NOT NULL,
  `valorUnitario` FLOAT NOT NULL,
  PRIMARY KEY (`idProduto`),
  UNIQUE INDEX `idItem_UNIQUE` (`idProduto` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `studiozero`.`Comanda` (
  `idComanda` INT NOT NULL AUTO_INCREMENT,
  `dataHoraAbertura` DATETIME NOT NULL,
  `dataHoraFechamento` DATETIME NULL,
  `desconto` DOUBLE NULL,
  `valorTotal` DOUBLE NOT NULL,
  `cliente` INT NULL,
  `funcionario` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idComanda`),
  INDEX `fk_Comanda_Cliente1_idx` (`cliente` ASC) VISIBLE,
  UNIQUE INDEX `Cliente_idCliente_UNIQUE` (`cliente` ASC) VISIBLE,
  INDEX `fk_Comanda_Funcionario1_idx` (`funcionario` ASC) VISIBLE,
  CONSTRAINT `fk_Comanda_Cliente1`
    FOREIGN KEY (`cliente`)
    REFERENCES `studiozero`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comanda_Funcionario1`
    FOREIGN KEY (`funcionario`)
    REFERENCES `studiozero`.`Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `studiozero`.`ComandaProduto` (
  `idComandaProduto` INT NOT NULL,
  `produto` INT NOT NULL,
  `comanda` INT NOT NULL,
  `qtdProduto` INT NOT NULL,
  `valorUnitario` DOUBLE NOT NULL,
  PRIMARY KEY (`idComandaProduto`, `produto`, `comanda`),
  INDEX `fk_Produto_has_Comanda_Comanda1_idx` (`comanda` ASC) VISIBLE,
  INDEX `fk_Produto_has_Comanda_Produto1_idx` (`produto` ASC) VISIBLE,
  UNIQUE INDEX `idComandaProduto_UNIQUE` (`idComandaProduto` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_has_Comanda_Produto1`
    FOREIGN KEY (`produto`)
    REFERENCES `studiozero`.`Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_has_Comanda_Comanda1`
    FOREIGN KEY (`comanda`)
    REFERENCES `studiozero`.`Comanda` (`idComanda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;