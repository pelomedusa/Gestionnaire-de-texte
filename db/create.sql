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

INSERT INTO portion_text VALUES
  ('1', 'je suis le texte1 de fils1', 'text1, fils1','2'),
  ('2', 'je suis le texte2 de fils1', 'text2, fils1','2'),
  ('3', 'je suis le texte de root', 'text, root','1'),
  ('4', 'je suis le texte1 de petit fils 1', 'text1, petit fils1','4'),
  ('5', 'je suis le texte2 de petit fils 1', 'text2, petit fils1','4'),
  ('6', 'je suis le texte de petit fils 2', 'text, petit fils2','5');
