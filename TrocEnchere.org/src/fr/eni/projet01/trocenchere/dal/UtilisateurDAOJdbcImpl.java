package fr.eni.projet01.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet01.trocenchere.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	private static final String INSERT_USER_SQL = "INSERT INTO utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal,"+
													"ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SELECTBYID_USER_SQL = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";
	private static final String SELECT_BY_PSEUDO_SQL = "SELECT * FROM utilisateurs WHERE pseudo = ?";
	
	private static final String UPDATE_USER_SQL = "UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?,telephone=?,rue=?,code_postal=?"+
													",ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur = ?";
			
	private static final String DELETE_USER_SQL = "DELETE FROM utilisateurs WHERE no_utilisateur=?";
	
	private static final String VERIF_PSEUDO = "SELECT * FROM utilisateurs WHERE pseudo = ? and mot_de_passe = ?";

 	public void insertUser(Utilisateur user) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Utilisateur selectByIdUser(int noUtilisateur) {
		
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public Utilisateur selectByPseudo(String pseudoId) {
		
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public void updateUser(Utilisateur user) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	@Override 
	public void deleteUser(int noUtilisateur) {
	
			try (Connection cnx = ConnectionProvider.getConnection();
					//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
					PreparedStatement state = cnx.prepareStatement(DELETE_USER_SQL)){
				state.setInt(1, noUtilisateur);
				state.executeUpdate();
//				test sans pool de connection
//				cnx.commit();
//				fr.eni.projet01.trocenchere.dal.Connection.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
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
	            utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
	            utilisateur.setEmail(pseudo);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return utilisateur;
	}


}
