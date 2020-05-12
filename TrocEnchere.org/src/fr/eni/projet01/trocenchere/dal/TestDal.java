package fr.eni.projet01.trocenchere.dal;

import fr.eni.projet01.trocenchere.bo.Utilisateur;

public class TestDal {

	public static void main(String[] args) {
		Utilisateur user = new Utilisateur("Titi", "Thierry", "ThierryT", "Thierry@Thierry.com", "025455455", "8 rue Thierry", "03258", "Vichy", "0123", 0, false);
		
		UtilisateurDAOJdbcImpl test = new UtilisateurDAOJdbcImpl();
		
		test.insertUser(user);
	}

}
