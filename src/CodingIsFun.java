import java.lang.reflect.Array;
import java.util.*;


public class CodingIsFun {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    //lc #215
    public static int findKthLargest(int[] nums, int k) {
        if (nums.length <= 1) {
            return nums[0];
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

        //int[] result = new int[k];
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
        }

        for (int j = 0; j < k; j++) {
            result = pq.poll();
        }
        return result;
    }


    // lc #1 : Two Sum
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
    // lc #344
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

    // lc #347
    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int num : nums) {
            Integer freq = freqMap.get(num);

            if (freq == null) {
                freqMap.put(num, 1);
            } else {
                freqMap.put(num, freq + 1);
            }
        }

        //System.out.println(freqMap);

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        //System.out.println(minHeap);

        for (int i = minHeap.size() - 1; i >= 0; i--) {
            result.add(minHeap.poll().getKey());
        }

        return result;
    }

    // lc #21
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (l1 != null && l2 != null) {
             if (l1.val > l2.val) {
                 cur.next = l2;
                 l2 = l2.next;
             } else {
                 cur.next = l1;
                 l1 = l1.next;
             }
             cur = cur.next;
        }

        if (l1 != null) {
            cur.next = l1;
        } else {
            cur.next = l2;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        char[] a = {'h','e','l','l','o'};
        reverseString(a);
        System.out.println(Arrays.toString(a));

        String b = "{[]}";
        System.out.println(isValid(b));

        int[] c = {1,1,1,2,2,3};
        System.out.println(topKFrequent(c, 2));
    }
}
