# Arkitehtuurikuvaus
## Rakenne
Ohjelma sisältää 3 pääpakkausta: logic, dao ja ui. Pakkaus logic sisältää lisäksi alapakkaukset freemovers, level ja object.
### logic
Pakkaus sisältää kaikki pelilogiikkaan liittyvät pakkaukset.
#### logic.freemovers
Tämä pakkaus sisältää FreeMover-luokan, ja sen perivät MainChara- sekä Enemy-luokat. Tämän pakkauksen luokat kuvaavat pelikentällä vapaasti liikkuvaia olioita, jotka reagoivat seiniin sekä piikkiesteisiin.
#### logic.levels
Tämä pakkaus sisältää Level, LvlConstructor ja Goal-luokat. Pakkaus sisältää siis taso-olion ja taso-olioihin liittyvät luokat.
#### logic.object
Tämä pakkaus sisältää pelikenttien esineet ja esteet, joiden kanssa MainChara-olio pystyy vuorovaikuttamaan, ja niiden luomiseen tarvittavat luokat. Pakkaus sisältää Item, MovingSpike, Spike ja WallConstructor-luokat.
### dao
Tämä pakkaus sisältää HighScoreDao-luokan, jonka tarkoitus on lukea high_score.db-tiedostoa ja säilöä pelin pistetilastot.
### ui
Tämä pakkaus sisältää luokat Main ja RealMain, jotka rakentavat itse ajettavan ohjelman.
## Luokkakaavio

<img src="https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/luokkakaavio.svg" width="600">
Yksinkertaistettu luokkakaavio. Kuvassa näkyy Level, MainChara, HighScoreDao ja LvlConstructor luokkien yhteys Main-ohjelmaan, labyrintti.logic paketun luokkien yhteydet Level-luokkaan, sekä luokkien perinnät. Paketin luokkien yhteyksiä toisiinsa ei ole kuvattu selkeyden vuoksi.

## Sovelluslogiikka

Main-ohjelma sisältää LvlConstructor-, HighScoreDao, MainChara-, Level-olioita. MainChara kuvaa pelihahmoa, joita Main-ohjelmalla on yksi käytössään, ja Level kuvaa yhtä tasoa, joita Main-ohjelmalla on useita. Kukin Level-olio ottaa MainChara-olion hetkellisesti käyttöönsä, kun kyseistä tasoa pelataan. LvlConstructor-luokka luo kaikki kentät, joita Main-ohjelma käyttää, sekä HighScoreDao-luokka hallitsee pistetilastojen säilömistä ja lukemista.  

### Pelilogiikka

Level-olio sisältää useita erilaisia objekteja, joiden kanssa pelihahmo voi vuorovaikuttaa. Nämä voidaan jaotella luokkiin  
- Goal  
- Spike  
- Enemy  
- Item  
- Door  

Goal-olio on kunkin tason maali, johon pelaaja pyrkii. Jokainen taso vaatii yhden maalin. Maali vie pelaajan seuraavaan
kenttään.  

Spike-oliot ovat objekteja, jotka vievät pelihahmolta yhden elämän ja palauttavat sen takaisin tason alkuun, jos niihin koskee. Luokka MovingSpike perii Spike-luokan, ja on liikkuva variantti tästä.

Enemy-oliot ovat pelihahmoa seuraavia esteitä, jotka vievät Spike-olioiden lailla pelihahmolta elämän ja palauttavat sen tason alkuun. Nämä oliot voi tuhota ohjaamalla ne Spike-olioita kohti. Viholliset perivät MainChara-olion lailla FreeMover-luokan, joka kuvastaa kentällä vapaasti liikkuvia, seinien kanssa vaikuttavia olioita.

Item-oliot ovat objekteja, jotka pelihahmon ne kerätessä antavat sille joko pisteitä tai muita positiivisia vaikutuksia.  

Door-oliot ovat seiniä, jotka pelaaja voi poistaa pelikentältä. Olioon liittyy yksi tai useampi Enemy-olio, jotka tuhoamalla ovi aukeaa.

