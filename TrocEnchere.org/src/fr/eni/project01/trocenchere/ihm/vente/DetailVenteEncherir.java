package fr.eni.project01.trocenchere.ihm.vente;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet01.trocenchere.bll.EnchereManager;
import fr.eni.projet01.trocenchere.bll.VenteManager;
import fr.eni.projet01.trocenchere.bo.Enchere;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

/**corresponds to maquette diapo 6
 * created by Janet
 * Servlet implementation class DetailVenteEncherir
 */
 
@WebServlet("/DetailVenteEncherir")
public class DetailVenteEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VenteManager vM = new VenteManager();
    EnchereManager eM = new EnchereManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				HttpSession session = request.getSession();
				//get noVente from incoming page which should arrive in a parameter
				//getting the id
				//test
				int noVenteAAfficher = 2;
				//int noVenteAAfficher = Integer.parseInt(request.getParameter("noVente"));
					
				//find the Vente in the database
				Vente venteAAfficher = new Vente();
				try {
					 venteAAfficher = vM.selectionnerVenteById(noVenteAAfficher);
					 System.out.println(venteAAfficher.getVendeur());
					 
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur", e.getListeErreur());
				}
				
				//set vente as attribute
				request.setAttribute("vente", venteAAfficher);
				
				//find the highest bid by id no
				Enchere highestEnchere = new Enchere();
				try {
					highestEnchere = eM.trouverHighestBid(noVenteAAfficher);
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur", e.getListeErreur());
				}
				//set bid as attribute
				
				request.setAttribute("enchere", highestEnchere);
				
				//get the current user id from session
				Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
				//send user to the jsp
				request.setAttribute("utilisateur", user);
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vente/detailVenteEncherir.jsp");
				rd.forward(request, response);
				
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		//get bid from jsp
		int enchere = Integer.parseInt(request.getParameter("encherir"));
		
		//get user
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		
		//get noVent
		int noVenteConcerne = Integer.parseInt(request.getParameter("noVente"));
		//get Vent
		Vente venteConcerne = new Vente();
		try {
			 venteConcerne = vM.selectionnerVenteById(noVenteConcerne);
			 System.out.println(venteConcerne.getVendeur());
			 
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeErreur());
		}
		
		//get local date
		ZoneId zonedId = ZoneId.of( "Europe/Paris" );
		LocalDate today = LocalDate.now( zonedId );
		
		//new bid
		Enchere newEnchere = new Enchere(today, venteConcerne, user, enchere);
		
		//insert into db
		try {
			eM.ajouterEnchere(newEnchere);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeErreur());
		}
		
		response.sendRedirect(request.getContextPath()+"/DetailVenteAnnulerEncherir");
		
	}

}
