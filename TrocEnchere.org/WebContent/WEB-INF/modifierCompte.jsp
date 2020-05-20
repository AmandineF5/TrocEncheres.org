
<!-- JANET -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="./CSS/main.css">
<script defer
	src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>

<title>Troc-Enchères modification de compte</title>
</head>
<body>
	<%@ include file="./fragments/header.jsp %>
	<main>
		<div class="subtitle is-medium">
	 		<h1>Modifier mon profil</h1>
	 	</div>
		<div>
			<c:if test="${listeCodesErreur!=null && listeCodesErreur.size()>0 }">
				<c:forEach var="err" items="${listeCodesErreur}">
					<li>${err}</li>
				</c:forEach>
			</c:if>
		</div>
	
		<form action="/TrocEnchere.org/ModifierCompte" method="post">
			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">Pseudo:</label>
				</div>
				<div class="field-body">
					<div class="field">
						<input type="text" class="input is-rounded" name="pseudoUtilisateur" value="${utilisateur.getPseudo()}" required>
					</div>
				</div>
			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">Nom:</label>
				</div>
				<div class="field-body">
					<div class="field">
						<input type="text" class="input is-rounded" name="nomUtilisateur" value="${utilisateur.getNom()}" required>
					</div>
				</div>
			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">Prénom:</label>
				</div>
				<div class="field-body">
					<div class="field">
						<input type="text" class="input is-rounded" name="prenomUtilisateur" value="${utilisateur.getPrenom()}" required>
					</div>
				</div>
			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">Email:</label>
				</div>
				<div class="field-body">
					<div class="field">
						<input type="email" class="input is-rounded" name="emailUtilisateur"
							value="${utilisateur.getEmail()}" required>
					</div>
				</div>
			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">Téléphone:</label>
				</div>
				<div class="field-body">
					<div class="field">
						<input type="text" class="input is-rounded" name="telUtilisateur" value="${utilisateur.getTelephone()}" required>
					</div>
				</div>
			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">Rue:</label>
				</div>
				<div class="field-body">
					<div class="field">
						<input type="text" class="input is-rounded" name="rueUtilisateur" value="${utilisateur.getRue()}" required>
					</div>
				</div>
			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">Code postal:</label>
				</div>
				<div class="field-body">
					<div class="field">
						<input type="text" class="input is-rounded" name="cpUtilisateur" value="${utilisateur.getCodePostal()}" required>
					</div>
				</div>
			</div>

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">Ville:</label>
				</div>
				<div class="field-body">
					<div class="field">
						<input type="text" class="input is-rounded" name="villeUtilisateur" value="${utilisateur.getVille()}" required>
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

			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">Crédit:</label>
				</div>
				<div class="field-body">
					<div class="field">
						<!--is this ok--, want to affiche the credit from the user-->
						${utilisateur.getCredit()}
					</div>
				</div>
			</div>

			<div class="field-body">
				<div class="field">
					<div class="control">
						<button type="submit" class="button main-button is-rounded" name="bouton"
							value="enregistrer">Enregistrer</button>
						<a href="${pageContext.request.contextPath}/Accueil"><button type="submit"
								class="button main-button is-rounded is-light" name="bouton" value="retour">Retour</button></a>
						<%--rediriger vers Acceuil --%>
					</div>
				</div>
			</div>

		</form>



	</main>

<%@ include file="./fragments/script.html" %>
</body>
</html>