import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day3 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input3.txt");
        Scanner scan = new Scanner(file);
        ArrayList<String> input = new ArrayList<String>();
        while (scan.hasNextLine()) {
            input.add(scan.nextLine());
        }
        scan.close();

        int ans1 = traverse(input, 0, 0, 3, 1);
        System.out.println("Question 1: " + ans1);

        int[][] q2_inputs = { { 1, 1 }, { 3, 1 }, { 5, 1 }, { 7, 1 }, { 1, 2 } };

        long ans2 = 1;

        for (int i = 0; i < q2_inputs.length; i++) {
            long trees = traverse(input, 0, 0, q2_inputs[i][0], q2_inputs[i][1]);
            ans2 *= trees;
        }

        System.out.println("Question 2: " + ans2);
    }

    public static int traverse(ArrayList<String> input, int x, int y, int right, int down) {
        int height = input.size();
        int width = input.get(0).length();

        if (y >= height) {
            return 0;
        } else if (x >= width) {
            return traverse(input, x - width, y, right, down);
        } else {
            if (input.get(y).charAt(x) == '#') {
                return 1 + traverse(input, x + right, y + down, right, down);
            } else {
                return traverse(input, x + right, y + down, right, down);
            }
        }
    }
}
