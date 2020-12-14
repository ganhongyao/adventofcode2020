import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class day14 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input14.txt");
        Scanner scan = new Scanner(file);
        ArrayList<String> input = new ArrayList<String>();
        while (scan.hasNextLine()) {
            input.add(scan.nextLine());
        }
        scan.close();

        part1(input);
        part2(input);
    }

    public static void part1(ArrayList<String> input) {
        String mask = "";
        HashMap<Integer, Long> mem = new HashMap<Integer, Long>();

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (line.startsWith("mask")) {
                mask = line.split("= ")[1];
            } else {
                int key = Integer.parseInt(line.substring(line.indexOf("[") + 1, line.indexOf("]")));
                String unmasked = Long.toBinaryString(Long.parseLong(line.split("= ")[1]));
                unmasked = "0".repeat(36 - unmasked.length()) + unmasked;
                char[] unmasked_arr = unmasked.toCharArray();
                for (int j = 0; j < unmasked.length(); j++) {
                    if (mask.charAt(j) != 'X') {
                        unmasked_arr[j] = mask.charAt(j);
                    }
                }
                String masked = String.valueOf(unmasked_arr);
                long value = Long.parseLong(masked, 2);
                mem.put(key, value);
            
            }
        }

        long sum = 0;
        for (Long l : mem.values()) {
            sum += l;
        }
        System.out.println("Question 1 Answer: " + sum);
    }

    public static void part2(ArrayList<String> input) {
        String mask = "";
        HashMap<Long, Long> mem = new HashMap<Long, Long>();
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (line.startsWith("mask")) {
                mask = line.split("= ")[1];
            } else {
                String unmasked_key = Long.toBinaryString(Long.parseLong(line.substring(line.indexOf('[') + 1, line.indexOf(']'))));
                unmasked_key = "0".repeat(36 - unmasked_key.length()) + unmasked_key;
                char[] unmasked_arr = unmasked_key.toCharArray();
                for (int j = 0; j < unmasked_arr.length; j++) {
                    if (mask.charAt(j) == '1') {
                        unmasked_arr[j] = '1';
                    } else if (mask.charAt(j) == 'X') {
                        unmasked_arr[j] = 'X';
                    }
                }
                long value = Long.parseLong(line.split("= ")[1]);
                long[] keys = get_keys(String.valueOf(unmasked_arr));
                for (int j = 0; j < keys.length; j++) {
                    mem.put(keys[j], value);
                }
            }
        }

        long sum = 0;
        for (Long l : mem.values()) {
            sum += l;
        }
        
        System.out.println("Question 2 Answer: " + sum);
    }

    public static long[] get_keys(String str) {
        ArrayList<Integer> x_positions = new ArrayList<Integer>();
        long min = 0;
        for (int i = 0; i < 36; i++) {
            if (str.charAt(36 - i - 1) == 'X') {
                x_positions.add(i);
            } else if (str.charAt(36 - i - 1) == '1') {
                min += Math.pow(2, i);
            }
        }
        int len = (int) Math.pow(2, x_positions.size());
        long[] addresses = new long[len];
        Arrays.fill(addresses, min);
        for (int i = 0; i < x_positions.size(); i++) {
            for (int j = 0; j < addresses.length; j++) {
                if ((j % len) < (len / 2)) {
                    addresses[j] = addresses[j] + (long) Math.pow(2, x_positions.get(i));
                }
            }
            len = len / 2;
        }

        return addresses;
    }
}