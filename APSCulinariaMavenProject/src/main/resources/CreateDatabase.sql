DROP TABLE avaliacao CASCADE;
DROP TABLE ingrediente CASCADE;
DROP TABLE usuario CASCADE;
DROP TABLE receita CASCADE;
DROP TABLE ingrediente_receita CASCADE;

CREATE TABLE avaliacao (
                           id_avaliacao  INTEGER NOT NULL AUTO_INCREMENT,
                           id_receita    INTEGER,
                           id_usuario    INTEGER,
                           descricao     VARCHAR2(800),
                           nota          FLOAT
);

CREATE TABLE ingrediente (
                              id_ingrediente    INTEGER NOT NULL AUTO_INCREMENT,
                              nome_ingrediente  VARCHAR2(128)
);

CREATE TABLE usuario (
                         id_usuario              INTEGER NOT NULL AUTO_INCREMENT,
                         cpf                     VARCHAR2(20),
                         nome_usuario            VARCHAR2(50)
);

CREATE TABLE receita (
                         id_receita              INTEGER NOT NULL AUTO_INCREMENT,
                         nome_receita            VARCHAR2(100),
                         tempo_preparo           FLOAT,
                         passos                  VARCHAR2(1000),
                         id_usuario      INTEGER NOT NULL
);

CREATE TABLE ingrediente_receita (
                        id_ingrediente INTEGER NOT NULL,
                        id_receita INTEGER NOT NULL,
                        quantidade VARCHAR2(1000) NOT NULL
);

-- RESTRICOES(PK, FK)

-- PK
ALTER TABLE avaliacao ADD CONSTRAINT avaliacao_pk PRIMARY KEY ( id_avaliacao );

ALTER TABLE ingrediente ADD CONSTRAINT ingredientes_pk PRIMARY KEY ( id_ingrediente );

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id_usuario );

ALTER TABLE receita ADD CONSTRAINT receita_pk PRIMARY KEY ( id_receita );

-- FK
ALTER TABLE avaliacao
    ADD CONSTRAINT avaliacao_usuario_fk FOREIGN KEY ( id_usuario )
        REFERENCES usuario ( id_usuario );

ALTER TABLE avaliacao
    ADD CONSTRAINT avaliacao_receita_fk FOREIGN KEY ( id_receita )
        REFERENCES receita ( id_receita );

ALTER TABLE receita
    ADD CONSTRAINT receita_usuario_fk FOREIGN KEY ( id_usuario )
        REFERENCES usuario ( id_usuario );

ALTER TABLE ingrediente_receita
    ADD CONSTRAINT ingrediente_receita_ingrediente_fk FOREIGN KEY ( id_ingrediente )
        REFERENCES ingrediente ( id_ingrediente );

ALTER TABLE ingrediente_receita
    ADD CONSTRAINT ingrediente_receita_receita_fk FOREIGN KEY ( id_receita )
        REFERENCES receita ( id_receita );

-- INSERINDO DADOS NAS TABELAS

-- INGREDIENTE
INSERT INTO INGREDIENTE(nome_ingrediente)
VALUES
    ('Farinha de Trigo'),
    ('Leite Condensado'),
    ('Fermento');

-- USUARIO
INSERT INTO USUARIO(cpf, nome_usuario)
VALUES
    ('11111111111', 'Pedro'),
    ('11111111111', 'Pedro Henique'),
    ('22222222222', 'Lucas Torres'),
    ('123412312412', 'Lucas Gontijo');


-- RECEITA
INSERT INTO RECEITA(nome_receita, tempo_preparo, passos, id_usuario)
VALUES
    ('Bolo de Chocolate', 55.00, 'passo 1: fazer isso, passo 2: fazer aquilo', 1),
    ('Bolo de Brigadeiro', 45.00, 'passo 1', 1),
    ('Bolo de Morango', 33.00, 'passo 1', 2);

-- AVALIACAO
INSERT INTO AVALIACAO(id_receita, id_usuario, nota, descricao)
VALUES(1, 3, 10.0, 'Receita muito boa'),
      (2, 2, 7.0, 'Receita boa, mas ficou com gosto de queimado'),
      (1, 2, 2.0, 'Receita não funcionou');