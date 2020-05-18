package fr.eni.projet01.trocenchere.ihm;

import java.io.IOException;

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
		List<Categorie> categories = new ArrayList<Categorie>();
		try {
			categories = vM.selectionnerCategorie();
		} catch (BusinessException e) {
			e.printStackTrace();
		}		
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
		List<Vente> venteByKeyword = new ArrayList<Vente>();
		List<Vente> venteByCategory = new ArrayList<Vente>();
		
		try {
			if (request.getParameter("mesVentes")!=null) {
				mesVentes = vM.selectionnerVenteUtilisateur(utilisateur.getNoUtilisateur());				
			}
			if (request.getParameter("mesEnchères")!=null) {
				mesEnchères = eM.selectionnerEnchereByIdUser (utilisateur.getNoUtilisateur());				
			}
			if (request.getParameter("noCategorie")!=null) {
				int noCategorie = Integer.parseInt(request.getParameter("noCategorie"));
				venteByKeyword = vM.selectionnerVenteByCategory(noCategorie);
			}
			if (request.getParameter("venteByKeyword")!=null) {
				venteByKeyword = vM.selectionnerVenteByKeyWord(request.getParameter("venteByKeyword"));
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		//Définir les informations à renvoyer à la JSP (accueil.jsp)
		request.setAttribute("mesVentes", mesVentes);
		request.setAttribute("mesEnchères", mesEnchères);
		request.setAttribute("venteByCategory", venteByCategory);
		request.setAttribute("venteByKeyword", venteByKeyword);
		
		
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

}
