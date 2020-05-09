# Ohjelmistotekniikka, harjoitustyö
## Labyrintti  
Labyrintti on 2D, ylhäältäkuvattu videopeli, missä pelaajan on tarkoitus navigoida erilaisia kenttia vältellen esteitä ja
etsien aarteita.
## Tämänhetkiset ominaisuudet
- Pelihahmoa pystyy liikuttamaan, ja sillä on elämät. Elämien loputtua peli päättyy. Peliruudun yläosassa näkyy jäljellä olevat elämät. 
- Seinät lisätty. 
- Tason maali lisätty (vie seuraavaan tasoon). 
- Liikuva sekä paikallaan oleva piikkieste lisätty (poistaa elämän). 
- Esineet lisätty (elämä ja kulta) ja toimivat. 
- Pelihahmoa seuraavat viholliset lisätty. 
- Ovet lisätty (avautuvat kun kaikki tason viholliset on tapettu).  
- Peli sisältää 5 oikeaa tasoa. 
- Tasovalikko lisätty valikkoon. 
- Valikosta ja pelin päättyessä voi tarkastella myös pistetilastoja. 
- Pelihahmo saa nyt 200 pistettä jokaisesta läpäistystä kentästä, ja pelin päätyttyä 50 pistettä jokaisesta jäljelläolevasta elämästä.
## Dokumentointi
[Määrittelydokumentti](https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/maarittelydokumentti.md)  
[Työaikakirjanpito](https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)  
[Käyttöohje](https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)  
[Arkkitehtuuri](https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/arkitehtuuri.md)
## Releaset
[Viikko 5](https://github.com/ikpa/ot-harjoitustyo/releases/tag/v1.0)  
[Viikko 6](https://github.com/ikpa/ot-harjoitustyo/releases/tag/v2.0)
## Komentorivikomennot
Kaikki maven-komentorivikomennot tehdään polussa `/ot-harjoitustyo/labyrintti_harjoitustyo/`.
### Ohjelman ajaminen
`mvn compile exec:java -Dexec.mainClass=labyrintti.ui.RealMain`
### Testaus
Testit suoritetaan komennolla  

`mvn test`  

Testikattavuusraportti generoidaan komennolla  

`mvn test jacoco:report`  

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto `target/site/jacoco/index.html`
### Jar
Komento  

`mvn package`  

generoi hakemistoon *target* suoritettavan jar-tiedoston *labyrintti_harjoitustyo-1.0-SNAPSHOT.jar*.
### Checkstyle
Checkstyle-raportti generoidaan komennolla  

`mvn jxr:jxr checkstyle:checkstyle`  

Raporttia voi tarkastella polusta `target/site/checkstyle.html`.

### JavaDoc
Generoi JavaDoc komennolla  

`mvn javadoc:javadoc`  

Voit tarkastella JavaDoccia polusta `target/site/apidocs`.
