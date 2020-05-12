package fr.eni.projet01.trocenchere.dal;

import fr.eni.projet01.trocenchere.bo.Utilisateur;

public interface UtilisateurDAO {
	public void insertUser (Utilisateur utilisateur);
	public Utilisateur selectByIdUser (int noUtilisateur);
	public void updateUser (Utilisateur utilisateur);
	public void deleteUser (int noUtilisateur);
}
