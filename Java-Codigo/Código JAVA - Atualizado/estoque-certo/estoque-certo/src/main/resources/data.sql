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
(nome_fantasia, cnpj, inscricao_estadual, cpf_empresario, qtd_produtos_vendidos, total_produtos_vendidos, email, senha)
values
('Loja do Jose', '55756157000133', '616.085.042.411', '386.218.240-19', 0, 0, 'a@a.com', '123456'),
('Loja da Maria', '28961059000165', '616.085.042.411', '052.664.570-90', 0, 0, 'b@c.com', '123456'),
('Loja da Julieta', '2258575000102', '616.085.042.411', '663.162.930-62', 0, 0, 'c@c.com', '123456');

-- Criando produto
--insert into produto_alimento
--(cnpj, nome, preco, qtd_estoque, qtd_vendidos, valor_vendidos, marca, peso, categoria, tipo_alimento, data_fabricacao, data_validade)
--values
  --  ('55756157000133', 'arroz', 10.0, 5, 0, 0, 'camil', 10, 'alimento', 'perecivel', '2021-04-11', '2022-09-11'),
    --('55756157000133', 'feijao', 15.0, 15, 0, 0, 'camil', 10, 'alimento', 'perecivel', '2021-01-11', '2022-09-11'),
    --('55756157000133', 'sal', 20.0, 5, 0, 0, 'gosto', 10, 'alimento', 'perecivel', '2021-03-11', '2022-09-11');
