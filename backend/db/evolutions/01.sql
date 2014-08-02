# --!Ups

CREATE TABLE IF NOT EXISTS arma (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  vl_max_municao bigint(20) DEFAULT NULL,
  tx_nome varchar(255) DEFAULT NULL,
  tp_tipo_arma int(11) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS historicopartida (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dt_acao datetime DEFAULT NULL,
  tp_acao int(11) DEFAULT NULL,
  id_arma bigint(20) DEFAULT NULL,
  id_jogador_executor_acao bigint(20) DEFAULT NULL,
  id_jogador_alvo_acao bigint(20) DEFAULT NULL,
  id_partida bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_id_partida (id_partida),
  KEY fk_id_jogador_executor_acao (id_jogador_executor_acao),
  KEY fk_id_arma (id_arma),
  KEY fk_id_jogador_alvo_acao (id_jogador_alvo_acao)
);

CREATE TABLE IF NOT EXISTS jogador (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  tx_nome varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS partida (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dt_fim datetime DEFAULT NULL,
  dt_inicio datetime DEFAULT NULL,
  idt_u_partida bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS partida_historicopartida (
  id_partida bigint(20) NOT NULL,
  historico_id bigint(20) NOT NULL,
  UNIQUE KEY historico_id (historico_id),
  KEY id_historico (historico_id),
  KEY id_partida (id_partida)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS partida_jogador (
  id_partida bigint(20) NOT NULL,
  id_jogador bigint(20) NOT NULL,
  UNIQUE KEY id_jogador (id_jogador),
  KEY id_jogador (id_jogador),
  KEY id_partida (id_partida)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS play_evolutions (
  id int(11) NOT NULL,
  hash varchar(255) NOT NULL,
  applied_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  apply_script text,
  revert_script text,
  state varchar(255) DEFAULT NULL,
  last_problem text,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE historicopartida
  ADD CONSTRAINT fk_id_jogador_alvo_acao FOREIGN KEY (id_jogador_alvo_acao) REFERENCES jogador (id),
  ADD CONSTRAINT fk_id_partida FOREIGN KEY (id_partida) REFERENCES partida (id),
  ADD CONSTRAINT fk_id_jogador_executor_acao FOREIGN KEY (id_jogador_executor_acao) REFERENCES jogador (id),
  ADD CONSTRAINT fk_id_arma FOREIGN KEY (id_arma) REFERENCES arma (id);

ALTER TABLE partida_historicopartida
  ADD CONSTRAINT id_partida FOREIGN KEY (id_partida) REFERENCES partida (id),
  ADD CONSTRAINT id_historico FOREIGN KEY (historico_id) REFERENCES historicopartida (id);

ALTER TABLE partida_jogador
  ADD CONSTRAINT fk_id_partida FOREIGN KEY (id_partida) REFERENCES partida (id),
  ADD CONSTRAINT fk_id_jogador FOREIGN KEY (id_jogador) REFERENCES jogador (id);
  
# --!Downs