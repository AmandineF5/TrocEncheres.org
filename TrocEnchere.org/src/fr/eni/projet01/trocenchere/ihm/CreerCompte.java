package fr.eni.projet01.trocenchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet01.trocenchere.bo.Utilisateur;

/**
 * Servlet implementation class CreerCompte
 */
public class CreerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/xxx/xxx.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		//méthode 1 => constructeur vide + multiple setters
		Utilisateur user1 = new Utilisateur();  
			user1.setPseudo(request.getParameter("pseudoUtilisateur"));
			user1.setNom(request.getParameter("nomUtilisateur"));
			user1.setPrenom(request.getParameter("prenomUtilisateur"));
			user1.setEmail(request.getParameter("emailUtilisateur"));
			user1.setTelephone(request.getParameter("telUtilisateur"));
			user1.setRue(request.getParameter("rueUtilisateur"));
			user1.setCodePostal(request.getParameter("cpUtilisateur"));
			user1.setVille(request.getParameter("villeUtilisateur"));
			user1.setMotDePasse(request.getParameter("mdpUtilisateur"));
			user1.setMotDePasse(request.getParameter("confMdpUtilisateur"));
			user1.setCredit(0f);
			user1.setAdministrateur(false);
			
	}

}
