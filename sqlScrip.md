CREATE DATABASE atividade;

CREATE SCHEMA cadastro;

CREATE TABLE cadastro.cadastropessoa
(
  cpf character varying(11) NOT NULL,
  nome character varying(120),
  peso double precision,
  uf character varying(2),
  data_nascimento date,
  CONSTRAINT cadastropessoa_pkey PRIMARY KEY (cpf)
);

CREATE TABLE cadastro.pessoa
(
  cpf numeric(11,0) NOT NULL,
  data_nascimento date,
  nome character varying(150) NOT NULL,
  peso double precision,
  uf character(2) NOT NULL,
  CONSTRAINT pessoa_pkey PRIMARY KEY (cpf)
);
