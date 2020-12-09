import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day9 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input9.txt");
        Scanner scan = new Scanner(file);
        ArrayList<String> input = new ArrayList<String>();
        while (scan.hasNextLine()) {
            input.add(scan.nextLine());
        }
        scan.close();

        long[] nums = new long[input.size()];
        for (int i = 0; i < input.size(); i++) {
            nums[i] = Long.parseLong(input.get(i));
        }

        long anomaly = find_anomaly(nums);
        System.out.println("Question 1 Answer: " + anomaly);

        ArrayList<Long> range = find_range(nums, anomaly);
        long min = anomaly;
        long max = -1;
        for (int i = 0; i < range.size(); i++) {
            min = range.get(i) < min ? range.get(i) : min;
            max = range.get(i) > max ? range.get(i) : max;
        }

        System.out.println("Question 2 Answer: " + (max + min));

    }

    public static long find_anomaly(long[] nums) {
        boolean found = false;
        for (int i = 25; i < nums.length; i++) {
            found = false;
            for (int j = i - 25; j < i - 1; j++) {
                for (int k = j + 1; k < i; k++) {
                    if (nums[i] == nums[j] + nums[k]) {
                        found = true;
                    }
                }
            }
            if (!found) {
                return nums[i];
            }
        }
        return -1;
    }

    public static ArrayList<Long> find_range(long[] nums, long anomaly) {
        long running_sum = 0;
        ArrayList<Long> range = new ArrayList<Long>();
        for (int i = 0; i < nums.length; i++) {
            range.add(nums[i]);
            running_sum += nums[i];
            while (running_sum > anomaly) {
                running_sum -= range.get(0);
                range.remove(0);
            }

            if (running_sum == anomaly) {
                return range;
            }
        }
        return range;
    }

}