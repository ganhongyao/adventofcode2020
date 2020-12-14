import java.math.BigInteger;
import java.util.HashMap;


public class day13 {

    public static void main(String[] args) {
        String services = "19,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,859,x,x,x,x,x,x,x,23,x,x,x,x,13,x,x,x,17,x,x,x,x,x,x,x,x,x,x,x,29,x,373,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,37";

        part1(1001798, services);
        part2(services);

    }

    public static void part1(int depart_time, String services) {
        String[] services_arr = services.split(",");
        HashMap<Integer, Integer> wait_times = new HashMap<Integer, Integer>();
        for (int i = 0; i < services_arr.length; i++) {
            if (!services_arr[i].equals("x")) {
                int bus_id = Integer.parseInt(services_arr[i]);
                wait_times.put(bus_id, bus_id - depart_time % bus_id);
            }
        }

        int min_wait = Integer.MAX_VALUE;
        int ans = 0;
        for (Integer id : wait_times.keySet()) {
            if (wait_times.get(id) < min_wait) {
                min_wait = wait_times.get(id);
                ans = min_wait * id;
            }
        }

        System.out.println("Question 1 Answer: " + ans);

        
    }

    public static void part2(String services) {
        String[] services_arr = services.split(",");
        HashMap<Integer, Integer> remainders = new HashMap<Integer, Integer>();
        for (int i = 0; i < services_arr.length; i++) {
            if (!services_arr[i].equals("x")) {
                int bus_id = Integer.parseInt(services_arr[i]);
                int remainder = (bus_id - i) % bus_id;
                remainders.put(bus_id, remainder);
            }
        }

        long N = 1;
        for (Integer n : remainders.keySet()) {
            N *= n;
        }
        long result = 0;

        // Chinese Remainder Theorem - https://www.youtube.com/watch?v=zIFehsBHB8o&t=202s&ab_channel=MathswithJay
        for (Integer n : remainders.keySet()) {
            long bi = remainders.get(n);
            long Ni = N / n;
            BigInteger bigintNi = new BigInteger(String.valueOf(Ni));
            BigInteger bigintn = new BigInteger(String.valueOf(n));
            long xi = bigintNi.modInverse(bigintn).longValue();
            long biNixi = bi * Ni * xi;
            result += biNixi;
        }

        result = result % N;
        System.out.println("Question 2 Answer: " + result);

    }





}