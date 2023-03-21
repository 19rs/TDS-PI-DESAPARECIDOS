-- Grupo: Adriano, Alessandro, Douglas e Viktor Leon

CREATE DATABASE DESAPARECIDOS;

USE DESAPARECIDOS;

CREATE TABLE USUARIO (
id_usuario INT NOT NULL PRIMARY KEY,
nome_completo VARCHAR(255) NOT NULL,
email VARCHAR(100) NOT NULL,
username VARCHAR(80) NOT NULL,
senha VARCHAR(25) NOT NULL
);

CREATE TABLE MENSAGEM (
id_mensagem INT NOT NULL PRIMARY KEY,
autor_id INT NOT NULL,
data DATETIME NOT NULL,
titulo VARCHAR(100) NOT NULL,
conteudo VARCHAR(255) NOT NULL,
FOREIGN KEY (autor_id) REFERENCES USUARIO (id_usuario) ON DELETE CASCADE
);

CREATE TABLE RESPOSTA (
id_resposta INT NOT NULL PRIMARY KEY,
mensagem_id INT,
autor_id INT,
data DATETIME,
conteudo VARCHAR(255),
FOREIGN KEY (mensagem_id) REFERENCES MENSAGEM (id_mensagem) ON DELETE CASCADE
);

CREATE TABLE ENDERECO (
id_endereco INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `Rua` NVARCHAR(45) NOT NULL,
  `Bairro` NVARCHAR(45) NOT NULL,
  `Cidade` NVARCHAR(45) NOT NULL,
  `Estado` CHAR(4) NOT NULL,
  `Info_extra` NVARCHAR(200) NULL DEFAULT 'Sem Informacoes Extras',
  `Pais` CHAR(20) NOT NULL
);

CREATE TABLE PESSOA (
id_pessoa INT NOT NULL PRIMARY KEY,
nome_completo VARCHAR(255),
cpf VARCHAR(80),
data_nasc DATE,
altura_estimada VARCHAR(10),
peso_estimado VARCHAR(10),
caracteristicas_fisicas VARCHAR(255),
informacao_extra VARCHAR(255),
residente_em INT,
FOREIGN KEY (residente_em) REFERENCES ENDERECO (id_endereco) ON DELETE SET NULL
);

CREATE TABLE DESAPARECIMENTO (
id_desaparecimento INT NOT NULL PRIMARY KEY,
pessoa_id INT,
numero_bo VARCHAR(100),
data DATE,
ultimo_local_visto INT,
informacao_extra VARCHAR(255),
FOREIGN KEY (pessoa_id) REFERENCES PESSOA (id_pessoa) ON DELETE CASCADE,
FOREIGN KEY (ultimo_local_visto) REFERENCES ENDERECO (id_endereco) ON DELETE SET NULL
);

CREATE TABLE AVISTADO (
id_avistado INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
desaparecimento_id INT,
`Data` DATE NOT NULL,
`Horario_Aproximado`TIME NOT NULL,
local_avistamento INT,
`Informacoes_Extras` NVARCHAR(200) NULL DEFAULT 'Sem Informacoes Extras',
FOREIGN KEY (desaparecimento_id) REFERENCES DESAPARECIMENTO (id_desaparecimento) ON DELETE CASCADE,
FOREIGN KEY (local_avistamento) REFERENCES ENDERECO (id_endereco) ON DELETE SET NULL
);

CREATE TABLE ENCONTRADO (
id_encontrado INT NOT NULL PRIMARY KEY,
desaparecimento_id INT,
data DATETIME,
local_encontrado INT,
FOREIGN KEY (desaparecimento_id) REFERENCES DESAPARECIMENTO (id_desaparecimento) ON DELETE CASCADE,
FOREIGN KEY (local_encontrado) REFERENCES ENDERECO (id_endereco) ON DELETE SET NULL
);