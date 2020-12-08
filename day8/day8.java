import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day8 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input8.txt");
        Scanner scan = new Scanner(file);
        ArrayList<String> lines = new ArrayList<String>();
        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }
        scan.close();

        int len = lines.size();
        String[] action = new String[len];
        int[] num = new int[len];

        for (int i = 0; i < len; i++) {
            action[i] = lines.get(i).substring(0, 3);
            num[i] = Integer.parseInt(lines.get(i).substring(4));
        }

        System.out.println("Question 1 Answer: " + do_line(action, num, new boolean[len], 0, 0));

        for (int i = 0; i < len; i++) {
            if (action[i].equals("nop")) {
                action[i] = "jmp";
                if (!check_loops(action, num, new boolean[len], 0)) {
                    System.out.println("No loops found. Answer: " + do_line(action, num, new boolean[len], 0, 0));
                    break;
                } else {
                    action[i] = "nop";
                }
            } else if (action[i].equals("jmp")) {
                action[i] = "nop";
                if (!check_loops(action, num, new boolean[len], 0)) {
                    System.out.println("No loops found. Question 2 Answer: " + do_line(action, num, new boolean[len], 0, 0));
                    break;
                } else {
                    action[i] = "jmp";
                }
            }
        }
    }

    public static int do_line(String[] action, int[] num, boolean[] visited, int line, int acc) {
        if (visited[line]) {
            System.out.println("Infinite loop. Terminating");
            return acc;
        } else {
            if (line == action.length - 1) {
                System.out.println("Last instruction. Terminating");
                return acc;
            }

            visited[line] = true;
            if (action[line].equals("acc")) {
                return do_line(action, num, visited, line + 1, acc + num[line]);
            } else if (action[line].equals("nop")) {
                return do_line(action, num, visited, line + 1, acc);
            } else { // action is jmp
                return do_line(action, num, visited, line + num[line], acc);
            }
        }
    }

    public static boolean check_loops(String[] action, int[] num, boolean[] visited, int line) {
        if (visited[line]) {
            return true;
        } else {
            if (line == action.length - 1) {
                return false;
            } else {
                visited[line] = true;
                if (action[line].equals("jmp")) {
                    return check_loops(action, num, visited, line + num[line]);
                } else {
                    return check_loops(action, num, visited, line + 1);
                }
            }
        }
    }

}