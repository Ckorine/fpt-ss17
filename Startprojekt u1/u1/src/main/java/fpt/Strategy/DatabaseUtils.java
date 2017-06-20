package fpt.Strategy;

import fpt.interfaces.SerializableStrategy;
import fpt.interfaces.Song;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by corin on 17.06.2017.
 */
public class DatabaseUtils implements SerializableStrategy {

    private Connection con;
    private String tableName;

    public DatabaseUtils() {
        createDatabase();
    }

    public void createDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection tempCon = DriverManager.getConnection("jdbc:sqlite:music.db")) {
                System.out.println("Opened database successfully");

                try (Statement stmt = tempCon.createStatement()) {
                    String createLibrary = "CREATE TABLE LIBRARY" +
                            "(ID LONG PRIMARY KEY  NOT NULL," +
                            "TITEL            TEXT ," +
                            "INTERPRET        TEXT ," +
                            "ALBUM            TEXT ," +
                            "PATH             TEXT )";
                    stmt.executeUpdate(createLibrary);
                    System.out.println("created Library Table");
                } catch (SQLException eLTest) {
                    System.out.println("Library Table already exists");
                }

                try (Statement stmt = tempCon.createStatement()) {
                    String createPlaylist = "CREATE TABLE PLAYLIST" +
                            "(ID LONG PRIMARY KEY  NOT NULL," +
                            "TITEL            TEXT ," +
                            "INTERPRET        TEXT ," +
                            "ALBUM            TEXT ," +
                            "PATH             TEXT )";
                    stmt.executeUpdate(createPlaylist);
                    System.out.println("created Playlist Table");
                } catch (SQLException eTest) {
                    System.out.println("Playlist Table already exist");
                }
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertSong(Song s) {
        try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO LIBRARY VALUES (?,?,?,?,?)")) {
            pstmt.setLong(1, s.getId());
            pstmt.setString(2, s.getTitle());
            pstmt.setString(3, s.getInterpret());
            pstmt.setString(4, s.getAlbum());
            pstmt.setString(5, s.getPath());
            pstmt.executeUpdate();
            System.out.println("Song " + s.getTitle() + " inserted");
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println("Song insertSong inserted");
    }
    public fpt.model.Song findSongByID(long id){
        ResultSet rs;
        fpt.model.Song s = new fpt.model.Song();
        try (PreparedStatement pstmt = con.prepareStatement("SELECT ID,TITEL,INTERPRET,ALBUM,PATH FROM " + tableName + " WHERE ID =" + id)){
            rs = pstmt.executeQuery();

            s.setId(rs.getInt("ID"));
            s.setTitle(rs.getString("TITEL"));
            s.setInterpret(rs.getString("INTERPRET"));
            s.setAlbum(rs.getString("ALBUM"));
            s.setPath(rs.getString("PATH"));

            System.out.println("Song :" + rs.getString("TITEL"));
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return s;
    }
    public ArrayList<fpt.model.Song> getAllSongsFromTable(){

        ArrayList<fpt.model.Song> sList = new ArrayList<>();
        ResultSet res;
        try{
            PreparedStatement pstmtL = con.prepareStatement("SELECT ID,TITEL,INTERPRET,ALBUM,PATH FROM " + tableName );
            res = pstmtL.executeQuery();
            while(res.next()){
                sList.add(new fpt.model.Song(res.getInt("ID"),res.getString("TITEL"),res.getString("INTERPRET"),res.getString("ALBUM"),res.getString("PATH")));
            }
        }catch (SQLException sl){}
        return sList;
    }
    public void deleteSongWithID(long id){
        try {
            PreparedStatement pstmtL = con.prepareStatement("DELETE ID,TITEL,INTERPRET,ALBUM,PATH FROM " + tableName + "WHERE ID =" + id);
            pstmtL.executeUpdate();
        }catch (SQLException|NullPointerException e){}
    }

    @Override
    public void openWriteableSongs() throws IOException {
        connect();
        tableName = "LIBRARY";
    }

    @Override
    public void openReadableSongs() throws IOException {
        connect();
        tableName = "LIBRARY";
    }

    @Override
    public void openWriteablePlaylist() throws IOException {
        connect();
        tableName = "PLAYLIST";
    }

    @Override
    public void openReadablePlaylist() throws IOException {
        connect();
        tableName = "PLAYLIST";
    }

    @Override
    public void writeSong(fpt.interfaces.Song s) throws IOException {

        System.out.println("+++");
        if (s != null) {
            insertSong(s);
            System.out.println("Song write inserted");
        } else {
            throw new IOException("Song to write doesn't exist");
        }
        System.out.println("Song write2  inserted");
    }

    @Override
    public fpt.interfaces.Song readSong() throws IOException, ClassNotFoundException {

            if (getAllSongsFromTable().isEmpty()) {
                return null;
            } else {
                return getAllSongsFromTable().remove(0);
            }

    }

    @Override
    public void closeReadable() throws IOException {
        try {
            con.close();
        } catch (SQLException | NullPointerException e) {}
    }

    @Override
    public void closeWriteable() throws IOException {
        try {
            con.close();
        } catch (SQLException | NullPointerException e) {}
    }

    private void connect() throws IOException {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection("jdbc:sqlite:music.db");
            }
        } catch (SQLException e) {
            throw new IOException("Couldn't open Database Connection");
        }
    }
}
