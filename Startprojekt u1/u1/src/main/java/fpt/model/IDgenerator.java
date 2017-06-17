package fpt.model;

/**
 * Created by benja on 07.06.2017.
 */
public class IDgenerator {
    private static final long idStart = 0;
    private static final long idEnde = 9999;
    private static Model model ;
    public IDgenerator (Model model1){
        this.model=model1;
    }
    public static  Long getNextId()throws IDOverFlowException{
          long id = idStart;
          try {
              while (model.findSongById(id) != null) {
                  id++;
                  if (id > idEnde) throw new IDOverFlowException();
              }

          }catch (NullPointerException ne){
              ne.printStackTrace();
          }return id;
    }
    //RIEN

}
