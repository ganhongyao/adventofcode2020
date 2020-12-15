import java.util.ArrayList;
import java.util.HashMap;

public class day15 {
    public static void main(String[] args) {
        String input = "15,5,1,4,7,0";
        
        System.out.println("Question 1 Answer: " + get_number(input, 2020));
        System.out.println("Question 2 Answer: " + get_number(input, 30000000));
        
    }

    public static int get_number(String input, int pos) {
        ArrayList<String> answers = new ArrayList<String>();
        String[] input_arr = input.split(",");
        HashMap<String, ArrayList<Integer>> dict = new HashMap<String, ArrayList<Integer>>();
        for (int i = 0; i < input_arr.length; i++) {
            answers.add(input_arr[i]);
            ArrayList<Integer> positions = new ArrayList<Integer>();
            positions.add(i + 1);
            dict.put(input_arr[i], positions);
        }
        
        for (int i = input_arr.length; i < pos; i++) {
            String prev_number = answers.get(i - 1);
            
            if (dict.get(prev_number).size() == 1) {
                dict.get("0").add(i + 1);
                answers.add("0");
            } else {
                ArrayList<Integer> prev_positions = dict.get(prev_number);
                String answer = String.valueOf(prev_positions.get(prev_positions.size() - 1) - prev_positions.get(prev_positions.size() - 2));
                answers.add(answer);
                dict.putIfAbsent(answer, new ArrayList<Integer>());
                dict.get(answer).add(i + 1);
            }
        }
        
        return Integer.parseInt(answers.get(pos - 1));
    }
}