import java.util.Scanner;

public class HW1 {
	public static void main(String[] args){
		// Create a Scanner that reads system input
		// Loop over the scanner's input
		// For each line of the input, send it to isPalindrome()
		// If isPalindrome returns true, print "This is a Palindrome." 
		// Otherwise print "Not a Palindrome."
		// Close the Scanner	
		Scanner scanner = new Scanner(System.in);
		int numProblems = scanner.nextInt();
		scanner.nextLine();
		for(int i = 0; i < numProblems; ++i){
			String line = scanner.nextLine();
			
			if (isPalindrome(line)){
				System.out.println("This is a Palindrome");
			} else {
				System.out.println("Not a Palindrome");			
			}
		}
		scanner.close();
	}
	
	public static boolean isPalindrome(String s){
		// Create a stack and a Queue of chars that represents the passed in string
		// Hint: While you loop through the given string, push the same char onto your stack
		//		 that you enqueue into your Queue. This way you can use dequeue to get 
		//       the string from left to right, but you pop the string from right to left
		
		// Compare your Queue and Stack to see if the input String was a Palindrome or not
        Queue<Character> queue = new Queue<Character>();
        Stack<Character> stack = new Stack<Character>();
		
		for (int i = 0; i < s.length(); i++){
			char ch = s.charAt(i);
			queue.enqueue(ch);
			stack.push(ch);
		}
		while (!queue.isEmpty()){
			char a = queue.dequeue().getData();
			char b = stack.pop().getData();
			if (a != b){
				return false;
			}
		}
		return true;
	}
}
