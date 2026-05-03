CREATE TABLE cep_logs (
    id UUID PRIMARY KEY,
    cep VARCHAR(8) NOT NULL,
    logradouro VARCHAR(255),
    bairro VARCHAR(255),
    localidade VARCHAR(100),
    uf VARCHAR(2),
    data_consulta TIMESTAMP NOT NULL
);