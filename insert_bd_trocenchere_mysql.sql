/* SUPPRESSION */
DELETE FROM `utilisateurs`;
DELETE FROM `ventes`;
DELETE FROM `retraits`;
DELETE FROM `encheres`;

/* /!\ PENSER A BIEN REINITIALISER L'AUTO-INCREMENT DES TABLES QUI EN USENT (valeur à 1) ! */

INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'didine', 'Foulet', 'Amandine', 'amande.foul@hotmail.com', '0601020304', '25 rue des fous', '35380', 'Maxent', 'didine', '500', b'0');
INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'coco', 'Villiermet', 'Corentin', 'coco.vivi@b.com', '0606060606', 'Chez Wam', '35200', 'Rennes-Alma', 'coco', '10000', b'0');
INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'jaja', 'Bembnister', 'Janet', 'jaja.bemb@gmal.com', '0708090605', '3 place de la Constitution', '35000', 'Rennes-Cleunay', 'jaja', '500', b'0');
INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'lili', 'Bileckot', 'Leslie', 'les.bileck@youpi.fr', '0609040302', '18 rue du Pommier', '35200', 'Rennes-Brequigny', 'lili', '0', b'0');
INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'Mr Patate', 'Hendricks', 'Jimmy', 'j.hendricks@rock.com', '0647389235', '25 rue des mecs cool', '35000', 'Rennes-Centre', 'jaimelafrite', '100', b'0');
INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'Kiss cool', 'De Niro', 'Robert', 'rdn@hotmail.com', '0764928465', '4 rue des clodos', '75000', 'Paris', 'trucmuch', '10', b'0');


INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'Jouet pour chien', 'balle qui fait du bruit', '2020-05-25', '2.00', '3.00', '2', '1'), (NULL, 'Os à ronger', 'os à moelle pour le dessert', '2020-05-26', '5', '15', '4', '1'), (NULL, 'Niche', 'niche en bois, isolation et chauffage', '2020-05-11', '50', '80', '3', '1');
INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'Aspirateur', 'Miele 5000 avec turbo-aspiro', '2020-05-25', '100', '120', '2', '2'), (NULL, 'balais', 'Nimbus 3000', '2020-05-09', '3', '4', '6', '2'), (NULL, 'pèse-personne électronnique', 'une balance qui pèse dans le game', '2021-05-11', '500', '500', '3', '2');
INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'marteau', 'bec de corbin', '2020-05-25', '10', '10', '5', '3'), (NULL, 'piqueur', 'good vibes only', '2020-05-25', '15', '15', '5', '3'), (NULL, 'pelle', 'toi tu creuses !', '2020-05-11', '5', '80', '1', '3');
INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'table apéro ikéa', 'petite table de 60x60cm rempli d air poloanis', '2020-05-27', '8', '3.00', '4', '4'), (NULL, 'armoire normande', 'armoire traditionnelle, 6 étages, très bon état', '2020-06-26', '800', '800', '4', '4'), (NULL, 'lampe de bureau', 'avec pied double pivot façon pixar', '2020-05-11', '50', '50', '2', '4');
INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'plante verte', 'fougère en pot', '2020-05-25', '20', '30', '3', '5'), (NULL, 'cadre de tableau', 'couleur dorée, bon état global, un coin écorné', '2020-05-26', '5', '15', '5', '5'), (NULL, 'longue vue', 'anciennement en service sur un navire, HS car lentilles manquante, belle décoration pour résidence bord de mer', '2020-07-14', '150', '150', '4', '5');
INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'chateau légo', 'chateau spacieux, bien aéré, bonne exposition sud sud-ouest, très beau volume, disponible de suite', '2020-05-25', '12', '13', '5', '6'), (NULL, 'dragon playmobil', 'avec flamme qui sort de la gueule et oeuf', '2020-11-11', '5', '15', '1', '6'), (NULL, 'doudou', 'très peu servi, pas de morve collé', '2020-05-11', '1', '1', '2', '6');
INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'harry potter et le prisonier d Azkaban gallimard deluxe', 'comme neuf, très dur à trouver', '2020-05-25', '5000', '5000', '4', '7'), (NULL, 'Oro', 'de Cizia Zykë, état moyen', '2020-04-12', '5', '5', '2', '7'), (NULL, 'apprendre PL-SQL', 'édition ENI, je troc car plus besoin', '2020-05-29', '30', '30', '3', '7');
INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'turbulette', 'duvet pour bébé, très bon état, motif bob l eponge', '2020-05-25', '12', '13', '5', '8'), (NULL, 'poussette', '4 roues motices anti-dérapages', '2020-09-26', '158', '158', '1', '8'), (NULL, 'livre musical', 'l histoire de la musique, 1 touche avec 1 son pour chaque style majeur du deedjeridoo à Kaaris en pasant par Mozart, mauvais état', '2020-05-11', '1', '1', '3', '8');
INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'pc gamer', 'de 1995 avec le premier Tomb Raider installé', '2020-05-25', '200', '300', '6', '9'), (NULL, 'imprimante', 'elle fonctionne du tonnerre !', '2020-05-26', '25', '35', '4', '9'), (NULL, 'souris', 'verte', '2020-05-11', '5', '8', '5', '9');
INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'iPhone Serge', 'écran pété, troc pour pièces', '2020-05-25', '10', '30', '2', '10'), (NULL, 'carte SIM', 'Itineris avec tout plein de crédit à l ancienne', '2020-12-26', '50', '60', '4', '10'), (NULL, 'chargeur', 'triple USB !', '2020-05-15', '50', '80', '4', '10');
INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'PS4', 'mais sans manette !', '2020-05-30', '200', '300', '1', '11'), (NULL, 'controleur', 'sans fil, avec LED', '2020-05-27', '50', '50', '4', '11'), (NULL, 'chifoumi numérique', 'affronte l ordi pour mieux supporter le confinement', '2020-05-11', '5', '5', '2', '11');
INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'mountain bike', 'marque décathlon, 128,7 vitesses, jantes chromées et petits néons de kéké', '2020-05-25', '250', '300', '2', '12'), (NULL, 'dérailleur', 'péché à l aimant dans la Vilaine, très bon état à part la rouille', '2020-03-26', '15', '15', '4', '12'), (NULL, 'mono-cycle', 'vélo à une roue, pour clowns et troubadours en tout genre, dédicacé par M Pinder !', '2020-05-11', '150', '200', '3', '12');
INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'chapeau melon', 'un seul objet ! pas deux...', '2020-05-25', '20', '30', '1', '13'), (NULL, 'bottes de cuir', 'cuir ! cuir-moustache !, marque Hermès', '2020-05-26', '5000', '5000', '1', '13'), (NULL, 'gilet', 'pare-balles, Avi...', '2020-05-11', '50', '80', '6', '13');


INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('1', 'Chez Wam', '35200', 'Rennes-Alma');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('2', '18 rue du Pommier', '35200', 'Rennes-Brequigny');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('3', '3 place de la Constitution', '35000', 'Rennes-Cleunay');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('4', '18 rue du Pommier', '35200', 'Rennes-Brequigny');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('5', '4 rue des clodos', '75000', 'Paris');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('6', '3 place de la Constitution', '35000', 'Rennes-Cleunay');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('7', '25 rue des mecs cool', '35000', 'Rennes'); 
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('8', '25 rue des mecs cool', '35000', 'Rennes');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('9', '25 rue des fous', '35380', 'Maxent');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('10', '18 rue du Pommier', '35200', 'Rennes-Brequigny');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('11', '18 rue du Pommier', '35200', 'Rennes-Brequigny');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('12', 'Chez Wam', '35200', 'Rennes-Alma');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('13', '3 place de la Constitution', '35000', 'Rennes-Cleunay');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('14', '25 rue des mecs cool', '35000', 'Rennes');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('15', '18 rue du Pommier', '35200', 'Rennes-Brequigny');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('16', '25 rue des mecs cool', '35000', 'Rennes');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('17', '25 rue des fous', '35380', 'Maxent');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('18', 'Chez Wam', '35200', 'Rennes-Alma');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('19', '18 rue du Pommier', '35200', 'Rennes-Brequigny');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('20', 'Chez Wam', '35200', 'Rennes-Alma');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('21', '3 place de la Constitution', '35000', 'Rennes-Cleunay');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('22', '25 rue des mecs cool', '35000', 'Rennes');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('23', '25 rue des fous', '35380', 'Maxent');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('24', '3 place de la Constitution', '35000', 'Rennes-Cleunay');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('25', '4 rue des clodos', '75000', 'Paris');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('26', '18 rue du Pommier', '35200', 'Rennes-Brequigny');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('27', '25 rue des mecs cool', '35000', 'Rennes');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('28', 'Chez Wam', '35200', 'Rennes-Alma');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('29', '18 rue du Pommier', '35200', 'Rennes-Brequigny');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('30', '18 rue du Pommier', '35200', 'Rennes-Brequigny');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('31', '25 rue des fous', '35380', 'Maxent');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('32', '18 rue du Pommier', '35200', 'Rennes-Brequigny');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('33', 'Chez Wam', '35200', 'Rennes-Alma');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('34', 'Chez Wam', '35200', 'Rennes-Alma');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('35', '18 rue du Pommier', '35200', 'Rennes-Brequigny');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('36', '3 place de la Constitution', '35000', 'Rennes-Cleunay');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('37', '25 rue des fous', '35380', 'Maxent');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('38', '25 rue des fous', '35380', 'Maxent');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('39', '4 rue des clodos', '75000', 'Paris');


INSERT INTO `encheres` (`date_enchere`, `no_acheteur`, `no_vente`) VALUES ('2020-05-15', '1', '1');
INSERT INTO `encheres` (`date_enchere`, `no_acheteur`, `no_vente`) VALUES ('2020-05-14', '2', '2');