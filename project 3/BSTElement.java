//Oscar Cansino
//creates key values
import java.util.*;
public class BSTElement{
   
   public void createKey(int power, ArrayList<Integer> x){
      int n = (int) Math.pow(2, power)-1;
      for (int i = 0; i < n; i++){ 
         x.add(i);
      }
   
   }


}