
<!-- CORENTIN ET LESLIE / LUNDI 18 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<title>Accueil</title>
</head>

<body>

	<header>
		<nav class="navbar" role="navigation" aria-label="main navigation">
			<!-- BARRE DE NAVIGATION -->

			<div class="navbar-brand">
				<!--PARTIE GAUCHE : LOGO -->
				<a class="navbar-item" href=""> <img src="media/logo.jpg">
				</a> <a role="button" class="navbar-burger burger" aria-label="menu"
					aria-expanded="false" data-target="navbarBasicExample"> <span
					aria-hidden="true"></span> <span aria-hidden="true"></span> <span
					aria-hidden="true"></span>
				</a>
			</div>

			<div id="navbarBasicExample" class="navbar-menu"> <!--PARTIE DROITE : BOUTONS ET LIENS -->
				<div class="navbar-end">
					<div class="navbar-item">
						<div class="buttons">
							<a class="button is-light">Vendre un article</a> <a
								class="button is-light">Mon profil</a> <a
								class="button is-primary"> <strong>Déconnexion</strong></a>
						</div>
					</div>
				</div>
			</div>
		</nav>
	</header>

	<main>

		<section> <!-- OUTILS DE RECHERCHE -->
			<form action="/TrocEnchere.org/accueil" method="post">

				<div class="field is-vertical"> <!-- FILTRES EN CHECKBOX -->
					<h3>Filtres :</h3>
					<div class="field-body">
						<div class="field">
							<label class="checkbox"> <input type="checkbox">Mes ventes
							</label>
						</div>
					</div>

					<div class="field-body">
						<div class="field">
							<label class="checkbox"> <input type="checkbox">Mes enchères en cours
							</label>
						</div>
					</div>

					<div class="field-body">
						<div class="field">
							<label class="checkbox"> <input type="checkbox">Mes acquisitions
							</label>
						</div>
					</div>

					<div class="field-body">
						<div class="field">
							<label class="checkbox"> <input type="checkbox">Autres enchères
							</label>
						</div>
					</div>

				</div>

				<div class="field"> <!-- BARRE DE RECHERCHE -->
					<div class="control">
						<input class="input is-primary" type="text"
							placeholder="Le nom de l'article contient">
					</div>
				</div>

				<div class="field is-horizontal"> <!-- MENU DEROULANT DES CATEGORIES -->
					<div class="field-label is-normal">
						<label class="label">Catégories :</label>
					</div>
					
					<div class="field">
						<div class="control">
							<div class="select is-primary">
								<select>
									<option>Toutes</option>
									<option>catégorie n°1</option>
									<option>catégorie n°2</option>
									<option>catégorie n°3</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					
					 <div class="field-body"> <!-- BOUTON POUR LANCER LA RECHERCHE -->
					    <div class="field">
					      <div class="control">
						 	<button type="submit" class="button is-primary" name="bouton" value="valider">Rechercher</button>
					  </div>
					</div>
				</div>
			</form>

		</section>

		<section>
			<!-- AFFICHAGE D'ANNONCES AU HASARD ? OU RESULTATS ? -->

		</section>


	</main>

</body>
</html>