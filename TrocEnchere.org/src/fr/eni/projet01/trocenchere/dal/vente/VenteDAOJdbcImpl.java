package fr.eni.projet01.trocenchere.dal.vente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet01.trocenchere.bo.Categorie;
import fr.eni.projet01.trocenchere.bo.Retrait;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
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
	public List<Vente> searchByKeyWord(String keyWord) throws BusinessException {
		
		List<Vente> listeVente = new ArrayList<Vente>();
		Vente vente = new Vente();
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state= cnx.prepareStatement(SEARCH_BY_KEYWORD_SQL);){			
			ResultSet rs;
			state.setString(1, keyWord);
			rs = state.executeQuery();
			rs.next();
			
			vente.setNoVente(rs.getInt("no_vente"));
			vente.setNomArticle(rs.getString("nomarticle"));
			vente.setDescription(rs.getString("description"));
			vente.setDateFinEncheres(rs.getDate("date_fin_echeres"));  //probleme sur le format LocalDateTime
			vente.setMiseAPrix(rs.getInt("prix_initial"));
			vente.setPrixVente(rs.getInt("prix_vente"));
			
//			vente.setVendeur(rs.getInt("no_utilisateur"));
//			vente.setCategorieArticle(rs.getInt("no_categorie"));
			
			listeVente.add(vente);
			rs.close();
			
			
			
//			test sans pool de connection
//			cnx.commit();
//			fr.eni.projet01.trocenchere.dal.Connection.closeConnection();
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: libellé inconnu");
			throw be;
		}
		return listeVente;
	}

	@Override
	public List<Vente> searchByCatagory(String libelle) throws BusinessException {
		
		List<Vente> listeVente = new ArrayList<Vente>();
		Vente vente = new Vente();
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state= cnx.prepareStatement(SEARCH_BY_CATEGORY_SQL);){			
			ResultSet rs;
			state.setString(1, libelle);
			rs = state.executeQuery();
			rs.next();
			
			vente.setNoVente(rs.getInt("no_vente"));
			vente.setNomArticle(rs.getString("nomarticle"));
			vente.setDescription(rs.getString("description"));
			//vente.setDateFinEncheres(rs.getDate("date_fin_echeres"));  //probleme sur le format LocalDateTime
			vente.setMiseAPrix(rs.getInt("prix_initial"));
			vente.setPrixVente(rs.getInt("prix_vente"));
			
//			vente.setVendeur(rs.getInt("no_utilisateur"));
//			vente.setCategorieArticle(rs.getInt("no_categorie"));
			
			listeVente.add(vente);
			rs.close();
			
//			private int noVente;
//			private String nomArticle;
//			private String description;
//			private LocalDateTime dateFinEncheres;
//			private Integer miseAPrix;
//			private Integer prixVente;
//			private Categorie categorieArticle;
//			private Utilisateur acheteur;
//			private Utilisateur vendeur;
//			private Retrait lieuRetrait;
			
			
			
			
//			test sans pool de connection
//			cnx.commit();
//			fr.eni.projet01.trocenchere.dal.Connection.closeConnection();
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: libellé inconnu");
			throw be;
		}
		return listeVente;
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
