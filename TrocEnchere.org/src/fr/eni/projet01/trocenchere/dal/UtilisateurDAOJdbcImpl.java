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
		try(Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state = cnx.prepareStatement(INSERT_USER_SQL);)
		{
			ResultSet rs;
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Utilisateur selectByIdUser(int noUtilisateur) {
		
		Utilisateur user = new Utilisateur();
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement state;
			ResultSet rs;
			//préparer le statement
			state = cnx.prepareStatement(SELECTBYID_USER_SQL);
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
			state.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(Utilisateur user) {
		Connection cnx;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement state;
			//préparer le statement
			state = cnx.prepareStatement(UPDATE_USER_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
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
			state.close();
			cnx.commit();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override 
	public void deleteUser(int noUtilisateur) {
	
			try {
				Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement state;
				state = cnx.prepareStatement(DELETE_USER_SQL);
				state.setInt(1, noUtilisateur);
				state.executeUpdate();
				state.close();
				cnx.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

}
