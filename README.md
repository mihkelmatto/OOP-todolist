# OOP-todolist
Projekti eesmärgiks on teha esmalt lokaalne todo list koos kasutajaliidese ja põhifunktsionaalsusega. <br>
Võimalusel lisandub ka server.  <br> 

UI käivitamine root kaustast:                 <br>
mvn clean install (enne esimest käivitamist)  <br>
mvn javafx:run                                <br>

## põhifunktsionaalus
Kasutaja saab sisse logides näha oma ülesandeid.<br>
Ülesanded on kategooriate kaupa ning saab teiste kasutajatega kategooriate kaupa jagada.<br>
<br>
ülesandel on pealkiri, kirjeldus ja tähtaeg. <br>
ülesandeid saab vaadata, lisada, muuta, kustutada (lõpetada) <br>
ülesandeid saab otsida - pealkirja, tähtaja, värvi järgi <br>
Ülesandeid ja kasutajainfot salvestatakse json-failidena <br>

## server
kõiki andmeid hoitakse keskses serveris, kuhu saab üle võrgu ühenduda <br>
kasutajad logivad sisse ja nende tehtud muudatused seotakse nende kontoga <br>
ülesande looja ja täitja on eraldi. kasutajad saavad üksteisele ülesandeid määrata <br>
ülesandeid saab otsida ka, looja, täitja järgi <br>

Sünkroniseerimine: <br>
- UUID põhjal <br>
- toimub muudatuste või UI avamise korral <br>
- veakindlus (erinevate crashide käsitlemine) <br>

## emailide saatmine kliendile
- tähtaja lähenedes <br>
- muutumisel / kommenteerimisel <br>
