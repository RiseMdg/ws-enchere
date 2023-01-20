BEGIN
update users set solde = solde - 100.00
where id = 1;
update users set solde = solde + 100.00
where id = 2;
COMMIT;


BEGIN
update users set solde = solde - ?
where id = ?;
update users set solde = solde + ?
where id = ?;
COMMIT;


BEGIN update users set solde = solde - ? where id = ?; update users set solde = solde + ? where id = ?; COMMIT;







