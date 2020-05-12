package fr.eni.projet01.trocenchere.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		
		Utilisateur user = new Utilisateur();  
			user.setPseudo(request.getParameter("pseudoUtilisateur"));
			user.setNom(request.getParameter("nomUtilisateur"));
			user.setPrenom(request.getParameter("prenomUtilisateur"));
			user.setEmail(request.getParameter("emailUtilisateur"));
			user.setTelephone(request.getParameter("telUtilisateur"));
			user.setRue(request.getParameter("rueUtilisateur"));
			user.setCodePostal(request.getParameter("cpUtilisateur"));
			user.setVille(request.getParameter("villeUtilisateur"));
			user.setMotDePasse(request.getParameter("mdpUtilisateur"));
			user.setMotDePasse(request.getParameter("confMdpUtilisateur"));
			user.setCredit(0);
			user.setAdministrateur(false);
			
			String mdpUtilisateur = request.getParameter("mdpUtilisateur");
			String confMdpUtilisateur = request.getParameter("confMdpUtilisateur");
			
			String pseudoUtilisateur = request.getParameter("pseudoUtilisateur");
			String pseudoExistant = null ;
			
			String emailUtilisateur = request.getParameter("emailUtilisateur");
			String emailExistant = null ;
			
			List<String> messageErreur = new ArrayList<String>();
			RequestDispatcher rd = null;
			
			
			//comparaison mot de passe
			if (mdpUtilisateur.equals(confMdpUtilisateur)) {
				rd = request.getRequestDispatcher("/WEB-INF/xxx/xxx");  //URL pour envoyer les champs vers la BDD
				} else {
					messageErreur.add("Les mots de passes sont différents");
					}
			//vérification pseudo unique
			 for (String string : messageErreur) {
				
			}
			
			if (true) {
				
			}
			
			//vérification email unique
			if (true) {
				
			}
			
			request.setAttribute("message", messageErreur);
			rd.forward(request, response);
			
	}

}
