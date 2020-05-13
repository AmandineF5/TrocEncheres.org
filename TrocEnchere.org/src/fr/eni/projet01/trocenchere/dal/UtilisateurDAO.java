package fr.eni.projet01.trocenchere.dal;



import java.sql.SQLException;

import fr.eni.projet01.trocenchere.bo.Utilisateur;

public interface UtilisateurDAO {
	public void insertUser (Utilisateur utilisateur);
	public Utilisateur selectByIdUser (int noUtilisateur);
	public void updateUser (Utilisateur utilisateur);	
	public void deleteUser (int noUtilisateur);
	public Utilisateur verificationConnectionComptePseudo (String pseudo, String mdp);
	public Utilisateur selectByPseudo (String pseudo);
	
}
