# TODO:

Account scene
    Read ühendada models-andmetega

    Lisada käitumine ridade muutmisel
    - Validatorid
    - Username vahetamisel unikaalsuse kontroll
    - nuppudele parameetriteks lambda-meetodid? (functional interface?)

Home scene
    TaskCard sisu:
    - property listenerid, bindingud jm käsitlemine

    account nupp:
    - ikoon
    - dropdown, mis navigeerib account lehele või logib välja

    parandada bug, kus headeri options-menüü tekib teisele ekraanile

Muu
    Viia ülejäänud UI-klassid standardformaati + refactor
    - TaskCard.setEditable() seest sorting teise kohta viia

    EditableField css-konstruktor eemaldada?

    Kõik listenerid-eventid üle vaadata
    - Mingi bug, kus iga taskcardi ajasektsioonid kaovad ära
    - headeri teksti update
    - ...

# Viimane commit:
UIUtils klass eemaldatud.
Refactor
EditableField võtab uuesti Stringi sisendina