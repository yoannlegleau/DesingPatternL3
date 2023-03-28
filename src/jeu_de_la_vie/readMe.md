# Jeux de la vie
Auteur : Yoann LE GLEAU 

Ce document est issu du projet suivent [TP3 Jeux de la vie](https://github.com/yoannlegleau/DesingPatternL3/tree/master/src/jeu_de_la_vie).

---

## Description
Implementation du jeu de la vie en Java dans le cadre du [TP3](doc/Jeu de la Vie.html) sur les Design Patterns.

## Design Pattern

### Pattern de creation d'objet
| Pattern            | Utiliser   | Utilisation                                                                          |
|--------------------|------------| ------------------------------------------------------------------------------------ |
| Singleton          | Oui        | Permet de créer une seule instance des classes CelluleEtatMort et CelluleEtatVivante |
| Fabrique           | TP1-Canards |                                                                                      |
| Fabrique Abstraite | TP1-Canards |                                                                                      |
| Monteur            | ❌          |                                                                                      |
| Prototype (clone)  | ❌          |                                                                                      |

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
| ------------------------ |------------------| ------------------------------------------------------------------- |
| Chaîne de responsabilité | TP1-Canards      |                                                                     |
| Commande                 | ✅                | Permet d'enregistre les modification de la grille (classe Commande) |
| Itérateur                | TP2-Fichiers     |                                                                     |
| Médiateur                | 🧠               |                                                                     |
| Mémento                  | 🧠               |                                                                     |
| Observateur              | ✅                | Permet de mètre a jour l'interface après les calcules               |
| État                     | ✅                | Permet de savoir si une cellule est morte ou vivante                |
| Stratégie                | TP2-Algo de tris |                                                                     |
| Patron de méthode        | ❌                |                                                                     |
| Visiteur                 | ✅               | Permet d'implémenter plusieurs Règles (basique …)                   |


# diagramme de classe

## Diagramme de classe de la première version
![Diagramme de classe de la première version](doc/package.png)


# Amélioration

## TODO
- [x] Interface grafique en AWT
- [ ] Écrire un deuxième observateur en mode texte qui se contente d’afficher dans la console Java le numéro de la génération courante et le nombre de cellules actuellement en vie.
- [ ] Règles alternatives (classique)
  - [ ] [HighLife](https://fr.wikipedia.org/wiki/HighLife_(automate_cellulaire))
    - Une cellule morte y naît à l'étape suivante si elle est entourée de 3 ou 6 voisines vivantes
    - une cellule vivante survit à l'étape suivante si elle est entourée de deux ou trois cellules vivantes.
  - [ ] [Day & Night](https://fr.wikipedia.org/wiki/Day_%26_Night)
    - Une cellule morte y naît à l'étape suivante si elle est entourée de 3, 6, 7 ou 8 voisines vivantes
    -  une cellule vivante survit à l'étape suivante si elle est entourée de 3, 4, 6, 7 ou 8 cellules vivantes
- [ ] Règles alternatives (multiple vie)
  - [ ] [QuadLife](https://fr.wikipedia.org/wiki/QuadLife)
    - Une cellule morte y naît à l'étape suivante si elle est entourée de 3 voisines
    - une cellule vivante survit à l'étape suivante si elle est entourée de 2 ou 3 cellules vivantes.
Lorsqu'une cellule naît, si toutes les cellules qui lui ont donné naissance se trouvent dans des états différents, la nouvelle cellule prend l'état restant. Dans le cas contraire, elle prend l'état de la majorité des trois cellules.
  - [ ] [Immigration](https://fr.wikipedia.org/wiki/Immigration_(automate_cellulaire))
    - Une cellule morte naît à l'étape suivante si elle est entourée de 3 voisines vivantes et prendra la couleur (ou état) de la majorité de celles-ci ;
    - Une cellule vivante survit à l'étape suivante si elle est entourée de 2 ou 3 cellules vivantes.
- [ ] Bouton pour exécuter/arrêter la boucle d’exécution
- [ ] Bouton pour avancer d’une génération lorsque la boucle est arrêtée ;
- [ ] Slider pour ajuster la vitesse de la boucle d’exécution ;
- [ ] Contrôle de sélection des règles du jeu, etc.
- [ ] Catalog de structure (depart)
- [ ] variation de la danciter de depar si selectioner
- [ ] taille de la grille / grille infinie
- [ ] Stoper la geeration quand tout est fix;
- [ ] methode de Undo (pattern memento)
- [ ] pattern Mediator pour la gestion de l'interface de creation de grille

## Interface grafique

Cette partie porte sur l'implémentation de l'interface graphique.
Swing sera la librairie graphique principal.
AWT sera utiliser pour certain element graphic mois générique.

3 options ont été envisager :
- AWT
- Swing
- JavaFX

Les principales raison de l'utilisation de Swing sont :
- Swing est tout comme AWT une bibliothèque graphique de Java.
- Swing est plus stable que AWT sur different system.
- JavaFX a une architecture plus lourde Swing est donc disproportionnée aux vus des ambitions du projet.
- Swing propose une meilleure gestion des listes (utile pour ce projet).





