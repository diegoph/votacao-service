SET timezone TO 'UTC';

CREATE TYPE voto_enum AS ENUM('SIM', 'NAO');
CREATE TYPE sessao_status_enum AS ENUM('ABERTA', 'FECHADA');

CREATE TABLE pauta (
  id BIGSERIAL NOT NULL,
  titulo VARCHAR(50) NOT NULL,
  descricao TEXT NOT NULL,
  CONSTRAINT pk_pauta PRIMARY KEY (id));

CREATE TABLE sessao (
  id BIGSERIAL NOT NULL,
  id_pauta BIGINT NOT NULL,
  status sessao_status_enum NOT NULL,
  data_abertura TIMESTAMPTZ NOT NULL,
  data_fechamento TIMESTAMPTZ NOT NULL,
  CONSTRAINT pk_sessao PRIMARY KEY (id),
  CONSTRAINT fk_pauta FOREIGN KEY (id_pauta) REFERENCES pauta (id));

CREATE TABLE voto (
  id BIGSERIAL NOT NULL,
  id_sessao BIGINT NOT NULL,
  id_associado BIGINT NOT NULL,
  voto voto_enum NOT NULL,
  CONSTRAINT fk_sessao FOREIGN KEY (id_sessao) REFERENCES sessao (id));

CREATE UNIQUE INDEX unique_voto_sessao_associado ON voto (id_sessao, id_associado);