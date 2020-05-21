
// Corentin et Leslie

package fr.eni.projet01.trocenchere.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet01.trocenchere.bll.EnchereManager;
import fr.eni.projet01.trocenchere.bll.VenteManager;
import fr.eni.projet01.trocenchere.bo.Categorie;
import fr.eni.projet01.trocenchere.bo.Enchere;
import fr.eni.projet01.trocenchere.bo.Utilisateur;
import fr.eni.projet01.trocenchere.bo.Vente;
import fr.eni.projet01.trocenchere.erreurs.BusinessException;

/**
 * @author Corentin et Leslie
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VenteManager vM = new VenteManager();
	EnchereManager eM = new EnchereManager();
	
	String[] filtresChk; 
	List<String> listFiltres;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récupérer les informations de l'utilisateur
		Utilisateur utilisateur = extractedUserSession(request);		
		request.setAttribute("utilisateur", utilisateur);
		//Récupérer et afficher les catégories
		List<Categorie> categories = toutesCategorie();		
		request.setAttribute("categories", categories);	
		//affichage de toutes les ventes
		List<Vente> lesVentes = new ArrayList<Vente>();
		try {
			lesVentes = vM.selectionnerToutesVentes();
			request.setAttribute("mesVentes", lesVentes);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		String servletToCall = null;
		for (Vente v : lesVentes) {
			if (utilisateur==null) {
				servletToCall = "DetailVente";
				v.setToCall(servletToCall);
			} else if (utilisateur!=null){
			
				try {
					servletToCall = this.redirectionVente(utilisateur.getNoUtilisateur(), v);				
					v.setToCall(servletToCall);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
	
		String titre = "Top classement des ventes en cours";
		request.setAttribute("titre", titre);
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//doGet(request, response);
		
		Utilisateur utilisateurSession = extractedUserSession(request);		
		request.setAttribute("utilisateur", utilisateurSession);
		
		
		List<Vente> mesVentes = new ArrayList<Vente>();
		List<Enchere> mesEncheres = new ArrayList<Enchere>();
		List<Vente> mesAcquisitions = new ArrayList<Vente>();
		List<Vente> autresEnchères = new ArrayList<Vente>();
		List<Vente> venteByKeyword = new ArrayList<Vente>();
		List<Vente> venteByCategory = new ArrayList<Vente>();
		
		LocalDate ajd = LocalDate.now();

		List<Vente> resultatAAfficher = new ArrayList<>();  //puis add les différentes ventes succèsivement en fct de ce qui a été coché
		
		try {
			filtresChk = request.getParameterValues("filtre");
			listFiltres = new ArrayList<String>();
			
			if (filtresChk!=null && filtresChk.length>0) {
				for (String s : filtresChk) {
					if (s!=null) {
						listFiltres.add(s);
					}
				}
			} else if (filtresChk==null) {
				listFiltres=null;
			}
			
			
			if (listFiltres==null) {
				resultatAAfficher = vM.selectionnerToutesVentes();
			} else if (listFiltres!=null && listFiltres.size()>0) {
				
				for (String s : listFiltres) {
					if (s.equals("mesVentes")) {
						mesVentes = vM.selectionnerVenteUtilisateur(utilisateurSession.getNoUtilisateur());
						for (Vente vente : mesVentes) {
								resultatAAfficher.add(vente); 
						}
					}
					if (s.equals("mesEncheres")) {
						mesEncheres = eM.selectionnerEnchereByIdUser (utilisateurSession.getNoUtilisateur());
						for (Enchere enchere : mesEncheres) {
							Vente vente = enchere.getConcerne();
							if (vente.getDateFinEncheres().isAfter(ajd)) {  //ventes en cours
								resultatAAfficher.add(vente);
							} 
						}
					}
					if (s.equals("mesAcquisitions")) {
						List<Vente> lesVentes = vM.selectionnerToutesVentes();
						for (Vente vente : lesVentes) {
							Enchere enchereGagnante = eM.trouverHighestBid(vente.getNoVente());
							Utilisateur utilisateurAVerifier = enchereGagnante.getEncherit();
							int noUserToCheck = utilisateurAVerifier.getNoUtilisateur();
							int noUserSession = utilisateurSession.getNoUtilisateur();
							if (noUserToCheck == noUserSession && vente.getDateFinEncheres().isBefore(ajd)) { //ventes terminées
								resultatAAfficher.add(vente);
							}
						}
					}
					if (s.equals("autresEncheres")) {
						resultatAAfficher = vM.selectionnerToutesVentes();  //on sélectionne ABSOLUMENT toutes les ventes du site
						
						mesVentes = vM.selectionnerVenteUtilisateur(utilisateurSession.getNoUtilisateur());  //on retire mesVentes
						for (Vente vente : mesVentes) {
								resultatAAfficher.remove(vente);
						}
						
						mesEncheres = eM.selectionnerEnchereByIdUser (utilisateurSession.getNoUtilisateur());  //on retire mesEncheres
						for (Enchere enchere : mesEncheres) {
							Vente vente = enchere.getConcerne();
							if (vente.getDateFinEncheres().isAfter(ajd)) {  //ventes en cours
								resultatAAfficher.remove(vente);
							}
						}
						
						List<Vente> lesVentes = vM.selectionnerToutesVentes();  //on retire mesAcquisitions
						for (Vente vente : lesVentes) {
							Enchere enchereGagnante = eM.trouverHighestBid(vente.getNoVente());
							Utilisateur utilisateurAVerifier = enchereGagnante.getEncherit();
							if (vente.getDateFinEncheres().isBefore(ajd) && utilisateurAVerifier == utilisateurSession ) { //ventes terminées
								resultatAAfficher.remove(vente);
							}
						}
					}
				
				}
			}
			
			
			
			if (request.getParameter("venteByKeyword").length()>0) {
				
				if (listFiltres==null) {
					resultatAAfficher = vM.selectionnerVenteByKeyWord(request.getParameter("venteByKeyword"));
				} else {
					List<Vente> listeVenteMotCle = vM.selectionnerVenteByKeyWord(request.getParameter("venteByKeyword"));
					List<Vente> listeTempo = new ArrayList<Vente>();
					for (Vente vente : resultatAAfficher) {
						listeTempo.add(vente);
					}
					resultatAAfficher.clear();
					
					for (Vente venteTempo : listeTempo) {
						Boolean isEqual = false;
						for (Vente venteMotCle : listeVenteMotCle) {
							if (venteMotCle==venteTempo) {
								isEqual = true;	
							}
							if (isEqual) {
								resultatAAfficher.add(venteMotCle);
								isEqual = false;
							}
						}
					}
					
				}
				
			}
			
			if (request.getParameter("categorie")!=null && request.getParameter("categorie").equalsIgnoreCase("toutes") ) {	//cas toutes catégories
				
				if (listFiltres==null && request.getParameter("venteByKeyword").length()==0) {  
					resultatAAfficher = vM.selectionnerToutesVentes();
					
				} else {  //au moins 1 filtre appliqué => resultatAAfficher reste le même
					
				}

			} else {  //cas 1 catégorie
				
				if (request.getParameter("categorie").trim().length()>0 && listFiltres==null && request.getParameter("venteByKeyword").length()==0) {  
					int noCategorie = Integer.parseInt(request.getParameter("categorie").trim());
					resultatAAfficher = vM.selectionnerVenteByCategory(noCategorie);	
					
				} else if (request.getParameter("venteByKeyword").length()>0 && request.getParameter("categorie").trim().length()>0){ //au moins 1 filtre appliqué => on compare
					
					int noCategorie = Integer.parseInt(request.getParameter("categorie").trim());
					List<Vente> listeVenteCategorie = vM.selectionnerVenteByCategory(noCategorie);
					List<Vente> listeTempo = new ArrayList<Vente>();
					
					for (Vente vente : resultatAAfficher) {
						listeTempo.add(vente);
					}
					resultatAAfficher.clear();
					
					for (Vente venteTempo : listeTempo) {
						Boolean isEqual = false;
						for (Vente venteMotCle : listeVenteCategorie) {
							if (venteMotCle.getNoVente()==venteTempo.getNoVente()) {
								isEqual = true;	
							}
							if (isEqual) {
								resultatAAfficher.add(venteMotCle);
								isEqual = false;
							}
						}
					}
				}
				
			}			
					
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		String servletToCall = null;
		String classement = null;
		for (Vente vente : resultatAAfficher) {
			//redirection en fonction de l'état de la vente
			try {
				servletToCall = this.redirectionVente(utilisateurSession.getNoUtilisateur(), vente);				
				vente.setToCall(servletToCall);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			//classement de mon enchère
			try {
				classement = eM.trouverClassementEnchere(vente.getNoVente(), utilisateurSession.getNoUtilisateur());
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("classement", classement);
		
		
		
		//Définir les informations à renvoyer à la JSP (accueil.jsp)
		request.setAttribute("mesVentes", resultatAAfficher);  //à changer le nom de l'attibut plus tard
		
		
		//Récupérer et afficher les catégories
		List<Categorie> categories = toutesCategorie();			
		request.setAttribute("categories", categories);	
		
		String titre = "Résultat de votre recherche";
		request.setAttribute("titre", titre);
		
		
		
		
		
		//Déléguer la réponse à la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
		//doGet(request, response);
	}
	
	private Utilisateur extractedUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		return utilisateur;
	}

	private List<Categorie> toutesCategorie (){
		List<Categorie> categories = new ArrayList<Categorie>();
		try {
			categories = vM.selectionnerCategorie();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	private String redirectionVente (int noUtilisateur, Vente vente) throws BusinessException {
		String servletToCall=null;
		Enchere enchere = eM.trouverHighestBid(vente.getNoVente());
		
		if (noUtilisateur == vente.getVendeur().getNoUtilisateur()) {
				if (LocalDate.now().isBefore(vente.getDateFinEncheres())) {
					servletToCall = "DetailVente";
				}else {
					servletToCall = "MesArticlesVendus";
				}	
		
		} else if (noUtilisateur==enchere.getEncherit().getNoUtilisateur() && LocalDate.now().isAfter(vente.getDateFinEncheres())) {
			servletToCall = "VenteRemportee";
		}else {
			Boolean enchereExist = eM.verifierEnchereExistant(noUtilisateur, vente.getNoVente());
			if (enchereExist) {
				servletToCall="DetailVenteAnnulerEncherir";
			} else {
				servletToCall="DetailVenteEncherir";
			}			
		}
	
		return servletToCall;
	}
}
