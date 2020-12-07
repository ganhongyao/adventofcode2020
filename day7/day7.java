import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class day7 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input7.txt");
        Scanner scan = new Scanner(file);
        ArrayList<String> input = new ArrayList<String>();
        while (scan.hasNextLine()) {
            input.add(scan.nextLine());
        }
        scan.close();

        Map<String, String[]> bagmap = new HashMap<>();

        for (int i = 0; i < input.size(); i++) {
            // starts with the string "dotted salmon bags contain 2 dark lavender bags, 1
            // muted red bag, 1 vibrant magenta bag."
            String this_bag = input.get(i).split("contain")[0].replace(" bags ", "").replace(" ", ""); // e.g.
                                                                                                       // dottedsalmon
            // creates a String array for each element being the number + name of bag
            // contained
            // e.g. [2darklavender, 1mutedred, 1vibrantmagenta]
            String[] contains = input.get(i).split("contain")[1].split(",");
            for (int j = 0; j < contains.length; j++) {
                String contained_bag = contains[j].replace(" ", "");
                String contained_bag_name = contained_bag.substring(0, contained_bag.indexOf("bag"));
                contains[j] = contained_bag_name;
            }
            bagmap.put(this_bag, contains);

        }

        int count1 = 0;

        for (String i : bagmap.keySet()) {
            if (contains_shiny_gold(i, bagmap)) {
                count1++;
            }
        }

        System.out.println("Question 1 Answer: " + count1);
        System.out.println("Question 2 Answer: " + countbags("shinygold", bagmap));
    }

    public static boolean contains_shiny_gold(String bag, Map<String, String[]> bagmap) {
        String[] arr = bagmap.get(bag);
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            if (arr[i].equals("noother")) { // does not contain any other bag
                return false;
            } else if (arr[i].indexOf("shinygold") != -1) { // contains shinygold directly
                return true;
            }
        }
        // if reaches here, means contains non-shinygold bags. check for each of this
        // bag.
        boolean result = false;
        for (int i = 0; i < len; i++) {
            if (contains_shiny_gold(arr[i].replaceAll("[0-9]", ""), bagmap)) {
                result = true;
            }
        }
        return result;
    }

    public static int countbags(String bag, Map<String, String[]> bagmap) {
        String[] arr = bagmap.get(bag);
        int len = arr.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i].equals("noother")) {
                continue;
            } else {
                int quantity = Integer.parseInt(String.valueOf(arr[i].charAt(0)));
                String this_bag = arr[i].replaceAll("[0-9]", "");
                count += quantity + (quantity * countbags(this_bag, bagmap));
            }
        }

        return count;
    }

}