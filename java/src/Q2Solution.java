import java.util.ArrayList;
import java.util.List;

public class Q2Solution {
    /*
        2) Create a function that takes an integer and outputs an n x n square solely consisting of the integer n.

        Examples
        squarePatch(3) --> [
        [3, 3, 3],
        [3, 3, 3],
        [3, 3, 3]
        ]

        squarePatch(5) --> [
        [5, 5, 5, 5, 5],
        [5, 5, 5, 5, 5],
        [5, 5, 5, 5, 5],
        [5, 5, 5, 5, 5],
        [5, 5, 5, 5, 5]
        ]

        squarePatch(1) --> [
        [1]
        ]

        squarePatch(0) --> []
        Notes
        n >= 0.
        If n = 0, return an empty array.
     */
    public static Integer [][] getSquare(Integer n) {
        Integer square[][] = new Integer[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                square[i][j] = n;
            }
        }
        return square;
    }

    public static void squarePatch(Integer n) {
        if (n==0) {
            System.out.println("[]");
            return;
        }
        List<List<Integer>> squareListList = new ArrayList<List<Integer>>();
        Integer[][] square = getSquare(n);
        System.out.println("[");
        for (int i=0; i<n; i++) {
            squareListList.add(new ArrayList<Integer>(List.of(square[i])));
            System.out.println(squareListList.get(i).toString()+ ((i<n-1)? ",":""));
        }
        System.out.println("]");
    }
    public static void main(String[] args) {
        // To run from bash:
        // javac Q2Solution.java
        // java Q2Solution
        squarePatch(3);
        squarePatch(5);
        squarePatch(1);
        squarePatch(0);
    }
}