package fpt.controller;

import fpt.model.Model;
import fpt.view.View;


/**
 * Created by corin on 09.05.2017.
 */
public class Controller {
    private View view;
    private Model model;

    public Controller() {

    }

    public void link (Model model,View view){
        this.model = model;
        this.view = view;

        view.link(this);
    }



}
