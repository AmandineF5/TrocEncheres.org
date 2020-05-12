package fr.eni.projet01.trocenchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projet01.trocenchere.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	private static final String INSERT_USER_SQL = "INSERT INTO utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal,"+
													"ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SELECTBYID_USER_SQL = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";
	
	private static final String UPDATE_USER_SQL = "UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?,telephone=?,rue=?,code_postal=?"+
													",ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur = ?";
			
	private static final String DELETE_USER_SQL = "DELETE FROM utilisateurs WHERE no_utilisateur=?";
	
	
	@Override
	public void insertUser(Utilisateur user) {
		try {
			Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
			//pool Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement state;
			ResultSet rs;
			//préparer le statement
			state = cnx.prepareStatement(INSERT_USER_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
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
			//executer le statement
			state.executeUpdate();
			//Récupère la clé générée
			rs = state.getGeneratedKeys();
			//on se balade ds le resultSet pr récupérer la clé
			if (rs.next()) {
				user.setNoUtilisateur(rs.getInt(1));
			}
			rs.close();
			state.close();
			cnx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			//cnx.rollback();
		}
	
	
	}

	@Override
	public Utilisateur selectByIdUser(int noUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int noUtilisateur) {
		// TODO Auto-generated method stub
		
	}

}
