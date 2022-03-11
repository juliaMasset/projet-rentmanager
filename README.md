# Rentmanager

# Comment lancer le site ?

- Exécuter le fichier fill database pour générer la base de données 
- Utiliser la commande `mvn tomcat7:run` dans la console puis ouvrir le site dans le navigateur avec l'adresse http://localhost:8080/rentmanager/home

# Fonctionnalités

- Ajout, modification, affichage des détails, suppression d'un client
- Ajout, modification, affichage des détails, suppression d'un vehicule
- Liste de tous les cients, tous les véhicules et toutes les réservations
- Ajout, affichage et suppression d'une réservation
- Lors de la création d’un Client, plusieurs champs sont obligatoires : nom, prénom, email et date de naissance
- validation des informations, en particulier le format des dates, la syntaxe de l’email, l'âge du client devant être supérieur à 18 ans, la taille du nom et prénom ainsi que le nombre de places
- affichage d'une alerte si date de naissance invalide
- nombre de places minimum 2 et maximum 7
- Lister toutes les Réservations associées à un Client donné
- Lister toutes les Réservations associées à un Véhicule donné
- Si un client ou un véhicule est supprimé, alors les réservations associées sont supprimées
- 


