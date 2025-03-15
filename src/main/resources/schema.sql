CREATE TABLE IF NOT EXISTS users (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS atendimento (
    id INT PRIMARY KEY,
    category VARCHAR(50),
    status VARCHAR(20),
    created_at TIMESTAMP NOT NULL
);