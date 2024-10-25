import java.util.ArrayList;
import java.util.Scanner;

public class Stack {

    static ArrayList<Integer> s;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Stack ss = new Stack();
        while (in.nextInt() != -1) {
            System.out.println("What option do you want? 1. push, 2. pop, or, 3. print?");
            int opt = in.nextInt();
            switch (opt) {
                case 1:
                    System.out.println("Give input: ");
                    int k = in.nextInt();
                    push(k);
                    break;
                case 2:
                    pop();
                    break;
                case 3:
                    System.out.println("S looks like this now...");
                    printS();
                    break;
                default:
                    System.out.println("Wrong input...");
            }
        }
        in.close();
    }

    public Stack() {
        s = new ArrayList<Integer>();

    }

    public static void push(int k) {
        s.add(k);
    }

    public int pop2() {
        if (s.isEmpty()) {
            System.out.println("The list is EMPTY. SORRY :(");
            return Integer.MIN_VALUE;

        } else {
            int lastE = s.get(s.size() - 1);
            System.out.println("Removed Item is: " + lastE);
            return s.remove(s.size() - 1);
        }
    }

    public static int pop() {
        try {
            int lastE = s.get(s.size() - 1);
            System.out.println("Removed Item is: " + lastE);
            return s.remove(s.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            return Integer.MIN_VALUE;
        }
    }

    public static void printS() {
        System.out.println(s.toString());
    }

}
