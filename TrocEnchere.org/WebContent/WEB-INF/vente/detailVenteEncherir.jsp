
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
<link rel="stylesheet" href="CSS/main.css">
<link rel="stylesheet" href="CSS/ventes.css">
<script defer
	src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
<!-- created by Janet -->
<title>Troc-Enchères Détail Vente</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	<main>
	<div class="rubrique">
		 	<h2>Détails de la vente</h2>
		 </div>
		 <table>
			 <thead>
				 <tr>
		           <th colspan="2">${vente.nomArticle}</th>
		        </tr>
        	</thead>
			 <tbody>
			 <tr>
		           <th colspan="2"><img src="images/ventes/${vente.noVente}.jpg" alt="article"></th>
		        </tr>
		         <tr>
		            <td class="row-title">Meilleure offre: </td>
		            <td>
						<c:choose>
							<c:when test="${enchere.points == null}">
	   			 			0
	  						</c:when>
							<c:otherwise>
	   						 ${enchere.points} points par ${enchere.getEncherit().getPseudo()}
	  						</c:otherwise>
						</c:choose>
					</td>
		        </tr>
		        <tr>
		            <td class="row-title">Prix: </td>
		            <td>${vente.miseAPrix} points</td>
		        </tr>
		         <tr>
		            <td class="row-title">Fin de l'enchère: </td>
		            <td>${vente.dateFinEncheres}</td>
		        </tr>
		         
		     </tbody>
		 </table>

		<div class="side-side">
			
				<img src="images/delivery.png" class="side1" alt="retrait">
			
		
			<div class="field is-horizontal side2">
				<div class="side2a">
					<h4>Retrait:</h4>
					${vente.getLieuRetrait().getRue()}<br />
					${vente.getLieuRetrait().getCodePostal()}
					${vente.getLieuRetrait().getVille()}

				</div>
				
				<div class="side2b">
					<h4>Vendeur:</h4>
					<a
						href="${pageContext.request.contextPath}/afficher-compte?noUtilisateur=${vente.vendeur.noUtilisateur}">
						${vente.vendeur.pseudo}</a>
				</div>
	
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
						<input type="number" class="input is-rounded" name="encherir"
							max="${utilisateur.credit}" min="${enchere.points+1}"
							value="${enchere.points+1}">
					</div>

				<div class="field-body">
						<div class="field">
							<div class="control">
								<button type="submit" class="button main-button is-rounded " name="encherir">Enchérir</button>
								<a href="${pageContext.request.contextPath}/Accueil"><button
									type="submit" class="button main-button is-rounded  is-light" name="bouton"
									value="retour">Retour</button></a>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</form>

		

	</main>
	<%@ include file="../fragments/script.html"%>
</body>
</html>