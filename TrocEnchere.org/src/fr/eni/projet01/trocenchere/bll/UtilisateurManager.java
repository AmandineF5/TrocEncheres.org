package fr.eni.projet01.trocenchere.bll;

import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.dal.DAOFactory;
import fr.eni.projet01.trocenchere.dal.UtilisateurDAO;

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
	 */
	public void ajouterUtilisateur(Utilisateur utilisateur) {
		DaoUser.insertUser(utilisateur);
	}
	
	/**
	 * S�lectionner un utilisation par son id permet d'afficher un profil
	 * (ID 1007)
	 * @param noUtilisateur
	 * @return un utilisateur
	 */
	public Utilisateur selectionnerUtilisateur(int noUtilisateur) {
		Utilisateur utilisateur;
		user = DaoUser.selectByIdUser(noUtilisateur);
		return utilisateur;
	}
	
	/**
	 * Mettre � jour le profil d'un utilisateur pour modifier tous les param�tres.
	 * (ID 1009 et 1003)
	 * @param utilisateur
	 */
	public void mettreAJourUtilisateur(Utilisateur utilisateur) {
		DaoUser.updateUser(utilisateur);
	}
	
	public boolean verificationConnectionCompte (String email, String mdp){
	    boolean loginOK = false;
	  try {
	            Utilisateur ut = UtilisateurDAO.verificationConnectionCompte(email, mdp);

	            if (ut != null) {
	               this.setUtilisateur(ut);
	               loginOK = true;
	            } 


	        } catch (SQLException | ClassNotFoundException ex) {
	            throw new ServletException(ex);
	        }
	    return loginOK;

	}

	public setUtilisateur(Utilisateur ut) {
	    utilisateur = ut;
	}
	
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}
	
	/**ITERATION 2 A enlever si on s'arr�te au 1!!!!!!!
	 * Permet de supprimer un compte. Utilisation par l'admin et l'utilisateur.
	 * (ID 1005 et 3001
	 * @param noUtilisateur
	 */
	public void supprimerUtilisateurr(int noUtilisateur) {
		DaoUser.deleteUser(noUtilisateur);
	}
	
}
