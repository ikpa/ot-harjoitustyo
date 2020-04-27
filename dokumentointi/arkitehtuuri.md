# Arkitehtuurikuvaus
## Rakenne
Ohjelma sisältää 5 pakkausta: chars, dao, levels, object ja ui.
### chars
Tämä pakkaus sisältää tällä hetkellä vain MainChara-luokan. Pakkauksen on alustavasti tarkoitus sisältää kaikki useammassa kuin yhdessä suunnassa liikkuvat pelikentän oliot, jotka eivät voi liikkua seinien läpi.
### dao
Tämä pakkaus sisältää HighScoreDao-luokan, jonka tarkoitus on lukea high_score.txt-tiedostoa ja säilöä pelin pistetilastot. Mikäli ohjelmaan lisätään muita datatiedostoja, niiden käsittelyn tekevät oliot lisätään tänne.
### levels
Tämä pakkaus sisältää Level ja LvlConstructor-luokat. Pakkaus sisältää siis taso-olion ja taso-olioiden luomiseen tarvittavat luokat.
### object
Tämä pakkaus sisältää pelikenttien esineet ja esteet, joiden kanssa pelihahmo MainChara pystyy vuorovaikuttamaan, ja niiden luomiseen tarvittavat luokat. Pakkaus sisältää Goal, Item, MovingSpike, Spike ja WallConstructor-luokat.
### ui
Tämä pakkaus sisältää luokat Main ja RealMain, jotka rakentavat itse ajettavan ohjelman.
## Luokkakaavio

<img src="https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/alustavaluokkakaavio.svg" width="300">

## Sovelluslogiikka

Ohjelma koostuu MainChara, sekä Level-olioista. MainChara kuvaa pelihahmoa, joita Main-ohjelmalla on yksi käytössään,
ja Level kuvaa yhtä tasoa, joita Main-ohjelmalla on useita. Kukin Level-olio ottaa MainChara-olion hetkellisesti käyttöönsä, kun kyseistä tasoa pelataan.  

Level-olio sisältää useita erilaisia objekteja, joiden kanssa pelihahmo voi vuorovaikuttaa. Nämä voidaan jaotella luokkiin  
- Goal  
- Spike  
- Item    

Goal-olio on kunkin tason maali, johon pelaaja pyrkii. Jokainen taso vaatii yhden maalin. Maali vie pelaajan seuraavaan
kenttään.  

Spike-oliot ovat objekteja, jotka vievät pelihahmolta yhden elämän ja palauttavat sen takaisin tason alkuun, jos niihin koskee. Luokka MovingSpike perii Spike-luokan, ja on liikkuva variantti tästä.

Item-oliot ovat objekteja, jotka pelihahmon ne kerätessä antavat sille joko pisteitä tai muita positiivisia vaikutuksia.  

Taso sisältää lisäksi joukon Rectangle-olioita, jotka kuvaavat tason seiniä, joiden läpi pelihahmo ei voi liikkua.

### Peliruudun päivittäminen
Pääohjelma Main kutsuu pelitilanteessa AnimationTimer luokan handle-metodin sisällä pelattavan tason update-metodia, joka päivittää peliruudun n. 60 kertaa sekunnissa. Tämä metodi käy läpi tason objektit, tarkistaen osumat esineiden, piikkiesteiden ja seinien kanssa, sekä liikuttaen piikkiesteitä ja pelihahmoa.

<img src="https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/update_sekvenssi.png" width="500">

Yllä oleva sekvenssikaavio kuvaa tilannetta, jossa peliruutu päivitetään. Kyseisessä tilanteessa pelaaja painaa oikeaa ja ylempää nuolinäppäintä, mutta pelihahmon oikealla puolella on seinä. Samanaikaisesti pelihahmo kerää yhden kulta-esineen, ja tasossa oleva piikkieste liikkuu.
