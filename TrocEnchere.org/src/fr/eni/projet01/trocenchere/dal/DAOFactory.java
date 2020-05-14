package fr.eni.projet01.trocenchere.dal;

import fr.eni.projet01.trocenchere.dal.vente.VenteDAO;

public class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
		
		
	public static VenteDAO getVenteDAO() {
		return new VenteDAOJdbcImpl();
	}
		
	}
}
