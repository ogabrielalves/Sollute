-- drop all tables
drop table if exists Empresa;
drop table if exists Produto;
drop table if exists Caixa;
drop table if exists Cliente;
drop table if exists Funcionario;
drop table if exists Fornecedor;

-- creating all tables separately
CREATE TABLE Empresa (
  id_empresa INT IDENTITY,
  email VARCHAR(45) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  nome_fantasia VARCHAR(45) NOT NULL,
  razao_social VARCHAR(45) NOT NULL,
  cnpj CHAR(14) NOT NULL,
  qtd_produtos_vendidos INT NOT NULL,
  total_produtos_vendidos DOUBLE PRECISION NOT NULL,
  logradouro VARCHAR(45) NOT NULL,
  cep CHAR(8) NOT NULL,
  uf CHAR(2) NOT NULL,
  cidade VARCHAR(45) NOT NULL,
  ponto_referencia VARCHAR(45),
  autenticado BIT NOT NULL,
  PRIMARY KEY (id_empresa)
);

CREATE TABLE Produto (
  id_produto INT IDENTITY,
  fk_empresa INT NOT NULL,
  codigo VARCHAR(20) NOT NULL,
  nome VARCHAR(45) NOT NULL,
  marca VARCHAR(45) NOT NULL,
  categoria VARCHAR(45) NOT NULL,
  tamanho VARCHAR(2) NOT NULL,
  peso DOUBLE PRECISION NOT NULL,
  preco_compra DOUBLE PRECISION NOT NULL,
  preco_venda DOUBLE PRECISION NOT NULL,
  estoque INT NOT NULL,
  estoque_min INT NOT NULL,
  estoque_max INT NOT NULL,
  qtd_vendidos INT NOT NULL,
  valor_vendidos DOUBLE PRECISION NOT NULL,
  PRIMARY KEY (id_produto)
);

CREATE TABLE Caixa (
  id_caixa INT IDENTITY,
  fk_empresa INT NOT NULL,
  valor DOUBLE PRECISION NOT NULL,
  qtd_entradas INT NOT NULL,
  qtd_saidas INT NOT NULL,
  valor_entradas DOUBLE PRECISION NOT NULL,
  valor_saidas DOUBLE PRECISION NOT NULL,
  PRIMARY KEY (id_caixa)
);

CREATE TABLE Carrinho (
  id_carrinho INT IDENTITY,
  fk_empresa INT NOT NULL,
  fk_produto INT NOT NULL,
  qtd_venda INT NOT NULL,
  valor_venda DOUBLE PRECISION NOT NULL
  PRIMARY KEY (id_carrinho)
);

CREATE TABLE Cliente (
  id_cliente INT NOT NULL,
  fk_empresa INT NOT NULL,
  nome_cliente VARCHAR(45) NOT NULL,
  telefone_cliente CHAR(11) NOT NULL,
  logradouro VARCHAR(45) NOT NULL,
  cep CHAR(8) NOT NULL,
  uf CHAR(2) NOT NULL,
  cidade VARCHAR(45) NOT NULL,
  ponto_referencia VARCHAR(45),
  PRIMARY KEY (id_cliente)
);

CREATE TABLE Funcionario (
  id_funcionario INT IDENTITY,
  fk_empresa INT NOT NULL,
  nome_funcionario VARCHAR(45) NOT NULL,
  cpf_funcionario CHAR(11) NOT NULL,
  telefone_funcionario CHAR(11) NOT NULL,
  salario_funcionario DOUBLE PRECISION NOT NULL,
  logradouro VARCHAR(45) NOT NULL,
  cep CHAR(8) NOT NULL,
  uf CHAR(2) NOT NULL,
  cidade VARCHAR(45) NOT NULL,
  ponto_referencia VARCHAR(45),
  PRIMARY KEY (id_funcionario)
);

CREATE TABLE Fornecedor (
  id_fornecedor INT IDENTITY,
  fk_empresa INT NOT NULL,
  nome_fornecedor VARCHAR(45) NOT NULL,
  telefone_fornecedor CHAR(11) NOT NULL,
  nome_produto VARCHAR(45) NOT NULL,
  qtd_fornecidas INT NOT NULL,
  logradouro VARCHAR(45) NOT NULL,
  cep CHAR(8) NOT NULL,
  uf CHAR(2) NOT NULL,
  cidade VARCHAR(45) NOT NULL,
  ponto_referencia VARCHAR(45),
  PRIMARY KEY (id_fornecedor)
);

-- referencing all foreign keys
Alter Table Produto Add Foreign Key (fk_empresa) References Empresa (id_empresa);
Alter table Caixa add Foreign Key (fk_empresa) References Empresa (id_empresa);
ALTER TABLE Carrinho ADD FOREIGN KEY (fk_produto) REFERENCES Produto (id_produto);
ALTER TABLE Carrinho ADD FOREIGN KEY (fk_empresa) REFERENCES Empresa (id_empresa);
ALTER TABLE Fornecedor ADD FOREIGN KEY (fk_empresa) REFERENCES Empresa (id_empresa);
ALTER TABLE Cliente ADD FOREIGN KEY (fk_empresa) REFERENCES Empresa (id_empresa);
ALTER TABLE Funcionario ADD FOREIGN KEY (fk_empresa) REFERENCES Empresa (id_empresa);

-- Testing the relationship between classes
select * from Empresa as e, Produto as p where p.fk_empresa = e.id_empresa;

-- Inserting data into tables
insert into Empresa (email, senha, nome_fantasia, razao_social, cnpj, qtd_produtos_vendidos, total_produtos_vendidos, autenticado, logradouro, cep, uf, cidade, ponto_referencia)
	values ('sollute@gmail.com', 'sollute123' , 'Sollute SA', '42297081000183', 15, 15.0, 'false',
'Rua Haddock Lobo 595', '01414-001', 'SP', 'São Paulo', 'Digital School');

insert into Produto (fk_empresa, codigo, nome, marca, categoria, tamanho, peso, preco_compra, preco_venda, estoque, estoque_min, estoque_max, qtd_vendidos, valor_vendidos)
	values (1, '001', 'BIG SWOOSH', 'Nike', 'Corta-vento', 'G', 1.0, 150.0, 200.0, 50, 0, 100, 1, 999.0);













