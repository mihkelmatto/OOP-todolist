# TODO:

Account scene
    Read ühendada models-andmetega

    Lisada käitumine ridade muutmisel
    - Validatorid
    - Username vahetamisel unikaalsuse kontroll

Home scene
    account nupp:
    - ikoon
    - dropdown, mis navigeerib account lehele või logib välja

    parandada bug, kus headeri options-menüü tekib teisele ekraanile

Muu
    Luua eraldi klass ikoonide kuvamiseks pildifailist (asendamaks näiteks Account.row ikooni)

    Utils.Auth teha ümber validator klassiks

    Eventide hierarhia / funktsionaalsus?
    - TaskGroup
    - Task

# Viimane commit:
    - HomeHeader.title uuendamine
    - Sorteerimisele lisatud ka sekundaarne tunnus (pealkiri, tähestiku järjekorras)
    - OutsideClickHandler -> focus-põhine TaskCardi setEditable()