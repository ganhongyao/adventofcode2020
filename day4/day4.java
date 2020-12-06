import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day4 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input4.txt");
        Scanner scan = new Scanner(file);
        ArrayList<String> input = new ArrayList<String>();
        while (scan.hasNextLine()) {
            input.add(scan.nextLine());
        }
        scan.close();

        int n_passports = input.size();

        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < n_passports; i++) {
            if (present(input.get(i))) {
                if (valid(input.get(i))) {
                    count2++;
                }
                count1++;
            }
        }
        System.out.println("Question 1 Answer: " + count1);
        System.out.println("Question 2 Answer: " + count2);
    }

    public static boolean present(String passport) {
        String[] contains = { "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" };
        for (int i = 0; i < contains.length; i++) {
            if (!passport.contains(contains[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean valid(String passport) {
        String[] subs = passport.split(" ");
        for (int i = 0; i < subs.length; i++) {
            String e = subs[i];
            if (e.startsWith("byr")) {
                int byr = Integer.parseInt(e.split(":")[1]);
                if (byr < 1920 || byr > 2002) {
                    return false;
                }
            } else if (e.startsWith("iyr")) {
                int iyr = Integer.parseInt(e.split(":")[1]);
                if (iyr < 2010 || iyr > 2020) {
                    return false;
                }
            } else if (e.startsWith("eyr")) {
                int eyr = Integer.parseInt(e.split(":")[1]);
                if (eyr < 2020 || eyr > 2030) {
                    return false;
                }
            } else if (e.startsWith("hgt")) {
                String hgt = e.split(":")[1];
                String unit = hgt.substring(hgt.length() - 2);
                int height = Integer.parseInt(hgt.replace(unit, ""));
                if (unit.equals("in")) {
                    if (height < 59 || height > 76) {
                        return false;
                    }
                } else {
                    if (height < 150 || height > 193) {
                        return false;
                    }
                }
            } else if (e.startsWith("hcl")) {
                String hcl = e.split(":")[1];
                if (!hcl.startsWith("#")) {
                    return false;
                } else {
                    for (int j = 1; j < hcl.length(); j++) {
                        char curr = hcl.charAt(j);
                        if (!((curr >= 48 && curr <= 57) || (curr >= 97 && curr <= 122))) {
                            return false;
                        }
                    }
                }
            } else if (e.startsWith("ecl")) {
                String eyec = e.split(":")[1];
                String[] accepted = { "amb", "blu", "brn", "gry", "grn", "hzl", "oth" };
                boolean found = false;
                for (int j = 0; j < accepted.length; j++) {
                    if (accepted[j].equals(eyec)) {
                        found = true;
                    }
                }
                if (!found) {
                    return false;
                }
            } else if (e.startsWith("pid")) {
                String pid = e.split(":")[1];
                if (pid.length() != 9) {
                    return false;
                }

                for (int j = 0; j < pid.length(); j++) {
                    if (pid.charAt(j) < 48 || pid.charAt(j) > 57) {
                        return false;
                    }
                }
            } else {
                continue;
            }
        }

        return true;
    }

}
