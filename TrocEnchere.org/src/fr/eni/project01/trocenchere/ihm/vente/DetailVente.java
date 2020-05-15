package fr.eni.project01.trocenchere.ihm.vente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet01.trocenchere.bll.EnchereManager;
import fr.eni.projet01.trocenchere.bll.VenteManager;
import fr.eni.projet01.trocenchere.bo.Enchere;
import fr.eni.projet01.trocenchere.bo.Retrait;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

/**
 * Servlet implementation class DetailVente
 */
@WebServlet("/DetailVente")
public class DetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       VenteManager vM = new VenteManager();
       EnchereManager eM = new EnchereManager();
  	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*test for jsp
		Vente venteAAfficher = new Vente();
		venteAAfficher.setMiseAPrix(3);
		venteAAfficher.setDescription("This is the article");
		venteAAfficher.setNomArticle("Tractor");
		Retrait pickUp = new Retrait();
		pickUp.setRue("rue");
		pickUp.setCodePostal("codePostal");
		pickUp.setVille("ville");
		venteAAfficher.setLieuRetrait(pickUp);
		Utilisateur test = new Utilisateur();
		test.setPseudo("Yoyoma");
		venteAAfficher.setVendeur(test);
		
		Enchere highestEnchere = new Enchere();
		highestEnchere.setPoints(6);
		Utilisateur person = new Utilisateur();
		person.setPseudo("face");
		highestEnchere.setEncherit(person);
		*/
		
		//get noVente from incoming page
		String noVente = (String) request.getAttribute("NoVente");
		int noVenteAAfficher = Integer.parseInt(noVente);
		
		//find the Vente in the database
		Vente venteAAfficher = new Vente();
		try {
			 venteAAfficher = vM.selectionnerVenteById(noVenteAAfficher);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeErreur());
		}
		
		//set vente as attribute
		request.setAttribute("vente", venteAAfficher);
		
		
		//find the highest bid by id no
		Enchere highestEnchere = new Enchere();
		try {
			highestEnchere = eM.trouverHighestBid(noVenteAAfficher);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeErreur());
		}
		//set bid as attribute
		
		request.setAttribute("enchere", highestEnchere);
		
				
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vente/detailVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		
		//get id from delete button
		int noVente= Integer.parseInt(request.getParameter("button"));
		//send id to delete the vente
		try {
			vM.supprimerVente(noVente);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeErreur());
		}
		response.sendRedirect(request.getContextPath()+"/NouvelleVente");
		doGet(request, response);
	}

}
