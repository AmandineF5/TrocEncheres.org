package fr.eni.projet01.trocenchere.dal;

import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public class TestDal {

	public static void main(String[] args) throws BusinessException {
		//Utilisateur user = new Utilisateur("Titi3", "Thierry", "ThierryT", "Thierry2@Thierry3.com", "06666666666", "8 rue Thierry", "03258", "Vichy", "0123", 0, false);
		
		UtilisateurDAOJdbcImpl test = new UtilisateurDAOJdbcImpl();
		
		//test.insertUser(user);
		//System.out.println(user);
		
		//Utilisateur user4 = test.selectByIdUser(6);
		//System.out.println(user4);
		//user4.setCodePostal("25654");
		//user4.setNom("Thierry789");
		//user4.setTelephone("0324564666");
		//test.updateUser(user4);
		//System.out.println(user4);
		//test.deleteUser(6);
		
		Utilisateur user3 = test.selectByPseudo("Titi");
		System.out.println(user3);
	}

}
