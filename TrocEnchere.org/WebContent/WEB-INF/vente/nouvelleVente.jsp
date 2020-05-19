
<!-- LESLIE -->

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
	<link rel="stylesheet" href="CSS/main.css">
	<script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>

<title>Troc-Enchères Nouvelle vente</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp" %>
	<main>
		<div class="subtitle is-medium">
	 		<h1>Déposez votre annonce</h1>
	 	</div>
	 	
		<form action="/TrocEnchere.org/NouvelleVente" method="post" > <!-- enctype="multipart/form-data" -->
		   <div class="field is-horizontal">
  			 <div class="field-label is-normal">
			    <label class="label">Article:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input is-rounded" name="nomArticle" >
		 		</div>
		 	</div>
		 </div>
		
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Description:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
    				<textarea class="input is-rounded" name="description" placeholder="Descritpion du produit" rows="20" cols="30"></textarea>
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Catégorie:</label>
			 </div>
			 <div class="field-body ">
    			<div class="select is-rounded">
    				<select class="is-hovered" name="categorie" size="1">
    					<c:forEach var="cat" items="${categories}" >
    						<option value="${cat.noCategorie} ">${cat.libelle} </option>
    					</c:forEach>
    				</select>
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Photo de l'article:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
    				<input type="file" class="input is-rounded" id="myPhoto" name="nomImage">
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Mise à prix:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="number" class="input is-rounded" name="prixInitial" min="0">
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Fin de l'enchére:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="date" class="input is-rounded" name="dateFinEncheres" >
		 		</div>
		 	</div>
		 </div>
		 
		 <fieldset>
		 	<h2>Retrait</h2>
			 
			 <div class="field is-horizontal">
	  			<div class="field-label is-normal">
				    <label class="label">Rue:</label>
				 </div>
				 <div class="field-body">
	    			<div class="field">
				    	<input type="text" class="input is-rounded" name="rueUtilisateur" value="${utilisateur.rue}">
			 		</div>
			 	</div>
			 </div>
			 
			  <div class="field is-horizontal">
	  			<div class="field-label is-normal">
				    <label class="label">Code postal:</label>
				 </div>
				 <div class="field-body">
	    			<div class="field">
				    	<input type="text" class="input is-rounded" name="cpUtilisateur" value="${utilisateur.codePostal}">
			 		</div>
			 	</div>
			 </div>
			 
			 <div class="field is-horizontal">
	  			<div class="field-label is-normal">
				    <label class="label">Ville:</label>
				 </div>
				 <div class="field-body">
	    			<div class="field">
				    	<input type="text" class="input is-rounded" name="villeUtilisateur" value="${utilisateur.ville}">
			 		</div>
			 	</div>
			 </div>
		 </fieldset>
		 
		 
		  <c:if test="${message!=null && message.length()>0 }">
		 	<p>${message}</p>
		  </c:if>
		  
		 
		<div class="field-body">
		    <div class="field">
		      <div class="control">
				  <button type="submit" class="button main-button is-rounded" name="bouton" value="Publier">Publier</button>
				  
				  <a href="/TrocEnchere.org/accueil"><button type="submit" class="button main-button is-rounded is-light" name="bouton" value="annuler">Annuler</button></a>
			  </div>
			</div>
		</div>
		
		</form>
	</main>
<%@ include file="../fragments/script.html" %>
</body>
</html>