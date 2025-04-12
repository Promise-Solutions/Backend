CREATE SCHEMA IF NOT EXISTS `studiozero` DEFAULT CHARACTER SET utf8;
USE
`studiozero`;
-- FUNCIONÁRIO
CREATE TABLE IF NOT EXISTS `Funcionario`
(
    `idFuncionario`
    CHAR
(
    36
) NOT NULL,
    `nome` VARCHAR
(
    45
) NOT NULL,
    `email` VARCHAR
(
    45
) NOT NULL,
    `contato` VARCHAR
(
    15
) NOT NULL,
    `cpf` VARCHAR
(
    14
) NOT NULL,
    `senha` VARCHAR
(
    45
) NOT NULL,
    `token` VARCHAR
(
    45
) NOT NULL,
    `ativo` BOOLEAN NOT NULL,
    PRIMARY KEY
(
    `idFuncionario`
)
    ) ENGINE = InnoDB;

-- CLIENTE
CREATE TABLE IF NOT EXISTS `Cliente`
(
    `idCliente`
    CHAR
(
    36
) NOT NULL,
    `nome` VARCHAR
(
    45
) NOT NULL,
    `cpf` CHAR
(
    14
) NOT NULL,
    `email` VARCHAR
(
    45
) NOT NULL,
    `contato` CHAR
(
    15
) NOT NULL,
    `tipoCliente` VARCHAR
(
    45
) NOT NULL,
    `ativo` BOOLEAN NOT NULL,
    PRIMARY KEY
(
    `idCliente`
)
    ) ENGINE = InnoDB;

-- SERVIÇO OU PACOTES
CREATE TABLE IF NOT EXISTS `ServicoOuPacotes`
(
    `idServico`
    CHAR
(
    36
) NOT NULL,
    `fkCliente` CHAR
(
    36
) NOT NULL,
    `valorTotal` FLOAT NOT NULL,
    `categoria` VARCHAR
(
    42
) NOT NULL,
    `status` VARCHAR
(
    42
) NOT NULL,
    `tipoServico` VARCHAR
(
    42
) NOT NULL,
    PRIMARY KEY
(
    `idServico`
),
    INDEX `fkCliente_idx`
(
    `fkCliente`
    ASC
),
    CONSTRAINT `fkCliente`
    FOREIGN KEY
(
    `fkCliente`
)
    REFERENCES `Cliente`
(
    `idCliente`
)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB;

-- TAREFA
CREATE TABLE IF NOT EXISTS `Tarefa`
(
    `idTarefa`
    CHAR
(
    36
) NOT NULL,
    `titulo` VARCHAR
(
    45
) NOT NULL,
    `descricao` VARCHAR
(
    250
) NOT NULL,
    `data_inicio` DATE NULL,
    `data_limite` DATE NULL,
    `fkFuncionario` CHAR
(
    36
) NOT NULL,
    `status` VARCHAR
(
    45
) NOT NULL,
    PRIMARY KEY
(
    `idTarefa`
),
    INDEX `fkFuncionario_idx`
(
    `fkFuncionario`
    ASC
),
    CONSTRAINT `fkFuncionario`
    FOREIGN KEY
(
    `fkFuncionario`
)
    REFERENCES `Funcionario`
(
    `idFuncionario`
)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB;

-- SUBSERVIÇO
CREATE TABLE IF NOT EXISTS `SubServico`
(
    `idSubServico`
    CHAR
(
    36
) NOT NULL,
    `tituloSubServico` VARCHAR
(
    45
) NOT NULL,
    `descricaoSubServico` VARCHAR
(
    250
) NULL,
    `valorSubServico` DOUBLE NOT NULL,
    `data` DATE NOT NULL,
    `horaInicio` TIME NOT NULL,
    `horaFim` TIME NULL,
    `status` VARCHAR
(
    42
) NOT NULL,
    `fkServico` CHAR
(
    36
) NOT NULL,
    PRIMARY KEY
(
    `idSubServico`,
    `fkServico`
),
    INDEX `fkServico_idx`
(
    `fkServico`
    ASC
),
    CONSTRAINT `fkServico`
    FOREIGN KEY
(
    `fkServico`
)
    REFERENCES `ServicoOuPacotes`
(
    `idServico`
)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB;

-- PRODUTO (MANTIDO COMO INT)
CREATE TABLE IF NOT EXISTS `Produto`
(
    `idProduto`
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    `nomeProduto`
    VARCHAR
(
    45
) NOT NULL,
    `qtdProduto` INT NOT NULL,
    `valorUnitario` FLOAT NOT NULL,
    PRIMARY KEY
(
    `idProduto`
)
    ) ENGINE = InnoDB;

-- COMANDA (MANTIDO COMO INT)
CREATE TABLE IF NOT EXISTS `Comanda`
(
    `idComanda`
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    `dataHoraAbertura`
    DATETIME
    NOT
    NULL,
    `dataHoraFechamento`
    DATETIME
    NULL,
    `desconto`
    DOUBLE
    NULL,
    `valorTotal`
    DOUBLE
    NOT
    NULL,
    `fkCliente`
    CHAR
(
    36
) NULL,
    `fkFuncionario` CHAR
(
    36
) NOT NULL,
    `status` VARCHAR
(
    45
) NOT NULL,
    PRIMARY KEY
(
    `idComanda`
),
    INDEX `fkCliente_idx`
(
    `fkCliente`
    ASC
),
    INDEX `fkFuncionario_idx`
(
    `fkFuncionario`
    ASC
),
    CONSTRAINT `fkCliente_Comanda`
    FOREIGN KEY
(
    `fkCliente`
)
    REFERENCES `Cliente`
(
    `idCliente`
)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fkFuncionario_Comanda`
    FOREIGN KEY
(
    `fkFuncionario`
)
    REFERENCES `Funcionario`
(
    `idFuncionario`
)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB;

-- COMANDA PRODUTO (MANTIDO COMO INT)
CREATE TABLE IF NOT EXISTS `ComandaProduto`
(
    `idComandaProduto`
    INT
    NOT
    NULL,
    `fkProduto`
    INT
    NOT
    NULL,
    `fkComanda`
    INT
    NOT
    NULL,
    `qtdProduto`
    INT
    NOT
    NULL,
    `valorUnitario`
    DOUBLE
    NOT
    NULL,
    PRIMARY
    KEY
(
    `idComandaProduto`,
    `fkProduto`,
    `fkComanda`
),
    INDEX `fkProduto_idx`
(
    `fkProduto`
    ASC
),
    INDEX `fkComanda_idx`
(
    `fkComanda`
    ASC
),
    CONSTRAINT `fkProduto`
    FOREIGN KEY
(
    `fkProduto`
)
    REFERENCES `Produto`
(
    `idProduto`
)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fkComanda`
    FOREIGN KEY
(
    `fkComanda`
)
    REFERENCES `Comanda`
(
    `idComanda`
)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ENGINE = InnoDB;
