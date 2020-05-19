
<!-- JANET -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bulma@0.8.2/css/bulma.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" href="./main.css">
<script defer
	src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
<!-- created by Janet -->
<title>Troc-Enchères Détail Vente</title>
</head>
<body>
	<header>
		<div>Troc-Enchères</div>
	</header>
	<main>
		<div class="subtitle is-medium">Détail Vente</div>

		<div class="field is-horizontal">
			<div class="field-label is-normal">
				<label class="label">Article:</label>
			</div>
			<div class="field-body">
				<div class="field">${vente.nomArticle}</div>
			</div>
		</div>

		<div class="field is-horizontal">
			<img
				src="${pageContext.request.contextPath}/nomdupath/${vente.nomImage}"
				alt="Photo d'objet à vendre">
		</div>

		<div class="field is-horizontal">
			<div class="field-label is-normal">
				<label class="label">Meilleure offre:</label>
			</div>
			<div class="field-body">
				<div class="field">
					<c:choose>
						<c:when test="${enchere.points == null}">
   			 			0
  						</c:when>
						<c:otherwise>
   						 ${enchere.points} pts par ${enchere.getEncherit().getPseudo()}
  						</c:otherwise>
					</c:choose>


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
				<a
					href="${pageContext.request.contextPath}/afficher-compte?noUtilisateur=${vente.vendeur.noUtilisateur}"><div
						class="field">${vente.vendeur.pseudo}</div></a>
			</div>
		</div>

		
				<c:if test="${message!=null && message.length()>0 }">
					<p>${message}</p>
				</c:if>
		

		<form action="/TrocEnchere.org/DetailVenteEncherir" method="post">
			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">Ma proposition:</label>
				</div>
				<div class="field-body">
					<div class="field">
						<input type="number" class="input" name="encherir"
							max="${utilisateur.credit}" min="${enchere.points+1}"
							value="${enchere.points+1}"> 
							<input type="hidden"
							name="noVente" value="${vente.noVente}">

					</div>

				</div>
				<div class="field-body">
					<div class="field">
						<div class="control">
							<button type="submit" class="button is-primary" name="encherir">Enchérir</button>
						</div>
					</div>
				</div>
			</div>
		</form>

		<a href="${pageContext.request.contextPath}/Accueil"><button type="submit"
				class="button is-primary is-light" name="bouton" value="retour">Back</button></a>
		
	</main>

</body>
</html>