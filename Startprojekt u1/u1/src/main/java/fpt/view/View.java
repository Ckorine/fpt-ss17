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
    Button removeFromPlaylist;
    Button addall;
    Button load;
    Button save;
    Button stop;
    Button play;
    Button next;
    Button commit;


    public Button getAddToPlayButton(){

        return addtoplaylist;
    }
    public Button getRemoveFromPLay(){

        return removeFromPlaylist;
    }
    public Button getPLay(){

        return play;
    }
    public Button getAddall(){
        return addall;
    }

    public ListView<Song> getSongList(){
        return songListV;
    }
    public ListView<Song> getPlayList(){
        return playListV;
    }

    public View() {
        VBox stack = new VBox();
        VBox stack2 = new VBox();
        VBox stack3 = new VBox();
        HBox hBox = new HBox();
        HBox hBox2 = new HBox();
        HBox hbox3 = new HBox();

        Label label1 = new Label("titel :");

        titelS.setPrefSize(200, 10);
        Label label2 = new Label("interpret :");
        TextField interpret = new TextField();
        interpret.setPrefSize(200, 10);
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



        load = new Button("load");
        save = new Button("save");
        load.setPrefSize(60, 10);
        save.setPrefSize(60, 10);
        addall = new Button("add all");
        addall.setPrefSize(100, 10);
        addtoplaylist = new Button("Add to playlist");
        addtoplaylist.setPrefSize(140, 10);
        removeFromPlaylist = new Button("remove from playlist");
        removeFromPlaylist.setPrefSize(160, 10);
        //ImageView stop = new ImageView();
        stop = new Button("stop");
        play = new Button("play");
        next = new Button("next");
        commit = new Button("commit");

        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.setPrefWidth(350);
        choiceBox.getItems().addAll("stategie");//spaeter werden die Strategie hinzugzfuegt
        choiceBox.getSelectionModel().selectFirst();

        hBox.setSpacing(40);
        hBox.getChildren().addAll(choiceBox, load, save);
        hBox2.getChildren().addAll(stack, stack2, stack3);
        stack.getChildren().addAll(songListV);
        stack2.getChildren().addAll(playListV);
        stack3.getChildren().addAll(label1, titelS, label2, interpret, label3, album, hbox3, addtoplaylist,removeFromPlaylist);
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
