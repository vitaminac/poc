CREATE TABLE policia (
  codigo    CHAR(5) PRIMARY KEY,
  nombre    VARCHAR2(20) NOT NULL,
  categoria INT CHECK (categoria BETWEEN 0 AND 30),
  funcion   VARCHAR2(30) CHECK (funcion IN ('administrativo', 'detective', 'PATRULLA')),
  jefe      CHAR(5)      REFERENCES policia ON DELETE SET NULL
);

SELECT *
FROM policia;

CREATE TABLE clasedearma (
  nombre VARCHAR2(20) PRIMARY KEY,
  peso   NUMBER(2, 2)
);

SELECT *
FROM clasedearma;

CREATE TABLE arma (
  codigo     CHAR(5) PRIMARY KEY,
  antiguedad DATE    NOT NULL,
  clase      VARCHAR2(20) REFERENCES clasedearma,
  policia    CHAR(5) REFERENCES policia ON DELETE SET NULL
);

SELECT *
FROM arma;

CREATE TABLE delicuente (
  dni         CHAR(20) PRIMARY KEY,
  nombre      VARCHAR2(20),
  apodo       VARCHAR2(15),
  descripcion VARCHAR2(40),
  telefono    NUMBER(9)
);

SELECT *
FROM delicuente;

CREATE TABLE caso (
  codigo      CHAR(5) PRIMARY KEY,
  descripcion VARCHAR2(25) NOT NULL,
  juzgado     INT CHECK (juzgado BETWEEN 1 AND 15),
  estadp      CHAR(7) CHECK (estadp IN ('abierto', 'cerrado'))
);

SELECT *
FROM caso;

CREATE TABLE involucrado (
  caso       CHAR(5) REFERENCES caso ON DELETE CASCADE,
  delicuente CHAR(20) REFERENCES delicuente ON DELETE CASCADE,
  cargo      VARCHAR2(20) NOT NULL,
  CONSTRAINT clavep_inv PRIMARY KEY (caso, delicuente)
);

SELECT *
FROM involucrado;

CREATE TABLE investiga (
  caso    CHAR(5) REFERENCES caso ON DELETE CASCADE,
  policia CHAR(5) REFERENCES policia ON DELETE CASCADE,
  PRIMARY KEY (caso, policia)
);

SELECT *
FROM investiga;

CREATE TABLE arresto (
  policia    CHAR(5) REFERENCES policia ON DELETE CASCADE,
  delicuente CHAR(20) REFERENCES delicuente ON DELETE CASCADE,
  caso       CHAR(5) REFERENCES caso ON DELETE CASCADE,
  PRIMARY KEY (policia, delicuente, caso)
);

SELECT *
FROM arresto;

CREATE TABLE plan (
  cod_paln             CHAR(20) PRIMARY KEY,
  nombre               CHAR(10),
  entidad_financiadora CHAR(20)
);

SELECT *
FROM plan;

CREATE TABLE proyecto (
  cod_proyecto CHAR(20) PRIMARY KEY,
  cod_plan     CHAR(20) REFERENCES plan,
  nombre       CHAR(50),
  fecha_inicio DATE,
  feche_fin    DATE,
  presupuesto  INTEGER
);

SELECT *
FROM proyecto;

CREATE TABLE investigador (
  dni_investigador CHAR(9) PRIMARY KEY,
  nombre           CHAR(50),
  ciudad           CHAR(15),
  telefono         CHAR(12)
);

SELECT *
FROM investigador;

CREATE TABLE asignado_a (
  cod_proyecto       CHAR(20) REFERENCES proyecto,
  dni_investigador   CHAR(9) REFERENCES investigador,
  fecha_inicio       DATE,
  fecha_fin          DATE,
  tipo_participacion CHAR(20)
);

SELECT *
FROM asignado_a;