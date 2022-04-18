

DROP TABLE IF EXISTS empreendedor;
CREATE TABLE empreendedor (
    idEmpreendedor INT IDENTITY PRIMARY KEY,
    fkEmpresa INT FOREIGN Key
    nome VARCHAR(45),
    cpf VARCHAR(11),
    nascimento DATE)

DROP TABLE IF EXISTS empresa;
CREATE TABLE empresa (
    idEmpresa INT IDENTITY PRIMARY KEY,
    login VARCHAR(45),
    senha VARCHAR(45),
    qtdProdutosVendidos INT,
    totalProdutosVendidos INT,
    autenticado BIT)

DROP TABLE IF EXISTS produto;
CREATE TABLE produto (
    codigo INT IDENTITY PRIMARY KEY,
    -- cnpj VARCHAR(14),
    nome VARCHAR(45),
    marca VARCHAR(45),
    categoria VARCHAR(45),
    peso DOUBLE,
    precoCompra DOUBLE,
    precoVenda DOUBLE,
    estoqueInicial INT,
    estoqueMin INT,
    estoqueMax INT,
    qtdVendidos INT,
    valorVendidos DOUBLE)