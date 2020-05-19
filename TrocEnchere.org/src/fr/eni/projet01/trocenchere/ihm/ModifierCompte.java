
// JANET

package fr.eni.projet01.trocenchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet01.trocenchere.bll.UtilisateurManager;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

/**
 * Servlet implementation class ModifierCompte
 */
@WebServlet("/ModifierCompte")
public class ModifierCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// open session to get user info, notably the credit
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		request.setAttribute("utilisateur", utilisateur);
		
		// forward to page
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierCompte.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// call manager
		UtilisateurManager UM = new UtilisateurManager();
		// get current user through session
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		// get id from from user session
		int userID = utilisateur.getNoUtilisateur();

		// get updated parameters from jsp, stored in new user to send to db
		Utilisateur newUtilisateur = new Utilisateur();
		newUtilisateur.setNoUtilisateur(userID);
		newUtilisateur.setPseudo(request.getParameter("pseudoUtilisateur"));
		newUtilisateur.setNom(request.getParameter("nomUtilisateur"));
		newUtilisateur.setPrenom(request.getParameter("prenomUtilisateur"));
		newUtilisateur.setEmail(request.getParameter("emailUtilisateur"));
		newUtilisateur.setTelephone(request.getParameter("telUtilisateur"));
		newUtilisateur.setRue(request.getParameter("rueUtilisateur"));
		newUtilisateur.setCodePostal(request.getParameter("cpUtilisateur"));
		newUtilisateur.setVille(request.getParameter("villeUtilisateur"));
		newUtilisateur.setMotDePasse(request.getParameter("mdpUtilisateur"));
		newUtilisateur.setCredit(utilisateur.getCredit());
		newUtilisateur.setAdministrateur(false);

		String mdpUtilisateur = request.getParameter("mdpUtilisateur");
		String confMdpUtilisateur = request.getParameter("confMdpUtilisateur");

		String messageErreur = "Les mots de passe sont différents";
		RequestDispatcher rd = null;

	
		
		// comparaison mot de passe
		if (mdpUtilisateur.equals(confMdpUtilisateur)) {
			try {
				UM.mettreAJourUtilisateur(newUtilisateur);
				// update user in session
				// set new one
				session.setAttribute("utilisateur", newUtilisateur);
				// send to affichage compte
				response.sendRedirect(request.getContextPath()+"/afficherCompte");
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeErreur());
			}

		}else {
			request.setAttribute("message", messageErreur);
			doGet(request, response);  //retour modifier compte 
			}
			

	}
}
