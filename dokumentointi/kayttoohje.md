# Käyttöohje
## Konfigurointi
Ohjelma ei tarvitse mitään erillisiä tiedostoja ajettavakseen. Pistetilastojen säilömiseen tarkoitettu `high_score.db`-tiedosto luodaan automaattisesti, mikäli sitä ei ole jo luotu.
## Sovelluksen avaaminen
Avaa sovellus komennolla `java -jar labyrintti_v3.0.jar`. Huomaa, että joudut vaihtamaan ajettavan jar-tiedoston nimeä, riippuen releasesta, jonka olet ladannut
## Valikko
Kun ajat koodin, aukeaa aloitusvalikko. Valikosta voit aloittaa pelin ensimmäisestä tasosta alkaen painamalla "Aloita peli" näppäintä. Voit halutessasi hypätä tiettyyn kenttään painamalla "Tasovalikko"-näppäintä, ja sieltä haluamaasi kenttää vastaavaa nappia. Voit myös tarkastella pistetilastoja "Pistelista" näppäimellä. Pistelista-näkymässä voit halutessasi poistaa pistetilastot.
## Pelaaminen
Pelin tarkoitus selviytyä kunkin tason loppuun vältellen esteitä ja keräten pisteitä.
### Näppäinkomennot
Ohjaa pelihahmoa nuolinäppäimillä
### Pelikentän objektit
#### Tärkeät objektit
##### Pelaajahahmo
Pelaajan hahmo on liila pallo.
##### Maali
Maali on suuri vihreä pallo. Kun ohjaat pelihahmon tämän sisälle, pääset seuraavaan tasoon.
##### Seinä
Seinät ovat mustia suorakulmioita, jotka rajaavat kentän. Seinien läpi ei voi kulkea.
#### Viholliset ja esteet
##### Piikkieste
Tämä musta, piikikäs liikkuva tai paikallaan istuva objekti vie sinulta yhden elämän ja palauttaa sinut tason alkuun. Vältä siis näitä!
##### Vihollinen
Viholliset ovat punaisia, piikikkäitä palloja, jotka piikkiesteiden tavoin vie sinulta elämän ja palauttaa sinut kentän alkuun. Viholliset pysyvät paikallaan pelaajan ollessa tarpeeksi kaukana, mutta ne alkavat seuraamaan pelaajan ollessa tarpeeksi lähellä. Viholliset pelihahmon tavoin ei voi kulkea seinien läpi. Pelaaja voi tuhota vihollisen johdattelemalla tämän piikkiestettä kohti.  
##### Ovi
Nämä punaiset seinät estävät pelaajan kulun johonkin alueeseen. Jokaiseen oveen liittyy yksi tai useampi vihollinen, jotka tuhoamalla ovi aukeaa
#### Esineet
##### Kulta
Tasoista voi löytää pieniä keltaisia palloja, jotka kuvaavat kultaa. Näistä saat 50 pistettä.
##### Elämä
Tämä pieni liila pallo antaa sinulle yhden elämän.
### Kuoleminen
Kun kosket viholliseen tai piikkiesteeseen, menetät yhden elämän sekä palaudut kentän alkuun. Tämän lisäksi jo tuhoamasi viholliset palautuvat alkuperäisille paikoilleen. Kaikki avatut ovet pysyvät avattuna.
### Pisteiden kerääminen
Pisteitä saat kerättyä etsimällä yllä kyvattuja kulta-esineitä. Tämän lisäksi pisteitä saa läpäisemällä kentän (200 pistettä), sekä jokaisesta elämästä, joka pelaajalla on koko peli läpäistäessä.
### Pelin päättyminen
Kun kaikki tasot on läpäisty tai kaikki elämät loppu, ohjelma pyytää sinulta nimen. Tämä nimi kirjataan `high_score.db` tiedostoon pisteidesi kanssa. Lopuksi ohjelma näyttää vielä pistetilastot, jonka jälkeen ohjelma täytyy sulkea.
