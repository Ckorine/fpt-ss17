package fpt.view;

import fpt.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class View extends BorderPane{
    private Controller controller;


    public View() {
        VBox stack = new VBox();
        VBox stack2 = new VBox();
        VBox stack3 = new VBox();
        HBox hBox = new HBox();
        HBox hBox2 = new HBox();
        HBox hbox3 = new HBox();

        Label label1 = new Label("titel :");
        TextField titel = new TextField();
        titel.setPrefSize(90, 10);
        Label label2 = new Label("interpret :");
        TextField interpret = new TextField();
        interpret.setPrefSize(90, 10);
        Label label3 = new Label("album :");
        TextField album = new TextField();
        album.setPrefSize(90, 10);

        final ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList(
                "Single", "Double", "Suite", "Family App");//nur fuer testen
        list.setItems(items);
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        list.setPrefSize(200, 550);

        final ListView<String> liedchoice = new ListView<String>();
        ObservableList<String> lieditems = FXCollections.observableArrayList(
                "Single", "Double");//nur fuer testen beim click soll  ausgewaehlte lied angezeigt werden
        liedchoice.setItems(lieditems);
        liedchoice.setPrefSize(200, 550);

        Button addall = new Button("addall");
        addall.setPrefSize(50, 10);
        Button load = new Button("load");
        Button save = new Button("save");
        load.setPrefSize(45, 10);
        save.setPrefSize(45, 10);
        Button addtoplaylist = new Button("Add to playlist");
        addtoplaylist.setPrefSize(90, 10);
        //ImageView stop = new ImageView();
        Button stop = new Button("stop");

        Button play = new Button("play");
        //addtoplaylist.setOnAction(e->{controller.;});
        Button next = new Button("next");
        Button commit = new Button("commit");

        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.setPrefWidth(200);
        choiceBox.getItems().addAll("stategie");//spaeter werden die Strategie hinzugzfuegt
        choiceBox.getSelectionModel().selectFirst();

        hBox.setSpacing(40);
        hBox.getChildren().addAll(choiceBox, load, save);
        hBox2.getChildren().addAll(stack, stack2, stack3);
        stack.getChildren().addAll(list);
        stack2.getChildren().addAll(liedchoice);
        stack3.getChildren().addAll(label1, titel, label2, interpret, label3, album, hbox3, addtoplaylist);
        stack3.setSpacing(20);
        hbox3.getChildren().addAll(stop, play, next, commit);
        hbox3.setSpacing(5);

        setTop(hBox);
        setBottom(addall);
        setCenter(hBox2);
    }

    public void link(Controller controller) {
        this.controller = controller;
    }
}
