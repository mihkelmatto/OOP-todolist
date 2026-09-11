package utils.eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/*
    Kutsub this.action poolt defineeritud funktsiooni, kui kasutaja klikib jälgitava elemendi piiridest välja.
    

    ##### EventFilter tuleks lisada Scenele, et ta suudaks kogu UI ulatuses klikke jälgida
    ##### Enne jälgitava node kustutamist tuleb EventFilter eemaldada (object lifecycle)

    Kasutamine:
    
    OutsideClickHandler clickhandler = new OutsideClickHandler(this);
    clickhandler.setAction(Runnable action);

    node.getScene.addEventFilter(
        MouseEvent.MOUSE_PRESSED,
        clickhandler
    );

    ...

    eemaldamine:

    node.getScene().removeEventFilter(
        MouseEvent.MOUSE_PRESSED,
        clickhandler
    );

    ...

    Vajadusel saab targetit vahetada või eemaldada:

    setTarget(Node node)
    clearTarget()
    setAction(Runnable action)
    
*/

public class OutsideClickHandler implements EventHandler<MouseEvent> {

    private Node target;
    private Runnable action = () -> {};

    public OutsideClickHandler(Node target) {
        this.target = target;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getTarget() instanceof Node node && !isInside(node)) {
            action.run();
        }
    }

    private boolean isInside(Node node) {
        while (node != null) {
            if (node == target) {
                return true;
            }

            node = node.getParent();
        }

        return false;
    }

    public void setTarget(Node node){
        this.target = node;
    }

    public void setAction(Runnable action){
        this.action = action;
    }

    public void clearTarget(){
        this.target = null;
    }
}
