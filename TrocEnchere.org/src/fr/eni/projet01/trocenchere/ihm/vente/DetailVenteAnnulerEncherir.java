package fr.eni.projet01.trocenchere.ihm.vente;

import java.io.IOException;
import java.time.LocalDate;

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
 * Servlet implementation class DetailVenteAnnulerEncherir corresponds to
 * maquette 7 Janet
 */
@WebServlet("/DetailVenteAnnulerEncherir")
public class DetailVenteAnnulerEncherir extends HttpServlet {

	private static final long serialVersionUID = 1L;
	VenteManager vM = new VenteManager();
	EnchereManager eM = new EnchereManager();
	UtilisateurManager uM = new UtilisateurManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doWork(request, response);

	}

	protected void doWork(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		// get noVente from incoming page which should arrive in a parameter
		// getting the id
		int noVenteAAfficher = 0;
		if (request.getAttribute("noVente") != null) {
			noVenteAAfficher = (int) request.getAttribute("noVente");
		}
		if (request.getParameter("noVente") != null) {
			noVenteAAfficher = Integer.parseInt(request.getParameter("noVente"));
		}
		
		// find the Vente in the database
		Vente venteAAfficher = new Vente();
		try {

			venteAAfficher = vM.selectionnerVenteById(noVenteAAfficher);

		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeErreur());
		}
		// Set vente in session for the post
		session.setAttribute("VenteConcerne", venteAAfficher);

		// set vente as attribute
		request.setAttribute("vente", venteAAfficher);

		// find the highest bid by id no
		Enchere highestEnchere = new Enchere();

		try {

			highestEnchere = eM.trouverHighestBid(noVenteAAfficher);

		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeErreur());
		}
		// set bid as attribute

		request.setAttribute("enchere", highestEnchere);

		// get the current user id from session
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		// send user to the jsp
		request.setAttribute("utilisateur", user);

		String message = null;

		// block bidding if credit is not ok
		if (user.getCredit() < highestEnchere.getPoints()) {
			message = "Vous n'avez pas assez de credit";
			request.setAttribute("message", message);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vente/detailVenteAnnulerEncherir.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getAttribute("noVente") != null) {
			doWork(request, response);
		}
		if (request.getParameter("noVente") != null) {
			doWork(request, response);
		}

		RequestDispatcher rd = null;
		HttpSession session = request.getSession();

		// get user
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		// get Vent
		Vente venteConcerne = (Vente) session.getAttribute("VenteConcerne");
		//annuler bid
		//remettre le prix de vente à jour qui est dans le function deleteUserBid
		if (request.getParameter("annuler") != null) {

			// delete bid
			try {
				eM.deleteUserbid(venteConcerne.getNoVente(), user.getNoUtilisateur());
			} catch (BusinessException e) {
				e.printStackTrace();
			}

			// remettre le credit à l'utilisateur
			// prendre le prix de l'enchere
			int bid = Integer.parseInt(request.getParameter("annuler"));
			// calculate
			int newCredit = user.getCredit() + bid;
			// enlever le credit de l'utilisateur
			user.setCredit(newCredit);
			// send user with new credit to be updated
			try {
				uM.mettreAJourUtilisateur(user);
				// update in session
				session.setAttribute("utilisateur", user);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

			// send to accueil
			response.sendRedirect(request.getContextPath() + "/Accueil");
		}

		if (request.getParameter("encherir") != null) {
			// get bid from jsp
			int bid = Integer.parseInt(request.getParameter("encherir"));
			// get local date
			LocalDate date = LocalDate.now();
			// new bid
			Enchere newEnchere = new Enchere(date, venteConcerne, user, bid);
			// insert into db
			try {
				eM.ajouterEnchere(newEnchere);
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeErreur());
			}
			// CREDIT UPDATE
			// calculate
			int newCredit = user.getCredit() - bid;
			// enlever le credit de l'utilisateur
			user.setCredit(newCredit);
			// send user with new credit to be updated
			try {
				uM.mettreAJourUtilisateur(user);
				// update in session
				session.setAttribute("utilisateur", user);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			// set noVente in parameters to be sent to the next page
			// get noVente
			int venteID = venteConcerne.getNoVente();
			request.setAttribute("noVente", venteID);

			rd = request.getRequestDispatcher("/DetailVenteAnnulerEncherir");
			rd.forward(request, response);
		}

	}

}
