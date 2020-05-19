<!-- LESLIE / MARDI 19 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.2/css/bulma.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel="stylesheet" href="./main.css">
	<script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
<title>Troc-Enchères Vente terminée</title>
</head>
<body>
<header>
		<div>Troc-Enchères</div>
	</header>
	<main>
		<div class="subtitle is-medium">${enchere.getEncherit().getPseudo()}? a remporté l'enchère</div>

		<div class="field is-horizontal">
			<div class="field-label is-normal">
				<label class="label">Article:</label>
			</div>
			<div class="field-body">
				<div class="field">${vente.nomArticle}</div>
			</div>
		</div>
		
		<div class="field is-horizontal">
  			<div class="field-label is-normal">
			    <label class="label">Description:</label>
			 </div>
			 <div class="field-body">
    			<div class="field">${vente.description}</div>
		 	</div>
		 </div>
		 
		<div class="field is-horizontal"><img src="${pageContext.request.contextPath}/nomdupath/${vente.nomImage}" alt="Photo d'objet à vendre"></div>
		
		<div class="field is-horizontal">
			<div class="field-label is-normal">
				<label class="label">Meilleure offre:</label>
			</div>
			<div class="field-body">
				<div class="field">
   					${enchere.points} pts par <a href="/TrocEnchere.org/ContactAcheteur?Acheteur=${enchere.encherit}">?${enchere.getEncherit().getPseudo()}</a>
				</div>
			</div>
		</div>

		<div class="field is-horizontal">
			<div class="field-label is-normal">
				<label class="label">Mise à prix:</label>
			</div>
			<div class="field-body">${vente.miseAPrix}</div>
		</div>

		<div class="field is-horizontal">
			<div class="field-label is-normal">
				<label class="label">Fin de l'enchère:</label>
			</div>
			<div class="field-body">
				<div class="field">${vente.dateFinEncheres}</div>
			</div>
		</div>

		<div class="field is-horizontal">
			<div class="field-label is-normal">
				<label class="label">Retrait:</label>
			</div>
			<div class="field-body">
				<div class="field">
					${vente.getLieuRetrait().getRue()}<br />
					${vente.getLieuRetrait().getCodePostal()}
					${vente.getLieuRetrait().getVille()}
				</div>
			</div>
		</div>

		<div class="field is-horizontal">
			<div class="field-label is-normal">
				<label class="label">Vendeur:</label>
			</div>
			<div class="field-body">
				<div class="field">${vente.vendeur.pseudo}</div>
			</div>
		</div>
		
		<c:if test="${message!=null && message.length()>0 }">
		 	<p>${message}</p>
		  </c:if>

		<div class="field-body">
			<div class="field">
				<div class="control">
					<form action="/TrocEnchere.org/MesVentesVendues" method="post">
					<button type="submit" class="button is-primary" name="validationRetrait" value="">Retrait effectué</button> <!-- button qui débite l'acheteur et crédite le vendeur? -->
					</form>
					<a href="/TrocEnchere.org/ContactAcheteur?Acheteur=${enchere.encherit}"><button type="submit" class="button is-primary is-light" name="bouton" value="annuler">Contacter ?${enchere.encherit.pseudo}</button></a>
					<a href="/TrocEnchere.org/accueil"><button type="submit" class="button is-primary is-light" name="bouton" value="retour">Back</button></a>
				</div>
			</div>
		</div>

	</main>

</body>
</html>