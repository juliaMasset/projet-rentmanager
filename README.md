# Rentmanager

# Comment lancer le site ?

- Exécuter le fichier fill database pour générer la base de données 
- Utiliser la commande `mvn tomcat7:run` dans la console puis ouvrir le site dans le navigateur avec l'adresse http://localhost:8080/rentmanager/home

# Fonctionnalités

Home:
- Affichage du nombre de clients, vehicules et reservations

Client:
- Create, update, details, delete (méthodes dans le DAO)
- Lister toutes les Réservations associées à un Client donné dans details (méthodes dans le DAO)
- Si un client est supprimé, alors les réservations associées sont supprimées (dans fill database, ajout de ONDELETE CASCADE)

Vehicule:
- Create, update, details, delete (méthodes dans le DAO)
- Lister toutes les Réservations associées à un Véhicule donné dans details (méthodes dans le DAO)
- Si un vehicule est supprimé, alors les réservations associées sont supprimées (dans fill database, ajout de ONDELETE CASCADE)

Reservation:
- Reservation: Ajout et suppression (méthodes dans le DAO)

# Contraintes

- tous les champs sont obligatoires dans les formulaires (required en HTML)

Pour les clients:
- Le format des dates et la syntaxe de l’email doivent être respectés 
- l'âge du client doit être supérieur à 18 ans (fonction javascript)
- Un client ayant une adresse mail déjà prise ne peut pas être créé (fonction javascript)
- affichage d'une alerte si date de naissance invalide (hidden quand l'âge est juste et disabled quand il ne l'est pas)
- affiche d'une alerte si adresse mail déjà utilisée (hidden quand l'âge est juste et disabled quand il ne l'est pas)
- nom et prenom doivent faire au moins 3 caractères (minlenght="3" en html)

Pour les voitures:
- nombre de places minimum 2 et maximum 9 (min="2" max="9" en HTML)
- Alerte si réservation est supérieure à 7 jours (fonction javascript)

# Contraintes non réussies

- Une voiture ne peux pas être réservée 2 fois le même jour
- Une voiture ne peux pas être réservée 30 jours de suite sans pause


