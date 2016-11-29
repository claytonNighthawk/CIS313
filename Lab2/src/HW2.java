import java.util.Scanner;

public class HW2 {
    
    private static boolean isIso(Node<Integer> root1, Node<Integer> root2){
        
        if (root1 == null && root2 == null){
            return true;
        }
        
        if (root1 == null || root2 == null){
            return false;
        }
        
        return isIso(root1.getLeftChild(), root2.getLeftChild()) && isIso(root1.getRightChild(), root2.getRightChild());
    }
    public static void main(String[] args) {

        // Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, perform the corresponding function
        //      (ie) insert, delete, find, inorder, preorder, or postorder
        // Hint: Use a switch-case statement
        // Don't forget to close your Scanner!
        
        BST<Integer> bst1 = new BST<Integer>();
        BST<Integer> bst2 = new BST<Integer>(); 
        Scanner scanner = new Scanner(System.in);
        int numLines = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < numLines; ++i){
            String line = scanner.nextLine();
            String[] command = line.split(" ");
            
            switch(command[0]){
                case "insert":
                    bst1.insert(Integer.parseInt(command[1]));
                    bst2.insert(-1 * Integer.parseInt(command[1]));
                    break;
                
                case "delete":
                    bst1.delete(Integer.parseInt(command[1]));
                    bst2.delete(Integer.parseInt(command[1]));
                    break;
                
                case "find":
                    System.out.println(bst1.find(Integer.parseInt(command[1])));
                    System.out.println(bst2.find(Integer.parseInt(command[1])));
                    break;
                
                //uncomment the 3 lines below for testing isIso
                case "preorder":
                    bst1.traverse("preorder", bst1.getRoot());
                    //bst2.traverse("preorder", bst2.getRoot());
                    break;
                    
                case "inorder":
                    bst1.traverse("inorder", bst1.getRoot());
                    //bst2.traverse("inorder", bst2.getRoot());
                    break;
                    
                case "postorder":
                    bst1.traverse("postorder", bst1.getRoot());
                    //bst2.traverse("postorder", bst2.getRoot());
                    break;
                
                case "isIso":
                    System.out.println(isIso(bst1.getRoot(), bst2.getRoot()));
                    break;
            }
        }
        scanner.close();
    }
}
