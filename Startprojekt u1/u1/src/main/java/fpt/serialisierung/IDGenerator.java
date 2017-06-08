package fpt.serialisierung;

/**
 * Created by corin on 02.06.2017.
 */
public class IDGenerator  {
    private static long  id ;

    public static long getNextID(){
        return id++;

    }
}
