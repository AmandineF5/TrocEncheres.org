package fr.eni.projet01.trocenchere.ihm.vente;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import fr.eni.projet01.trocenchere.bo.Retrait;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

/**@author Leslie
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/NouvelleVente")
public class NouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;    
	VenteManager vM = new VenteManager();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NouvelleVente() {
        super();
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = extractedUserSession(request);		
		request.setAttribute("utilisateur", utilisateur);	

		List<Categorie> categories = new ArrayList<Categorie>();
		try {
			categories = vM.selectionnerCategorie();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
				
		request.setAttribute("categories", categories);				
		request.getRequestDispatcher("/WEB-INF/vente/nouvelleVente.jsp").forward(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vente newVente = new Vente();
		
		newVente.setNomArticle(request.getParameter("nomArticle"));
		newVente.setDescription(request.getParameter("description"));
		
        LocalDate date =  LocalDate.parse(request.getParameter("dateFinEncheres"));
        newVente.setDateFinEncheres(date);
		
        int prix = Integer.parseInt(request.getParameter("prixInitial"));
		newVente.setMiseAPrix(prix);		
		newVente.setPrixVente(prix);		
				
		int noCategorie = Integer.parseInt(request.getParameter("categorie").trim());
		
		
		Categorie newCategarieChoisi = new Categorie(noCategorie, "");
		newVente.setCategorieArticle(newCategarieChoisi);
		
		Utilisateur utilisateur = extractedUserSession(request);		
		newVente.setVendeur(utilisateur);
		
		Retrait retrait = new Retrait(request.getParameter("rueUtilisateur"), request.getParameter("cpUtilisateur"), request.getParameter("villeUtilisateur"));
		newVente.setLieuRetrait(retrait);
		
		newVente.setNomImage("Essai");
		
		//Enregistrer ou publier une vente		
		String boutonChoix = request.getParameter("bouton");
		if (boutonChoix.equalsIgnoreCase("Publier")) {
			newVente.setPublie(true);
		} else {
			newVente.setPublie(false);
		}		
		
		Vente venteAAfficher = new Vente();
		
		try {
			venteAAfficher = vM.ajouterVente(newVente);
			Enchere debutEnchere = new Enchere();
			debutEnchere.setConcerne(venteAAfficher);
			LocalDate today = LocalDate.now();
			debutEnchere.setDateEnchere(today);
			debutEnchere.setEncherit(extractedUserSession(request));
			debutEnchere.setPoints(prix);
			
			EnchereManager em = new EnchereManager();
			em.ajouterEnchere(debutEnchere);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("NoVente", venteAAfficher.getNoVente());
		response.sendRedirect(request.getContextPath()+"/DetailVente");

	
	}
		
	private Utilisateur extractedUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		return utilisateur;
	}


}
