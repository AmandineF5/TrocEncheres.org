package fr.eni.projet01.trocenchere.dal;



import java.sql.SQLException;

import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public interface UtilisateurDAO {
	public void insertUser (Utilisateur utilisateur) throws BusinessException;
	public Utilisateur selectByIdUser (int noUtilisateur) throws BusinessException;
	public void updateUser (Utilisateur utilisateur) throws BusinessException;	
	public void deleteUser (int noUtilisateur) throws BusinessException;
	public Utilisateur verificationConnectionComptePseudo (String pseudo, String mdp) throws BusinessException;
	public Utilisateur selectByPseudo (String pseudo) throws BusinessException;
	
}
