# 原题
Leetcode 410

410 Split Array Largest Sum
Hard

Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.


# 解法

这是一个DP解法。

dp[i][j] 代表 前j个项目分成i个部分后的最小的最大值（结果）
初始化dp[0][0]=0；其他的为Integer.MAX_VALUE，因为要求最小值。

先对数组求出和数组，用sum[]表示，sum[i]为前i个数字之和。
```Java
        for (int i = 1; i <= n ; i++)
            sum[i] = sum[i -1] + nums[i - 1];
```
i循环是分组，有效值是[1,m]，代表最小可以分为一组，最大是M组。
j循环是前 j个数组，分成i组。有效值 [1, n]
k循环用来尝试每个组合：

每个k位置代表： k之后的数组单独一组，k及其之前数字分成 i - 1个组，所以 这个dp[i][k] 的当前值是
```Java
int val = Math.max( dp[i - 1][k], sum[j] - sum[k] );
```
dp[i - 1][k] 代表 i-1个组时候，前k的值，然后 sum[j] - sum[k] 代表 k之后的，长度为j的数组的和

然后更新 dp[i][j]为较小的值。

k的有效值是[i-1, j)，i-1是因为如果分成 i组，k之后单独一组，k不能小于i-1 （k=i-1代表前面每个数字是单独的一组）

最后返回  dp[m][n]


## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
    public int splitArray(int[] nums, int m) {

        int n = nums.length;
        int[] sum = new int[n + 1];
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        dp[0][0] = 0;

        for (int i = 1; i <= n ; i++)
            sum[i] = sum[i -1] + nums[i - 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = i - 1; k < j; k++) {

                    int val = Math.max( dp[i - 1][k], sum[j] - sum[k] );
                    dp[i][j] = Math.min(dp[i][j], val);

                }
            }
        }

        return dp[m][n];
    }
```
