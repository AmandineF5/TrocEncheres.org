package fr.eni.projet01.trocenchere.ihm.vente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet01.trocenchere.bll.VenteManager;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

/**
 * @author Leslie
 * Servlet implementation class VenteRemportee
 */
@WebServlet("/VenteRemportee")
public class VenteRemportee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VenteManager vM = new VenteManager();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VenteRemportee() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noVenteAAfficher = Integer.parseInt(request.getParameter("noVente"));
		Vente venteAAfficher = new Vente();
		try {
			venteAAfficher = vM.selectionnerVenteById(noVenteAAfficher);
			request.setAttribute("vente", venteAAfficher);
			 
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeErreur());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vente/venteRemportee.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
