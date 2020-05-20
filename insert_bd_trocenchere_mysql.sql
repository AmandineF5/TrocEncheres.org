/* SUPPRESSION */
DELETE FROM `utilisateurs`;
DELETE FROM `ventes`;
DELETE FROM `retraits`;
DELETE FROM `encheres`;

/* /!\ PENSER A BIEN REINITIALISER L'AUTO-INCREMENT DE LA TABLE ! */
INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'amande', 'Foulet', 'Amandine', 'amande.foul@hotmail.com', '0601020304', '25 rue des fous', '35380', 'Maxent', 'amande', '500', b'0');
INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'coco', 'Villiermet', 'Corentin', 'coco.vivi@b.com', '0606060606', 'Chez Wam', '35200', 'Rennes-Alma', 'coco', '10000', b'0');
INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'jaja', 'Bembnister', 'Janet', 'jaja.bemb@gmal.com', '0708090605', '3 place de la Constitution', '35000', 'Rennes-Cleunay', 'jaja', '500', b'0');
INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'lili', 'Bileckot', 'Leslie', 'les.bileck@youpi.fr', '0609040302', '18 rue du Pommier', '35000', 'Rennes-Brequigny', 'lili', '0', b'0');
INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'Mr Patate', 'Hendricks', 'Jimmy', 'j.hendricks@rock.com', '0647389235', '25 rue des mecs cool', '35000', 'Rennes-Centre', 'jaimelafrite', '100', b'0');
INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'Kiss cool', 'De Niro', 'Robert', 'rdn@hotmail.com', '0764928465', '4 rue des clodos', '75000', 'Paris', 'trucmuch', '10', b'0');


INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'Buzz l\'éclair', 'figurine de toy story', '2020-05-15', '2.00', '3.00', '1', '10'), (NULL, 'Gauffrier', 'gauffrier Tefal, neuf', '2020-05-11', '10.50', '15.00', '2', '3');

INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('1', '25 rue des mecs cool', '35000', 'Rennes'), ('2', '4 rue des clodos', '75000', 'Paris');

INSERT INTO `encheres` (`date_enchere`, `no_acheteur`, `no_vente`) VALUES ('2020-05-15', '1', '1'), ('2020-05-14', '2', '2');