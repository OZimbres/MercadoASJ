create table funcionarios(
	cpf_funcionario DECIMAL(11,0) not null,
	nome_funcionario VARCHAR(300) not null,
	telefone_funcionario DECIMAL(11,0) default 0,
	rua_funcionario VARCHAR(300) default '',
	numero_rua_funcionario VARCHAR(10) default '',
	cep_funcionario DECIMAL(8,0) not null,
	senha_funcionario VARCHAR(24) not null,
	nivel_acesso_funcionario VARCHAR(50) not null, --=| 2 níveis de acesso ('operador' e 'gerente') |=--
	primary key(cpf_funcionario)
);
insert into funcionarios(cpf_funcionario, nome_funcionario, cep_funcionario, senha_funcionario, nivel_acesso_funcionario) values(12345678901, 'Geraldo Santos', 14256800, 'senha', 'gerente');
select * from funcionarios;

create table estoque(
	codigo_produto DECIMAL(3,0) not null,
	nome_produto VARCHAR(300) not null,
	descricao_produto VARCHAR(1000) not null,
	nome_fornecedor VARCHAR(300) not null,
	preco_produto DECIMAL(12,2) not null,
	quantidade_produto DECIMAL(6,0) not null,
	desconto_vip_produto DECIMAL(3,3) default 0, --=| desconto em PORCENTAGEM |=--
	status_produto BOOLEAN default FALSE,
	primary key(codigo_produto)
);

create table clientes(
	cpf_cliente DECIMAL(11,0) not null,
	nome_cliente VARCHAR(300) not null,
	telefone_cliente DECIMAL(11,0) default 0,
	rua_cliente VARCHAR(300) default '',
	numero_rua_cliente VARCHAR(10) default '',
	cep_cliente DECIMAL(8,0) default 0,
	primary key(cpf_cliente)
);

create table vendas(
	id_venda SERIAL not null,
	cpf_cliente_venda DECIMAL(11,0) not null,
	codigo_produto_venda DECIMAL(3,0) not null,
	quantidade_produto_venda DECIMAL(6,0) not null,
	preco_venda DECIMAL(12,2) not null,
	metodo_pagamento_venda VARCHAR(100) not null,
	data_venda DATE not null,
	primary key(id_venda),
	constraint fk_cpf_cliente_venda foreign key(cpf_cliente_venda) references clientes(cpf_cliente),
	constraint fk_codigo_produto_venda foreign key(codigo_produto_venda) references estoque(codigo_produto)
);