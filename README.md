# OOP-todolist
Projekti eesmärgiks on teha esmalt lokaalne todo list koos kasutajaliidese ja põhifunktsionaalsusega. Võimalusel lisandub ka server. 

UI käivitamine root kaustast:
mvn clean install (enne esimest käivitamist)  <br>
mvn javafx:run                                <br>


## Ülesannetel on:
- pealkiri
- UUID
- tähtaeg
- viimase muudatuse aeg?
- Looja (User klass, et hiljem oleks lihtsam serverit teha)
- ülesande sisu
- värv (organiseerimine)

## põhifunktsionaalus
ülesandeid saab vaadata, lisada, muuta, kustutada ja lõpetatuks märkida
ülesandeid saab otsida - pealkirja, tähtaja, värvi järgi
ülesandele saab tähtaja määrata
Ülesandeid saab kettale salvestada ja neid sealt lugeda
Ülesandeid saab näha kasutajaliidese kaudu

## server
kõiki andmeid hoitakse keskses serveris, kuhu saab üle võrgu ühenduda
kasutajad logivad sisse ja nende tehtud muudatused seotakse nende kontoga
ülesande looja ja täitja on eraldi. kasutajad saavad üksteisele ülesandeid määrata
ülesandeid saab otsida ka, looja, täitja järgi

Sünkroniseerimine:
- UUID põhjal
- toimub muudatuste või UI avamise korral
- veakindlus (erinevate crashide käsitlemine)

## emailide saatmine kliendile
- tähtaja lähenedes
- muutumisel / kommenteerimisel
