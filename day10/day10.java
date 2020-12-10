import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class day10 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input10.txt");
        Scanner scan = new Scanner(file);
        ArrayList<Integer> input = new ArrayList<Integer>();

        input.add(0); // Add rating of charging outlet

        while (scan.hasNextLine()) {
            input.add(Integer.parseInt(scan.nextLine())); // Add ratings of joltage adapters
        }
        scan.close();

        Collections.sort(input);
        input.add(input.get(input.size() - 1) + 3); // Add built-in adapter (joltage of 3 higher than max)

        int count1 = 0;
        int count3 = 0;
        for (int i = 0; i < input.size() - 1; i++) {
            if (input.get(i) == input.get(i + 1) - 1) {
                count1++;
            } else if (input.get(i) == input.get(i + 1) - 3) {
                count3++;
            }
        }
        System.out.println("Question 1 Answer: " + (count1 * count3));

        int len = input.size();
        long[] dp = new long[len];
        Arrays.fill(dp, -1);

        System.out.println("Question 2 Answer: " + count_ways(input, 0, dp));

    }

    public static long count_ways(ArrayList<Integer> sequence, int index, long[] dp) {
        if (index == sequence.size() - 1) {
            return 1;
        }

        if (dp[index] != -1) {
            return dp[index];
        } else {
            long count = 0;
            for (int i = index + 1; i <= Math.min(index + 3, sequence.size() - 1); i++) {
                if (sequence.get(index) + 3 >= sequence.get(i)) {
                    count += count_ways(sequence, i, dp);
                }
            }
            dp[index] = count;
            return count;
        }

    }

}