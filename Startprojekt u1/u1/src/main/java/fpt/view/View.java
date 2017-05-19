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
    private final  ListView<Song> songListV = new ListView<>();
    private final ListView<Song> playListV = new ListView<>();
    private TextField titelS = new TextField();

    Button addtoplaylist;

    public Button getAddToPlayButton(){

        return addtoplaylist;
    }
    public ListView<Song> getSongList(){
        return songListV;
    }

    public View() {
        VBox stack = new VBox();
        VBox stack2 = new VBox();
        VBox stack3 = new VBox();
        HBox hBox = new HBox();
        HBox hBox2 = new HBox();
        HBox hbox3 = new HBox();

        Label label1 = new Label("titel :");

        titelS.setPrefSize(90, 10);
        Label label2 = new Label("interpret :");
        TextField interpret = new TextField();
        interpret.setPrefSize(90, 10);
        Label label3 = new Label("album :");
        TextField album = new TextField();
        album.setPrefSize(90, 10);


        songListV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        songListV.setPrefSize(350, 550);
        songListV.setCellFactory(e -> new ListCell<Song>() {
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


        playListV.setPrefSize(350, 550);
        playListV.setCellFactory(e -> new ListCell<Song>() {
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

        Button addall = new Button("addall");
        addall.setPrefSize(50, 10);
        Button load = new Button("load");
        Button save = new Button("save");
        load.setPrefSize(60, 10);
        save.setPrefSize(60, 10);
        addtoplaylist = new Button("Add to playlist");
        addtoplaylist.setPrefSize(90, 10);
        //ImageView stop = new ImageView();
        Button stop = new Button("stop");

        Button play = new Button("play");
        //addtoplaylist.setOnAction(e->{controller.;});
        Button next = new Button("next");
        Button commit = new Button("commit");

        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.setPrefWidth(350);
        choiceBox.getItems().addAll("stategie");//spaeter werden die Strategie hinzugzfuegt
        choiceBox.getSelectionModel().selectFirst();

        hBox.setSpacing(40);
        hBox.getChildren().addAll(choiceBox, load, save);
        hBox2.getChildren().addAll(stack, stack2, stack3);
        stack.getChildren().addAll(songListV);
        stack2.getChildren().addAll(playListV);
        stack3.getChildren().addAll(label1, titelS, label2, interpret, label3, album, hbox3, addtoplaylist);
        stack3.setSpacing(20);
        hbox3.getChildren().addAll(stop, play, next, commit);
        hbox3.setSpacing(5);

        setTop(hBox);
        setBottom(addall);
        setCenter(hBox2);

        songListV.setOnMousePressed(event -> {
            System.out.println("CLICK");
            titelS.setText(songListV.getSelectionModel().getSelectedItem().getTitle());
        });

       songListV.setOnMouseClicked(event -> {

           titelS.setText(songListV.getSelectionModel().getSelectedItem().getTitle());
           interpret.setText(songListV.getSelectionModel().getSelectedItem().getInterpret());
            album.setText(songListV.getSelectionModel().getSelectedItem().getAlbum());
        });
        commit.setOnAction(event -> {
                    Song s = songListV.getSelectionModel().getSelectedItem();
                   s.setAlbum(album.getText());
            s.setInterpret(interpret.getText());
            s.setTitle(titelS.getText());


                }
        );




    }

    public void fillPlayList(SongList items) {
        playListV.setItems(items);
    }

    public void fillSongList(SongList items) {
        songListV.setItems(items);
    }

    public void link(ButtonAction controller) {
        this.controller = controller;
    }
}
