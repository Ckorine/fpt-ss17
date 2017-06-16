package fpt.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by corin on 05.05.2017.
 */
public class Song implements fpt.interfaces.Song,Externalizable{

    private SimpleStringProperty path = new SimpleStringProperty();
    private SimpleStringProperty titel = new SimpleStringProperty();
    private SimpleStringProperty album = new SimpleStringProperty();
    private SimpleStringProperty interpret = new SimpleStringProperty();
    private long id;
    public Song(){}

    public Song(long id, String titel, String path){
        this.id = id;
        this.path.set(path);
        this.titel.set(titel);


    }


    @Override
    public String getAlbum() {
        return album.get();

    }

    @Override
    public void setAlbum(String album) {
        this.album.set(album);
    }



    @Override
    public String getInterpret() {
        return interpret.get();
    }

    @Override
    public void setInterpret(String interpret) {
        this.interpret.set(interpret);

    }

    @Override
    public String getPath() {

        return path.get();
    }

    @Override
    public void setPath(String path) {
        this.path.set(path);

    }

    @Override
    public String getTitle() {

        return titel.getValue();
    }

    @Override
    public void setTitle(String title) {
        this.titel.set(title);
    }

    @Override
    public long getId() {
       
        return id;

    }


    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public ObservableValue<String> pathProperty() {
        return path;
    }

    @Override
    public ObservableValue<String> albumProperty() {
        return album;
    }

    @Override
    public ObservableValue<String> interpretProperty() {
        return interpret;
    }

    @Override
    public String toString() {
        return String.format("%02d", getId()) + " | " + getTitle();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
