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

/**
 * Servlet implementation class ConnecterCompte
 */
@WebServlet("/ConnecterCompte")
public class ConnecterCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/xxx.jsp"); // lien vers la jsp de connection au compte
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String mdp = request.getParameter("mdp");
		UtilisateurManager um = new UtilisateurManager();
		boolean loginOK;
		loginOK = um.verificationConnectionCompte(email, mdp);
		Utilisateur ut = um.getUtilisateur();
		RequestDispatcher rd; 
		if (loginOK) {
			HttpSession session = request.getSession();
            session.setAttribute("utilisateur", ut);
            rd = request.getRequestDispatcher("/"); // lien vers servlet de gestion du compte;
            rd.forward(request, response);
		} else if (!loginOK) {
			String message = "Invalid email/password";
            request.setAttribute("message", message);
            doGet(request, response);
		}
	}

}
