
// JANET ET LESLIE

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
 * @author Janet
 * modifié par Leslie
 * Servlet implementation class AfficherCompte
 */
public class AfficherCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/afficherCompte.jsp");
		
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		UtilisateurManager UM = new UtilisateurManager();
		
		if (request.getParameter("noUtilisateur")!=null) {
			int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
			Utilisateur vendeur;
			try {
				vendeur = UM.selectionnerUtilisateurById(noUtilisateur);
				request.setAttribute("utilisateur", vendeur);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("utilisateur", utilisateur);
		}
		
		//pour tester si aucune session chargée !
//		Utilisateur utilisateur = null;
//		try {
//			utilisateur = UM.selectionnerUtilisateurById(1);
//		} catch (BusinessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
