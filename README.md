# OOP-todolist
Projekti eesmärgiks on teha esmalt lokaalne todo list koos kasutajaliidese ja põhifunktsionaalsusega. Võimalusel lisandub ka server. 

UI käivitamine root kaustast:                 <br>
mvn clean install (enne esimest käivitamist)  <br>
mvn javafx:run                                <br>


## Ülesannetel on:
- pealkiri <br>
- UUID <br>
- tähtaeg <br>
- viimase muudatuse aeg? <br>
- Looja (User klass, et hiljem oleks lihtsam serverit teha) <br>
- ülesande sisu <br>
- värv (organiseerimine) <br>

## põhifunktsionaalus
ülesandeid saab vaadata, lisada, muuta, kustutada ja lõpetatuks märkida <br>
ülesandeid saab otsida - pealkirja, tähtaja, värvi järgi <br>
ülesandele saab tähtaja määrata <br>
Ülesandeid saab kettale salvestada ja neid sealt lugeda <br>
Ülesandeid saab näha kasutajaliidese kaudu <br>

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
