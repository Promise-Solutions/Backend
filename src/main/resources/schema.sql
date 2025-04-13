-- FUNCIONÁRIO
CREATE SCHEMA IF NOT EXISTS `studiozero` DEFAULT CHARACTER SET utf8;
USE `studiozero`;

CREATE TABLE IF NOT EXISTS `funcionario` (
  `id_funcionario` CHAR(36) NOT NULL PRIMARY KEY,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `contato` VARCHAR(15) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  `token` VARCHAR(45) NOT NULL,
  `ativo` BOOLEAN NOT NULL
);
CREATE TABLE IF NOT EXISTS `funcionario`
(
    `id_funcionario` CHAR(36) NOT NULL,
    `nome` VARCHAR(45) NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    `contato` VARCHAR(15) NOT NULL,
    `cpf` VARCHAR(14) NOT NULL,
    `senha` VARCHAR(45) NOT NULL,
    `token` VARCHAR(45) NOT NULL,
    `ativo` BOOLEAN NOT NULL,
    PRIMARY KEY (`id_funcionario`)
    ) ENGINE = InnoDB;

-- CLIENTE
CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` CHAR(36) NOT NULL PRIMARY KEY,
  `nome` VARCHAR(45) NOT NULL,
  `cpf` CHAR(14) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `contato` CHAR(15) NOT NULL,
  `tipo_cliente` VARCHAR(45) NOT NULL,
  `ativo` BOOLEAN NOT NULL
);
CREATE TABLE IF NOT EXISTS `cliente`
(
    `id_cliente` CHAR(36) NOT NULL,
    `nome` VARCHAR(45) NOT NULL,
    `cpf` CHAR(14) NOT NULL,
    `email` VARCHAR(45) NOT NULL,
    `contato` CHAR(15) NOT NULL,
    `tipo_cliente` VARCHAR(45) NOT NULL,
    `ativo` BOOLEAN NOT NULL,
    PRIMARY KEY (`id_cliente`)
    ) ENGINE = InnoDB;

-- SERVIÇO OU PACOTES
CREATE TABLE IF NOT EXISTS `ServicoOuPacotes` (
  `idServico` CHAR(36) NOT NULL,
  `fkCliente` CHAR(36) NOT NULL,
  `valorTotal` FLOAT NOT NULL,
  `categoria` VARCHAR(42) NOT NULL,
  `status` VARCHAR(42) NOT NULL,
  `tipoServico` VARCHAR(42) NOT NULL,
  PRIMARY KEY (`idServico`),
  INDEX `fkCliente_idx` (`fkCliente` ASC),
  CONSTRAINT `fkCliente`
    FOREIGN KEY (`fkCliente`)
    REFERENCES `Cliente` (`idCliente`)
CREATE TABLE IF NOT EXISTS `servico_ou_pacotes`
(
    `id_servico` CHAR(36) NOT NULL,
    `fk_cliente` CHAR(36) NOT NULL,
    `valor_total` FLOAT NOT NULL,
    `categoria` VARCHAR(42) NOT NULL,
    `status` VARCHAR(42) NOT NULL,
    `tipo_servico` VARCHAR(42) NOT NULL,
    PRIMARY KEY (`id_servico`),
    INDEX `fk_cliente_idx` (`fk_cliente` ASC),
    CONSTRAINT `fk_cliente`
    FOREIGN KEY (`fk_cliente`)
    REFERENCES `cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- TAREFA
CREATE TABLE IF NOT EXISTS `Tarefa` (
  `idTarefa` CHAR(36) NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(250) NOT NULL,
  `data_inicio` DATE NULL,
  `data_limite` DATE NULL,
  `fkFuncionario` CHAR(36) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTarefa`),
  INDEX `fkFuncionario_idx` (`fkFuncionario` ASC),
  CONSTRAINT `fkFuncionario`
    FOREIGN KEY (`fkFuncionario`)
    REFERENCES `Funcionario` (`idFuncionario`)
CREATE TABLE IF NOT EXISTS `tarefa`
(
    `id_tarefa` CHAR(36) NOT NULL,
    `titulo` VARCHAR(45) NOT NULL,
    `descricao` VARCHAR(250) NOT NULL,
    `data_inicio` DATE NULL,
    `data_limite` DATE NULL,
    `fk_funcionario` CHAR(36) NOT NULL,
    `status` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id_tarefa`),
    INDEX `fk_funcionario_idx` (`fk_funcionario` ASC),
    CONSTRAINT `fk_funcionario`
    FOREIGN KEY (`fk_funcionario`)
    REFERENCES `funcionario` (`id_funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- SUBSERVIÇO
CREATE TABLE IF NOT EXISTS `SubServico` (
  `idSubServico` CHAR(36) NOT NULL,
  `tituloSubServico` VARCHAR(45) NOT NULL,
  `descricaoSubServico` VARCHAR(250) NULL,
  `valorSubServico` DOUBLE NOT NULL,
  `data` DATE NOT NULL,
  `horaInicio` TIME NOT NULL,
  `horaFim` TIME NULL,
  `status` VARCHAR(42) NOT NULL,
  `fkServico` CHAR(36) NOT NULL,
  PRIMARY KEY (`idSubServico`, `fkServico`),
  INDEX `fkServico_idx` (`fkServico` ASC),
  CONSTRAINT `fkServico`
    FOREIGN KEY (`fkServico`)
    REFERENCES `ServicoOuPacotes` (`idServico`)
CREATE TABLE IF NOT EXISTS `sub_servico`
(
    `id_sub_servico` CHAR(36) NOT NULL,
    `titulo_sub_servico` VARCHAR(45) NOT NULL,
    `descricao_sub_servico` VARCHAR(250) NULL,
    `valor_sub_servico` DOUBLE NOT NULL,
    `data` DATE NOT NULL,
    `hora_inicio` TIME NOT NULL,
    `hora_fim` TIME NULL,
    `status` VARCHAR(42) NOT NULL,
    `fk_servico` CHAR(36) NOT NULL,
    PRIMARY KEY (`id_sub_servico`, `fk_servico`),
    INDEX `fk_servico_idx` (`fk_servico` ASC),
    CONSTRAINT `fk_servico`
    FOREIGN KEY (`fk_servico`)
    REFERENCES `servico_ou_pacotes` (`id_servico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- PRODUTO (MANTIDO COMO INT)
CREATE TABLE IF NOT EXISTS `Produto` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `nomeProduto` VARCHAR(45) NOT NULL,
  `qtdProduto` INT NOT NULL,
  `valorUnitario` FLOAT NOT NULL,
  PRIMARY KEY (`idProduto`)
) ENGINE = InnoDB;
-- PRODUTO
CREATE TABLE IF NOT EXISTS `produto`
(
    `id_produto` INT NOT NULL AUTO_INCREMENT,
    `nome_produto` VARCHAR(45) NOT NULL,
    `qtd_produto` INT NOT NULL,
    `valor_unitario` FLOAT NOT NULL,
    PRIMARY KEY (`id_produto`)
    ) ENGINE = InnoDB;

