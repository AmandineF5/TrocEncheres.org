package fr.eni.projet01.trocenchere.dal;

import fr.eni.projet01.trocenchere.dal.enchere.EnchereDAO;
import fr.eni.projet01.trocenchere.dal.enchere.EnchereDAOJdbcImpl;
import fr.eni.projet01.trocenchere.dal.vente.VenteDAO;
import fr.eni.projet01.trocenchere.dal.vente.VenteDAOJdbcImpl;

public class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
		}	
		
	public static VenteDAO getVenteDAO() {
		return new VenteDAOJdbcImpl();
		}
	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
		}
		
	}

