package fpt.view;

import fpt.interfaces.ButtonAction;
import fpt.interfaces.Song;
import fpt.model.SongList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class View extends BorderPane{
    private ButtonAction controller;
    private final ListView<Song> songList = new ListView();
    private final ListView<Song> playList = new ListView();

    public TextField getTitel() {
        return titel;
    }

    private TextField titel = new TextField();


    public View() {
        VBox stack = new VBox();
        VBox stack2 = new VBox();
        VBox stack3 = new VBox();
        HBox hBox = new HBox();
        HBox hBox2 = new HBox();
        HBox hbox3 = new HBox();

        Label label1 = new Label("titel :");

        titel.setPrefSize(90, 10);
        Label label2 = new Label("interpret :");
        TextField interpret = new TextField();
        interpret.setPrefSize(90, 10);
        Label label3 = new Label("album :");
        TextField album = new TextField();
        album.setPrefSize(90, 10);


        songList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      /*  songList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Song>() {

            @Override
            public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue) {
                // Your action here

            }
        });*/

        songList.setPrefSize(200, 550);
        songList.setCellFactory(e -> new ListCell<Song>() {
            @Override
            protected void updateItem(Song item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getTitle() == null) {
                    setText(null);
                } else {
                    setText(item.getTitle());
                }
            }
        });

        playList.setPrefSize(200, 550);

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
        stack.getChildren().addAll(songList);
        stack2.getChildren().addAll(playList);
        stack3.getChildren().addAll(label1, titel, label2, interpret, label3, album, hbox3, addtoplaylist);
        stack3.setSpacing(20);
        hbox3.getChildren().addAll(stop, play, next, commit);
        hbox3.setSpacing(5);

        setTop(hBox);
        setBottom(addall);
        setCenter(hBox2);
    }

    public void fillPlayList(ObservableList items) {
        playList.setItems(items);
    }

    public void fillSongList(SongList items) {
        songList.setItems(items);
    }

    public void link(ButtonAction controller) {
        this.controller = controller;
    }
}
