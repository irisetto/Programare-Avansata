punctul 1 - am creat clasele noi Programmer si Designer
, care extind clasa PPerson. Am adaugat 
in Person birthDate si pentru Programmer 
am adaugat ca proprietate specifica 
<i>usedProgrammingLanguage</i>, iar pt
Designer <i>usedDesignApp</i>.

punctul 2 - in clasa Person am creat Map 
in care cu ajutorul functiei addRelationship
adaugam o relatie pentru un nod de tipul
Company sau Person. Company are de asemenea
Map, dar poate avea relatii doar cu clasa Person.
Cand adaugam  o relatie in una din cele doua clase
vom adauga relatia si in mapa de relatii a obiectului
dat ca argument.

punctul 3 - am creat clasa Network care contine
o lista de noduri. Vom adauga obiectele Company si Person
 create cu tot cu mapa de relatii cu ajutorul funtiei addNode.

punctul 4 - am creat metoda <i>importanceOf</i> care sorteaza
 in prima faza lista de noduri cu ajutorul method reference. Se
sorteaza descrescator dupa numarul de conexiuni, astfel incat 
obiectul cu cele mai multe conexiuni va avea importanta 1
aka cel mai important.

punctul 5 - sortam iar prin method reference in functia 
print, iar apoi afisam efectiv nodurile care contine toate
proprietatile obiectelor si numarul de conexiuni.

Afiseaza asta:
```
Teodora
Olivia
2
Importance of Teodora is 1 with 3 connections.
Importance of Amelia is 2 with 2 connections.
Importance of Spartan is 4 with 1 connection.
[Programmer: name='Teodora' | birthDate='06.08.2002' | usedProgrammingLanguage='Java' | numberOfRelationships= 3
, Designer: name='Amelia' | birthDate='11.08.2030' | usedDesignApp='Illustrator' | numberOfRelationships= 2
, Designer: name='Olivia' | birthDate='10.08.2030' | usedDesignApp='Photoshop' | numberOfRelationships= 1
, Company: name='Spartan' | yearFounded='2000' | numberOfRelationships= 1
]
```