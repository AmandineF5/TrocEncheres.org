package fr.eni.projet01.trocenchere.ihm;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet01.trocenchere.bll.UtilisateurManager;
import fr.eni.projet01.trocenchere.bo.Utilisateur;


public class ConnecterCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp"); // lien vers la jsp de connection au compte
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudoUtilisateur");
		String mdp = request.getParameter("mdpUtilisateur");
		UtilisateurManager um = new UtilisateurManager();
		String message = null;
		boolean loginOK;
		try {
			loginOK = um.verificationConnectionCompte(pseudo, mdp);
			Utilisateur ut = um.getUtilisateur();
			
			if (loginOK) {
				HttpSession session = request.getSession();
	            session.setAttribute("utilisateur", ut);
	            Cookie pseudoUtilisateur = new Cookie("pseudo", ut.getPseudo());
	            response.addCookie(pseudoUtilisateur);
	            // lien vers servlet de gestion du compte;
	            response.sendRedirect("");
			} else if (!loginOK) {
				message = "Email ou mot de passe invalide";
	            request.setAttribute("message", message);
	            doGet(request, response);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		};
		
	}

}
