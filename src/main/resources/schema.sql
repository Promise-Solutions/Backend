CREATE DATABASE studiozero DEFAULT CHARACTER SET utf8mb4;

USE studiozero;

-- FUNCIONÁRIO
CREATE TABLE funcionario (
                             id_funcionario BINARY(16) NOT NULL PRIMARY KEY,
                             nome VARCHAR(45) NOT NULL,
                             email VARCHAR(45) NOT NULL,
                             contato VARCHAR(15) NOT NULL,
                             cpf VARCHAR(14) NOT NULL,
                             senha VARCHAR(45) NOT NULL,
                             token VARCHAR(45) NOT NULL,
                             ativo BOOLEAN NOT NULL
);

-- CLIENTE
CREATE TABLE cliente (
                         id_cliente BINARY(16) NOT NULL PRIMARY KEY,
                         nome VARCHAR(45) NOT NULL,
                         cpf CHAR(14) NOT NULL,
                         email VARCHAR(45) NOT NULL,
                         contato CHAR(15) NOT NULL,
                         tipo_cliente VARCHAR(45) NOT NULL,
                         ativo BOOLEAN NOT NULL
);

-- SERVIÇO OU PACOTES
CREATE TABLE servico_ou_pacotes (
                                    id_servico BINARY(16) NOT NULL PRIMARY KEY,
                                    titulo VARCHAR(42) NOT NULL,
                                    fk_cliente BINARY(16) NOT NULL,
                                    valor_total FLOAT NOT NULL,
                                    categoria VARCHAR(42) NOT NULL,
                                    status VARCHAR(42) NOT NULL,
                                    tipo_servico VARCHAR(42) NOT NULL,
                                    INDEX fk_cliente_idx (fk_cliente),
                                    FOREIGN KEY (fk_cliente) REFERENCES cliente (id_cliente)
);

-- TAREFA
CREATE TABLE tarefa (
                        id_tarefa BINARY(16) NOT NULL PRIMARY KEY,
                        titulo VARCHAR(45) NOT NULL,
                        descricao VARCHAR(250) NOT NULL,
                        data_inicio DATE,
                        data_limite DATE,
                        fk_funcionario BINARY(16),
                        status VARCHAR(45) NOT NULL,
                        INDEX fk_funcionario_idx (fk_funcionario),
                        FOREIGN KEY (fk_funcionario) REFERENCES funcionario (id_funcionario)
);

-- SUBSERVIÇO
CREATE TABLE sub_servico (
                             id_sub_servico BINARY(16) NOT NULL,
                             titulo_sub_servico VARCHAR(45) NOT NULL,
                             descricao_sub_servico VARCHAR(250),
                             valor_sub_servico DOUBLE NOT NULL,
                             data DATE NOT NULL,
                             hora_inicio TIME NOT NULL,
                             hora_fim TIME,
                             status VARCHAR(42) NOT NULL,
                             fk_servico BINARY(16) NOT NULL,
                             PRIMARY KEY (id_sub_servico, fk_servico),
                             INDEX fk_servico_idx (fk_servico),
                             FOREIGN KEY (fk_servico) REFERENCES servico_ou_pacotes (id_servico)
);

-- PRODUTO
CREATE TABLE produto (
                         id_produto INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         nome_produto VARCHAR(45) NOT NULL,
                         qtd_produto INT NOT NULL,
                         valor_unitario FLOAT NOT NULL
);

-- COMANDA
CREATE TABLE comanda (
                         id_comanda INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         data_hora_abertura DATETIME NOT NULL,
                         data_hora_fechamento DATETIME,
                         desconto DOUBLE,
                         valor_total DOUBLE NOT NULL,
                         fk_cliente BINARY(16),
                         fk_funcionario BINARY(16) NOT NULL,
                         status VARCHAR(45) NOT NULL,
                         INDEX fk_cliente_idx (fk_cliente),
                         INDEX fk_funcionario_idx (fk_funcionario),
                         FOREIGN KEY (fk_cliente) REFERENCES cliente (id_cliente),
                         FOREIGN KEY (fk_funcionario) REFERENCES funcionario (id_funcionario)
);

-- COMANDA PRODUTO
CREATE TABLE comanda_produto (
                                 id_comanda_produto INT NOT NULL,
                                 fk_produto INT NOT NULL,
                                 fk_comanda INT NOT NULL,
                                 qtd_produto INT NOT NULL,
                                 valor_unitario DOUBLE NOT NULL,
                                 PRIMARY KEY (
                                              id_comanda_produto,
                                              fk_produto,
                                              fk_comanda
                                     ),
                                 INDEX fk_produto_idx (fk_produto),
                                 INDEX fk_comanda_idx (fk_comanda),
                                 FOREIGN KEY (fk_produto) REFERENCES produto (id_produto),
                                 FOREIGN KEY (fk_comanda) REFERENCES comanda (id_comanda)
);