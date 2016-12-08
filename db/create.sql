DROP TABLE IF EXISTS categorie;
DROP TABLE IF EXISTS portion_text;

CREATE TABLE categorie(
  id integer primary key AUTOINCREMENT,
  libelle text NOT NULL,
);

CREATE TABLE portion_text(
  id integer primary key AUTOINCREMENT,
  contenu text NOT NULL,
  mot_cle text,
  id_categorie integer constraint fk_categorie_portion references categorie(id)
);

CREATE TABLE contient(
  
);

INSERT INTO categorie  VALUES ('1',"test 1",'');
INSERT INTO categorie  VALUES ('2',"sous test 1",'1');