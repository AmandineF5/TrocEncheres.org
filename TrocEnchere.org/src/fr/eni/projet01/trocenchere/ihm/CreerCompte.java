package fr.eni.projet01.trocenchere.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;
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
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/creationCompte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
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
			
			String messageErreur = "Les mots de passe sont différents";
			RequestDispatcher rd = null;
			
			UtilisateurManager UM = new UtilisateurManager();
			
			//comparaison mot de passe
			if (mdpUtilisateur.equals(confMdpUtilisateur)) {
				try {
					UM.ajouterUtilisateur(utilisateur);
					
					HttpSession session = request.getSession();
		            session.setAttribute("utilisateur", utilisateur);
		            
		            String pseudoForCookie = utilisateur.getPseudo();
		            String encodedpseudoForCookie = Base64.getEncoder().encodeToString(pseudoForCookie.getBytes()); //pour autoriser les espaces dans le pseudo
		            Cookie pseudoUtilisateur = new Cookie("pseudo", encodedpseudoForCookie);
		            response.addCookie(pseudoUtilisateur);
		            
		            // lien vers servlet de gestion du compte;
		            response.sendRedirect("");
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur",e.getListeErreur());
					doGet(request, response);  
				}
				
				} else {
					request.setAttribute("message", messageErreur);
					doGet(request, response);  //retour cr�ation compte 
					}

			
			
	}

}
