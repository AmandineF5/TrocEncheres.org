
// JANET ET LESLIE

package fr.eni.projet01.trocenchere.bll;

import java.sql.SQLException;

import javax.servlet.ServletException;

import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.dal.DAOFactory;
import fr.eni.projet01.trocenchere.dal.UtilisateurDAO;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public class UtilisateurManager {
	private UtilisateurDAO DaoUser;
	private Utilisateur utilisateur;
	
	public UtilisateurManager() {
		this.DaoUser = DAOFactory.getUtilisateurDAO();
	}

	/**
	 * Proc�dure pour ins�rer l'utilisateur dans le base de donn�e lors d'une cr�ation de compte.
	 * (ID 1004)
	 * @param utilisateur
	 * @throws BusinessException 
	 */
	public void ajouterUtilisateur(Utilisateur utilisateur) throws BusinessException {
		DaoUser.insertUser(utilisateur);
	}
	
	/**
	 * S�lectionner un utilisation par son id permet d'afficher un profil
	 * (ID 1007)
	 * @param noUtilisateur
	 * @return un utilisateur
	 * @throws BusinessException 
	 */
	public Utilisateur selectionnerUtilisateurById(int noUtilisateur) throws BusinessException {
		Utilisateur utilisateur;
		utilisateur = DaoUser.selectByIdUser(noUtilisateur);
		return utilisateur;
	}
	
	/**
	 * S�lectionner un utilisation par son pseudo permet d'afficher un profil
	 * (ID 1007)
	 * @param String pseudo
	 * @return un utilisateur
	 * @throws BusinessException 
	 */
	public Utilisateur selectionnerUtilisateurByPseudo(String pseudo) throws BusinessException {
		Utilisateur utilisateur;
		utilisateur = DaoUser.selectByPseudo(pseudo);
		return utilisateur;
	}
	
	/**
	 * Mettre � jour le profil d'un utilisateur pour modifier tous les param�tres.
	 * (ID 1009 et 1003)
	 * @param utilisateur
	 * @throws BusinessException 
	 */
	public void mettreAJourUtilisateur(Utilisateur utilisateur) throws BusinessException {
		DaoUser.updateUser(utilisateur);
	}
	
	public boolean verificationConnectionCompte (String pseudo, String mdp) throws SQLException, ClassNotFoundException, BusinessException{
	    boolean loginOK = false;
	  Utilisateur ut = DaoUser.verificationConnectionComptePseudo(pseudo, mdp);

	if (ut != null) {
	   this.setUtilisateur(ut);
	   loginOK = true;
	}
	    return loginOK;

	}

	public void setUtilisateur(Utilisateur ut) {
	    utilisateur = ut;
	}
	
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}
	
	/**ITERATION 2 A enlever si on s'arr�te au 1!!!!!!!
	 * Permet de supprimer un compte. Utilisation par l'admin et l'utilisateur.
	 * (ID 1005 et 3001
	 * @param noUtilisateur
	 * @throws BusinessException 
	 */
	public void supprimerUtilisateurr(int noUtilisateur) throws BusinessException {
		DaoUser.deleteUser(noUtilisateur);
	}
	
}
