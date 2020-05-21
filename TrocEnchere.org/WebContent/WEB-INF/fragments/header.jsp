<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header>
	<div>
		<img src="./images/banner.png" alt="banniere">
	</div>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
		  <span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarTogglerDemo01">
		  <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		    <li class="nav-item ">
		      <a class="nav-link" href="/TrocEnchere.org/accueil" style="font-weight: bold">Accueil</a>
		    </li>
		     <li class="nav-item">
		      <a class="nav-link" href="/TrocEnchere.org/NouvelleVente">Déposer une vente</a>
		    </li>
		     <li class="nav-item">
		     	<div class="nav-link" id="menu1" onclick="afficheMenu(this)">
		     		<a href="#">Mon profil</a>
		     	</div>
		      
		      	 <ul id="sousmenu1" style="display:none">
				     <li class="nav-item sousmenu">
				     	 <a class="nav-link" href="/TrocEnchere.org/ModifierCompte">Modifier mon profil</a>
				     </li>
				     <li class="nav-item sousmenu">
				      	<a class="nav-link" href="/TrocEnchere.org/ConnecterCompte">Connexion</a>
				     </li>
				     <li class="nav-item sousmenu">
				      <a class="nav-link" href="/TrocEnchere.org/DeconnecterCompte">Déconnexion</a>
				    </li>
			    </ul>
		    </li>
		  </ul>  
		</div>
		<div class="userco">
			<c:if test="${!empty sessionScope.utilisateur.pseudo}"> 
			  	<p>Connecté(e) en tant que ${sessionScope.utilisateur.pseudo}<br>
			  	<a class="nav-link" href="/TrocEnchere.org/DeconnecterCompte" style="font-style: italic">Déconnexion</a></p>
			  	
			 </c:if>
		</div>
	</nav>
	
</header>