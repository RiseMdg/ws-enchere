\c postgres

drop database enchere;

create database enchere;

\c enchere;

create table admins
(
    id serial primary key not null,
    email varchar(50) unique,
    password varchar(50) unique
);

insert into admins
(email, password)
VALUES
('admin@gmail.com','adminmdp');

create table category
(
    id serial not null primary key,
    nom varchar(50) 
);

insert into category
(nom)
VALUES
('Voiture');

create table users
(
    id serial not null primary key,
    username varchar(50) unique,
    email varchar(50) unique,
    password varchar(50),
    solde double precision
);

INSERT INTO users
(username, email, password,solde)
VALUES
('Fitia','fitia@gmail.com','fitiamdp',100000),
('Nary','nary@gmail.com','narymdp',100000),
('Amy','amy@gmail.com','amymdp',100000),
('Lahatra','lahatra@gmail.com','lahatramdp',100000),
('Ando','ando@gmail.com','andomdp',100000);

create table enchere
(
    id serial not null primary key,
    datedebut timestamp,
    datefin timestamp,
    produit varchar,
    prixminimal double precision,
    description varchar(255),
    category_id int not null,
    user_id int not null,
    statut int not null,
    foreign key(category_id) references category(id),
    foreign key(user_id) references users(id) 
);

create table images
(
    id serial primary key not null,
    filename varchar(255) not null,
    enchere_id int,
    foreign key(enchere_id) references enchere(id)
);

insert into enchere
(datedebut,datefin,produit,prixminimal,description,category_id,user_id,statut)
VALUES
('2010-10-10','2010-10-10','produit',10000,'description',1,1,0);

create table mise
(
    id serial not null primary key,
    prixmise double precision,
    enchere_id int not null,
    user_id int not null,
    foreign key(user_id) references users(id),
    foreign key(enchere_id) references enchere(id)
);

create table rechargement
(
    id serial primary key,
    montant double precision,
    etat int,
    user_id int,
    foreign key(user_id) references users(id)
);

create view enchereCategory
as
select produit, category.nom as category_nom from enchere
join category on category.id = enchere.category_id;

create view miseEnchereCategory
as
select produit, category.nom as category_nom from mise
join enchere on enchere.id = mise.enchere_id
join category on category.id = enchere.category_id;

-- // list produit b enchere indrindra

create view produitBeEnchere as
SELECT produit as nom, COUNT(produit) AS nombre FROM encherecategory GROUP BY produit ORDER BY nombre DESC LIMIT 3;

-- // list category b enchere indrindra

create view categoryBeEnchere as
SELECT category_nom as nom, COUNT(category_nom) AS nombre FROM encherecategory GROUP BY category_nom ORDER BY nombre DESC LIMIT 3;

-- // list produit b mise indrindra

create view produitBeMise as
SELECT produit as nom, COUNT(produit) AS nombre FROM miseencherecategory GROUP BY produit ORDER BY nombre DESC LIMIT 3;

-- // list category b mise indrindra

create view categoryBeMise as
SELECT category_nom as nom, COUNT(category_nom) AS nombre FROM miseencherecategory GROUP BY category_nom ORDER BY nombre DESC LIMIT 3;


insert into category(nom)values('Technologies'),('artisanaux'),('Alimentation'),('Beaute'),('Sante');

insert into enchere(datedebut,datefin,produit,prixminimal,description,category_id,user_id,statut)
values
('2022-04-12 12:00:03','2022-06-30 12:00:00','ordinateur',1000000,'Asus corei5, nouvelle generation, 16G de ram,120htz',1,1,1),
('2022-09-05 03:00:03','2022-10-20 12:00:00','armoire',1000000,'meuble fermé, généralement en bois(palisandre), toujours adossé à un mur',2,2,0),
('2022-09-16 14:20:03','2022-12-23 18:00:00','Farilac',1000000,'Riche en calcium et en fer, c''est un vrai délice pour tous les petits. Il les aide dans leur croissance',3,3,1),
('2022-12-22 14:59:00','2023-01-29 18:00:00','Chemise',1000000,'couleur:gris',4,4,1),
('2022-11-11 06:20:03','2023-02-25 18:00:00','Sireau',1000000,'100 cl,disponibles pour tous les bebes.Il les aide dans leur croissance',5,5,0);

insert into mise (prixmise,user_id,enchere_id)values('200000',1,1),('250000',2,2),('1000000',3,3),('600000',4,4),('400000',5,5);
insert into mise (prixmise,user_id,enchere_id)values('200000',1,2),('250000',2,3),('1000000',3,4),('600000',4,5),('400000',5,1);
insert into mise (prixmise,user_id,enchere_id)values('200000',1,3),('250000',2,4),('1000000',3,5),('600000',4,1),('400000',5,2);
insert into mise (prixmise,user_id,enchere_id)values('200000',1,4),('250000',2,5),('1000000',3,1),('600000',4,2),('400000',5,3);
insert into mise (prixmise,user_id,enchere_id)values('200000',1,5),('250000',2,1),('1000000',3,2),('600000',4,3),('400000',5,4);