//Oscar Cansino
//Application class but also sets up and does operations on given BST's
import java.util.*;
public class BSTP3 extends Sample{
   BSTNode root;
   
   ArrayList<Integer> key = new ArrayList<Integer>();
   ArrayList<Integer> reinsert = new ArrayList<Integer>();
   
   public void BSTP3(){ 
      root = null; 
   } 
   
   public static void main(String args[]){ 
      BSTP3 tree = new BSTP3();  
      ArrayList<Integer> Height = new ArrayList<Integer>();
      ArrayList<Integer> HeightInsert = new ArrayList<Integer>();
      ArrayList<Integer> HeightDelete = new ArrayList<Integer>();
      int run = 0;
      int power = 5;
      BSTElement one = new BSTElement();
      do{
         one.createKey(power, tree.key);
         
         Collections.shuffle(tree.key);
         
         tree.create(power);
         tree.verify();
         Height.add(tree.height(tree.root));
         
         tree.deleteQue(power);
         tree.verify();
         HeightDelete.add(tree.height(tree.root));
         
         tree.reinsertNode();
         tree.verify();
         HeightInsert.add(tree.height(tree.root));
         
         tree.reset();
         run++;
      }while(run < 1000);
      System.out.println("Create: ");
      tree.Run(Height);
      System.out.println("Delete: ");
      tree.Run(HeightDelete);
      System.out.println("Insert: ");
      tree.Run(HeightInsert);  
   }       
   //gets the height
   public int height(BSTNode root){ 
      if (root == null){ 
         return 0;
      }    
      else{ 
         int leftHeight = height(root.left); 
         int rightHeight = height(root.right); 
         if (leftHeight > rightHeight){ 
            return(leftHeight+1);
         } 
         else{ 
            return(rightHeight+1);
         }  
      } 
   }  
   
    //verifies if tree is bst 
   public boolean verify(){
      assert isBST();
      if (isBST()){ 
         return true; 
      }
      else{
         return false;
      }  
   } 
    //creates bst
   public void create(int power){
      int nodeNum = (int) Math.pow(2, power) - 1;
      for(int x = 0; x < nodeNum; x++){
         callInsert(key.get(x));
      }
   }
    //calls insert method
   public void callInsert(int key){ 
      root = insert(root, key); 
   } 
      
    //inserts into BST
   public BSTNode insert(BSTNode root, int key){ 
      if (root == null) { 
         root = new BSTNode(key); 
         return root; 
      } 
  
      if (key < root.value){ 
         root.left = insert(root.left, key);
      } 
      
      else if (key > root.value){ 
         root.right = insert(root.right, key); 
      }
      return root; 
   } 
  
   public boolean isBST()  { 
      return isBSTCheck(root, Integer.MIN_VALUE, Integer.MAX_VALUE); 
   } 
  
    // Returns true if the tree is a BST
   public boolean isBSTCheck(BSTNode node, int min, int max){ 
      if (node == null){
         return true; 
      }
        
      if (node.value < min || node.value > max){ 
         return false; 
      } 
      return (isBSTCheck(node.left, min, node.value-1) && isBSTCheck(node.right, node.value+1, max)); 
   } 
    
   public void reset(){
      root = null;
      key.clear();
      reinsert.clear();
   }
    
    //sets up deletion and saves deleted key for reinsert 
   public void deleteQue(int power){
      int deleteNum = (int) Math.pow(2, (power - 1))-1;
      for (int x = 0; x < deleteNum; x++){
         deleteKey(key.get(x));
         reinsert.add(key.get(x));
      }
   }
   
   public void deleteKey(int key){ 
      root = delete(root, key);
   } 
  
    //deletes a key in BST 
   public BSTNode delete(BSTNode root, int key){ 
      if (root == null){  
         return root; 
      } 
      if (key < root.value){ 
         root.left = delete(root.left, key);
      } 
      else if (key > root.value){ 
         root.right = delete(root.right, key);
      } 
      else{ 
         if (root.left == null){ 
            return root.right; 
         }
         else if (root.right == null){ 
            return root.left;
         } 
         root.value = minValue(root.right); 
         root.right = delete(root.right, root.value); 
       } 
       return root; 
   }
    //finds min value
   public int minValue(BSTNode root){ 
      int minval = root.value; 
      while (root.left != null){ 
         minval = root.left.value; 
         root = root.left; 
      } 
      return minval; 
   }  
    //reinserts
   public void reinsertNode(){
      for(int x = 0; x < reinsert.size(); x++){
         callInsert(reinsert.get(x));
      }
   }

} 

