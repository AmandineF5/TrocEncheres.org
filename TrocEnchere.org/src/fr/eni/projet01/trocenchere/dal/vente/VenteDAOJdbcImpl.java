
// CREE PAR AMANDINE ET CORENTIN, RETOUCHE PAR TOUT LE MONDE

package fr.eni.projet01.trocenchere.dal.vente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

//fait par Corentin et Amandine
//modifié par Leslie, Janet
public class VenteDAOJdbcImpl implements VenteDAO {
	
	private static final String INSERT_VENTES_SQL = "INSERT INTO ventes(nomarticle, description, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, publiee, nomImage) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_RETRAITS_SQL = "INSERT INTO retraits(no_vente, rue, code_postal, ville) VALUES (?,?,?,?)";

	private static final String SELECTBYID_VENTES_PUBLIEES_SQL = "SELECT * FROM ventes INNER JOIN retraits ON ventes.no_vente = retraits.no_vente INNER JOIN categories ON categories.no_categorie = ventes.no_categorie INNER JOIN utilisateurs ON utilisateurs.no_utilisateur = ventes.no_utilisateur WHERE ventes.no_vente = ? AND ventes.publiee=1";
	private static final String SELECTALL_VENTES_PUBLIEES_SQL = "SELECT * FROM ventes INNER JOIN retraits ON ventes.no_vente = retraits.no_vente INNER JOIN categories ON categories.no_categorie = ventes.no_categorie INNER JOIN utilisateurs ON utilisateurs.no_utilisateur = ventes.no_utilisateur WHERE ventes.no_utilisateur = ? AND ventes.publiee=1";
	
	private static final String SELECTBYID_VENTES_NONPUBLIEES_SQL = "SELECT * FROM ventes INNER JOIN retraits ON ventes.no_vente = retraits.no_vente INNER JOIN categories ON categories.no_categorie = ventes.no_categorie INNER JOIN utilisateurs ON utilisateurs.no_utilisateur = ventes.no_utilisateur WHERE ventes.no_vente = ? AND ventes.publiee=0";
	private static final String SELECTALL_VENTES_NONPUBLIEES_SQL = "SELECT * FROM ventes INNER JOIN retraits ON ventes.no_vente = retraits.no_vente INNER JOIN categories ON categories.no_categorie = ventes.no_categorie INNER JOIN utilisateurs ON utilisateurs.no_utilisateur = ventes.no_utilisateur WHERE ventes.no_utilisateur = ? AND ventes.publiee=0";
	
	private static final String SEARCH_BY_KEYWORD_SQL = "SELECT * FROM ventes INNER JOIN retraits ON ventes.no_vente = retraits.no_vente INNER JOIN categories ON categories.no_categorie = ventes.no_categorie INNER JOIN utilisateurs ON utilisateurs.no_utilisateur = ventes.no_utilisateur WHERE nomarticle LIKE ? OR description LIKE ? AND ventes.publiee=1";
	private static final String SEARCH_BY_CATEGORY_SQL = "SELECT * FROM ventes INNER JOIN retraits ON ventes.no_vente = retraits.no_vente INNER JOIN categories ON categories.no_categorie = ventes.no_categorie INNER JOIN utilisateurs ON utilisateurs.no_utilisateur = ventes.no_utilisateur WHERE ventes.no_categorie = ? AND ventes.publiee=1";
	
	private static final String DELETE_VENTES_SQL = "DELETE FROM ventes WHERE no_vente = ?";
	private static final String DELETE_RETRAITS_SQL = "DELETE FROM retraits WHERE no_vente = ?";
	
	private static final String SELECTALL_CATEGORIES_SQL = "SELECT * FROM categories";
	
	private static final String UPDATE_VENTES_SQL = "UPDATE ventes SET prix_vente=? WHERE no_vente=?";
	
	private static final String SELECT_ALL_VENTES = "SELECT * FROM ventes INNER JOIN retraits ON ventes.no_vente = retraits.no_vente INNER JOIN categories ON categories.no_categorie = ventes.no_categorie INNER JOIN utilisateurs ON utilisateurs.no_utilisateur = ventes.no_utilisateur";

