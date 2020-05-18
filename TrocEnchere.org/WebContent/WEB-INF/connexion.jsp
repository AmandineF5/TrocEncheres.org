<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.2/css/bulma.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel="stylesheet" href="./CSS/main.css">
	<script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>

<title>Troc-Enchères connexion</title>

</head>

<body>

<header>
	<div>
		<img src="./images/banner.png" alt="banniere">
	</div>
</header>
	
<main>
	<div class="subtitle">
	 	<h1>Bonjour !</h1>
		<h2>Connectez-vous pour découvrir toutes nos fonctionnalités.</h2>
 	</div>
	
	<div>
		  <c:if test="${messageDeco!=null && messageDeco.length()>0 }">
		 	<p>${messageDeco}</p>
		  </c:if>
  	</div>
  	
  	<div>
		  <c:if test="${listeCodesErreur!=null && listeCodesErreur.size()>0 }">
		  	<c:forEach var="err" items="${listeCodesErreur}">
		 		<li>${err}</li>
		 	</c:forEach>
		  </c:if>
  	</div>
  	
  	

	<form action="/TrocEnchere.org/ConnecterCompte" method="post">
	 		
	
		   <div class="field is-horizontal">
  			 <div class="field-label is-normal">
			    <label class="label">Identifiant :</label>
			 </div>
			 
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input" name="pseudoUtilisateur" >
		 		</div>
		 	</div>
		 </div>

		<div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Mot de passe :</label>
			 </div>
			 
			 <div class="field-body">
    			<div class="field">
			    	<input type="password" class="input" name="mdpUtilisateur" >
		 		</div>
		 	</div>
		 </div>
		 
		 <div>
			  <c:if test="${message!=null && message.length()>0 }">
			 	<p>${message}</p>
			  </c:if>
		  </div>
		 
		 <div class="field-body">
		    <div class="field">
		      <div class="control">
				  <button type="submit" class="button main-button" name="bouton" value="valider">Connexion</button>
			  </div>
			</div>
		</div>
		
		<div>
		  <input type="checkbox" id="souvenir" name="souvenir">
		  <label for="souvenir">Se souvenir de moi</label>
		</div>
		
		<a href="url/vers/page/récupération/mdp" class="italic">Mot de passe oublié</a>
				
	</form>

	
	<form action="/TrocEnchere.org/nouveau-compte" method="get">
		<div class="field-body">
			    <div class="field">
			      <div class="control">
					  <button type="submit" class="button main-button" name="bouton" value="valider">Créer un nouveau compte</button>
				  </div>
				</div>
			</div>
		</form>
	
</main>






</body>

</html>