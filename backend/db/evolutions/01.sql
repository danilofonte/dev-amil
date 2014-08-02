# --!Ups

CREATE TABLE IF NOT EXISTS arma (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  vl_max_municao bigint(20) DEFAULT NULL,
  tx_nome varchar(255) DEFAULT NULL,
  tp_tipo_arma int(11) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS historico_partida (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dt_acao datetime DEFAULT NULL,
  tp_acao int(11) DEFAULT NULL,
  id_arma bigint(20) DEFAULT NULL,
  id_jogador_executou_acao bigint(20) DEFAULT NULL,
  id_jogador_recebeu_acao bigint(20) DEFAULT NULL,
  id_partida bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_id_partida (id_partida),
  KEY fk_id_jogador_executou_acao (id_jogador_executou_acao),
  KEY fk_id_jogador_recebeu_acao (id_jogador_recebeu_acao),
  KEY fk_id_arma (id_arma)
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
  id_u_partida bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE historico_partida
  ADD CONSTRAINT fk_id_arma FOREIGN KEY (id_arma) REFERENCES arma (id),
  ADD CONSTRAINT fk_id_partida FOREIGN KEY (id_partida) REFERENCES partida (id),
  ADD CONSTRAINT fk_id_jogador_executou_acao FOREIGN KEY (id_jogador_executou_acao) REFERENCES jogador (id),
  ADD CONSTRAINT fk_id_jogador_recebeu_acao FOREIGN KEY (id_jogador_recebeu_acao) REFERENCES jogador (id);
  
# --!Downs

DROP TABLE IF EXISTS historico_partida;
DROP TABLE IF EXISTS jogador;
DROP TABLE IF EXISTS partida;
DROP TABLE IF EXISTS arma;