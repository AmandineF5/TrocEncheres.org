
<!-- CORENTIN ET LESLIE -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="CSS/accueil.css">
<script defer
	src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>

<title>Accueil</title>
</head>

<body>

	<%@ include file="./fragments/header.jsp" %>

	<main>
		<h1>Bienvenue sur TrocEnchères.org</h1>
		<h2>Recherchez directement les ventes qui vous intéressent</h2>
		<section> <!-- OUTILS DE RECHERCHE -->
			<form action="/TrocEnchere.org/accueil" method="post">

				<div class="field is-vertical"> <!-- FILTRES EN CHECKBOX -->
					<h3>Filtres :</h3>
					<div class="field-body">
						<div class="field">
							<label class="checkbox"> <input class="squarebox" name="filtre" value="mesVentes" type="checkbox">Mes ventes
							</label>
						</div>
					</div>

					<div class="field-body">
						<div class="field">
							<label class="checkbox"> <input class="squarebox" name="filtre" value="mesEncheres" type="checkbox">Mes enchères en cours
							</label>
						</div>
					</div>

					<div class="field-body">
						<div class="field">
							<label class="checkbox"> <input class="squarebox" name="filtre" value="mesAcquisitions" type="checkbox">Mes acquisitions
							</label>
						</div>
					</div>

					<div class="field-body">
						<div class="field">
							<label class="checkbox"> <input class="squarebox" name="filtre" value="autresEncheres"  type="checkbox">Autres enchères
							</label>
						</div>
					</div>

				</div>

				<div class="field"> <!-- BARRE DE RECHERCHE -->
					<div class="control">
						<input name="venteByKeyword" class="input is-rounded" type="text"
							placeholder="Le nom de l'article contient" >
					</div>
				</div>

				<div class="field ">
		  			<div class="field-label is-normal">
					    <label class="label label-categorie">Catégorie:</label>
					 </div>
					 <div class="field-body">
		    			<div class="select is-rounded categorie">
		    				<select class="is-hovered " name="categorie" size="1">
								<option value="toutes">Toutes</option>
   								<c:forEach var="cat" items="${categories}" >
    								<option value="${cat.noCategorie}">${cat.libelle}</option>
    								</c:forEach>
 							</select>
							</div>
						</div>
					</div>
				
					
					 <div class="field-body"> <!-- BOUTON POUR LANCER LA RECHERCHE -->
					    <div class="field">
					      <div class="control">
						 	<button type="submit" class="button main-button big-button is-rounded" name="bouton" value="valider">Rechercher</button>
					  </div>
					</div>
				</div>
			</form>

		</section>

		<section>
			<!-- AFFICHAGE D'ANNONCES AU HASARD ? OU RESULTATS ? -->
			<h3 style="text-align:center">${titre}</h3>
			<div class="columns">
				
				<c:forEach var="ventes" items="${mesVentes}">
					
					<table class="column">
					    <thead>
					        <tr>
					            <th colspan="2"><a  href="/TrocEnchere.org/${ventes.toCall}?noVente=${ventes.noVente}">${ventes.nomArticle}</a> </th>
					        </tr>
					    </thead>
					    <tbody>
					    	<tr>
					            <td>Classement: </td>
					            <td>${classement}</td>
					        </tr>
					         <tr>
					            <td>Prix: </td>
					            <td>${ventes.prixVente}</td>
					        </tr>
					         <tr>
					            <td>Fin de l'enchère: </td>
					            <td>${ventes.dateFinEncheres}</td>
					        </tr>
					         <tr>
					            <td>Retrait: </td>
					            <td>${ventes.lieuRetrait.rue} ${ventes.lieuRetrait.codePostal} ${ventes.lieuRetrait.ville}</td>
					        </tr>
					         <tr>
					            <td>Vendeur: </td>
					            <td><a href="/TrocEnchere.org/afficher-compte?noUtilisateur=${ventes.vendeur.noUtilisateur}">${ventes.vendeur.pseudo}</a></td>
					        </tr>
					    </tbody>
					</table>
				
				</c:forEach>
				
			</div>
		</section>


	</main>
<%@ include file="./fragments/script.html" %>
</body>
</html>