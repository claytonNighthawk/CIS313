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
		for(int i = 0; i < numProblems; ++i){
			String a = scanner.nextLine();
			String b = scanner.nextLine();
			boolean a_isPali = isPalindrome(a);
			boolean b_isPali = isPalindrome(b);
			if (a_isPali){
				System.out.println("This is a Palindrome");
			} else {
				System.out.println("Not a Palindrome");			
			}
			if (b_isPali){
				System.out.println("This is a Palindrome");
			} else {
				System.out.println("Not a Palindrome");
			}
		}
	}
	
	public static boolean isPalindrome(String s){
	
		// Create a stack and a Queue of chars that represents the passed in string
		// Hint: While you loop through the given string, push the same char onto your stack
		//		 that you enqueue into your Queue. This way you can use dequeue to get 
		//       the string from left to right, but you pop the string from right to left
		
		// Compare your Queue and Stack to see if the input String was a Palindrome or not	
		return false;
	}
}
