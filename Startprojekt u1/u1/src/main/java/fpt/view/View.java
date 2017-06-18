package fpt.view;

import fpt.controller.Controller;
import fpt.interfaces.Song;
import fpt.model.SongList;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;

import java.awt.*;
import java.awt.Rectangle;
import java.text.NumberFormat;


public class View extends BorderPane{
    private Song selectedSong;


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
    private ChoiceBox<String> choiceBox;
    private Controller controller = new Controller();


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
    public Button getNext(){
        return next;
    }
    public Button getLoad(){ return  load;}
    public Button getSave(){return  save;}


    public ListView<Song> getSongList(){
        return songListV;
    }
    public ListView<Song> getPlayList(){
        return playListV;
    }


    public View() {
        Label label1 = new Label("Titel :");
        titelS.setPrefSize(200, 10);
        Label label2 = new Label("Interpret :");
        interpret.setPrefSize(200, 10);
        Label label3 = new Label("Album :");
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
                }
            }
        });


        //ImageView stop = new ImageView();
        load = new Button("Load");
        save = new Button("Save");
        pause = new Button("||");
        load.setPrefSize(60, 10);
        save.setPrefSize(60, 10);
        addAll = new Button("Add all");
        addAll.setPrefSize(100, 10);
        addToPlayList = new Button("Add to playlist");
        addToPlayList.setPrefSize(140, 10);
        removeFromPlaylist = new Button("Remove from playlist");
        removeFromPlaylist.setPrefSize(160, 10);
        stop = new Button();
        play = new Button();
        next = new Button(">>");
        commit = new Button("Commit");
        javafx.scene.shape.Rectangle r = new javafx.scene.shape.Rectangle(10,10);
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
                0.0, 5.0,
                10.0, 10.0,
                0.0, 15.0 });

        play.setGraphic(polygon);
        stop.setGraphic(r);
        stop.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        play.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        load.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        save.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        pause.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        addAll.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));

        addToPlayList.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        removeFromPlaylist.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        next.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));
        commit.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));

        stop.setTextFill(javafx.scene.paint.Paint.valueOf("#000000"));
        play.setTextFill(javafx.scene.paint.Paint.valueOf("#000000"));
        load.setTextFill(javafx.scene.paint.Paint.valueOf("#000000"));
        pause.setTextFill(javafx.scene.paint.Paint.valueOf("#000000"));
        save.setTextFill(javafx.scene.paint.Paint.valueOf("#000000"));
        addAll.setTextFill(javafx.scene.paint.Paint.valueOf("#000000"));
        addToPlayList.setTextFill(javafx.scene.paint.Paint.valueOf("#000000"));
        removeFromPlaylist.setTextFill(javafx.scene.paint.Paint.valueOf("#000000"));
        next.setTextFill(javafx.scene.paint.Paint.valueOf("#000000"));
        commit.setTextFill(javafx.scene.paint.Paint.valueOf("#000000"));

         choiceBox = new ChoiceBox(FXCollections.observableArrayList(controller.strategies));
        choiceBox.setPrefWidth(350);
        choiceBox.getItems().addAll("Binäre Serialisierung","XMLSerialisierung","XStreamSerialisierung");//spaeter werden die Strategie hinzugzfuegt
        choiceBox.getSelectionModel().getSelectedItem();

        //choiceBox.getItems().addAll("Binäre Serialisierung");//spaeter werden die Strategie hinzugzfuegt
        choiceBox.getSelectionModel().selectFirst();
        choiceBox.getSelectionModel().selectedItemProperty().addListener(e ->{controller.setStrategy(choiceBox.getSelectionModel().getSelectedIndex());});

        choiceBox.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));

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
        //playListV.setBackground(new Background(new BackgroundFill(Color.INDIANRED, CornerRadii.EMPTY, Insets.EMPTY)));


        setTop(hBox);
        setBottom(addAll);
        setCenter(hBox2);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));

        songListV.setOnMouseClicked(event -> {
            Song s = songListV.getSelectionModel().getSelectedItem();
            if (s != null) {
                selectedSong = s;
                titelS.setText(s.getTitle());
                interpret.setText(s.getInterpret());
                album.setText(s.getAlbum());
            }
        });
        playListV.setOnMouseClicked(event -> {
            Song s = playListV.getSelectionModel().getSelectedItem();
            if (s != null) {
                selectedSong = s;
                titelS.setText(s.getTitle());
                interpret.setText(s.getInterpret());
                album.setText(s.getAlbum());
            }
        });

        commit.setOnAction(event -> {
                    if(selectedSong==null){
                        return;
                    }
                    selectedSong.setAlbum(album.getText());
                    selectedSong.setInterpret(interpret.getText());
                    selectedSong.setTitle(titelS.getText());
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
