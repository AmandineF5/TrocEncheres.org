--INSERT INTO `categories` (`no_categorie`, `libelle`) VALUES ('121', 'Jouet'), ('135', 'Electroménager');--
INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'Mr Patate', 'Hendricks', 'Jimmy', 'j.hendricks@rock.com', NULL, '25 rue des mecs cool', '35000', 'Rennes', 'jaimelafrite', '0', b'0');
INSERT INTO `utilisateurs` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES (NULL, 'Kiss cool', 'De Niro', 'Robert', 'rdn@hotmail.com', NULL, '4 rue des clodos', '75000', 'Paris', 'trucmuch', '0', b'0');
INSERT INTO `ventes` (`no_vente`, `nomarticle`, `description`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`) VALUES (NULL, 'Buzz l\'éclair', 'figurine de toy story', '2020-05-15', '2.00', '3.00', '1', '10'), (NULL, 'Gauffrier', 'gauffrier Tefal, neuf', '2020-05-11', '10.50', '15.00', '2', '3');
INSERT INTO `retraits` (`no_vente`, `rue`, `code_postal`, `ville`) VALUES ('1', '25 rue des mecs cool', '35000', 'Rennes'), ('2', '4 rue des clodos', '75000', 'Paris');
INSERT INTO `encheres` (`date_enchere`, `no_utilisateur`, `no_vente`) VALUES ('2020-05-15', '1', '1'), ('2020-05-14', '2', '2');