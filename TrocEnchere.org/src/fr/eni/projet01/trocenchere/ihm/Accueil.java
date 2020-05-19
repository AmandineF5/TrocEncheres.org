package fr.eni.projet01.trocenchere.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet01.trocenchere.bll.EnchereManager;
import fr.eni.projet01.trocenchere.bll.VenteManager;
import fr.eni.projet01.trocenchere.bo.Categorie;
import fr.eni.projet01.trocenchere.bo.Enchere;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

/**
 * @author Corentin et Leslie
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VenteManager vM = new VenteManager();
	EnchereManager eM = new EnchereManager();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupérer les informations de l'utilisateur
		Utilisateur utilisateur = extractedUserSession(request);		
		request.setAttribute("utilisateur", utilisateur);
		//Récupérer et afficher les catégories
		List<Categorie> categories = toutesCategorie();		
		request.setAttribute("categories", categories);	
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//doGet(request, response);

		Utilisateur utilisateur = extractedUserSession(request);		
		request.setAttribute("utilisateur", utilisateur);
		
		
		List<Vente> mesVentes = new ArrayList<Vente>();
		List<Enchere> mesEnchères = new ArrayList<Enchere>();
		List<Vente> mesAcquisitions = new ArrayList<Vente>();
		List<Vente> autresEnchères = new ArrayList<Vente>();
		List<Vente> venteByKeyword = new ArrayList<Vente>();
		List<Vente> venteByCategory = new ArrayList<Vente>();
		
		LocalDate ajd = LocalDate.now(); 

		
		List resultatAAfficher = new ArrayList();  //puis add les différentes ventes succèsivement en fct de ce qui a été coché
		//OU initialiser resultatAAfficher sur toutes ventes puis retirer ce qui ne correspond pas ?
		
		try {
			if (request.getParameter("mesVentes")!=null ) {  //tout ce que l'utilisateur est en train de vendre OU a déjà vendu !
				mesVentes = vM.selectionnerVenteUtilisateur(utilisateur.getNoUtilisateur());
				for (Vente vente : mesVentes) {
						resultatAAfficher.add(vente);
				}
			}
			
			if (request.getParameter("mesEnchères")!=null) {  //tout ce que l'utilisateur est en train d'acheter (date_de_fin > ajd ET au moins une enchère !)
				//if (vente.getDateFinEncheres().isBefore(ajd)) {  //ventes en cours
				mesEnchères = eM.selectionnerEnchereByIdUser (utilisateur.getNoUtilisateur());
				for (Enchere enchere : mesEnchères) {
					resultatAAfficher.add(enchere);
					
				}
			}
			
			if (request.getParameter("mesAcquisitions")!=null) {  //tout ce que l'utilisateur a déjà acheté ! (date_de_fin < ajd ET dernier encheriseur !)
				mesVentes = vM.selectionnerVenteUtilisateur(utilisateur.getNoUtilisateur());
				for (Vente vente : mesVentes) {
					Enchere dernierEncheriseur = eM.trouverHighestBid(vente.getNoVente());
					if (vente.getDateFinEncheres().isBefore(ajd)) { //ventes terminées
					}
				}
			}
			
			if (request.getParameter("autresEnchères")!=null) { // = toutes enchères - les miennes
				
			}
			
			if (request.getParameter("venteByKeyword")!=null) {
				venteByKeyword = vM.selectionnerVenteByKeyWord(request.getParameter("venteByKeyword"));
			}
			
			if (request.getParameter("categorie")!=null && request.getParameter("categorie").equalsIgnoreCase("toutes")) {				
				//cas toutes catégories
				
				
				
	
			} else {
				//cas 1 catégorie
				int noCategorie = Integer.parseInt(request.getParameter("categorie").trim());
				venteByCategory = vM.selectionnerVenteByCategory(noCategorie);	
			}			
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		//Définir les informations à renvoyer à la JSP (accueil.jsp)
		request.setAttribute("mesVentes", mesVentes);
		request.setAttribute("mesEnchères", mesEnchères);
		request.setAttribute("venteByCategory", venteByCategory);
		request.setAttribute("venteByKeyword", venteByKeyword);
		
		//Récupérer et afficher les catégories
		List<Categorie> categories = toutesCategorie();			
		request.setAttribute("categories", categories);	
		
		//Déléguer la réponse à la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
		//doGet(request, response);
	}
	
	private Utilisateur extractedUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		return utilisateur;
	}

	private List<Categorie> toutesCategorie (){
		List<Categorie> categories = new ArrayList<Categorie>();
		try {
			categories = vM.selectionnerCategorie();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return categories;
	}
}
