package fr.eni.project01.trocenchere.ihm.vente;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet01.trocenchere.bo.Categorie;
import fr.eni.projet01.trocenchere.bo.Retrait;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;

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
		
		request.getRequestDispatcher("/WEB-INF/vente/nouvelleVente.jsp").forward(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vente newVente = new Vente();
		
		//newVente.setNoVente(noVente);
		newVente.setNomArticle(request.getParameter("nomArticle"));
		newVente.setDescription(request.getParameter("description"));
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(request.getParameter("dateFinEncheres"), dtf);
		LocalDateTime dateFinEncheres = date.atTime(0, 0);
		newVente.setDateFinEncheres(dateFinEncheres);		
		newVente.setMiseAPrix(Integer.parseInt(request.getParameter("prixInitial")));		
		
		//newVente.setCategorieArticle(request.getParameter("categorie"));
		
		Utilisateur utilisateur = extractedUserSession(request);
		newVente.setVendeur(utilisateur);
		Retrait retrait = new Retrait(request.getParameter("rueUtilisateur"), request.getParameter("cpUtilisateur"), request.getParameter("villeUtilisateur"));
		newVente.setLieuRetrait(retrait);
		
//		noVente;
//		private String nomArticle;
//		private String description;
//		private LocalDateTime dateFinEncheres;
//		private Integer miseAPrix;
//		private Integer prixVente;
//		private Categorie categorieArticle;
//		private Utilisateur acheteur;
//		private Utilisateur vendeur;
//		private Retrait lieuRetrait;
		
		//doGet(request, response);
	}
		
	private Utilisateur extractedUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		return utilisateur;
	}

}
