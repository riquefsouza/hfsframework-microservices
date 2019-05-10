--------------- H2 ---------------
drop table if exists adm_parametro_categoria;
CREATE TABLE adm_parametro_categoria (
	pmc_seq integer NOT NULL,
	pmc_descricao varchar(100) NOT NULL,
	pmc_ordem DECIMAL(20, 2) NULL,
	CONSTRAINT adm_parametro_categoria_pkey PRIMARY KEY (pmc_seq),
	CONSTRAINT adm_parametro_categoria_un UNIQUE (pmc_descricao)
);

drop table if exists adm_parametro;
CREATE TABLE adm_parametro (
	par_seq integer NOT NULL,
	par_valor varchar(4000) NULL,
	par_descricao varchar(255) NOT NULL,
	par_codigo varchar(255) NOT NULL,
	par_pmc_seq integer NOT NULL,
	CONSTRAINT adm_parametro_pkey PRIMARY KEY (par_seq),
	CONSTRAINT adm_parametro_un UNIQUE (par_descricao),
	CONSTRAINT uk_adm_parametro UNIQUE (par_codigo),
	CONSTRAINT adm_parametro_par_pmc_seq_fkey FOREIGN KEY (par_pmc_seq) REFERENCES adm_parametro_categoria(pmc_seq)
);

