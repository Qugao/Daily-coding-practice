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


    private static boolean equals(char[] large, char[] small, int i) {
        for (int j = 0; j < small.length; j++) {
            if (large[j + i] != small[j]) {
                return false;
            }
        }
        return true;
    }

    public static String longestCommon(String source, String target) {
        // Write your solution here
        int end = 0;
        boolean flag = false;
        for (int i = 0; i < source.length(); i++) {
            if (end <= target.length() - 1 && source.charAt(i) == target.charAt(end)) {
                end++;
                flag = true;
            }
        }
        if (!flag) {
            return "";
        } else {
            return target.substring(0, end);
        }
    }

    public static int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }

    public static int romanToInt(String s) {
        char[] arr = s.toCharArray();
        int[] nums = {1, 5, 10, 50, 100, 500, 1000};
        char[] roms = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] temp = new int[s.length() + 1];
        int result = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < roms.length; j++) {
                if (arr[i] == roms[j]) {
                    temp[i] = nums[j];
                }
            }
        }

        System.out.println(Arrays.toString(temp));

        for (int k = 0; k < temp.length - 1; k++) {
            if (temp[k] >= temp[k + 1]) {
                result += temp[k];
            } else {
                result += temp[k + 1] - temp[k];
                k++;
            }
        }

        return result;
    }
    public static void printNode(ListNode head) {
        System.out.print("[");
        while (head != null) {
           System.out.print(" " + head.val);
           head = head.next;
        }
        System.out.print(" ]");
    }

    public static int getInt(ListNode head) {
        StringBuilder st = new StringBuilder();

        while (head != null) {
            st.append(head.val);
            head = head.next;
        }

        return Integer.parseInt(st.toString());

    }

    public static ListNode getNode(int a) {
        String temp = Integer.toString(a);
        char[] arr = temp.toCharArray();
        ListNode head = new ListNode(0);

        for (int i = 0; i < arr.length; i++) {
            head = insert(head, Character.getNumericValue(arr[i]));
        }

        return head.next;
    }

    public static ListNode insert(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode newNode = new ListNode(val);

        while (cur.next != null) {
            cur = cur.next;
        }

        newNode.next = cur.next;
        cur.next = newNode;

        return dummy.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        int a = getInt(l1);
        int b = getInt(l2);

        ListNode result = getNode(a + b);
        result = reverse(result);

        return result;
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
           return head;
        }

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int j = 0;
        int i = m;

        while (i < nums1.length && j < nums2.length) {
            nums1[i] = nums2[j];
            i++;
            j++;
        }

      //  System.out.println(Arrays.toString(nums1));
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val == o2.val) {
                    return 0;
                }
                return o1.val < o2.val ? -1 : 1;
            }
        });

        for (ListNode nodes : lists) {
            if (nodes != null) {
                pq.offer(nodes);
            }
        }

        while (!pq.isEmpty()) {
            cur.next = pq.poll();

            if (cur.next.next != null) {
                pq.offer(cur.next.next);
            }
            cur = cur.next;
        }

        return dummy.next;
    }

    public static ListNode singleMerger(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0);
       // dummy.next = a;
        ListNode cur = dummy;

        while (a != null && b != null) {
           // System.out.println(1);
            if (a.val < b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }

        if (a != null) {
            cur.next = a;
        } else {
            cur.next = b;
        }

        return dummy.next;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int j = 1; j < nums.length; j++) {
            int tar = (nums[j - 1] + nums[j]) * -1;
            if (map.containsValue(tar)) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(j - 1);
                list.add(j);
                list.add(map.get(tar));
                result.add(list);
            }
        }
        return result;
    }

    public static void heapSort(int[] array) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < array.length; i++) {
            minHeap.offer(array[i]);
        }

        int index = 0;
        while (!minHeap.isEmpty()) {
            array[index++] = minHeap.poll();
        }

    }

    public static int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            int a = email.indexOf('@');
            String firstPart = email.substring(0, a);
            String secondPart = email.substring(a, email.length());

            if (firstPart.contains("+")) {
                int breakPoint = firstPart.indexOf("+");
                firstPart = firstPart.substring(0, breakPoint);
            }

            firstPart = firstPart.replaceAll("\\.", "");

            set.add(firstPart + secondPart);
        }

        return set.size();
    }

    public static String licenseKeyFormatting(String S, int K) {
        S = S.toUpperCase();
        S = S.replaceAll("-","");

        StringBuilder sb = new StringBuilder(S);
        int i = S.length() - K;

        while (i > 0) {
            System.out.println(i);
            sb.insert(i, '-');
            i -= K;
        }
        return sb.toString();
    }

    public static int lengthOfLongestSubstring(String s) {
        //StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>();
        int longest = 0;

        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                longest = longest > set.size() ? longest : set.size();
                set.clear();
            } else {
                set.add(s.charAt(i));
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));


    }
}
