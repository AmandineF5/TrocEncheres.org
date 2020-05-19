
// LESLIE

package fr.eni.projet01.trocenchere.bll;

import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public class TestBll {

	public static void main(String[] args) throws BusinessException {
		UtilisateurManager testBll = new UtilisateurManager();
		Utilisateur user = new Utilisateur("Titi3", "Thierry", "ThierryT", "Thierry2@Thierry3.com", "06666666666", "8 rue Thierry", "03258", "Vichy", "0123", 0, false);
		
		testBll.ajouterUtilisateur(user);
		
		Utilisateur user2 = testBll.selectionnerUtilisateurById(4);
		System.out.println(user2);
		
		Utilisateur user3 = testBll.selectionnerUtilisateurByPseudo("Titi3");
		System.out.println(user3);
		
		user3.setCodePostal("66666");
		testBll.mettreAJourUtilisateur(user3);
		
		testBll.supprimerUtilisateurr(4);
		
		
	
	}

}
