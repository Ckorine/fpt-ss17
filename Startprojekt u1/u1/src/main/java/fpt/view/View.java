package fpt.view;

import fpt.controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.awt.event.ActionListener;

/**
 * Created by corin on 09.05.2017.
 */
public class View extends BorderPane{
    private Controller controller;

    public View () {
        Button b = new Button("press");

    }

    public void link(Controller c) {
        this.controller = c;
    }
}
