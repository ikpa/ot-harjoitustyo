# Testausdokumentti
Ohjelman metodeita on testattu lukuisin automaattisten yksikkö- ja integraatiotestein JUnitin avulla, sekä jokaisen
pelikentän objektin tsekä itse kenttien toimivuus on testattu manuaalisesti pelaamalla. 
## Yksikkö- ja integraatiotestit
### Sovelluslogiikka
Automaattisilla testeillä testataan pakkauksen labyrintti.logic metodien toimintaa. Testiluokkien avulla pyrittiin simuloimaan eri pelikentän objektien toimintaa ja vuorovaikutusta toistensa kanssa. Pakkauksen labyrintti.logic.freemovers luokkien testeillä pyritään testaamaan liikkumiseen sekä seinien ja piikki-olioiden kanssa vuorovaikuttamiseen.  

Paketin labyrintti.logic.level luokan Level testit testaavat tason päivittäviin, ja objektien poistamiseen perustuvia metodeita. Itse objektien välinen vuorovaikutus jäi muiden pakettien testien kokeiltavaksi. Lisäksi varsinaisen update-metodin testaaminen tehtiin manuaalisesti. LvlConstructor-luokan testit kokeilevat vain, että luokat luovat oikean määrän objekteja kyseisiin kenttiin. Ilman näitä testejä rivikattavuutta ei olisi saatu viikkopalautuksiin vaadituille tasolle.  

Paketin labyrintti.logic.object testit testaavat pääasiassa vuorovaikutusta MainChara-olion kanssa. 
### DAO-luokat
HighScoreDao-luokkaa testattiin luomalla väliaikainen tiedosto ennen joka testiä, johon tieto tallennettiin.  
### Testauskattavuus
Lukuunottamatta käyttöliittymää, automaattisten testien rivikattavuus oli 98%, sekä haarautumakattavuus 82%
<img src="https://github.com/ikpa/ot-harjoitustyo/blob/master/dokumentointi/testikattavuus.png" width="800">  

Automaattisilla testeillä pyrittiin testaamaan yksiselitteisiä tapauksia; hienovaraisempi testaus tehtiin manuaalisesti testaamalla. Lisäksi testaamatta jäi sellaiset haarautumat, joissa metodin toiminta lopetetaan ArrayListin ollessa tyhjä.
## Manuaalinen testaus
Jokaisen objektin ja tason, sekä valikkojen toiminta on testattu manuaalisesti.
### Peliobjektit
Jokaisen pelihahmon kanssa vuorovaikuttavan objektin toiminta on testattu testikenttien avulla. Etenkin rajatapauksia, sekä hienovaraisuutta vaativia toimintoja ja säätöjä pyrittiin testaamaan manuaalisten testien avulla automaattisten testien sijasta.  

Jokaisen kentän kaikki seinät ja kulmat on testattu manuaalisesti siten, että mistään ei pääse vahingossa läpi tai jää jumiin.  
### Valikot
Valikkojen toiminta on testattu manuaalisesti siten, että napit toimivat ja ruudut vaihtuvat oikealla tavalla, sekä että ohjelma näyttää oikean tiedon kussakin tilanteessa.  
## Sovellukseen jääneet ongelmat
Peli voi edelleen sisältää erilaisia bugeja etenkin seinien toimintaan sekä tasojen vaihtoon liittyen. Suurin koodiin jäänyt bugi on joissakin tilanteissa neljänneltä tasolta viidennelle vaihtaminen ei toimi. Tämän bugin lähdettä ei tiedetä.
