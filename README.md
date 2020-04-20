# Ohjelmistotekniikka, harjoitustyö
## Labyrintti  
Labyrintti on 2D, ylhäältäkuvattu videopeli, missä pelaajan on tarkoitus navigoida erilaisia kenttia vältellen esteitä ja
etsien aarteita.
## Tämänhetkiset ominaisuudet
Pelihahmoa pystyy liikuttamaan, ja sillä on elämät. Elämien loputtua peli päättyy. Peliruudun yläosassa näkyy jäljellä olevat elämät. Seinät lisätty ja toimivat. Tason maali lisätty ja toimiva (vie seuraavaan tasoon). Liikuva sekä paikallaan oleva piikkieste lisätty ja toimiva (poistaa elämän). Esineet lisätty (elämä ja kulta) ja toimivat. Ensimmäiset kaksi oikeaa tasoa tehty. Tasovalikko lisätty valikkoon (sisältää vain testikentät) ja toimii. Valikosta ja pelin päättyessä voi tarkastella myös pistetilastoja. 
## Dokumentointi
[Määrittelydokumentti](https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/maarittelydokumentti.md)  
[Työaikakirjanpito](https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)  
[Käyttöohje](https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)
[Arkkitehtuuri](https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/arkitehtuuri.md)
## Releaset
[Viikko 5](https://github.com/ikpa/ot-harjoitustyo/releases/tag/v1.0)
## Komentorivikomennot
Kaikki maven-komentorivikomennot tehdään polussa `/ot-harjoitustyo/labyrintti_harjoitustyo/`.
### Ohjelman ajaminen
`mvn compile exec:java -Dexec.mainClass=labyrintti_harjoitustyo.RealMain`
### Testaus
Testit suoritetaan komennolla  

`mvn test`  

Testikattavuusraportti generoidaan komennolla  

`mvn test jacoco:report`  

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto `target/site/jacoco/index.html`
### Jar
Komento  

`mvn package`  

generoi hakemistoon *target* suoritettavan jar-tiedoston *labyrintti_harjoitustyo-1.0-SNAPSHOT.jar*. Ohjelman suorittaminen vaatii `high_score.txt`-tiedoston olemassaolon samassa polussa.
### Checkstyle
Checkstyle-raportti generoidaan komennolla  

`mvn jxr:jxr checkstyle:checkstyle`  

Raporttia voi tarkastella polusta `target/site/checkstyle.html`.
