import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day1 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input1.txt");
        Scanner scan = new Scanner(file);
        ArrayList<Integer> input = new ArrayList<Integer>();
        while (scan.hasNextLine()) {
            input.add(Integer.parseInt(scan.nextLine()));
        }
        scan.close();

        System.out.println("Q1 Answer: " + two_sum(input));
        System.out.println("Q2 Answer: " + three_sum(input));

    }

    public static int two_sum(ArrayList<Integer> input) {
        for (int i = 0; i < input.size() - 1; i++) {
            for (int j = i; j < input.size(); j++) {
                if (input.get(i) + input.get(j) == 2020) {
                    return (input.get(i) * input.get(j));

                }

            }
        }

        return -1;
    }

    public static int three_sum(ArrayList<Integer> input) {
        for (int i = 0; i < input.size() - 2; i++) {
            for (int j = 0; j < input.size() - 1; j++) {
                for (int k = 0; k < input.size(); k++) {
                    if (input.get(i) + input.get(j) + input.get(k) == 2020) {
                        return input.get(i) * input.get(j) * input.get(k);
                    }
                }
            }
        }

        return -1;
    }

}