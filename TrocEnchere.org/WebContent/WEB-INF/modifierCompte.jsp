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

<title>Troc-Enchères modification de compte</title>
</head>
<body>
	<header>
		<div>Troc-Enchères</div>
	</header>
	<main>
		<div>
		  <c:if test="${listeCodesErreur!=null && listeCodesErreur.size()>0 }">
		  	<c:forEach var="err" items="${listeCodesErreur}">
		 		<p>err</p>
		 	</c:forEach>
		  </c:if>
	  	</div>
		<div class="subtitle is-medium">
	 		Mon Profil
	 	</div>
		<form action="/TrocEnchere.org/nouveau-compte" method="post">
		   <div class="field is-horizontal">
  			 <div class="field-label is-normal">
			    <label class="label">Pseudo:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input" name="pseudoUtilisateur" >
		 		</div>
		 	</div>
		 </div>
		
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Nom:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input" name="nomUtilisateur" >
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Prénom:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input" name="prenomUtilisateur" >
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Email:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="email" class="input" name="emailUtilisateur" placeholder="xxx@yyy.zzz" >
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Téléphone:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input" name="telUtilisateur" >
		 		</div>
		 	</div>
		 </div>
		 
		 <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Rue:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input" name="rueUtilisateur" >
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Code postal:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input" name="cpUtilisateur" >
		 		</div>
		 	</div>
		 </div>
		 
		 <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Ville:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="text" class="input" name="villeUtilisateur" >
		 		</div>
		 	</div>
		 </div>
		 
		 
		 <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Mot de passe:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="password" class="input" name="mdpUtilisateur" >
		 		</div>
		 	</div>
		 </div>
		 
		  <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Confirmation:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
			    	<input type="password" class="input" name="confMdpUtilisateur" >
		 		</div>
		 	</div>
		 </div>
		  <c:if test="${message!=null && message.length()>0 }">
		 	<p>${message}</p>
		  </c:if>
		 
		 <div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Crédit:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">
    			<!--is this ok--, want to affiche the credit from the user-->
			    	<p>${utilisateur.getCredit()}</p>
		 		</div>
		 	</div>
		 </div>
		 
		<div class="field-body">
		    <div class="field">
		      <div class="control">
				  <button type="submit" class="button is-primary" name="bouton" value="enregistrer">Enregistrer</button>
				  <button type="submit" class="button is-primary" name="bouton" value="supprimer">Supprimer mon compte</button>
				  <a href=""><button type="submit" class="button is-primary is-light" name="bouton" value="retour">Retour</button></a> <%--rediriger vers Ou? --%>
			  </div>
			</div>
		</div>
		
		</form>
	</main>

</body>
</html>
</body>
</html>