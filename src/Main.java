import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class Main {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {

        LinkedHashSet<Integer> open = new LinkedHashSet<>();
        ArrayList<Integer> blocked = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            open.add(prerequisites[i][1]);
            blocked.add(prerequisites[i][0]);
        }

        open.removeAll(blocked);
        System.out.println("BEFORE");
        System.out.println("open " + open);
        System.out.println("blocked " + blocked);

        boolean flag = false;
        while (flag) {
            flag = false;
            for (int i = 0; i < numCourses; i++) {
                if (open.contains(prerequisites[i][1])) {
                    blocked.remove(Integer.valueOf(prerequisites[i][0]));
                    if (!blocked.contains(prerequisites[i][0])) {
                        open.add(prerequisites[i][0]);
                    }
                }
            }
        }

        System.out.println("\nAFTER");
        System.out.println("open " + open);
        System.out.println("blocked " + blocked);

        int[] array = open.stream().mapToInt(Integer::intValue).toArray();
        return array;
    }

    public static void main(String[] args) {

        int numCourses = 4;
        int[][] prerequisites = {{1,0},{3,2},{2,0},{3,1}};

        System.out.println(
                Arrays.toString(
                        findOrder(numCourses, prerequisites)
                )
        );
    }
}