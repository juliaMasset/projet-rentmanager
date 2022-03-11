# Rentmanager

# Comment lancer le site ?

- Exécuter le fichier fill database pour générer la base de données 
- Utiliser la commande `mvn tomcat7:run` dans la console puis ouvrir le site dans le navigateur avec l'adresse http://localhost:8080/rentmanager/home

# Fonctionnalités

- Ajout, modification, affichage des détails, suppression d'un client (méthodes dans le DAO)
- Ajout, modification, affichage des détails, suppression d'un vehicule (méthodes dans le DAO)
- Liste de tous les cients, tous les véhicules et toutes les réservations (méthodes dans le DAO)
- Ajout, affichage et suppression d'une réservation (méthodes dans le DAO)
- Lors de la création d’un Client, plusieurs champs sont obligatoires : nom, prénom, email et date de naissance (required en HTML)
- validation des informations, en particulier le format des dates, la syntaxe de l’email, l'âge du client devant être supérieur à 18 ans, la taille du nom et prénom ainsi que le nombre de places
- affichage d'une alerte si date de naissance invalide (hidden abled quand l'âge est juste et disabled quand il ne l'est pas)
- nombre de places minimum 2 et maximum 9 (min="2" max="9" en HTML)
- Alerte si réservation est supérieure à 7 jours (fonction javascript)
- Lister toutes les Réservations associées à un Client donné (méthodes dans le DAO)
- Lister toutes les Réservations associées à un Véhicule donné (méthodes dans le DAO)
- Si un client ou un véhicule est supprimé, alors les réservations associées sont supprimées (dans fill database, ajout de ONDELETE CASCADE)

# Contraintes non réussies

- Une voiture ne peux pas être réservée 2 fois le même jour
- Une voiture ne peux pas être réservée 30 jours de suite sans pause
- Un client ayant une adresse mail déjà prise ne peut pas être créé


