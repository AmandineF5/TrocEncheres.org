
<!-- AMANDINE  -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.2/css/bulma.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel="stylesheet" href="./CSS/main.css">
	<script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>

<title>Troc-Enchères création de compte</title>
</head>
<body>
	<%@ include file="./fragments/header.jsp" %>
	<main>
		<div class="subtitle is-medium">
	 		<h1>Bienvenue chez TrocEnchères.org</h1>
	 	</div>
	 	<div class="subtitle is-small">
	 		<h2>Veuillez remplir les champs ci-dessous</h2>
	 	</div>
  	
	  	<div>
			  <c:if test="${listeCodesErreur!=null && listeCodesErreur.size()>0 }">
			  	<c:forEach var="err" items="${listeCodesErreur}">
			 		<li>${err}</li>
			 	</c:forEach>
			  </c:if>
	  	</div>
	 	
		<form action="/TrocEnchere.org/nouveau-compte" method="post">
		   <div class="field is-horizontal">
  			 <div class="field-label is-normal">
			    <label class="label">Pseudo:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input is-rounded" name="pseudoUtilisateur" required>
		 		</div>
		 	</div>
		 </div>
		
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Nom:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input is-rounded" name="nomUtilisateur" required>
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Prénom:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input is-rounded" name="prenomUtilisateur" required>
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Email:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="email" class="input is-rounded" name="emailUtilisateur" placeholder="xxx@yyy.zzz" required>
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Téléphone:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input is-rounded" name="telUtilisateur" required>
		 		</div>
		 	</div>
		 </div>
		 
		 <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Rue:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input is-rounded" name="rueUtilisateur" required>
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Code postal:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input is-rounded" name="cpUtilisateur" required>
		 		</div>
		 	</div>
		 </div>
		 
		 <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Ville:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input is-rounded" name="villeUtilisateur" required>
		 		</div>
		 	</div>
		 </div>
		 
		 
		 <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Mot de passe:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="password" class="input is-rounded" name="mdpUtilisateur" required>
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Confirmation:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="password" class="input is-rounded" name="confMdpUtilisateur" required>
		 		</div>
		 	</div>
		 </div>
		  <c:if test="${message!=null && message.length()>0 }">
		 	<p>${message}</p>
		  </c:if>
		  
		  <div>
			<input type="checkbox" id="terms" name="terms">
		  	<label class="text-small-pad" > « J'accepte les Conditions Générales de Vente et les Conditions Générales d'Utilisation »</label>
		</div>
		 
		<div class="field-body">
		    <div class="field">
		      <div class="control">
				  <button type="submit" class="button main-button is-rounded" name="bouton" value="valider">Valider</button>
				  <a href=""><button type="submit" class="button main-button is-rounded is-light" name="bouton" value="annuler">Annuler</button></a> <%--rediriger vers l'accueil --%>
			  </div>
			</div>
		</div>
		
		</form>
	</main>
<%@ include file="./fragments/script.html" %>
</body>
</html>
