package fpt.Strategy;

import java.sql.*;

import static java.sql.JDBCType.NULL;
import static java.text.Collator.PRIMARY;
import static jdk.nashorn.internal.parser.TokenType.NOT;
import static sun.security.x509.CertificateX509Key.KEY;

/**
 * Created by corin on 17.06.2017.
 */
public class DatabaseUtils {

    public static void createDatabase(){
        //Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (  ClassNotFoundException e ) {
            e.printStackTrace();
            /*System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);*/
        }
        try(Connection c = DriverManager.getConnection("jdbc:sqlite:music.db")){
        }catch(SQLException se){
            /*System.err.println( se.getClass().getName() + ": " + se.getMessage() );
            System.exit(0);*/

            se.printStackTrace();
        }
        System.out.println("Opened database successfully");
    }
}
