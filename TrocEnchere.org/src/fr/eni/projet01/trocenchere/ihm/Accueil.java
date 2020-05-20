
// Corentin et Leslie

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
		//affichage de toutes les ventes
		try {
			List<Vente> lesVentes = vM.selectionnerToutesVentes();
			request.setAttribute("mesVentes", lesVentes);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//doGet(request, response);
		
		Utilisateur utilisateurSession = extractedUserSession(request);		
		request.setAttribute("utilisateur", utilisateurSession);
		
		
		List<Vente> mesVentes = new ArrayList<Vente>();
		List<Enchere> mesEnchères = new ArrayList<Enchere>();
		List<Vente> mesAcquisitions = new ArrayList<Vente>();
		List<Vente> autresEnchères = new ArrayList<Vente>();
		List<Vente> venteByKeyword = new ArrayList<Vente>();
		List<Vente> venteByCategory = new ArrayList<Vente>();
		
		LocalDate ajd = LocalDate.now();

		List<Vente> resultatAAfficher = new ArrayList<>();  //puis add les différentes ventes succèsivement en fct de ce qui a été coché
		
		try {
			//l'utilisateur n'a coché aucun des 4 filtres NI de mot-clef
			if (request.getParameter("mesVentes") == null && request.getParameter("mesEncheres") == null && request.getParameter("mesAcquisitions") == null 
					&& request.getParameter("autresEncheres") == null && request.getParameter("venteByKeyword") == null) {  
				resultatAAfficher = vM.selectionnerToutesVentes();
			}
			
			
			
			if (request.getParameter("mesVentes")!=null ) {  //tout ce que l'utilisateur est en train de vendre OU a déjà vendu !
				mesVentes = vM.selectionnerVenteUtilisateur(utilisateurSession.getNoUtilisateur());
				for (Vente vente : mesVentes) {
						resultatAAfficher.add(vente);
				}
			}
			
			if (request.getParameter("mesEncheres")!=null) {  //tout ce que l'utilisateur est en train d'acheter (date_de_fin > ajd ET au moins une enchère !)
				mesEnchères = eM.selectionnerEnchereByIdUser (utilisateurSession.getNoUtilisateur());
				for (Enchere enchere : mesEnchères) {
					Vente vente = enchere.getConcerne();
					System.out.println(vente);
					if (vente.getDateFinEncheres().isAfter(ajd)) {  //ventes en cours
						resultatAAfficher.add(vente);
					}
				}
			}
			
			if (request.getParameter("mesAcquisitions")!=null) {  //tout ce que l'utilisateur a déjà acheté ! (date_de_fin < ajd ET dernier encheriseur !)
				List<Vente> lesVentes = vM.selectionnerToutesVentes();
				for (Vente vente : lesVentes) {
					Enchere enchereGagnante = eM.trouverHighestBid(vente.getNoVente());
					Utilisateur utilisateurAVerifier = enchereGagnante.getEncherit();
					if (vente.getDateFinEncheres().isBefore(ajd) && utilisateurAVerifier == utilisateurSession ) { //ventes terminées
						resultatAAfficher.add(vente);
					}
				}
			}
			
			if (request.getParameter("autresEncheres")!=null) { // = toutes vente - mesVentes - mesEncheres - mesAcquisitions
				
				resultatAAfficher = vM.selectionnerToutesVentes();  //on sélectionne ABSOLUMENT toutes les ventes du site
				
				mesVentes = vM.selectionnerVenteUtilisateur(utilisateurSession.getNoUtilisateur());  //on retire mesVentes
				for (Vente vente : mesVentes) {
						resultatAAfficher.remove(vente);
				}
				
				mesEnchères = eM.selectionnerEnchereByIdUser (utilisateurSession.getNoUtilisateur());  //on retire mesEncheres
				for (Enchere enchere : mesEnchères) {
					Vente vente = enchere.getConcerne();
					System.out.println(vente);
					if (vente.getDateFinEncheres().isAfter(ajd)) {  //ventes en cours
						resultatAAfficher.remove(vente);
					}
				}
				
				List<Vente> lesVentes = vM.selectionnerToutesVentes();  //on retire mesAcquisitions
				for (Vente vente : lesVentes) {
					Enchere enchereGagnante = eM.trouverHighestBid(vente.getNoVente());
					Utilisateur utilisateurAVerifier = enchereGagnante.getEncherit();
					if (vente.getDateFinEncheres().isBefore(ajd) && utilisateurAVerifier == utilisateurSession ) { //ventes terminées
						resultatAAfficher.remove(vente);
					}
				}
			}
			
			if (request.getParameter("venteByKeyword")!=null) {
				if (request.getParameter("mesVentes") == null && request.getParameter("mesEncheres") == null && request.getParameter("mesAcquisitions") == null 
					&& request.getParameter("autresEncheres") == null) {
					resultatAAfficher = vM.selectionnerVenteByKeyWord(request.getParameter("venteByKeyword"));
				} else {
					List<Vente> listeVenteMotCle = vM.selectionnerVenteByKeyWord(request.getParameter("venteByKeyword"));
					List<Vente> listeTempo = new ArrayList<Vente>();
					for (Vente vente : resultatAAfficher) {
						listeTempo.add(vente);
					}
					resultatAAfficher.clear();
					for (Vente venteTempo : listeTempo) {
						Boolean isEqual = false;
						for (Vente venteMotCle : listeVenteMotCle) {
							if (venteMotCle==venteTempo) {
								isEqual = true;	
							}
							if (isEqual) {
								resultatAAfficher.add(venteMotCle);
								isEqual = false;
							}
						}
					}
					
				}
				
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
		request.setAttribute("mesVentes", resultatAAfficher);  //à changer le nom de l'attibut plus tard
		
		
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