	/**
	 * @author créé par Amandine
	 * @author modifié par Leslie et Amandine
	 * @param un objet vente
	 * @return une vente avec son numéro de vente (id)
	 */
 	public Vente insert(Vente vente) throws BusinessException {
 		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement state = cnx.prepareStatement(INSERT_VENTES_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
			state.setString(1, vente.getNomArticle());
			state.setString(2, vente.getDescription());
			state.setDate(3, java.sql.Date.valueOf(vente.getDateFinEncheres()));
			state.setInt(4, vente.getMiseAPrix());
			state.setInt(5, vente.getPrixVente());			
			state.setInt(6, vente.getVendeur().getNoUtilisateur());	
			state.setInt(7, vente.getCategorieArticle().getNoCategorie());
			state.setBoolean(8, vente.getPublie());
			state.setString(9, vente.getNomImage());
			
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
	
	/**
	 * @author créé par Amandine
	 * @param numéro de vente et prix de la vente (points)
	 * @return vide
	 */
	public void update (int noVente, Integer points) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement state= cnx.prepareStatement(UPDATE_VENTES_SQL);){			
			state.setInt(1, points);
			state.setInt(2, noVente);
	
			state.execute();
			
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: mise à jour des points impossible");
			throw be;
		}
		
	}

	/**
	 * @author créé par Amandine
	 * @author modifié par Leslie et Amandine
	 * @param numéro de vente
	 * @return une vente publiée 
	 */
	public Vente selectById(int noVente) throws BusinessException {
		Vente vente = new Vente();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement state= cnx.prepareStatement(SELECTBYID_VENTES_PUBLIEES_SQL);){			
			ResultSet rs;
			state.setInt(1, noVente);
			rs = state.executeQuery();
			rs.next();
			vente.setNomArticle(rs.getString("nomarticle"));
			vente.setDescription(rs.getString("description"));
			vente.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
			vente.setMiseAPrix(rs.getInt("prix_initial"));
			vente.setPrixVente(rs.getInt("prix_vente"));
			vente.setNomImage(rs.getString("nomImage"));
			vente.setPublie(rs.getBoolean("publiee"));
			vente.setNoVente(noVente);
			
			Retrait retrait = new Retrait();
			retrait.setRue(rs.getString("rue"));
			retrait.setCodePostal(rs.getString("code_postal"));
			retrait.setVille(rs.getString("ville"));
			vente.setLieuRetrait(retrait);
			
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(rs.getInt("no_categorie"));
			categorie.setLibelle(rs.getString("libelle"));
			vente.setCategorieArticle(categorie);
			
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			utilisateur.setPseudo(rs.getString("pseudo"));
			utilisateur.setNom(rs.getNString("nom"));
			utilisateur.setPrenom(rs.getNString("prenom"));
			utilisateur.setEmail(rs.getNString("email"));
			utilisateur.setTelephone(rs.getString("telephone"));
			utilisateur.setRue(rs.getString("rue"));
			utilisateur.setCodePostal(rs.getString("code_postal"));
			utilisateur.setVille(rs.getString("ville"));
			utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
			utilisateur.setCredit(rs.getInt("credit"));
			utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
			vente.setVendeur(utilisateur);
			
			rs.close();	
			
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: id inconnu");
			throw be;
		}
		
