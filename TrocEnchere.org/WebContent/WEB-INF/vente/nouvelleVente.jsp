<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.2/css/bulma.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel="stylesheet" href="./main.css">
	<script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>

<title>Troc-Enchères Nouvelle vente</title>
</head>
<body>
	<header>
		<div>Troc-Enchères</div>
	</header>
	<main>
		<div class="subtitle is-medium">
	 		Nouvelle vente
	 	</div>
	 	
		<form action="${request.contextPath}/NouvelleVente" method="post">
		   <div class="field is-horizontal">
  			 <div class="field-label is-normal">
			    <label class="label">Article:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input" name="nomarticle" >
		 		</div>
		 	</div>
		 </div>
		
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Description:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
    				<textarea class="input" name="description" placeholder="Descritpion du produit" rows="10" cols="30"></textarea>
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Catégorie:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
    				<select name="categorie" size="1">
    					<option></option>
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
    				<!-- <button name="photoArticle" type="button" onclick="">UPLOADER</button> -->
    				<input type="file" id="myPhoto" name="photoArticle">
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Mise à prix:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="number" class="input" name="prixInitial" >
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Fin de l'enchère:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="datetime-local" class="input" name="dateFinEncheres" >
		 		</div>
		 	</div>
		 </div>
		 
		 <fieldset>
		 	<legend>Retrait</legend>
			 
			 <div class="field is-horizontal">
	  			<div class="field-label is-normal">
				    <label class="label">Rue:</label>
				 </div>
				 <div class="field-body">
	    			<div class="field">
				    	<input type="text" class="input" name="rueUtilisateur" >${utilisateur.rue}
			 		</div>
			 	</div>
			 </div>
			 
			  <div class="field is-horizontal">
	  			<div class="field-label is-normal">
				    <label class="label">Code postal:</label>
				 </div>
				 <div class="field-body">
	    			<div class="field">
				    	<input type="text" class="input" name="cpUtilisateur" >${utilisateur.codePostal}
			 		</div>
			 	</div>
			 </div>
			 
			 <div class="field is-horizontal">
	  			<div class="field-label is-normal">
				    <label class="label">Ville:</label>
				 </div>
				 <div class="field-body">
	    			<div class="field">
				    	<input type="text" class="input" name="villeUtilisateur" >${utilisateur.ville}
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
				  <button type="submit" class="button is-primary" name="bouton" value="valider">Publier</button>
				  <button type="submit" class="button is-primary" name="bouton" value="valider">Enregistrer</button>
				  <a href=""><button type="submit" class="button is-primary is-light" name="bouton" value="annuler">Annuler</button></a> <%--rediriger vers l'accueil --%>
			  </div>
			</div>
		</div>
		
		</form>
	</main>

</body>
</html>