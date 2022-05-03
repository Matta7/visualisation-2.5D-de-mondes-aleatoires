Projet réalisé par:
PRONOST Sacha 21901956
SIEPKA Aurélien 21906664
VALLEE Mathieu 21910887
WILLENBUCHER Gurvan 21908377

Pour initialiser notre programme (compilé, crée un javadoc, crée un .jar) il vous suffit
de placer votre bash dans notre dossier de jeu, puis d’entrer la commande :
ant

Afin de lancer notre jeu, il vous suffit de lancer le fichier ProjetAnnuel.jar se trouvant
dans le répertoire dist.

De plus, vous pouvez le lancer en utilisant ANT, pour cela placer votre bash dans
notre dossier de jeu, puis entrée la commande :
ant run

Il est aussi possible de lancer le programme en ligne de commande bash. Pour cela
placer votre bash dans le dossier principal de notre jeu (ou se situe notre fichier build.xml),
ensuite exécuter ces deux lignes de commande :
javac −d ./build src/*/*.java
java −cp ./build lanceur.Lanceur

------------------------------------------------
Commande:

Les touches Z,Q,S,D permettent respectivement d'avancer, d'aller à gauche, en arrière et à droite
Les touches A et E permettent d'augmenter, ou de baisser sa hauteur
Les flèches directionnelles haut et bas permettent de regarder en haut, ou en bas
Les flèches directionnelles gauche et droite permettent d'effectuer une rotation à gauche, ou à droite.
