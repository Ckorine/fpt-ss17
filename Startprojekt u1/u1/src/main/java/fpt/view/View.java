package fpt.view;

import fpt.interfaces.Song;
import fpt.model.SongList;
import javafx.beans.binding.Bindings;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


import java.awt.*;
import java.text.NumberFormat;


public class View extends BorderPane{
    VBox stack = new VBox();
    VBox stack2 = new VBox();
    VBox stack3 = new VBox();
    HBox hBox = new HBox();
    HBox hBox2 = new HBox();
    HBox hbox3 = new HBox();

    private final  ListView<Song> songListV = new ListView<>();
    private final ListView<Song> playListV = new ListView<>();

    private TextField titelS = new TextField();
    TextField interpret = new TextField();
    TextField album = new TextField();
    private Button addToPlayList;
    private Button removeFromPlaylist;
    private Button addAll;
    private Button load;
    private Button save;
    private Button stop;
    private Button play;
    private Button next;
    private Button commit;
    private Button pause;


    public Button getAddToPlayButton(){

        return addToPlayList;
    }
    public Button getRemoveFromPLay(){

        return removeFromPlaylist;
    }
    public Button getPlay(){

        return play;
    }
    public Button getAddall(){
        return addAll;
    }
    public Button getStop(){
        return stop;
    }
    public Button getPause(){
        return pause;
    }

    public ListView<Song> getSongList(){
        return songListV;
    }
    public ListView<Song> getPlayList(){
        return playListV;
    }

    public View() {


        Label label1 = new Label("titel :");
        titelS.setPrefSize(200, 10);
        Label label2 = new Label("interpret :");
        interpret.setPrefSize(200, 10);
        Label label3 = new Label("album :");
        album.setPrefSize(90, 10);
        songListV.setPrefSize(350, 550);
        playListV.setPrefSize(350, 550);
       // songListV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // playListV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        songListV.setCellFactory(e -> new ListCell<Song>() {
            @Override
            protected void updateItem(Song item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getTitle() == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                    //setText(String.format("%02d", item.getId()) + " | " + item.getTitle());
                }
            }
        });


        playListV.setCellFactory(e -> new ListCell<Song>() {
            @Override
            protected void updateItem(Song item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getTitle() == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                    //setText(String.format("%02d", item.getId())+ " | " + item.getTitle());
                }
            }
        });



        load = new Button("load");
        save = new Button("save");
        pause = new Button("||");
        load.setPrefSize(60, 10);
        save.setPrefSize(60, 10);
        addAll = new Button("add all");
        addAll.setPrefSize(100, 10);
        addToPlayList = new Button("Add to playlist");
        addToPlayList.setPrefSize(140, 10);
        removeFromPlaylist = new Button("remove from playlist");
        removeFromPlaylist.setPrefSize(160, 10);
        //ImageView stop = new ImageView();
        stop = new Button("stop");
        play = new Button(">");
        next = new Button(">>");
        commit = new Button("commit");

        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.setPrefWidth(350);
        choiceBox.getItems().addAll("stategie");//spaeter werden die Strategie hinzugzfuegt
        choiceBox.getSelectionModel().selectFirst();

        stack3.setStyle("-fx-background: #DC143C;");
        hBox2.setStyle("-fx-background: #DC143C;");

        hBox.setSpacing(40);
        hBox.getChildren().addAll(choiceBox, load, save);
        hBox2.getChildren().addAll(stack, stack2, stack3);
        stack.getChildren().addAll(songListV);
        stack2.getChildren().addAll(playListV);
        stack3.getChildren().addAll(label1, titelS, label2, interpret, label3, album, hbox3, addToPlayList,removeFromPlaylist);
        stack3.setSpacing(10);
        hbox3.getChildren().addAll(stop, play, pause, next, commit);
        hbox3.setSpacing(5);
        hBox.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        stack.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        hBox2.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        songListV.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        playListV.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        playListV.setStyle("#DC143C");

        setTop(hBox);
        setBottom(addAll);
        setCenter(hBox2);

        songListV.setOnMouseClicked(event -> {
            Song s = songListV.getSelectionModel().getSelectedItem();
            if (s != null) {
                titelS.setText(s.getTitle());
                interpret.setText(s.getInterpret());
                album.setText(s.getAlbum());
            }
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
}
