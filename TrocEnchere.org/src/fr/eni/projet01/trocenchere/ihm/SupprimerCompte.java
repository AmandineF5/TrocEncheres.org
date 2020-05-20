package fr.eni.projet01.trocenchere.ihm;

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
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

/**
 * Servlet implementation class SupprimerCompte
 */
@WebServlet("/SupprimerCompte")
public class SupprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Utilisateur ut = (Utilisateur) session.getAttribute("utilisateur");
		int noUser = ut.getNoUtilisateur();
		
		try {
			
			VenteManager vm = new VenteManager();
			vm.supprimerVenteUser(noUser);
			UtilisateurManager um = new UtilisateurManager();
			um.supprimerUtilisateurr(noUser);
			
			
		} catch (BusinessException e) {
			
			e.printStackTrace();
		}
        if(session!=null) {
        	session.invalidate(); //supprime les attributs de la session
        }
        doGet(request, response);
	}
	

}