Taso sisältää lisäksi joukon Rectangle-olioita, jotka kuvaavat tason seiniä, joiden läpi pelihahmo ei voi liikkua. Seinien joukkoon lasketaan lisäksi kaikki Door-oliot, joita ei ole avattu.

#### Peliruudun päivittäminen
Pääohjelma Main kutsuu pelitilanteessa AnimationTimer luokan handle-metodin sisällä pelattavan tason update()-metodia, joka päivittää peliruudun n. 60 kertaa sekunnissa. Tämä metodi liikuttaa ensin pelihahmoa MainChara-luokan move()-metodin avulla Main-ohjelman napinpainallukset sisältävän buttonPress-mapin sekä tason seinien perusteella. Tämän jälkeen taso iteroi tason kaikkien Spike, Enemy, Item sekä Door-olioiden yli updateSpikes(), updateEnemies(), updateItems() sekä updateDoors()-metodien avulla, liikuttaen mahdollisia liikutettavia olioita sekä tarkistaen vuorovaikutukset muiden pelikentän olioiden kanssa. Mikäli Spike ja Enemy-luokat havaitsevat osuman MainChara-olion kanssa, kutsutaan MainChara-olion excecuteHit()-metodia joka vähentää pelaajalta elämän sekä Level-luokan resetEnemies()-metodia joka palauttaa Enemy-oliot alkupaikoilleen.  

**HUOM** Jokainen FreeMover-luokan perivä olio vaatii ns. `voffset`-luvun toimiakseen. Peliruudun yläosaan lisätty infopalkki jossa pelihahmon elämät ja pisteet näkyvät vääristää kentän seinien sekä liikkuvien olioiden y-koordinaattia, ja `voffset`-luku vaaditaan näiden välisten osumien korjaamiseen.

Tarkastellaan nyt edellämainittujen update-metodien toimintaa esimerkkien avuilla. Kaikissa näissä metodeissa iteroidaan monen olion yli ja suoritetaan kaikki toiminnot jokaiselle oliolle, mutta näissä esimerkeissä tarkastellaan vain yksittäisiä olioita.

##### MainChara.move()
Tutkitaan esimerkkinä tapausta, jossa pelaaja painaa oikeaa sekä ylintä nuolinäppäintä, mutta pelihahmon oikealla puolella on seinä.

<img src="https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/move_sekvenssi.png" width="500">

Ylläoleva sekvenssikaavio kuvaa kyseistä tilannetta. Ensin MainChara-olio iteroi kaikkien seiniä kuvaavien Rectangle-olioiden yli allowedDirectionsForAll()-metodin avulla, jossa metodi allowedDirections muokkaa sallittut suunnat sisältävää ArrayList-listaa. Indeksi 0 kuvaa suuntaa ylös, 1 oikealle, 2 alas, ja 3 vasemmalle; true kuvaa sallittua suuntaa ja false kiellettyä suuntaa. 

Kun kaikki tason seinät on tarkistettu, MainChara-olio tarkistaa, kuinka suuren osan nopeudestaan se liikkuu. Tällä tavalla varmistetaan ettei diagonaalisessa liikkeessä nopeus ylitä konstruktorissa määriteltyä nopeutta. Tässä tapauksessa hahmo liikkuu 1/sqrt(2) osuuden nopeudestaan, sillä pelaaja painaa diagonaaliseen suuntaan osoittavia nuolinäppäimiä.

Lopuksi MainChara suorittaa itse liikkumisen. Koska pelaaja painoi ylös- sekä oikeaa nuolinäppäintä, mutta oikealla oli seinä, kutsutaan loppujen lopuksi vain pelihahmoa ylös liikuttavaa moveUP()-metodia

##### Level.updateSpikes()
Tutkitaan tilannetta, jossa tason yksi MovingSpike-olio päivitetään. Olio liikkuu vaakasuunnassa oikealle. Olio osuu samalla pelihahmoon.

<img src="https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/updateSpikes_sekvenssi.png" width="500">

