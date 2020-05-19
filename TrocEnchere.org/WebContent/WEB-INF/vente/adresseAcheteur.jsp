<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.2/css/bulma.min.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel="stylesheet" href="./main.css">
	<script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
<title>Troc-Enchères Adresse de l'acheteur</title>
</head>
	<body>
		<header>
			<div>Troc-Enchères</div>
			<div class="subtitle is-medium"> Profil de ${acheteur.pseudo}</div>
		</header>
		<main>
			
			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">Pseudo:</label>
				</div>
				<div class="field-body">
					<div class="field">
						${acheteur.pseudo}
					</div>
				</div>
			</div>
			
			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">Adresse:</label>
				</div>
				<div class="field-body">
					<div class="field">
						${acheteur.rue}
						${acheteur.codePostal}
						${acheteur.ville}
					</div>
				</div>
			</div>
			
			<div class="field is-horizontal">
				<div class="field-label is-normal">
					<label class="label">Téléphone:</label>
				</div>
				<div class="field-body">
					<div class="field">
						${acheteur.telephone}
					</div>
				</div>
			</div>
			<a href="/TrocEnchere.org/MesArticlesVendus"><button type="submit" class="button is-primary is-light" name="retour" value="retour">Back</button></a>
		</main>
	</body>
</html>