-- Criando empreendedores
insert into empreendedor
(nome, cpf, nascimento)
values
('Jose', '386.218.240-19', '1990-09-09'),
('Maria', '052.664.570-90', '1998-09-10'),
('Julieta', '663.162.930-62', '1987-12-15'),
('Vinicius', '084.248.010-24', '1993-12-25'),
('Romeu', '731.167.850-15', '1991-11-12'),
('Antonio', '986.537.110-31', '1964-02-29');

-- Criando empresas
insert into empresa
(login, senha, autenticado, nome_fantasia, razao_social, cnpj, cep, uf, cidade, logradouro, ponto_referencia, qtd_produtos_vendidos, total_produtos_vendidos)
values
('nestor', 'nestor123', false, 'Loja Nestor', 'Loja Nestor', '55756157000133', '05857425', 'SP', 'Sao Paulo', 'Av Brasil', 'Perto da quadra', 0, 0);
