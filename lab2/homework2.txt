punctul 1: nu prea am inteles la ce se refera clasa Problem sau ce se cere ca e vag, asa ca am facut ce am crezut ca ar fi util
am facut ca in aceasta clasa sa se creeze legaturile dintre locatii, deoarece mi se pare mai intuitiv sa am o clasa ce se ocupa 
de toate rutele si care lucreaza cu ele decat sa adaug inca alte doua argumente la road si sa incarc clasa aia cu lista de adiacenta.
asa ca in Problem cu addRoute conectam locatiile si adaugam in lista de adiacenta a fiecaruia la cine e conectat si cu ce drum.
lista de adiacenta este o structura de tip Map, care pt fiecare obiect Location avem o lista de destinatii care e creata cu ajutorul 
clasei Destination care defineste ca obiect fiecare locatie la care se poate ajunge dintr-o locatie.

punctul 2: am creat cele doua functii equals pt Road si Location si pt a verifica ca un obiect nu a mai fost definit o data m-am
folosit de HashSet, care ofera o cheie hash pt fiecare combinatie de argumente si daca o combinatie exista deja inseamna ca si acea
cheie hash exista deja si se afiseaza un mesaj.

punctul 3: am inlocuit enum-ul pentru locatii si deci clasa de baza Location am transformat-o intr-una abstracta si tipurile
specifice de locatii le-am transformat in clase care extind clasa abstracta si pot apela toate metodele din cea abstracta.

punctul 4: nu am inteles deloc ce se cere in acest punct. eu in Problem am creat intr-un fel ipoteza problemei si cand voi face 
bonusul si voi implementa solutia voi prelua lista de adiacenta din Problem pentru immplementarea dijkstra.

punctul 5: este metoda existPath din clasa Problem care primeste care argumente cele 2 locatii pt care verificam daca este drum
si pt a face asta implementam un bfs cu ajutorul unui set ce memoreaza cine a fost vizitat si o coada in care adaugam urmatoarea 
locatie vecina pt a-i verifica destinatiile din lista de adiacenta. eu prin given roads am inteles toate drumurile pe care le 
am in lista

punctul 6: am scris niste explicatii pt fiecare clasa ca doc comments si am adaugat in repo ce mi s-a creat prin generarea javadoc
