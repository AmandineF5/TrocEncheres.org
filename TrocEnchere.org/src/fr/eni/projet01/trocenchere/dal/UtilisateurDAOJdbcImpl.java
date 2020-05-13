package fr.eni.projet01.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	private static final String INSERT_USER_SQL = "INSERT INTO utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal,"+
													"ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SELECTBYID_USER_SQL = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";
	private static final String SELECTBYEMAIL_USER_SQL = "SELECT * FROM utilisateurs WHERE email = ?";
	private static final String SELECT_BY_PSEUDO_SQL = "SELECT * FROM utilisateurs WHERE pseudo = ?";
	
	private static final String UPDATE_USER_SQL = "UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?,telephone=?,rue=?,code_postal=?"+
													",ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur = ?";
			
	private static final String DELETE_USER_SQL = "DELETE FROM utilisateurs WHERE no_utilisateur=?";
	
	private static final String VERIF_PSEUDO = "SELECT * FROM utilisateurs WHERE pseudo = ? and mot_de_passe = ?";

 	public void insertUser(Utilisateur user) throws BusinessException {
 		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state = cnx.prepareStatement(INSERT_USER_SQL);){
			state.setString(1, user.getPseudo());
			state.setString(2, user.getNom());
			state.setString(3, user.getPrenom());
			state.setString(4, user.getEmail());
			state.setString(5, user.getTelephone());
			state.setString(6, user.getRue());
			state.setString(7, user.getCodePostal());
			state.setString(8, user.getVille());
			state.setString(9, user.getMotDePasse());
			state.setFloat(10, user.getCredit());
			state.setBoolean(11, user.isAdministrateur());
			state.executeUpdate();
			//test sans pool de connection
			//cnx.commit();
			//fr.eni.projet01.trocenchere.dal.Connection.closeConnection();
			
		} catch (MySQLIntegrityConstraintViolationException e1) {
			e1.printStackTrace();
			String nouvelUtilisateurPseudo = user.getPseudo();
			Utilisateur utilisateurExistantPseudo = this.selectByPseudo(nouvelUtilisateurPseudo);
			String pseudoExistant = utilisateurExistantPseudo.getPseudo();
			String nouvelUtilisateurEmail = user.getEmail();
			Utilisateur utilisateurExistantEmail = this.selectByEmail(nouvelUtilisateurEmail);
			String emailExistant = utilisateurExistantEmail.getEmail();
			if (utilisateurExistantPseudo!=null && nouvelUtilisateurPseudo.equals(pseudoExistant)) {
				be.ajouterErreur("Erreur: Ce pseudo est déjà pris");
			} else if (nouvelUtilisateurEmail!=null && nouvelUtilisateurEmail.equals(emailExistant)){
				be.ajouterErreur("Erreur: Cet email est déjà pris");
			}
			
			throw be;
		} catch (Exception e2) {
			e2.printStackTrace();
			be.ajouterErreur("Erreur: impossible de créer le compte");
			throw be;
		}
	}

	private Utilisateur selectByEmail(String utilisateurEmail) throws BusinessException {
		Utilisateur user = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state= cnx.prepareStatement(SELECTBYEMAIL_USER_SQL);){			
			ResultSet rs;
			state.setString(1, utilisateurEmail);
			rs = state.executeQuery();
			rs.next();
			user.setNoUtilisateur(rs.getInt("no_utilisateur"));
			user.setEmail(rs.getString("email"));
			user.setPseudo(rs.getString("pseudo"));
			user.setNom(rs.getString("nom"));
			user.setPrenom(rs.getString("prenom"));
			user.setTelephone(rs.getString("telephone"));
			user.setRue(rs.getString("rue"));
			user.setCodePostal(rs.getString("code_postal"));
			user.setVille(rs.getString("ville"));
			user.setMotDePasse(rs.getString("mot_de_passe"));
			user.setCredit(rs.getInt("credit"));
			rs.close();	
//			test sans pool de connection
//			cnx.commit();
//			fr.eni.projet01.trocenchere.dal.Connection.closeConnection();
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: email inconnu");
			throw be;
		}
		
		return user;
		
	}

	@Override
	public Utilisateur selectByIdUser(int noUtilisateur) throws BusinessException {
		
		Utilisateur user = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state= cnx.prepareStatement(SELECTBYID_USER_SQL);){			
			ResultSet rs;
			state.setInt(1, noUtilisateur);
			rs = state.executeQuery();
			rs.next();
			user.setNoUtilisateur(noUtilisateur);
			user.setPseudo(rs.getString("pseudo"));
			user.setNom(rs.getString("nom"));
			user.setPrenom(rs.getString("prenom"));
			user.setEmail(rs.getString("email"));
			user.setTelephone(rs.getString("telephone"));
			user.setRue(rs.getString("rue"));
			user.setCodePostal(rs.getString("code_postal"));
			user.setVille(rs.getString("ville"));
			user.setMotDePasse(rs.getString("mot_de_passe"));
			user.setCredit(rs.getInt("credit"));
			rs.close();	
//			test sans pool de connection
//			cnx.commit();
//			fr.eni.projet01.trocenchere.dal.Connection.closeConnection();
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: id inconnu");
			throw be;
		}
		
		return user;
	}

	@Override
	public Utilisateur selectByPseudo(String pseudoId) throws BusinessException {
		
		Utilisateur user = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state= cnx.prepareStatement(SELECT_BY_PSEUDO_SQL);){			
			ResultSet rs;
			state.setString(1, pseudoId);
			rs = state.executeQuery();
			rs.next();
			user.setNoUtilisateur(rs.getInt("no_utilisateur"));
			user.setPseudo(pseudoId);
			user.setNom(rs.getString("nom"));
			user.setPrenom(rs.getString("prenom"));
			user.setEmail(rs.getString("email"));
			user.setTelephone(rs.getString("telephone"));
			user.setRue(rs.getString("rue"));
			user.setCodePostal(rs.getString("code_postal"));
			user.setVille(rs.getString("ville"));
			user.setMotDePasse(rs.getString("mot_de_passe"));
			user.setCredit(rs.getInt("credit"));
			rs.close();
//			test sans pool de connection
//			cnx.commit();
//			fr.eni.projet01.trocenchere.dal.Connection.closeConnection();
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: pseudo inconnu");
			throw be;
		}
		return user;
	}
	@Override
	public void updateUser(Utilisateur user) throws BusinessException {
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state = cnx.prepareStatement(UPDATE_USER_SQL);){
			state.setString(1, user.getPseudo());
			state.setString(2, user.getNom());
			state.setString(3, user.getPrenom());
			state.setString(4, user.getEmail());
			state.setString(5, user.getTelephone());
			state.setString(6, user.getRue());
			state.setString(7, user.getCodePostal());
			state.setString(8, user.getVille());
			state.setString(9, user.getMotDePasse());
			state.setFloat(10, user.getCredit());
			state.setBoolean(11, user.isAdministrateur());
			state.setInt(12, user.getNoUtilisateur());
			state.execute();
//			test sans pool de connection
//			cnx.commit();
//			fr.eni.projet01.trocenchere.dal.Connection.closeConnection();
		}  catch (MySQLIntegrityConstraintViolationException e1) {
			e1.printStackTrace();
			String nouveauPseudo = user.getPseudo();
			Utilisateur utilisateurExistantPseudo = this.selectByPseudo(nouveauPseudo);
			String pseudoExistant = utilisateurExistantPseudo.getPseudo();
			String nouvelEmail = user.getEmail();
			Utilisateur utilisateurExistantEmail = this.selectByEmail(nouvelEmail);
			String emailExistant = utilisateurExistantEmail.getEmail();
			if (utilisateurExistantPseudo!=null && nouveauPseudo.equals(pseudoExistant)) {
				be.ajouterErreur("Erreur: Ce pseudo est déjà pris");
			} else if (utilisateurExistantEmail!=null && nouvelEmail.equals(emailExistant)){
				be.ajouterErreur("Erreur: Cet email est déjà pris");
			}
			
			throw be;
		} catch (Exception e2) {
			e2.printStackTrace();
			be.ajouterErreur("Erreur: impossible de modifier le compte");
			throw be;
		}
	}

	
	@Override 
	public void deleteUser(int noUtilisateur) throws BusinessException {
	
			try (Connection cnx = ConnectionProvider.getConnection();
					//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
					PreparedStatement state = cnx.prepareStatement(DELETE_USER_SQL)){
				state.setInt(1, noUtilisateur);
				state.executeUpdate();
//				test sans pool de connection
//				cnx.commit();
//				fr.eni.projet01.trocenchere.dal.Connection.closeConnection();
			} catch (Exception e) {
				BusinessException be = new BusinessException();
				e.printStackTrace();
				be.ajouterErreur("Erreur: supression ompossible car id inconnu");
				throw be;
			}
			
		
	}

	
	@Override
	public Utilisateur verificationConnectionComptePseudo(String pseudo, String mdp) {
		Utilisateur utilisateur = null;
		
		try (Connection cnx= ConnectionProvider.getConnection();
				PreparedStatement state = cnx.prepareStatement(VERIF_PSEUDO);){
			state.setString(1, pseudo);
	        state.setString(2, mdp);
	        ResultSet rs = state.executeQuery();     
	        if (rs.next()) {
	            utilisateur = new Utilisateur();
	            utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
	            utilisateur.setNom(rs.getString("nom"));
	            utilisateur.setPrenom(rs.getString("prenom"));
	            utilisateur.setEmail(pseudo);
	            utilisateur.setTelephone(rs.getString("telephone"));
	            utilisateur.setRue(rs.getString("rue"));
	            utilisateur.setCodePostal(rs.getString("code_postal"));
	            utilisateur.setVille(rs.getString("ville"));
	            utilisateur.setMotDePasse(mdp);
	            utilisateur.setCredit(rs.getInt("credit"));                     
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return utilisateur;
	}


}
