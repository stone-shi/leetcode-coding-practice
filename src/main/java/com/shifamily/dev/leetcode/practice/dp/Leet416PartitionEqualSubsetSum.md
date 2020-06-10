# 原题
416 Partition Equal Subset Sum
Medium

2387

65

Add to List

Share
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.
 

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
 

Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
 

Accepted
166,929
Submissions
386,759
Seen this question in a real interview before?

Yes

No
Contributor
LeetCode
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Facebook
|
3

Amazon
|
2
Partition to K Equal Sum Subsets
Medium
# 解法
0/1背包问题
能分成2个和相同的分组，就是意味着能不能找到一组数组的和等于总的和的1/2。
所以先找出总数组和，如果不能被2整除，那就返回false。否则继续。
建立dp[i][j]，含义为，考虑第i个数字时，能不能找出j的和。

初始化dp[0][0]为true，意味着：当有0个数组，和为0，成立。
每个数有2个选择，pick还是不pick。
如果这个数字大于sum时，显然不pick。这时候dp[i][j] = dp[i - 1][j];
如果这个数字小于sum时，如果pick，dp[i][j] = dp[i - 1][ j - nums[i]]。含义是这时候的状态是，j （sum）减去当前数字 nums[i]，时候上次的状态。

综合： dp[i][j] = dp[i - 1][j] || dp[i - 1][ j - nums[i]].

优化：
dp数组可以看到，我们只要 i-1时候的状态，不需要知道全部。
所以只要j从后往前循环，就可以保证：
不选：
dp[i][j] = dp[i - 1][j];
如果变成一维数组，dp[j] = dp[j]。这句语句可以去掉。
选：
dp[i][j] = dp[i - 1][ j - nums[i]]
如果变成一维数组：dp[i] = dp[ i - num ];
如果sum从高到底循环，就可以不会复写之前一行的数据。

## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
    @CaseRunner
    public boolean canPartition(int[] nums) {

        int len = nums.length;
        int sum = 0;
        for (int n : nums)
            sum += n;

        if (sum % 2 == 1)
            return false;
        sum = sum / 2;

        boolean[][] dp = new boolean[len + 1][sum + 1];
        dp[0][0] = true;
        for (int i = 1; i < len + 1; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < len + 1 ; i++) {
            for (int j = 1; j < sum + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if ( nums[i - 1] <= j){
                    dp[i][j] = dp[i][j] || dp[i - 1][ j - nums[i - 1] ];
                }
            }
        }

        return dp[len][sum];
    }

    @CaseRunner
    public boolean canPartition1dDp(int[] nums) {

        int len = nums.length;
        int sum = 0;
        for (int n : nums)
            sum += n;

        if (sum % 2 == 1)
            return false;
        sum = sum / 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int num: nums) {
            for (int i = sum ; i > 0; i--) {
                if ( num <= i){
                    dp[i] = dp[i] || dp[ i - num ];
                }
            }
        }

        return dp[sum];
    }
```
