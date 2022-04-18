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
(nome_fantasia, cnpj, inscricao_estadual, cpf_empresario, qtd_produtos_vendidos, total_produtos_vendidos, login, senha, autenticado)
values
('Loja do Jose', '55756157000133', '616.085.042.411', '386.218.240-19', 0, 0, 'jose', '123456', false),
('Loja da Maria', '28961059000165', '616.085.042.411', '052.664.570-90', 0, 0, 'maria', '123', false),
('Loja da Julieta', '2258575000102', '616.085.042.411', '663.162.930-62', 0, 0, 'julieta', '456', false);