		return vente;
	}
	
	/**
	 * @author créé par Amandine
	 * @author modifié par Leslie et Amandine
	 * @param numéro d'utilisateur
	 * @return liste de vente publiée classée par le numéro d'utilisateur (vendeur)
	 */
	public List<Vente> selectAll(int noUtilisateur) throws BusinessException {
		List<Vente> listeVentes = new ArrayList<Vente>();
		Vente vente = new Vente();
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state= cnx.prepareStatement(SELECTALL_VENTES_PUBLIEES_SQL);){			
			ResultSet rs;
			state.setInt(1, noUtilisateur);
			rs = state.executeQuery();
			while (rs.next()) {
				if (rs.getInt("no_vente")!=vente.getNoVente()) {
					vente = new Vente();
					vente.setNoVente(rs.getInt("no_vente"));
					vente.setNomArticle(rs.getString("nomarticle"));
					vente.setDescription(rs.getString("description"));
					vente.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
					vente.setMiseAPrix(rs.getInt("prix_initial"));
					vente.setPrixVente(rs.getInt("prix_vente"));
					vente.setNomImage(rs.getString("nomImage"));
					vente.setPublie(rs.getBoolean("publiee"));
					
					Retrait retrait = new Retrait();
					retrait.setRue(rs.getString("rue"));
					retrait.setCodePostal(rs.getString("code_postal"));
					retrait.setVille(rs.getString("ville"));
					vente.setLieuRetrait(retrait);
					
					Categorie categorie = new Categorie();
					categorie.setNoCategorie(rs.getInt("no_categorie"));
					categorie.setLibelle(rs.getString("libelle"));
					vente.setCategorieArticle(categorie);
					
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					utilisateur.setPseudo(rs.getString("pseudo"));
					utilisateur.setNom(rs.getNString("nom"));
					utilisateur.setPrenom(rs.getNString("prenom"));
					utilisateur.setEmail(rs.getNString("email"));
					utilisateur.setTelephone(rs.getString("telephone"));
					utilisateur.setRue(rs.getString("rue"));
					utilisateur.setCodePostal(rs.getString("code_postal"));
					utilisateur.setVille(rs.getString("ville"));
					utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
					utilisateur.setCredit(rs.getInt("credit"));
					utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
					vente.setVendeur(utilisateur);
					
					listeVentes.add(vente);					
				}				
			}
			
			rs.close();	
			
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: id inconnu");
			throw be;
		}
		
		return listeVentes;
	}
	
	/**
	 * @author créé par Amandine
	 * @author 
	 * @param numéro de vente
	 * @return une vente publiée 
	 */
	public Vente selectByIdNonPublic(int noVente) throws BusinessException {
		Vente vente = new Vente();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement state= cnx.prepareStatement(SELECTBYID_VENTES_NONPUBLIEES_SQL);){			
			ResultSet rs;
			state.setInt(1, noVente);
			rs = state.executeQuery();
			rs.next();
			vente.setNomArticle(rs.getString("nomarticle"));
			vente.setDescription(rs.getString("description"));
			vente.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
			vente.setMiseAPrix(rs.getInt("prix_initial"));
			vente.setPrixVente(rs.getInt("prix_vente"));
			vente.setNomImage(rs.getString("nomImage"));
			vente.setPublie(rs.getBoolean("publiee"));
			
			Retrait retrait = new Retrait();
			retrait.setRue(rs.getString("rue"));
			retrait.setCodePostal(rs.getString("code_postal"));
			retrait.setVille(rs.getString("ville"));
			vente.setLieuRetrait(retrait);
			
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(rs.getInt("no_categorie"));
			categorie.setLibelle(rs.getString("libelle"));
			vente.setCategorieArticle(categorie);
			
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
			utilisateur.setPseudo(rs.getString("pseudo"));
			utilisateur.setNom(rs.getNString("nom"));
			utilisateur.setPrenom(rs.getNString("prenom"));
			utilisateur.setEmail(rs.getNString("email"));
			utilisateur.setTelephone(rs.getString("telephone"));
			utilisateur.setRue(rs.getString("rue"));
			utilisateur.setCodePostal(rs.getString("code_postal"));
			utilisateur.setVille(rs.getString("ville"));
			utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
			utilisateur.setCredit(rs.getInt("credit"));
			utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
			vente.setVendeur(utilisateur);
			
			rs.close();	
			
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: id inconnu");
			throw be;
		}
		
		return vente;
	}
	
	/**
	 * @author créé par Amandine
	 * @author modifié par Leslie et Amandine
	 * @param numéro d'utilisateur
	 * @return liste de vente publiée classée par le numéro d'utilisateur (vendeur)
	 */
	public List<Vente> selectAllNonPublic(int noUtilisateur) throws BusinessException {
		List<Vente> listeVentes = new ArrayList<Vente>();
		Vente vente = new Vente();
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state= cnx.prepareStatement(SELECTALL_VENTES_NONPUBLIEES_SQL);){			
			ResultSet rs;
			state.setInt(1, noUtilisateur);
			rs = state.executeQuery();
			while (rs.next()) {
				if (rs.getInt("no_vente")!=vente.getNoVente()) {
					vente = new Vente();
					vente.setNomArticle(rs.getString("nomarticle"));
					vente.setDescription(rs.getString("description"));
					vente.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
					vente.setMiseAPrix(rs.getInt("prix_initial"));
					vente.setPrixVente(rs.getInt("prix_vente"));
					vente.setNomImage(rs.getString("nomImage"));
					vente.setPublie(rs.getBoolean("publiee"));
					
					Retrait retrait = new Retrait();
					retrait.setRue(rs.getString("rue"));
					retrait.setCodePostal(rs.getString("code_postal"));
					retrait.setVille(rs.getString("ville"));
					vente.setLieuRetrait(retrait);
					
					Categorie categorie = new Categorie();
					categorie.setNoCategorie(rs.getInt("no_categorie"));
					categorie.setLibelle(rs.getString("libelle"));
					vente.setCategorieArticle(categorie);
					
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					utilisateur.setPseudo(rs.getString("pseudo"));
					utilisateur.setNom(rs.getNString("nom"));
					utilisateur.setPrenom(rs.getNString("prenom"));
					utilisateur.setEmail(rs.getNString("email"));
					utilisateur.setTelephone(rs.getString("telephone"));
					utilisateur.setRue(rs.getString("rue"));
					utilisateur.setCodePostal(rs.getString("code_postal"));
					utilisateur.setVille(rs.getString("ville"));
					utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
					utilisateur.setCredit(rs.getInt("credit"));
					utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
					vente.setVendeur(utilisateur);
					
					listeVentes.add(vente);					
				}				
			}
			
			rs.close();	
			
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: id inconnu");
			throw be;
		}
		
		return listeVentes;
	}

	/**
	 * @author créé par Corentin
	 * @author modifié par Leslie et Amandine
	 * @param mot-clé
	 * @return liste de vente publiée classée par mot-clé
	 */
	@Override
	public List<Vente> searchByKeyWord(String keyWord) throws BusinessException {
		
		List<Vente> listeVente = new ArrayList<Vente>();
		Vente vente = new Vente();
		
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state= cnx.prepareStatement(SEARCH_BY_KEYWORD_SQL);){			
			ResultSet rs;
			state.setString(1, keyWord);
			state.setString(2, keyWord);
			rs = state.executeQuery();
			while (rs.next()) {
				if (rs.getInt("no_vente")!=vente.getNoVente()) {
					vente = new Vente();
					vente.setNoVente(rs.getInt("no_vente"));
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
					
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					utilisateur.setPseudo(rs.getString("pseudo"));
					utilisateur.setNom(rs.getNString("nom"));
					utilisateur.setPrenom(rs.getNString("prenom"));
					utilisateur.setEmail(rs.getNString("email"));
					utilisateur.setTelephone(rs.getString("telephone"));
					utilisateur.setRue(rs.getString("rue"));
					utilisateur.setCodePostal(rs.getString("code_postal"));
					utilisateur.setVille(rs.getString("ville"));
					utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
					utilisateur.setCredit(rs.getInt("credit"));
					utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
					vente.setVendeur(utilisateur);
					
					listeVente.add(vente);
				}
				
			}
			
			rs.close();
			
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: libellé inconnu");
			throw be;
		}
		return listeVente;
	}

	/**
	 * @author créé par Corentin
	 * @author modifié par Leslie et Amandine
	 * @param numéro de la catégorie
	 * @return liste de vente publiée classée par catégorie
	 */
	@Override
	public List<Vente> searchByCatagory(int noCategorie) throws BusinessException {
		
		List<Vente> listeVente = new ArrayList<Vente>();
		Vente vente = new Vente();
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement state= cnx.prepareStatement(SEARCH_BY_CATEGORY_SQL);){			
			ResultSet rs;
			state.setInt(1, noCategorie);
			rs = state.executeQuery();
			while (rs.next()) {
				if (rs.getInt("no_vente")!=vente.getNoVente()) {
					vente =  new Vente();
					vente.setNoVente(rs.getInt("no_vente"));
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
					
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					utilisateur.setPseudo(rs.getString("pseudo"));
					utilisateur.setNom(rs.getNString("nom"));
					utilisateur.setPrenom(rs.getNString("prenom"));
					utilisateur.setEmail(rs.getNString("email"));
					utilisateur.setTelephone(rs.getString("telephone"));
					utilisateur.setRue(rs.getString("rue"));
					utilisateur.setCodePostal(rs.getString("code_postal"));
					utilisateur.setVille(rs.getString("ville"));
					utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
					utilisateur.setCredit(rs.getInt("credit"));
					utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
					vente.setVendeur(utilisateur);
					
					listeVente.add(vente);
					
				}
	
			}
			
			rs.close();			
			
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
			PreparedStatement state1 = cnx.prepareStatement(DELETE_RETRAITS_SQL);
			PreparedStatement state2 = cnx.prepareStatement(DELETE_VENTES_SQL)){
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

	@Override
	public List<Categorie> selectCatagory() throws BusinessException {
		
		List<Categorie> listeCategorie = new ArrayList<>();
		Categorie categorie = new Categorie();
		
		try (Connection cnx = ConnectionProvider.getConnection();
				//Connection cnx = fr.eni.projet01.trocenchere.dal.Connection.getConnection();
				PreparedStatement state= cnx.prepareStatement(SELECTALL_CATEGORIES_SQL);){			
			ResultSet rs = state.executeQuery();			
			while (rs.next()) {
				if (rs.getInt("no_categorie")!=categorie.getNoCategorie()) {
					categorie = new Categorie();
					categorie.setLibelle(rs.getString("libelle"));
					categorie.setNoCategorie(rs.getInt("no_categorie"));				
					listeCategorie.add(categorie);					
				}
				
			}	
			rs.close();	
			
		} catch (Exception e) {
			BusinessException be = new BusinessException();
			e.printStackTrace();
			be.ajouterErreur("Erreur: problème survenu lors de la récupération des catégories");
			throw be;
		}
		
		return listeCategorie;
	}
	
	/**
	 * @author créé par Corentin et Leslie
	 * @return liste de vente publiée
	 */
	@Override
	public List<Vente> selectAll() throws BusinessException {
		List<Vente> listeVente = new ArrayList<Vente>();
		Vente vente = new Vente();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement state= cnx.prepareStatement(SELECT_ALL_VENTES);){			
			ResultSet rs = state.executeQuery();			
			while (rs.next()) {
				if (rs.getInt("no_vente")!=vente.getNoVente()) {
					vente = new Vente();
					vente.setNoVente(rs.getInt("no_vente"));
					vente.setNomArticle(rs.getString("nomarticle"));
					vente.setDescription(rs.getString("description"));
					vente.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
					vente.setMiseAPrix(rs.getInt("prix_initial"));
					vente.setPrixVente(rs.getInt("prix_vente"));
					vente.setNomImage(rs.getString("nomImage"));
					vente.setPublie(rs.getBoolean("publiee"));
					
					Retrait retrait = new Retrait();
					retrait.setRue(rs.getString("rue"));
					retrait.setCodePostal(rs.getString("code_postal"));
					retrait.setVille(rs.getString("ville"));
					vente.setLieuRetrait(retrait);
					
					Categorie categorie = new Categorie();
					categorie.setNoCategorie(rs.getInt("no_categorie"));
					categorie.setLibelle(rs.getString("libelle"));
					vente.setCategorieArticle(categorie);
					
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					utilisateur.setPseudo(rs.getString("pseudo"));
					utilisateur.setNom(rs.getNString("nom"));
					utilisateur.setPrenom(rs.getNString("prenom"));
					utilisateur.setEmail(rs.getNString("email"));
					utilisateur.setTelephone(rs.getString("telephone"));
					utilisateur.setRue(rs.getString("rue"));
					utilisateur.setCodePostal(rs.getString("code_postal"));
					utilisateur.setVille(rs.getString("ville"));
					utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
					utilisateur.setCredit(rs.getInt("credit"));
					utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
					vente.setVendeur(utilisateur);
					
					listeVente.add(vente);					
				}			
			}	
			rs.close();	

	} catch (SQLException e) {
		e.printStackTrace();
	}
		return listeVente;
}
}