Ensin updateSpikes()-metodi liikuttaa MovingSpike-oliota move()-metodin avulla. Tässä metodissa tarkistetaan, onko piikki vertikaalisesti vai horisontaalisesti liikkuva piikki, minkä jälkeen kutsutaan horMove()-metodia. Tässä metodissa tarkistetaan, onko piikkieste saavuttanut liikkeensä päätepisteen. Tässä tapauksessa liike on oikealle, ja metodia moveRIGHT() kutsutaan.

Tämän jälkeen piikki tarkistaa, onko se osunut pelihahmoon. Level olio kutsuu ensin piikkiesteen muotoa kuvaavan Polygon-olion, minkä jälkeen MainChara-olio tarkistaa, onko siihen osuttu. Koska tässä tilanteessa pelihahmoon osuttiin, palauttaa updateSpikes()-metodi lopulta arvon true.

##### Level.updateEnemies()
Tutkitaan esimerkkinä tilannetta, jossa tason yksi Enemy-olio liikkuu pelihahmoa kohti joka on suoraan vihollisen yläpuolella, mutta osuu samalla piikkiin. Enemy-olio ei osu pelihahmoon.

<img src="https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/updateEnemies_sekvenssi.png" width="700">

Ensin Enemy-olio liikkuu move()-metodin avulla. Olio laskee x ja y-suuntaisen, sekä kokonaisetäisyyden pelihahmoon. Tämän jälkeen olio tarkistaa sallitut suunnat enemyAllowedDirections()-metodin avulla (metodin toimintaa ei ole kuvattu sekvenssikaaviossa selkeyden vuoksi). Tämä metodi tarkistaa ensin osumat tason seinien kanssa metodin allowedDirectionsForAll() avulla, jonka jälkeen se tarkistaa osumat muiden kentän Enemy-olioiden kanssa. Tämän jälkeen olio laskee osuuden, jonka se nopeudestaan liikkuu mihinkin suuntaan aiemmin laskettujen etäisyyksien avaulla. Tässä tilanteessa pelihahmo sijaitsee vihollisen yläpuolella, joten vain moveUP()-metodia kutsutaan.

Tämän jälkeen Enemy-olion checkHit()-metodia kutsutaan. Tämä metodi iteroi kaikkien kentän piikkien yli, ja palauttaa true mikäli olio on osunut yhteenkään piikkiin. Mikäli vihollinen on osunut piikkiin, sen indeksi otetaan ylös poistoa varten.

Seuraavaksi tarkistetaan, onko vihollinen osunut pelihahmoon. Tässä tapauksessa pelihahmoon ei ole osuttu, joten metodi palauttaa loppujen lopuksi arvon false.

Iteroinnin jälkeen metodi poistaa pelikentältä kaikki viholliset jotka osuivat piikkeihin metodin removeEnemies()-avulla.

##### Level.updateItems()
Tutkitaan seuraavaksi tilannetta, jossa pelihahmo kerää kentän ainoan esineen, joka on elämä.

<img src="https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/updateSpikes_sekvenssi.png" width="500">

Ensin metodi kutsuu MainChara-olion checkGet()-metodia, joka tarkistaa kaikki parametrina annetun listan esineet. Mikäli pelihahmo peittää esineen kokonaan, tarkistaa se esineen tyypin ja suorittaa sen tarjoamat positiiviset vaikutukset (tässä tapauksessa hahmolle annetaan yksi elämä). Metodi checkGet() palauttaa lisäksi ArrayListin, joka sisältää kerättyjen esineiden indeksit.

Viimeiseksi metodi kutsuu Level-olion removeItems()-metodia, joka poistaa kaikki esineet kentästä, joiden indeksit löytyvät parametrina annetusta listasta.

##### Level.updateDoors()
Tutkitaan tilannetta, jossa kentän ainoa ovi avautuu. Kyseiseen oveen liittyy vain yksi vihollinen.

<img src="https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/updateDoors_sekvenssi.png" width="500">

Ensin metodi suorittaa Door-luokan executeOpen()-metodin, joka tarkistaa kaikki oveen liittyvät vihollisoliot. Mikäli kaikkiin vihollisiin on osuttu, asetetaan oven open-parametri arvoon true.

Lopuksi kyseinen Door-olio poistetaan kentältä, mikäli oven open-parametri on true.
