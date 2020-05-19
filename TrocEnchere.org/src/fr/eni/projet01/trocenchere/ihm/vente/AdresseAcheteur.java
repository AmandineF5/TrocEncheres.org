
// LESLIE

package fr.eni.projet01.trocenchere.ihm.vente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet01.trocenchere.bll.EnchereManager;
import fr.eni.projet01.trocenchere.bll.UtilisateurManager;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

/**
 * @author Leslie
 * Servlet implementation class AdresseAcheteur
 */
@WebServlet("/AdresseAcheteur")
public class AdresseAcheteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager uM = new UtilisateurManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdresseAcheteur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noAcheteur = 2;//Integer.parseInt(request.getParameter("Acheteur"));
		Utilisateur acheteur = new Utilisateur();
		try {
			acheteur = uM.selectionnerUtilisateurById(noAcheteur);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("acheteur", acheteur);
		request.getRequestDispatcher("/WEB-INF/vente/adresseAcheteur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
