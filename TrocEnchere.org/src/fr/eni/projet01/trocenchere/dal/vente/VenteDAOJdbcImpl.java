package fr.eni.projet01.trocenchere.dal.vente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet01.trocenchere.bll.UtilisateurManager;
import fr.eni.projet01.trocenchere.bo.Categorie;
import fr.eni.projet01.trocenchere.bo.Retrait;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.dal.ConnectionProvider;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public class VenteDAOJdbcImpl implements VenteDAO {
	
	private static final String INSERT_VENTES_SQL = "INSERT INTO ventes(nomarticle, description, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?)\r\n";
	private static final String INSERT_RETRAITS_SQL = "INSERT INTO retraits(no_vente, rue, code_postal, ville) VALUES (?,?,?,?)";

	private static final String SELECTBYID_VENTES_SQL = "SELECT * FROM ventes INNER JOIN retraits ON ventes.no_vente = retraits.no_vente INNER JOIN categories ON categories.no_categorie = ventes.no_categorie WHERE ventes.no_vente = ?";
	private static final String SELECTALL_VENTES_SQL = "SELECT * FROM ventes INNER JOIN retraits ON ventes.no_vente = retraits.no_vente INNER JOIN categories ON categories.no_categorie = ventes.no_categorie WHERE ventes.no_utilisateur = ?";
	private static final String SELECTCATEGORIE_SQL = "SELECT * FROM categories"; 
	
	private static final String SEARCH_BY_KEYWORD_SQL = "SELECT * FROM ventes JOIN retraits ON ventes.no_vente = retraits.no_vente WHERE nomarticle LIKE ? OR description LIKE ?";
	private static final String SEARCH_BY_CATEGORY_SQL = "SELECT * FROM ventes JOIN retraits ON ventes.no_vente = retraits.no_vente WHERE libelle = ?";
	
	private static final String DELETE_VENTES_SQL = "DELETE FROM ventes WHERE no_vente = ? \r\n";
	private static final String DELETE_RETRAITS_SQL = "DELETE FROM retraits WHERE no_vente = ?";
		

	public Vente insert(Vente vente) throws BusinessException {
 		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement state = cnx.prepareStatement(INSERT_VENTES_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
			state.setString(1, vente.getNomArticle());
			state.setString(2, vente.getDescription());
			state.setDate(3, java.sql.Date.valueOf(vente.getDateFinEncheres()));
			state.setInt(4, vente.getMiseAPrix());
			state.setInt(5, vente.getPrixVente());
			
			Utilisateur ut = vente.getAcheteur();
			
			state.setInt(6, ut.getNoUtilisateur());
			
			Categorie cat = vente.getCategorieArticle();
			
			state.setInt(7, cat.getNoCategorie());

			state.executeUpdate();
			ResultSet rs = state.getGeneratedKeys();
			if(rs.next())
			{
				vente.setNoVente(rs.getInt(1));
			}
			rs.close();
			state.close();
			
			state = cnx.prepareStatement(INSERT_RETRAITS_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
			
			Retrait retrait = vente.getLieuRetrait();
			state.setInt(1, vente.getNoVente());
			state.setString(2, retrait.getRue());
			state.setString(3, retrait.getCodePostal());
			state.setString(4, retrait.getRue());
			
			vente.setLieuRetrait(retrait);
			
			state.executeUpdate();
			
			state.close();
			
		} catch (Exception e4) {
			e4.printStackTrace();
			be.ajouterErreur("Erreur: impossible de créer la vente");
			throw be;
		}
		
		return vente;
	}

	public Vente selectById(int noVente) throws BusinessException {
		List<Vente> listeVentes = new ArrayList<Vente>();
		Vente vente = new Vente();
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state= cnx.prepareStatement(SELECTBYID_VENTES_SQL);){			
			ResultSet rs;
			state.setInt(1, noVente);
			rs = state.executeQuery();
			rs.next();
			vente.setNomArticle(rs.getString("nomarticle"));
			vente.setDescription(rs.getString("description"));
			vente.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
			vente.setMiseAPrix(rs.getInt("prix_initial"));
			vente.setPrixVente(rs.getInt("prix_vente"));
			
			Retrait retrait = new Retrait();
			retrait.setRue(rs.getString("rue"));
			retrait.setCodePostal(rs.getString("code_postal"));
			retrait.setVille(rs.getString("ville"));
			vente.setLieuRetrait(retrait);
			
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(rs.getInt("no_categorie"));
			categorie.setLibelle(rs.getString("libelle"));
			vente.setCategorieArticle(categorie);
			
			rs.close();	
//			
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: id inconnu");
			throw be;
		}
		
		return vente;
	}
	
	public List<Vente> selectAll(int noUtilisateur) throws BusinessException {
		List<Vente> listeVentes = new ArrayList<Vente>();
		Vente vente = new Vente();
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state= cnx.prepareStatement(SELECTALL_VENTES_SQL);){			
			ResultSet rs;
			state.setInt(1, noUtilisateur);
			rs = state.executeQuery();
			rs.next();
			vente.setNomArticle(rs.getString("nomarticle"));
			vente.setDescription(rs.getString("description"));
			vente.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
			vente.setMiseAPrix(rs.getInt("prix_initial"));
			vente.setPrixVente(rs.getInt("prix_vente"));
			
			Retrait retrait = new Retrait();
			retrait.setRue(rs.getString("rue"));
			retrait.setCodePostal(rs.getString("code_postal"));
			retrait.setVille(rs.getString("ville"));
			vente.setLieuRetrait(retrait);
			
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(rs.getInt("no_categorie"));
			categorie.setLibelle(rs.getString("libelle"));
			vente.setCategorieArticle(categorie);
			
			//listeVentes.add(vente); ???
			
			rs.close();	
			
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: id inconnu");
			throw be;
		}
		
		return listeVentes;
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
			vente.setDateFinEncheres(rs.getDate("date_fin_echeres").toLocalDate());
			vente.setMiseAPrix(rs.getInt("prix_initial"));
			vente.setPrixVente(rs.getInt("prix_vente"));
			
			UtilisateurManager UM = new UtilisateurManager();
			Utilisateur utilisateur = UM.selectionnerUtilisateurById(rs.getInt("no_utilisateur"));
			vente.setVendeur(utilisateur);
			
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(rs.getInt("no_categorie"));
			categorie.setLibelle(rs.getString("libelle"));
			vente.setCategorieArticle(categorie);
			
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
			vente.setDateFinEncheres(rs.getDate("date_fin_echeres").toLocalDate());
			vente.setMiseAPrix(rs.getInt("prix_initial"));
			vente.setPrixVente(rs.getInt("prix_vente"));
			
			UtilisateurManager UM = new UtilisateurManager();
			Utilisateur utilisateur = UM.selectionnerUtilisateurById(rs.getInt("no_utilisateur"));
			vente.setVendeur(utilisateur);
			
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