-- COMANDA (MANTIDO COMO INT)
CREATE TABLE IF NOT EXISTS `Comanda` (
  `idComanda` INT NOT NULL AUTO_INCREMENT,
  `dataHoraAbertura` DATETIME NOT NULL,
  `dataHoraFechamento` DATETIME NULL,
  `desconto` DOUBLE NULL,
  `valorTotal` DOUBLE NOT NULL,
  `fkCliente` CHAR(36) NULL,
  `fkFuncionario` CHAR(36) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idComanda`),
  INDEX `fkCliente_idx` (`fkCliente` ASC),
  INDEX `fkFuncionario_idx` (`fkFuncionario` ASC),
  CONSTRAINT `fkCliente_Comanda`
    FOREIGN KEY (`fkCliente`)
    REFERENCES `Cliente` (`idCliente`)
-- COMANDA
CREATE TABLE IF NOT EXISTS `comanda`
(
    `id_comanda` INT NOT NULL AUTO_INCREMENT,
    `data_hora_abertura` DATETIME NOT NULL,
    `data_hora_fechamento` DATETIME NULL,
    `desconto` DOUBLE NULL,
    `valor_total` DOUBLE NOT NULL,
    `fk_cliente` CHAR(36) NULL,
    `fk_funcionario` CHAR(36) NOT NULL,
    `status` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id_comanda`),
    INDEX `fk_cliente_idx` (`fk_cliente` ASC),
    INDEX `fk_funcionario_idx` (`fk_funcionario` ASC),
    CONSTRAINT `fk_cliente_comanda`
    FOREIGN KEY (`fk_cliente`)
    REFERENCES `cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkFuncionario_Comanda`
    FOREIGN KEY (`fkFuncionario`)
    REFERENCES `Funcionario` (`idFuncionario`)
    CONSTRAINT `fk_funcionario_comanda`
    FOREIGN KEY (`fk_funcionario`)
    REFERENCES `funcionario` (`id_funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- COMANDA PRODUTO (MANTIDO COMO INT)
CREATE TABLE IF NOT EXISTS `ComandaProduto` (
  `idComandaProduto` INT NOT NULL,
  `fkProduto` INT NOT NULL,
  `fkComanda` INT NOT NULL,
  `qtdProduto` INT NOT NULL,
  `valorUnitario` DOUBLE NOT NULL,
  PRIMARY KEY (`idComandaProduto`, `fkProduto`, `fkComanda`),
  INDEX `fkProduto_idx` (`fkProduto` ASC),
  INDEX `fkComanda_idx` (`fkComanda` ASC),
  CONSTRAINT `fkProduto`
    FOREIGN KEY (`fkProduto`)
    REFERENCES `Produto` (`idProduto`)
-- COMANDA PRODUTO
CREATE TABLE IF NOT EXISTS `comanda_produto`
(
    `id_comanda_produto` INT NOT NULL,
    `fk_produto` INT NOT NULL,
    `fk_comanda` INT NOT NULL,
    `qtd_produto` INT NOT NULL,
    `valor_unitario` DOUBLE NOT NULL,
    PRIMARY KEY (`id_comanda_produto`, `fk_produto`, `fk_comanda`),
    INDEX `fk_produto_idx` (`fk_produto` ASC),
    INDEX `fk_comanda_idx` (`fk_comanda` ASC),
    CONSTRAINT `fk_produto`
    FOREIGN KEY (`fk_produto`)
    REFERENCES `produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkComanda`
    FOREIGN KEY (`fkComanda`)
    REFERENCES `Comanda` (`idComanda`)
    CONSTRAINT `fk_comanda`
    FOREIGN KEY (`fk_comanda`)
    REFERENCES `comanda` (`id_comanda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;
    ) ENGINE = InnoDB;

-- Verificação da tabela funcionario
DESC funcionario;
