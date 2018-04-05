import java.io.File;
import java.io.PrintWriter;
import java.util.*;
public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean again = false;
        int r1;
        int r2;
        int r3;
        int o1;
        int o2;
        int o3;
        int s1;
        int s2;
        int s3;
        do {
            System.out.println("Enter the number of the first (fast) rotor:");
             r1 = sc.nextInt();
            System.out.println("Enter the number of the second (medium) rotor:");
             r2 = sc.nextInt();
            System.out.println("Enter the number of the third (slow) rotor:");
             r3 = sc.nextInt();
            System.out.println("Enter the number of the first rotor offset:");
             o1 = sc.nextInt();
            System.out.println("Enter the number of the second rotor offset:");
             o2 = sc.nextInt();
            System.out.println("Enter the number of the third rotor offset:");
             o3 = sc.nextInt();
            System.out.println("Enter the ring setting of the first rotor:");
             s1 = sc.nextInt();
            System.out.println("Enter the ring setting of the second rotor:");
             s2 = sc.nextInt();
            System.out.println("Enter the ring setting of the third rotor:");
             s3 = sc.nextInt();
            if (r1 < 1 || r1 > 5 || r2 < 1 || r2 > 5 || r3 < 1 || r3 > 5 || r1 == r2 || r2 == r3 || r3 == r1 || o1 < 1 || o1 > 26 || o2 < 1 || o2 > 26 || o3 < 1 || o3 > 26 || s1 < 1 || s1 > 26 || s2 < 1 || s2 > 26 || s3 < 1 || s3 > 26) {
                System.out.println("Your input was invalid. Try again.");
                again = true;
            }
        } while(!again);
        
        System.out.println("Enter the plugboard swaps in the form: 'AB,CD,EF,GH'.  Do not have letters used in multiple pairs and do not have more than 13 pairs.");
        while (!sc.hasNext()) {
            //wait
        }
        String plug = sc.next();
        String[] pairs = plug.split("\\s*,\\s*");
        int[] rotorNums = {r1,r2,r3};
        int[] startPos = {o1,o2,o3};
        int[] ringPos = {s1,s2,s3};
        sc.close();
        try {
            Enigma e = new Enigma(rotorNums, startPos, ringPos, pairs);
            File file = new File("input.txt");
            String str = "";
            //Read the string from the file
            Scanner sc2 = new Scanner(file);
            while (sc2.hasNextLine()) {
                str += sc2.nextLine() + "\n";
            }
            sc2.close();
            String encrypted = e.encrypt(str);
            PrintWriter writer = new PrintWriter("encrypted.txt", "UTF-8");
            writer.print(encrypted);
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
