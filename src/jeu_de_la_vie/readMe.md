# Jeux de la vie
Auteur : Yoann LE GLEAU 

---

## Description
Implementation du jeu de la vie en Java dans le cadre du cours sur les Design Patterns.

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
