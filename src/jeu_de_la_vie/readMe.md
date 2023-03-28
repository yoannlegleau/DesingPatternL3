# Jeux de la vie
Auteur : Yoann LE GLEAU 

---

## Description
Implementation du jeu de la vie en Java dans le cadre du [TP3](doc/Jeu de la Vie.html) sur les Design Patterns.

## Design Pattern

### Pattern de creation d'objet
| Pattern            | Utiliser    | Utilisation                                                                          |
| ------------------ | ----------- | ------------------------------------------------------------------------------------ |
| Singleton          | Oui         | Permet de créer une seule instance des classes CelluleEtatMort et CelluleEtatVivante |
| Fabrique           |             |                                                                                      |
| Fabrique Abstraite | TP1-Canards |                                                                                      |
| Monteur            |             |                                                                                      |
| Prototype          |             |                                                                                      |

### Pattern structurels
| Pattern      | Utiliser    | Utilisation |
| ------------ | ----------- | ----------- |
| Adaptateur   | TP1-Canards |             |
| Pont         |             |             |
| Composite    | TP2-Fichier |             |
| Décorateur   | TP1-Canards |             |
| Façade       |             |             |
| Poids mouche |             |             |
| Procuration  |             |             |

### Pattern comportementaux
| Pattern                  | Utiliser         | Utilisation                                                         |
| ------------------------ | ---------------- | ------------------------------------------------------------------- |
| Chaîne de responsabilité | TP1-Canards      |                                                                     |
| Commande                 | OUI              | Permet d'enregistre les modification de la grille (classe Commande) |
| Itérateur                | TP2-Fichiers     |                                                                     |
| Médiateur                |                  |                                                                     |
| Mémento                  |                  |                                                                     |
| Observateur              | OUI              | Permet de mètre a jour l'interface après les calcules               |
| État                     | OUI              | Permet de savoir si une cellule est morte ou vivante                |
| Stratégie                | TP2-Algo de tris |                                                                     |
| Patron de méthode        |                  |                                                                     |
| Visiteur                 | OUI              | Permet d'implémenter plusieurs Règles (basique …)                   |

# Amélioration

## TODO
- Interface grafique en swing
- Écrire un deuxième observateur en mode texte qui se contente d’afficher dans la console Java le numéro de la génération courante et le nombre de cellules actuellement en vie.
- Règles alternatives (classique)
  - [HighLife](https://fr.wikipedia.org/wiki/HighLife_(automate_cellulaire))
    - Une cellule morte y naît à l'étape suivante si elle est entourée de 3 ou 6 voisines vivantes
    - une cellule vivante survit à l'étape suivante si elle est entourée de deux ou trois cellules vivantes.
  - [Day & Night](https://fr.wikipedia.org/wiki/Day_%26_Night)
    - Une cellule morte y naît à l'étape suivante si elle est entourée de 3, 6, 7 ou 8 voisines vivantes
    -  une cellule vivante survit à l'étape suivante si elle est entourée de 3, 4, 6, 7 ou 8 cellules vivantes
- Règles alternatives (multiple vie)
  - [QuadLife](https://fr.wikipedia.org/wiki/QuadLife)
    - Une cellule morte y naît à l'étape suivante si elle est entourée de 3 voisines
    - une cellule vivante survit à l'étape suivante si elle est entourée de 2 ou 3 cellules vivantes.
Lorsqu'une cellule naît, si toutes les cellules qui lui ont donné naissance se trouvent dans des états différents, la nouvelle cellule prend l'état restant. Dans le cas contraire, elle prend l'état de la majorité des trois cellules.
  - [Immigration](https://fr.wikipedia.org/wiki/Immigration_(automate_cellulaire))
    - Une cellule morte naît à l'étape suivante si elle est entourée de 3 voisines vivantes et prendra la couleur (ou état) de la majorité de celles-ci ;
    - Une cellule vivante survit à l'étape suivante si elle est entourée de 2 ou 3 cellules vivantes.
- Bouton pour exécuter/arrêter la boucle d’exécution
- Bouton pour avancer d’une génération lorsque la boucle est arrêtée ;
- Slider pour ajuster la vitesse de la boucle d’exécution ;
- Contrôle de sélection des règles du jeu, etc.
- Catalog de structure (depart)
- variation de la danciter de depar si selectioner
- taille de la grille / grille infinie
- Stoper la geeration quand tout est fix;
