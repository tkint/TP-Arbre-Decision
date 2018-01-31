# TP Abre Décision

## Ideas

#### Out of memory :
* Try catch -> remove entries.get(0) + stock dans var la taille de entries
* Dans le try, juste après avoir passé l’instruction qui peut planter et si var est set, on stop le programme en affichant var (comme ça on connaît la taille maximale)

#### Decide :
* Demander si accepte decision :
  * Si oui ajout dans entries
  * Si non ajout dans entries + regen arbre
* Si taille max atteint on remove entries.get(0) avant d’ajouter l’entry	

#### Param inconnu en entry :
* Ajout de 2 entry au entries :
  * Avec Jouer = OUI
  * Avec Jouer = NON
* Regen arbre
* Recommencer le decide

#### Stockage des données :
* Fichier texte
  * Les entries sous forme de liste :
    * [att1], [att2], [att3], [att4], etc
  * La taille maximum de entries
* Au lancement du programme, on charge les données
* A la modification de entries, on sauvegarde les données


#### Récupération des données depuis le fichier:
* Fichier texte
  * Boucler sur le fichier plutot que sur la liste de entries
  * Plus besoin de liste de entries
  * Pour chaque ligne on créer une Entry, on la traite puis on passe à la ligne suivante

#### Accès aux données :
* Accéder directement sur le fichier texte et non plus sur la liste d'entry
* Boucler sur le fichier texte dans les algos

#### Généricité
* Dans Node:
  * Créer un HashMap<String, HashMap<Integer, String>> references
    * Correspond à l'ensemble des données uniques avec leur correspondance en français
    * Remplir ce HashMap à l'ajout d'une Entry dans addEntry
  * getStringValue doit maintenant récupérer la valeur depuis references
  * getUniqueValues est inutile ou doit être modifié
* Dans Entry
  * Créer un HashMap<String, Integer> values
    * Correspond à l'ensemble des données d'une Entry
    * Résultat:
      * Dernière donnée du HashMap?
      * Champ à part de type boolean
