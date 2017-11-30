# TP Abre Décision

## Ideas

#### Out of memory :
* Try catch -> remove data.get(0) + stock dans var la taille de data
* Dans le try, juste après avoir passé l’instruction qui peut planter et si var est set, on stop le programme en affichant var (comme ça on connaît la taille maximale)

#### Decide :
* Demander si accepte decision :
  * Si oui ajout dans data
  * Si non ajout dans data + regen arbre
* Si taille max atteint on remove data.get(0) avant d’ajouter l’entry	

#### Param inconnu en entry :
* Ajout de 2 entry au data :
  * Avec Jouer = OUI
  * Avec Jouer = NON
* Regen arbre
* Recommencer le decide

#### Stockage des données :
* Fichier texte
  * Les entries sous forme de liste :
    * [att1], [att2], [att3], [att4], etc
  * La taille maximum de data
* Au lancement du programme, on charge les données
* A la modification de data, on sauvegarde les données