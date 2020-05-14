package fr.eni.projet01.trocenchere.dal;

import fr.eni.projet01.trocenchere.dal.vente.VenteDAO;
import fr.eni.projet01.trocenchere.dal.vente.VenteDAOJdbcImpl;

public class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
		}
		
		
	public static VenteDAO getVenteDAO() {
		return new VenteDAOJdbcImpl();
		}
		
	}

