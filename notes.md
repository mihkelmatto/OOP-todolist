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
    - taskcard setEditable() seest sorting teise kohta viia
    - taskCard setEditable kutsutakse nupule vajutades kaks korda
        - task.editableproperty asemel kasutada booleani ja eventi?
    - lisada focus-põhine setEditable(false)? (miks headeri nupuga see niisama töötab?)

    account nupp:
    - ikoon
    - dropdown, mis navigeerib account lehele või logib välja

    parandada bug, kus headeri options-menüü tekib teisele ekraanile

Muu
    Luua eraldi klass ikoonide kuvamiseks pildifailist (asendamaks näiteks Account.row ikooni)

    TaskCard.setEditable() seest sorting teise kohta viia

    Utils.Auth teha ümber validator klassiks

    Kõik listenerid-eventid üle vaadata
    - headeri teksti update
    - ...
    + DLWidget

    Eventide hierarhia / funktsionaalsus?
    - TaskGroup
    - Task

# Viimane commit:
    - dropdownwidget -> Dropdown, HomeHeader
    - Task Eventid: new, del, update.
    - 