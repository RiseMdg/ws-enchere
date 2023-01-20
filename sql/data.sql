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
insert into admins(email)values('rojo.rabenanahary@gmail.fr.com');

create table category
(
    id serial not null primary key,
    nom varchar(50) 
);
insert into category(nom)values('Technologies'),('artisanaux'),('Alimentation'),('Beaute'),('Sante');





create table users
(
    id serial not null primary key,
    username varchar(50),
    email varchar(50),
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
    descriptions varchar(1000),
    category_id int not null,
    user_id int not null,
    foreign key(category_id) references category(id),
    foreign key(user_id) references users(id) 
);

insert into enchere(datedebut,datefin,produit,prixminimal,descriptions,category_id,user_id)
values
('12-04-2022 12:00:03','31-06-2022 12:00:00','ordinateur','Asus corei5, nouvelle generation, 16G de ram,120htz',1,1),
('05-09-2022 03:00:03','20-10-2022 12:00:00','armoire','meuble fermé, généralement en bois(palisandre), toujours adossé à un mur',2,2),
('16-09-2022 14:20:03','23-12-2022 18:00:00','Farilac','Riche en calcium et en fer, c''est un vrai délice pour tous les petits. Il les aide dans leur croissance',3,3),
('22-12-2022 14:59:00','29-01-2023 18:00:00','Chemise','couleur:gris',4,4),
('11-11-2022 06:20:03','25-02-2023 18:00:00','Sireau','100 cl,disponibles pour tous les bebes.Il les aide dans leur croissance',5,5);


create table mise
(
    id serial not null primary key,
    prixmise double precision,
    user_id int not null,
    foreign key(user_id) references users(id)
);
insert into mise (prixmise,user_id)values('200000',1)('250000',2)('1000000',3),('600000',4),('400000',5);