package fr.eni.project01.trocenchere.ihm.vente;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet01.trocenchere.bll.VenteManager;
import fr.eni.projet01.trocenchere.bo.Categorie;
import fr.eni.projet01.trocenchere.bo.Retrait;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/NouvelleVente")
public class NouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		//Catégories en dur en attendant une requête pour récupérer la liste des catégories.
				List<Categorie> categories = new ArrayList<Categorie>();
				Categorie categorie1 = new Categorie(1, "Jouet");
				Categorie categorie2 = new Categorie(2, "Livres");
				categories.add(categorie1);
				categories.add(categorie2);
				request.setAttribute("categories", categories);	
				
		request.getRequestDispatcher("/WEB-INF/vente/nouvelleVente.jsp").forward(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vente newVente = new Vente();
		VenteManager vM = new VenteManager();
		
		//newVente.setNoVente(noVente);
		newVente.setNomArticle(request.getParameter("nomArticle"));
		newVente.setDescription(request.getParameter("description"));
		
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy"); //  yyyy-MM-dd
		LocalDate date = LocalDate.parse(request.getParameter("dateFinEncheres")); //, dtf
		//LocalDateTime dateFinEncheres = date.atTime(0, 0);
		newVente.setDateFinEncheres(date);		
		newVente.setMiseAPrix(Integer.parseInt(request.getParameter("prixInitial")));		
				
		//int categorieChoisi = Integer.parseInt(request.getParameter("categorie"));
		String categorieChoisi = request.getParameter("categorie");
		int noCategorie = Integer.parseInt(categorieChoisi.trim());
		
		Categorie newCategarieChoisi = new Categorie(noCategorie, "");
		newVente.setCategorieArticle(newCategarieChoisi);
		
		Utilisateur utilisateur = extractedUserSession(request);
		newVente.setVendeur(utilisateur);
		Retrait retrait = new Retrait(request.getParameter("rueUtilisateur"), request.getParameter("cpUtilisateur"), request.getParameter("villeUtilisateur"));
		newVente.setLieuRetrait(retrait);
		
		
		//Enregistrer ou publier une vente
		String boutonChoix = request.getParameter("bouton");
		if (boutonChoix.equals("Publier")) {
			newVente.setPublie(true);
		} else {
			newVente.setPublie(false);
		}		
		
//		try {
//			Vente vente = vM.ajouterVente(newVente);
//		} catch (BusinessException e) {
//			e.printStackTrace();
//		}
		
		System.out.println(newVente.toString());
		//Cookie pour renvoyer le numéro de vente à afficher
		String noNewVente = String.valueOf(newVente.getNoVente());
		Cookie noVente = new Cookie("noVente", noNewVente);
		noVente.setMaxAge(20);
		response.addCookie(noVente);

		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/DetailVente");
		rd.forward(request, response);
	
	}
		
	private Utilisateur extractedUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		return utilisateur;
	}

}
