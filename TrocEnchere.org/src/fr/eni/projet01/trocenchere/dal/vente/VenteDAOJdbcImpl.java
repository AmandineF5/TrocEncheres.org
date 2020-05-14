package fr.eni.projet01.trocenchere.dal.vente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.dal.ConnectionProvider;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public class VenteDAOJdbcImpl implements VenteDAO {
	
	private static final String INSERT_VENTES_SQL = "INSERT INTO ventes(nomarticle, description, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?)\r\n";
	private static final String INSERT_RETRAITS_SQL = "INSERT INTO retraits(no_vente, rue, code_postal, ville) VALUES (?,?,?,?)";

	private static final String SELECTBYID_VENTES_SQL = "SELECT * FROM ventes JOIN retraits ON ventes.no_vente = retraits.no_vente WHERE no_vente = ?";
	private static final String SELECTALL_VENTES_SQL = "SELECT * FROM ventes JOIN retraits ON ventes.no_vente = retraits.no_vente WHERE no_utilisateur = ?";
	
	private static final String SEARCH_BY_KEYWORD_SQL = "SELECT * FROM ventes JOIN retraits ON ventes.no_vente = retraits.no_vente WHERE nomarticle LIKE ? OR description LIKE ?";
	private static final String SEARCH_BY_CATEGORY_SQL = "SELECT * FROM ventes JOIN retraits ON ventes.no_vente = retraits.no_vente WHERE libelle = ?";
	
	private static final String DELETE_VENTES_SQL = "DELETE FROM ventes WHERE no_vente = ? \r\n";
	private static final String DELETE_RETRAITS_SQL = "DELETE FROM retraits WHERE no_vente = ?";
		

	@Override
	public void insert(Vente Vente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vente selectById(int noVente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vente> selectAll(int noUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vente> searchByKeyWord(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vente> searchByCatagory(String libelle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int noVente) throws BusinessException {
		
		try (Connection cnx = ConnectionProvider.getConnection();
			//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
			PreparedStatement state1 = cnx.prepareStatement(DELETE_VENTES_SQL);
			PreparedStatement state2 = cnx.prepareStatement(DELETE_RETRAITS_SQL)){
			state1.setInt(1, noVente);
			state2.setInt(1, noVente);
			state1.executeUpdate();
			state2.executeUpdate();
	//		test sans pool de connection
	//		cnx.commit();
	//		fr.eni.projet01.trocenchere.dal.Connection.closeConnection();
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: supression ompossible car n° de vente inconnu");
			throw be;
		}
	}
	
	
	

}
