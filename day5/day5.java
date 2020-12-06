import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class day5 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input5.txt");
        Scanner scan = new Scanner(file);
        ArrayList<String> passes = new ArrayList<String>();
        while (scan.hasNextLine()) {
            passes.add(scan.nextLine());
        }
        scan.close();
        int size = passes.size();
        int max = 0;
        ArrayList<Integer> ids = new ArrayList<Integer>();

        for (int i = 0; i < size; i++) {
            int row = get_num(passes.get(i).substring(0, 7));
            int col = get_num(passes.get(i).substring(7));
            int id = 8 * row + col;
            ids.add(id);
            max = Math.max(max, ids.get(i)); // Q1
        }
        System.out.println("Question 1 Answer: " + max);

        // Q2
        Collections.sort(ids);
        for (int i = 0; i < ids.size() - 1; i++) {
            if (ids.indexOf(ids.get(i) + 1) == -1) {
                System.out.println("Question 2 Answer: " + (ids.get(i) + 1));
                break;
            }
        }
    }

    public static int get_num(String letters) {
        int len = letters.length();
        int num = 0;
        for (int i = 0; i < len; i++) {
            if (letters.charAt(i) == 'B' || letters.charAt(i) == 'R') {
                num += Math.pow(2, len - i - 1);
            }
        }
        return num;
    }
}
