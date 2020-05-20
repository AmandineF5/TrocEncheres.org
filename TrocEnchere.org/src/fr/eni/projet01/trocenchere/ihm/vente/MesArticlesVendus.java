
// LESLIE

package fr.eni.projet01.trocenchere.ihm.vente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet01.trocenchere.bll.EnchereManager;
import fr.eni.projet01.trocenchere.bll.UtilisateurManager;
import fr.eni.projet01.trocenchere.bll.VenteManager;
import fr.eni.projet01.trocenchere.bo.Enchere;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

/**
 * @author Leslie and Janet
 * Servlet implementation class MesVentesVendues
 */
@WebServlet("/MesArticlesVendus")
public class MesArticlesVendus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VenteManager vM = new VenteManager();
    EnchereManager eM = new EnchereManager();   
    UtilisateurManager uM = new UtilisateurManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MesArticlesVendus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noVenteAAfficher = Integer.parseInt(request.getParameter("noVente"));
		
		Vente venteAAfficher = new Vente();
		Enchere enchèreRemportee = new Enchere();
		try {
			enchèreRemportee = eM.trouverHighestBid(noVenteAAfficher);
			venteAAfficher = vM.selectionnerVenteById(noVenteAAfficher);
			request.setAttribute("vente", venteAAfficher);
			request.setAttribute("enchere", enchèreRemportee);
			 
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeErreur());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vente/mesArticlesVendus.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//fonction qui débite l'acheteur et crédite le vendeur?
		//donner credit à le vendeur
		//recoupere le parameters de vendeur par le session
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		//recouperer le montant du enchere
		int creditAAjouter = Integer.parseInt(request.getParameter("enchereValue"));
		int newCredit = creditAAjouter + user.getCredit();
		//set new credit
		user.setCredit(newCredit);
		//update in database and in session
		try {
			uM.mettreAJourUtilisateur(user);
			session.setAttribute("utilisateur", user);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	
		//give credit back to others with bids
		//get noVente from parameters
		int noVente = Integer.parseInt(request.getParameter("venteNo"));
		try {
			eM.RembourserEnFinVente(noVente);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/TrocEnchere.org/accueil");		
		//doGet(request, response);
	}

}
