import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class day11 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input11.txt");
        Scanner scan = new Scanner(file);
        ArrayList<String> input = new ArrayList<String>();
        while (scan.hasNextLine()) {
            input.add(scan.nextLine());
        }
        scan.close();

        char[][] grid = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            grid[i] = input.get(i).toCharArray();
        }
        
        int height = grid.length;
        int width = grid[0].length;

        char[][] part1 = new char[height][width];
        char[][] part2 = new char[height][width];

        // copy arrays to prevent mutation of original grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                part1[i][j] = grid[i][j]; 
                part2[i][j] = grid[i][j];
            }
        }

        // get seating area to reach equilibrium for part 1
        while (count_total_occupied(part1) != count_total_occupied(round1(part1, 4))) {
            part1 = round1(part1, 4);
        }

        System.out.println("Question 1 Answer: " + count_total_occupied(part1));

        // get seating area to reach equilibrium for part 2
        while (count_total_occupied(part2) != count_total_occupied(round2(part2, 5))) {
            part2 = round2(part2, 5);
        }

        System.out.println("Question 2 Answer: " + count_total_occupied(part2));

    }
    
    public static char[][] round1(char[][] grid, int max_occ) {
        int height = grid.length;
        int width = grid[0].length;
        char[][] copy = new char[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] != '.') {
                    if (occupied(y, x, grid) == 0) {
                        if (count_adj_occupied(y, x, grid) == 0) {
                            copy[y][x] = '#';
                        } else {
                            copy[y][x] = 'L';
                        }
                    } else {
                        if (count_adj_occupied(y, x, grid) >= max_occ) {
                            copy[y][x] = 'L';
                        } else {
                            copy[y][x] = '#';
                        }
                    }
                } else {
                    copy[y][x] = '.';
                }
            }
        }

        return copy;
    }
    
    public static int count_adj_occupied(int y, int x, char[][] grid) {
        int count = 0;
        int height = grid.length;
        int width = grid[0].length;
        if (x > 0) { // check left
            count += occupied(y, x - 1, grid);
        }
        if (x < width - 1) { // check right
            count += occupied(y, x + 1, grid);
        }
        if (y > 0) { // check up
            count += occupied(y - 1, x, grid);
        }
        if (y < height - 1) { // check down
            count += occupied(y + 1, x, grid);
        }
        if (x > 0 && y > 0) { // check upleft
            count += occupied(y - 1, x - 1, grid);
        }
        if (x < width - 1 && y > 0) { // check upright
            count += occupied(y - 1, x + 1, grid);
        }
        if (y < height - 1 && x > 0) { // check downleft
            count += occupied(y + 1, x - 1, grid);
        }
        if (y < height - 1 && x < width - 1) { // check downright
            count += occupied(y + 1, x + 1, grid);
        }
        return count;
    }

    public static char[][] round2(char[][] grid, int max_occ) {
        int height = grid.length;
        int width = grid[0].length;
        char[][] copy = new char[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] != '.') {
                    if (occupied(y, x, grid) == 0) {
                        if (count_adj_occupied2(y, x, grid) == 0) {
                            copy[y][x] = '#';
                        } else {
                            copy[y][x] = 'L';
                        }
                    } else {
                        if (count_adj_occupied2(y, x, grid) >= max_occ) {
                            copy[y][x] = 'L';
                        } else {
                            copy[y][x] = '#';
                        }
                    }
                } else {
                    copy[y][x] = '.';
                }
            }
        }

        return copy;
    }

    public static int count_adj_occupied2(int y, int x, char[][] grid) {
        int count = 0;
        int height = grid.length;
        int width = grid[0].length;
        if (x > 0) { // check left 
            for (int i = x - 1; i >= 0; i--) {
                if (grid[y][i] != '.') {
                    count += occupied(y, i, grid);
                    break;
                }
            }
        }
        if (x < width - 1) { // check right
            for (int i = x + 1; i < width; i++) {
                if (grid[y][i] != '.') {
                    count += occupied(y, i, grid);
                    break;
                }
            }
        }
        if (y > 0) { // check up
            for (int i = y - 1; i >= 0; i--) {
                if (grid[i][x] != '.') {
                    count += occupied(i, x, grid);
                    break;
                }
            }
        }
        if (y < height - 1) { // check down
            for (int i = y + 1; i < height; i++) {
                if (grid[i][x] != '.') {
                    count += occupied(i, x, grid);
                    break;
                }
            }
        }
        if (x > 0 && y > 0) { // check upleft 
            for (int i = y - 1, j = x - 1; i >= 0 && j >= 0; i--, j--) {
                if (grid[i][j] != '.') {
                    count += occupied(i, j, grid);
                    break;
                }
            }
        }
        if (x < width - 1 && y > 0) { // check upright
            for (int i = y - 1, j = x + 1; i >= 0 && j < width; i--, j++) {
                if (grid[i][j] != '.') {
                    count += occupied(i, j, grid);
                    break;
                }
            }
        }
        if (y < height - 1 && x > 0) { // check downleft
            for (int i = y + 1, j = x - 1; i < height && j >= 0; i++, j--) {
                if (grid[i][j] != '.') {
                    count += occupied(i, j, grid);
                    break;
                }
            }
        }
        if (y < height - 1 && x < width - 1) { // check downright
            for (int i = y + 1, j = x + 1; i < height && j < width; i++, j++) {
                if (grid[i][j] != '.') {
                    count += occupied(i, j, grid);
                    break;
                }
            }
        }
        return count;
    }

    public static int occupied(int y, int x, char[][] grid) {
        if (grid[y][x] == '#') {
            return 1;
        } else {
            return 0;
        }
    }

    public static int count_total_occupied(char[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                count += occupied(i, j, grid);
            }
        }
        return count;
    }

}