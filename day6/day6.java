import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * day6
 */
public class day6 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input6.txt");
        Scanner scan = new Scanner(file);
        ArrayList<String> input = new ArrayList<String>();
        while (scan.hasNextLine()) {
            input.add(scan.nextLine());
        }
        scan.close();

        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < input.size(); i++) {
            sum1 += get1(input.get(i));
            sum2 += get2(input.get(i));
        }
        
        System.out.println("Question 1 Answer: " + sum1);
        System.out.println("Question 2 Answer: " + sum2);
    }

    public static int get1(String str) {
        String[] indi = str.split(" ");
        ArrayList<Character> nodup = new ArrayList<Character>();
        for (int i = 0; i < indi.length; i++) {
            for (int j = 0; j < indi[i].length(); j++) {
                if (nodup.indexOf(indi[i].charAt(j)) == -1) { // add character only if not in nodup
                    nodup.add(indi[i].charAt(j));
                }
            }
        }

        return nodup.size();
    }

    public static int get2(String str) {
        String[] strings = str.split(" ");
        ArrayList<String> intersect = new ArrayList<String>();

        for (int i = 0; i < strings[0].length(); i++) {
            intersect.add(Character.toString(strings[0].charAt(i))); // sets first person's answers to be in intersect
        }

        ArrayList<String> to_remove = new ArrayList<String>();

        for (int j = 1; j < strings.length; j++) {
            for (int i = 0; i < intersect.size(); i++) {
                if (strings[j].indexOf(intersect.get(i)) == -1) { // to remove character from intersect if not found
                    to_remove.add(intersect.get(i));
                }
            }
            for (int i = 0; i < to_remove.size(); i++) { 
                intersect.remove(to_remove.get(i)); // removes characters
            }
        }

        return intersect.size();
    }
}