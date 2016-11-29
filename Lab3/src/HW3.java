import java.util.Scanner;

public class HW3 {
    public static void main(String[] args) {
    	// Loop over the Scanner
        // Split each line into the task and the corresponding number (if one exists)
        // Depending on what the input task was, perform the corresponding function
        //      (ie) insert, findMin, removeMin, isEmpty, or print
        // Hint: Use a switch-case statement
        // Don't forget to close your Scanner!
        Scanner scanner = new Scanner(System.in);
        int numLines = scanner.nextInt();
        pQueue<Integer> pq = new pQueue<Integer>(numLines);
        scanner.nextLine();
        for(int i = 0; i < numLines; ++i){
            String line = scanner.nextLine();
            String[] command = line.split(" ");
            
            switch(command[0]){
                case "insert":
                    pq.insert(Integer.parseInt(command[1]));
                    break;
                
                case "findMin":
                    System.out.println(pq.findMin());
                    break;
                    
                case "removeMin":
                    System.out.println(pq.removeMin());
                    break;
                
                case "isEmpty":
                    if (pq.isEmpty()){
                        System.out.println("Empty");
                    } else {
                        System.out.println("Not Empty");
                    }
                    break;
                
                case "print":
                    pq.print();
                    break;
            }
        }
        scanner.close();
    }
}



