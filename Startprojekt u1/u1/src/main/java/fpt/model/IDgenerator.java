package fpt.model;

import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by benja on 07.06.2017.
 */
public class IDgenerator {
    private static final long idstart = 0;
    private static final long idende = 9999;
    private Model model ;
    public IDgenerator (Model model1){
        this.model=model1;
    }
    public  Long getNextId()throws IDOverFlowException{
          long id = idstart;
          while (model.findSongById(id)!= null){
              id ++;
             if (id > idende) throw new IDOverFlowException ();
          }
        return id;
    }
    //RIEN

}
