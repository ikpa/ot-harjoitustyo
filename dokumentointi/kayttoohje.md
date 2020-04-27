# Käyttöohje
## Konfigurointi
Ohjelma olettaa, että hakemistossa on tiedosto `high_score.txt`, jota käytetään pistetilastojen säilyttämiseen. Tiedoston saat ladattua releaseista jar-tiedostojen kanssa.
## Sovelluksen avaaminen
Avaa sovellus komennolla `java -jar labyrintti_v2.0.jar`. Huomaa, että joudut vaihtamaan ajettavan jar-tiedoston nimeä, riippuen releasesta, jonka olet ladannut
## Valikko
Kun ajat koodin, aukeaa aloitusvalikko. Valikosta voit aloittaa pelin ensimmäisestä tasosta alkaen painamalla "Aloita peli" näppäintä. Voit halutessasi hypätä tiettyyn kenttään painamalla "Tasovalikko"-näppäintä, ja sieltä haluamaasi kenttää vastaavaa nappia. Voit myös tarkastella pistetilastoja "Pistelista" näppäimellä. Pistelista-näkymässä voit myös poistaa pistetilastot.
## Pelaaminen
Pelin tarkoitus selviytyä kunkin tason loppuun vältellen esteitä ja keräten pisteitä. Esteisiin koskemalla menetät elämän, jotka ovat kirjattuna peliruudun yläreunaan. Kun olet menettänyt kaikki elämät, tai kun olet läpäissyt kaikki tasot, peli päättyy.
### Näppäinkomennot
Ohjaa pelihahmoa nuolinäppäimillä
### Hahmot, viholliset ja esineet
#### Pelaajahahmo
Pelaajan hahmo on liila pallo.
#### Maali
Maali on suuri vihreä pallo. Kun ohjaat pelihahmon tämän sisälle, pääset seuraavaan tasoon
#### Piikkieste
Tämä musta, piikikäs liikkuva tai paikallaan istuva objekti vie sinulta yhden elämän ja palauttaa sinut tason alkuun. Vältä siis näitä!
#### Kulta
Tasoista voi löytää pieniä keltaisia palloja, jotka kuvaavat kultaa. Näistä saat pisteitä.
#### Elämä
Tämä pieni liila pallo antaa sinulle yhden elämän.
### Pelin päättyminen
Kun kaikki tasot on läpäisty tai kaikki elämät loppu, ohjelma pyytää sinulta nimen. Tämä nimi kirjataan `high_score.txt` tiedostoon pisteidesi kanssa. Lopuksi ohjelma näyttää vielä pistetilastot, jonka jälkeen ohjelma täytyy sulkea.
