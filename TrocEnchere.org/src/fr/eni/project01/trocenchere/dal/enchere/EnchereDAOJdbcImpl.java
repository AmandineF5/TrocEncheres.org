package fr.eni.project01.trocenchere.dal.enchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet01.trocenchere.bo.Categorie;
import fr.eni.projet01.trocenchere.bo.Enchere;
import fr.eni.projet01.trocenchere.bo.Retrait;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.dal.ConnectionProvider;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String INSERT_ENCHERE_SQL = "INSERT INTO encheres(date_enchere, no_acheteur, no_vente, points) VALUES (?,?,?,?)";

	private static final String SELECTBYID_ENCHERE_SQL = "SELECT * FROM encheres INNER JOIN ventes ON encheres.no_vente = ventes.no_vente WHERE ventes.no_vente = ?";
	private static final String SELECTBY_UTILISATEURID_VENTES_SQL = "SELECT * FROM encheres INNER JOIN ventes ON encheres.no_vente = ventes.no_vente WHERE encheres.no_acheteur = ?";
	
	private static final String DELETE_ENCHERES_SQL = "DELETE FROM encheres WHERE no_vente = ?";
	
	
	@Override
	public List<Enchere> selectByUserId(int noUtilisateur) throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		Enchere enchere = new Enchere();
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state= cnx.prepareStatement(SELECTBY_UTILISATEURID_VENTES_SQL);){			
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
		return null;
		
	}

	@Override
	public List<Enchere> selectByVenteId(int noVente) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Enchere newEnchere) throws BusinessException {
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement state = cnx.prepareStatement(INSERT_ENCHERE_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
			state.setDate(1, java.sql.Date.valueOf(newEnchere.getDateEnchere()).toLocalDate);
			Utilisateur user = newEnchere.getEncherit();
			int idUser = user.getNoUtilisateur();
			state.setInt(2, idUser);
			Vente sale = newEnchere.getConcerne();
			int idSale = sale.getNoVente();
			state.setInt(3, idSale);
			
			state.executeUpdate();
			
			state.close();
			
		} catch (Exception e4) {
			e4.printStackTrace();
			be.ajouterErreur("Erreur: impossible de créer l'enchere");
			throw be;
		}
	}
		
	

	@Override
	public void delete(int noVente) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}
