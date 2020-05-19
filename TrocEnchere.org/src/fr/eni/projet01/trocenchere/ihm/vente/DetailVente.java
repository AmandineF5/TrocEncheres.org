
// JANET

package fr.eni.projet01.trocenchere.ihm.vente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import fr.eni.projet01.trocenchere.bll.EnchereManager;
import fr.eni.projet01.trocenchere.bll.UtilisateurManager;
import fr.eni.projet01.trocenchere.bll.VenteManager;
import fr.eni.projet01.trocenchere.bo.Enchere;
import fr.eni.projet01.trocenchere.bo.Retrait;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

/**
 * Servlet implementation class DetailVente
 */
@WebServlet("/DetailVente")
public class DetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       VenteManager vM = new VenteManager();
       EnchereManager eM = new EnchereManager();
       
  	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get noVente from incoming page
		
		//getting cookie
		//String noVente = (String) request.getAttribute("noVente");
		int noVenteAAfficher = getNoVenteDeSession(request);
		
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
		
			
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vente/detailVente.jsp");
		rd.forward(request, response);
		
	}

	private int getNoVenteDeSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int noVente = (int) session.getAttribute("NoVente");
		//getting the id
		int noVenteAAfficher = noVente; //Integer.parseInt(noVente);
		return noVenteAAfficher;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		
		int venteID = getNoVenteDeSession(request);
		//send id to delete the vente
		try {
			vM.supprimerVente(venteID);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeErreur());
		}
		
		response.sendRedirect(request.getContextPath()+"/NouvelleVente");
		
	}

}
