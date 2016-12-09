DROP TABLE IF EXISTS categorie;
DROP TABLE IF EXISTS portion_text;

CREATE TABLE categorie(
  id integer primary key AUTOINCREMENT,
  libelle text NOT NULL,
  id_categorie_parent integer constraint fk_categorie_parent references categorie(id)
);

CREATE TABLE portion_text(
  id integer primary key AUTOINCREMENT,
  contenu text NOT NULL,
  mot_cle text,
  id_categorie integer constraint fk_categorie_portion references categorie(id)
);


INSERT INTO categorie  VALUES ('1','root','');
INSERT INTO categorie  VALUES ('2','fils 1','1');
INSERT INTO categorie  VALUES ('3','fils 2','1');
INSERT INTO categorie  VALUES ('4','petit fils 1','2');
INSERT INTO categorie  VALUES ('5','petit fils 2','2');