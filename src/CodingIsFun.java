import java.lang.reflect.Array;
import java.util.*;

public class CodingIsFun {
    public static int[] largestAndSecond(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 == o2) {
                    return 0;
                }
                return o1 > o2 ? -1 : 1;
            }
        });
        int[] result = {0, 0};

        for (int i = 0; i < array.length; i++) {
            pq.add(array[i]);
        }

        result[0] = pq.poll();
        result[1] = pq.poll();
        return result;
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = {0, 0};

        for (int i = 0; i < nums.length; i++) {
            int tar = target - nums[i];

            if (map.containsKey(tar)) {
                result[0] = map.get(tar);
                result[1] = i;
            } else {
                map.put(nums[i], i);
            }
        }

        return result;
    }

    public static void reverseString(char[] s) {
        int j = s.length - 1;
        for (int i = 0; i < s.length / 2; i++, j--) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
    }


    public static boolean isValid(String s) {

        return true;
    }

    public static void main(String[] args) {
        char[] a = {'h','e','l','l','o'};
        reverseString(a);
        System.out.println(Arrays.toString(a));

        String b = "{[]}";
        System.out.println(isValid(b));
    }
}
