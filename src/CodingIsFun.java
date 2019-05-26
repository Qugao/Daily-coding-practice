import javax.swing.tree.TreeNode;
import java.lang.reflect.Array;
import java.util.*;


public class CodingIsFun {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int key) {
          this.key = key;
        }
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

    // lc #20
    public static boolean isValid(String s) {
        if (s.isEmpty()) {
            return true;
        }

        Deque<Character> stack = new ArrayDeque<>();
        char[] input = s.toCharArray();

        stack.push(input[0]);

        for (int i = 1; i < input.length; i++) {
            if (input[i] == '}' || input[i] == ']' || input[i] == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                if (input[i] == '}' && stack.peekFirst() != '{') {
                    return false;
                }

                if (input[i] == ']' && stack.peekFirst() != '[') {
                    return false;
                }

                if (input[i] == ')' && stack.peekFirst() != '(') {
                    return false;
                }
                stack.pop();
            } else {
                stack.offerFirst(input[i]);
            }
        }

        if (stack.isEmpty()) {
            return true;
        }
        return false;
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

    public boolean isCompleted(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }

        Queue<TreeNode> que = new LinkedList<TreeNode>();
        boolean flag = false;
        que.offer(root);

        while (!que.isEmpty()) {
            TreeNode cur = que.poll();

            if (cur.left == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                que.offer(cur.left);
            }

            if (cur.right == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                que.offer(cur.right);
            }
        }
        return true;
    }



    public static int[] sort(int[] arr) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int[] a = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            minHeap.add(arr[i]);
        }

        for (int j = 0; j < arr.length; j++) {
            a[j] = minHeap.poll();
        }

        return a;
    }

    public static int strstr(String large, String small) {
        // Write your solution here
        if (large.length() < small.length()) {
            return -1;
        }
        if (small.isEmpty()) {
            return 0;
        }

        char[] smallChar = small.toCharArray();
        char[] largeChar = large.toCharArray();

        for (int i = 0; i <= largeChar.length - smallChar.length; i++) {
            if (equals(largeChar, smallChar, i)) {
                return i;
            }
        }
        return -1;
    }

    public String rightShift(String input, int n) {
        // Write your solution here
        char[] arr = input.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (n != 0) {

            }
        }
    }

    private static boolean equals(char[] large, char[] small, int i) {
        for (int j = 0; j < small.length; j++) {
            if (large[j + i] != small[j]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[] a = {'h','e','l','l','o'};
        reverseString(a);
        System.out.println(Arrays.toString(a));

        String b = "";
        System.out.println(isValid(b));

        String c = "abbaabbab";
        String d = "bbab";
        System.out.println(strstr(c, d));

    }
}
