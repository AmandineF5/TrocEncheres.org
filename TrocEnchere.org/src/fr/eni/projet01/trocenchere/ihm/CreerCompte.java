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
import fr.eni.projet01.trocenchere.bll.UtilisateurManager;

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
		
		Utilisateur utilisateur = new Utilisateur();  
			utilisateur.setPseudo(request.getParameter("pseudoUtilisateur"));
			utilisateur.setNom(request.getParameter("nomUtilisateur"));
			utilisateur.setPrenom(request.getParameter("prenomUtilisateur"));
			utilisateur.setEmail(request.getParameter("emailUtilisateur"));
			utilisateur.setTelephone(request.getParameter("telUtilisateur"));
			utilisateur.setRue(request.getParameter("rueUtilisateur"));
			utilisateur.setCodePostal(request.getParameter("cpUtilisateur"));
			utilisateur.setVille(request.getParameter("villeUtilisateur"));
			utilisateur.setMotDePasse(request.getParameter("mdpUtilisateur"));
			utilisateur.setMotDePasse(request.getParameter("confMdpUtilisateur"));
			utilisateur.setCredit(0);
			utilisateur.setAdministrateur(false);
			
			String mdpUtilisateur = request.getParameter("mdpUtilisateur");
			String confMdpUtilisateur = request.getParameter("confMdpUtilisateur");
			
			List<String> messageErreur = new ArrayList<String>();
			RequestDispatcher rd = null;
			
			UtilisateurManager UM = new UtilisateurManager();
			
			//comparaison mot de passe
			if (mdpUtilisateur.equals(confMdpUtilisateur)) {
				UM.ajouterUtilisateur(utilisateur);
				rd = request.getRequestDispatcher("/WEB-INF/xxx/xxx");  //page suivante
				} else {
					messageErreur.add("Les mots de passes sont différents");
					request.setAttribute("message", messageErreur);
					rd = request.getRequestDispatcher("/WEB-INF/xxx/xxx");  //retour création compte 
					}

			rd.forward(request, response);
			
	}

}
