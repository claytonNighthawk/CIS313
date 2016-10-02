import java.util.Scanner;

public class HW0{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int numProblems = scanner.nextInt();
		for(int i = 0; i < numProblems; ++i){
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int g = gcd(a,b);
			int l = lcm(a,b);
			System.out.println(g + " " + l);
		}
		scanner.close();
	}

	public static int gcd(int a, int b){
	// Find the greatest common divisor of a and b
	// Hint: Use Euclid's Algorithm
		if (b == 0){
			return a;
		}
		return gcd(b, a % b);
	}

	public static int lcm(int a, int b){
	// Find the least common multiple of a and b
	// Hint: Use the gcd of a and b
	 return (a * b) / gcd(a,b);
	}
}
