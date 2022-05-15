# Leetcode #1509 Minimum Difference Between Largest and Smallest Value in Three Moves

## 原题

1509 Minimum Difference Between Largest and Smallest Value in Three Moves
Medium
1091
148
Add to List
Share
You are given an integer array nums. In one move, you can choose one element of nums and change it by any value.
Return the minimum difference between the largest and smallest value of nums after performing at most three moves.

Example 1:

Input: nums = [5,3,2,4]
Output: 0
Explanation: Change the array [5,3,2,4] to [2,2,2,2].
The difference between the maximum and minimum is 2-2 = 0.
Example 2:

Input: nums = [1,5,0,10,14]
Output: 1
Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1]. 
The difference between the maximum and minimum is 1-0 = 1.
 
Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109


## 解法

修改k做法：

取最小 k + 1, 最大 k + 1 ，java可以用priority queue.

k = 3
n[0] n[1] n[2] n[3] ...... n[len - 4], n[len - 3], n[len -2], n[len - 1]
然后取下列差最小值

n[len - 4] - n[0]
n[len - 3] - n[1]
n[len - 2] - n[2]
n[len - 1] - n[3]

含义是，我们可以改最大三个值，那就是最大第4个值和最小值之间的差
也可以额改最小三个值，然后最小第四个和最大值比较
也可以最小二个值和最大一个值，那就是最小第三个值和最大第二个值差，
依次类推

另一种解法，可以不用priority queue，直接sort

## 复杂度
双qp：
O(2 n lg K)
Sort:
O(n lg n)


## 代码


```Java
 @CaseRunner
    public int minDifferenceSort(int[] nums) {
        int k = 3;
        int len = nums.length;
        if (len <= k + 1) {
            return 0;
        }
        Arrays.sort(nums);
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < k + 1; i++) {
            minVal = Math.min(nums[len - 1 - k + i] - nums[i], minVal);
        }
        return minVal;
    }

    @CaseRunner
    public int minDifferencePQ(int[] nums) {
        int k = 3;
        int len = nums.length;
        if (len <= k + 1)
            return 0;

        PriorityQueue<Integer> pqMin = new PriorityQueue<>();
        PriorityQueue<Integer> pqMax = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < len; i++) {
            pqMin.offer(nums[i]);
            pqMax.offer(nums[i]);
            if (i > k){
                pqMin.poll(); 
                pqMax.poll();
            }
        }
        int[] smallNum = new int[k + 1];
        int i = 0;
        while (!pqMax.isEmpty()){
            smallNum[k - i] = pqMax.poll();
            i++;
        }
        i = 0;
        int m = Integer.MAX_VALUE;
        while (!pqMin.isEmpty()){
            m = Math.min( pqMin.poll() - smallNum[i++], m);
        }
        return m;
    }
```
