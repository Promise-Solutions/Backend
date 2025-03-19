CREATE TABLE IF NOT EXISTS usuario (
    id CHAR(36) PRIMARY KEY,
    cpf CHAR(14) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS atendimento (
    id INT PRIMARY KEY,
    categoria VARCHAR(50),
    status VARCHAR(20),
    criado_em TIMESTAMP NOT NULL
);