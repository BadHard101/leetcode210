import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class Main {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {

        LinkedHashSet<Integer> open = new LinkedHashSet<>();
        ArrayList<Integer> blocked = new ArrayList<>();

        if (prerequisites.length == 0) {
            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                res[i] = i;
            }
            return res; //?
        }

        for (int i = 0; i < numCourses; i++) {
            open.add(i);
        }

        for (int i = 0; i < prerequisites.length; i++) {
            //open.add(prerequisites[i][1]);
            blocked.add(prerequisites[i][0]);
        }

        open.removeAll(blocked);
        System.out.println("BEFORE");
        System.out.println("open " + open);
        System.out.println("blocked " + blocked);

        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == -1) continue;
                if (open.contains(prerequisites[i][1])) {
                    blocked.remove(Integer.valueOf(prerequisites[i][0]));
                    prerequisites[i][1] = -1;
                    flag = true;
                    if (!blocked.contains(prerequisites[i][0])) {
                        open.add(prerequisites[i][0]);
                    }
                }
            }
        }

        System.out.println("\nAFTER");
        System.out.println("open " + open);
        System.out.println("blocked " + blocked);

        flag = true;
        for (int i = 0; i < prerequisites.length; i++) {
            if (prerequisites[i][1] != -1) {
                flag = false;
                break;
            }
        }
        int[] array = {};
        if (flag) {
            array = open.stream().mapToInt(Integer::intValue).toArray();
        }
        return array;
    }

    public static void main(String[] args) {

        int numCourses1 = 2;
        int numCourses2 = 4;
        int numCourses3 = 1;
        int[][] prerequisites1 = {{1,0}};
        int[][] prerequisites2 = {{1,0},{3,2},{2,0},{3,1}};
        int[][] prerequisites3 = {};

        System.out.println(
                Arrays.toString(
                        findOrder(numCourses1, prerequisites1)
                )
        );
    }
}