import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day2 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input2.txt");
        Scanner scan = new Scanner(file);
        ArrayList<String> input = new ArrayList<String>();
        while (scan.hasNextLine()) {
            input.add(scan.nextLine());
        }
        scan.close();

        int n_entries = input.size();

        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < n_entries; i++) {
            if (check1(input, i)) {
                count1++;
            }
        }

        System.out.println("Question 1 Answer: " + count1);

        for (int i = 0; i < n_entries; i++) {
            if (check2(input, i)) {
                count2++;
            }
        }

        System.out.println("Question 2 Answer: " + count2);

    }

    public static boolean check1(ArrayList<String> input, int i) {
        String line = input.get(i); // "7-12 c: wfpscbrrxsssccbwg"
        String password = line.split(":")[1].replace(" ", ""); // "wfpscbrrxsssccbwg"
        String requirement = line.split(":")[0]; // "7-12 c"
        int min = Integer.parseInt(requirement.split("-")[0]); // 7
        int max = Integer.parseInt(requirement.split("-")[1].split(" ")[0]); // 12
        String letter = requirement.split(" ")[1]; // "c"

        int count = password.length() - password.replace(letter, "").length();

        return count >= min && count <= max;

    }

    public static boolean check2(ArrayList<String> input, int i) {
        String line = input.get(i); // "7-12 c: wfpscbrrxsssccbwg"
        String password = line.split(":")[1].replace(" ", ""); // "wfpscbrrxsssccbwg"
        String requirement = line.split(":")[0]; // "7-12 c"
        int index1 = Integer.parseInt(requirement.split("-")[0]) - 1; // 7
        int index2 = Integer.parseInt(requirement.split("-")[1].split(" ")[0]) - 1; // 12
        char letter = requirement.split(" ")[1].charAt(0); // 'c'

        return password.charAt(index1) == letter 
            ? password.charAt(index2) != letter 
            : password.charAt(index2) == letter;
    }
}
