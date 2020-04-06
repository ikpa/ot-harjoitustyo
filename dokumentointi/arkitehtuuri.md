# Arkitehtuurikuvaus
## Luokkakaavio

<img src="https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/alustavaluokkakaavio.svg" width="160">

## Sovelluslogiikka

Ohjelma koostuu MainChara, sekä Level-olioista. MainChara kuvaa pelihahmoa, joita Main-ohjelmalla on yksi käytössään,
ja Level kuvaa yhtä tasoa, joita Main-ohjelmalla on useita. Kukin Level-olio ottaa MainChara-olion hetkellisesti käyttöönsä,
kun kyseistä tasoa pelataan.  

Level sisältää useita erilaisia objekteja, joiden kanssa pelihahmo voi vuorovaikuttaa. Nämä voidaan jaotella luokkiin  
-Goal  
-Hostile  
-Item  
-Door  

Goal-olio on kunkin tason maali, johon pelaaja pyrkii. Jokainen taso vaatii yhden maalin. Maali vie pelaajan seuraavaan
kenttään.  

Hostile-oliot ovat objekteja, jotka voivat vahingoittaa pelihahmoa, eli viedä siltä elämän. Hostile-olioita ovat esim.
piikkiesteet sekä viholliset.  

Item-oliot ovat objekteja, jotka pelihahmon ne kerätessä antavat sille joko pisteitä tai muita positiivisia vaikutuksia.  

Door-oliot ovat ovia, jotka estävät pelihahmon kulun johonkin alueeseen. Door-luokkaan kuuluu eri alaluokkia riippuen siitä,
miten pelaaja ne voi avata. Esim. EnemyDoor-oven voi avata tuhoamalla kaikki kentän viholliset ja ButtonDoor-oven painamalla
yhtä tai useampaa kentästä löytyvää nappia.
