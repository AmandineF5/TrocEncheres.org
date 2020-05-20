<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.2/css/bulma.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel="stylesheet" href="CSS/main.css">
	<script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
<title>Troc-Enchères Vente terminée</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp" %>
	<main>

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
   					${vente.prixVente} pts
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
				<label class="label">Retrait:</label>
			</div>
			<div class="field-body">
				<div class="field">
					${vente.getLieuRetrait().getRue()}
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
		
		<div class="field is-horizontal">
			<div class="field-label is-normal">
				<label class="label">Tel:</label>
			</div>
			<div class="field-body">
				<div class="field">${vente.vendeur.telephone}</div>
			</div>
		</div>
		
		<c:if test="${message!=null && message.length()>0 }">
		 	<p>${message}</p>
		  </c:if>

		<div class="field-body">
			<div class="field">
				<div class="control">
					<a href="/TrocEnchere.org/accueil"><button type="submit" class="button is-primary is-light" name="retour" value="retour">Back</button></a>
				</div>
			</div>
		</div>

	</main>
<%@ include file="../fragments/script.html" %>
</body>
</html>