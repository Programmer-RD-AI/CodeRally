package coderally.biologicalhazard;

  /*
  -----------------------------------------------------
   Team Name: ENIGMATICS
   Lead: Senura
   String Manipulation: Pasan
   Algorithm : Thamindu
   Data Structures Specialist: Ranuga
   Problem Solver: Siyath (Behemoth)
   -----------------------------------------------------
    * */

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {
    /*
     * Complete the 'bioHazard' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY allergic
     *  3. INTEGER_ARRAY poisonous
     */

    public static long bioHazard(int n, List<Integer> allergic, List<Integer> poisonous) {
        List<int[]> allPosibilities = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                int[] combination = new int[j - i + 1];
                for (int k = 0; k < combination.length; k++) {
                    combination[k] = i + k;
                }
                allPosibilities.add(combination);
            }
        }

        List<int[]> reduceList = new ArrayList<>();
        for (int i = 0; i < allergic.size(); i++) {
            reduceList.add(new int[]{allergic.get(i), poisonous.get(i)});
        }

        allPosibilities.removeIf(possibility -> {
            for (int[] reduce : reduceList) {
                if (isSubset(possibility, reduce)) {
                    return true;
                }
            }
            return false;
        });

        return allPosibilities.size();
    }

    private static boolean isSubset(int[] array, int[] subset) {
        int i = 0, j = 0;
        while (i < array.length && j < subset.length) {
            if (array[i] == subset[j]) {
                j++;
            }
            i++;
        }
        return j == subset.length;
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int allergicCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> allergic = IntStream.range(0, allergicCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int poisonousCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> poisonous = IntStream.range(0, poisonousCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        long result = Result.bioHazard(n, allergic, poisonous);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

/*
Logic :
I have a coding question in that i get an integer n and from that I need to make the all the possible combinations by incrementing 1,
for example n = 3, then the possible combinations are {1},{2},{3},{1,2},{2,3},{1,2,3} and I need to add the all the possible combinations to a list,

for and then I have 2 arrays allergic and poisonous, I need to add the equevalent arrays indexes of the both arrays to a seperate array list, example
if allergic = {1,2} and poisonous = {2,3} then the  new array list should be first element of the allergic and the first elements of {1,2} and after that the rest
all the combinations in the new array list should be = {1,2},{2,3} , should name the array list as reduceList

Then after that I need to remove the combinations on the reduceList from the allPosibilities list and return the size of the allPosibilities list.
* */