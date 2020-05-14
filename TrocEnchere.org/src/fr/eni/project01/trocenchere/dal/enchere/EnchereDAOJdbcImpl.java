package fr.eni.project01.trocenchere.dal.enchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet01.trocenchere.bll.UtilisateurManager;
import fr.eni.projet01.trocenchere.bll.VenteManager;
import fr.eni.projet01.trocenchere.bo.Categorie;
import fr.eni.projet01.trocenchere.bo.Enchere;
import fr.eni.projet01.trocenchere.bo.Retrait;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.dal.ConnectionProvider;
import fr.eni.projet01.trocenchere.dal.UtilisateurDAOJdbcImpl;
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
			state.setInt(1, noUtilisateur);
			rs = state.executeQuery();
			rs.next();
			enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
			
			UtilisateurManager um = new UtilisateurManager();
			Utilisateur user = um.selectionnerUtilisateurById(noUtilisateur);
			enchere.setEncherit(user);
			
			VenteManager vm = new VenteManager();
			Vente vente = vm.selectionnerVenteById(rs.getInt("no_vente"));
			enchere.setConcerne(vente);

			enchere.setPoints(rs.getInt("points"));
			
			
			rs.close();	
			
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: id inconnu");
			throw be;
		}
		
		return listeEnchere;
		
	}

	@Override
	public List<Enchere> selectByVenteId(int noVente) throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		Enchere enchere = new Enchere();
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state= cnx.prepareStatement(SELECTBYID_ENCHERE_SQL);){			
			ResultSet rs;
			state.setInt(1, noVente);
			rs = state.executeQuery();
			rs.next();
			enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
			
			UtilisateurManager um = new UtilisateurManager();
			Utilisateur user = um.selectionnerUtilisateurById(rs.getInt("no_acheteur"));
			enchere.setEncherit(user);
			
			VenteManager vm = new VenteManager();
			Vente vente = vm.selectionnerVenteById(noVente);
			enchere.setConcerne(vente);

			enchere.setPoints(rs.getInt("points"));
			
			
			rs.close();	
			
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: id inconnu");
			throw be;
		}
		
		return listeEnchere;
	}

	@Override
	public void insert(Enchere newEnchere) throws BusinessException {
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement state = cnx.prepareStatement(INSERT_ENCHERE_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
			state.setDate(1, java.sql.Date.valueOf(newEnchere.getDateEnchere()));
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
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement state = cnx.prepareStatement(DELETE_ENCHERES_SQL)){
				
				state.setInt(1, noVente);
				state.executeUpdate();
		
			} catch (Exception e) {
				BusinessException be = new BusinessException();
				e.printStackTrace();
				be.ajouterErreur("Erreur: supression impossible car n° de vente inconnu");
				throw be;
			}
		
	}

}
