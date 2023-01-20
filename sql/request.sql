


// list produit b enchere indrindra

create view produitBeEnchere as
SELECT produit as nom, COUNT(produit) AS nombre FROM encherecategory GROUP BY produit ORDER BY nombre DESC LIMIT 3;

// list category b enchere indrindra

create view categoryBeEnchere as
SELECT category_nom as nom, COUNT(category_nom) AS nombre FROM encherecategory GROUP BY category_nom ORDER BY nombre DESC LIMIT 3;

// list produit b mise indrindra

create view produitBeMise as
SELECT produit as nom, COUNT(produit) AS nombre FROM miseencherecategory GROUP BY produit ORDER BY nombre DESC LIMIT 3;

// list category b mise indrindra

create view categoryBeMise as
SELECT category_nom as nom, COUNT(category_nom) AS nombre FROM miseencherecategory GROUP BY category_nom ORDER BY nombre DESC LIMIT 3;
