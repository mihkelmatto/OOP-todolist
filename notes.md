# editableField loomine ja implementeerimine
+ editableField, mille seletus on lisatud kommentaarina
    on mõeldud asendama UIUtils.createTextField()

+ implementeeritud:
    account lehel
    home pealkirjas

# TODO:

täielikult implementeerida editableField (UIUtils asemel)
account nupp:
- ikoon
- dropdown, mis navigeerib account lehele või logib välja

Account scene
- nuppudele parameetriteks lambda-meetodid (functional interface?)


TaskCard sisu:
- setEditable // toggleEditable käsitlemine erinevates komponentides
- property listenerid, bindingud jm käsitlemine

EditableField implementeerimine TaskCard (ja DLWidget) klassis:
- TaskCard klass
- DLWidget klass
    - date-time uuendab end mitu korda
    - input validation liigutada Validator liidesega klassi
    - 


# Viimane commit:
Editablefield
- kasutab nüüd seesmiselt talle antud StringProperty-t.
- update tsükkel: valuefield -> validate -> valueproperty -> valuelabel

Task:
- updateDeadline loogika
    - formaat -> DLwidget
    - validatsioon -> Validator klass

DLwidget:
- sisaldab staatiliselt ajaformaati