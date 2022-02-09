import java.util.ArrayList;
import java.util.List;
/*
1) Write a function that returns true if a string consists of ascending or ascending AND consecutive numbers.

        Examples
        ascending("232425") --> true
        // Consecutive numbers 23, 24, 25

        ascending("2324256") --> false
        // No matter how this string is divided, the numbers are not consecutive.

        ascending("444445") --> true
        // Consecutive numbers 444 and 445.
        Notes
        A number can consist of any number of digits, so long as the numbers are adjacent to each other, and the string has at least two of them.
 */
public class Q1Solution {
    public static boolean ascending(String inputString) {
        boolean isAscending = false;
        StringBuilder sb = new StringBuilder(inputString);
        List<Integer> numbers = new ArrayList<>();
        for (int digits = 1; digits<=inputString.length()/2; digits++) {
            numbers.clear();
            isAscending = false;
            for (int nextNumIdx = 0; nextNumIdx<inputString.length(); nextNumIdx=nextNumIdx+digits) {
                if (nextNumIdx==0) {
                    numbers.add(Integer.parseInt(sb.substring(0, digits)));
                }
                else if (nextNumIdx>0) {
                    if (nextNumIdx+digits>inputString.length()) return false;   // prematurely short string means we exhausted all tests already
                    Integer nextNumber = Integer.parseInt(sb.substring(nextNumIdx, nextNumIdx+digits));
                    if ( (nextNumber-1) == numbers.get(numbers.size()-1)) {    // It's consecutive
                        numbers.add(nextNumber);
                        isAscending = true;
                    } else {    // If it's not consecutive, test the edge cases where digits changes from 2->3 etc ...99, 100...
                        Integer nextNumberHigherDigit = Integer.parseInt(sb.substring(nextNumIdx, nextNumIdx+(digits+1)));
                        if ( (nextNumberHigherDigit-1) == numbers.get(numbers.size()-1) ) {
                            numbers.add(nextNumberHigherDigit);
                            isAscending = true;
                            digits++;
                            continue;
                        }
                        isAscending = false;
                        break;
                    }
                }
            }
            if (isAscending) break;
        }
        return isAscending;
    }

    public static void main(String[] args) {
        // To test from bash:
        // javac Q1Solution.java
        // java Q1Solution
        System.out.println("ascending(\"232425\") --> "+ ascending("232425"));
        System.out.println("ascending(\"2324256\") --> "+ ascending("2324256"));
        System.out.println("ascending(\"444445\") --> " +ascending("444445"));
        System.out.println("ascending(\"56789\") --> " +ascending("56789"));
        // Edge cases
        System.out.println("ascending(\"9897100101102\") --> " +ascending("9897100101102"));
        System.out.println("ascending(\"9897100101102\") --> " +ascending("9897100101102"));
        System.out.println("ascending(\"999100010011002\") --> " +ascending("999100010011002"));
        System.out.println("ascending(\"999100110031005\") --> " +ascending("999100110031005"));
    }
}