# 原题

560. Subarray Sum Equals K
Medium

1969

52

Favorite

Share
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

# 解法

要找的解是 sum[i, j] = k。

sum[i, j] = sum[0, j] - sum[0, i - 1]

建立一个循环，把 sum[0, i]的和存入一个HashMap，题目不要求写出解，只要求出解的个数，所以不需要保存i，只要保存和以及和发生了几次。
和是有可能多个子数组产生同样的和的

然后检查 0 - j，sum[0, j] - k 有没有已经存在的 sum[0, i] (已经在hashmap了)。如果有，说明 sum[i, j]存在，result把那个和发生过的次数累加上去就好了，次数代表i可能是多个。
题目不要求写出子数组，所以记录次数就好了。

因为子数组是按照顺序来的，所以计算并保存进hash map的操作和查找的操作是可以合并在一个循环里的。

## 复杂度
时间复杂度：O(n)，只有一个循环，HashMap插入查找都是O(1)
空间复杂度：O(n)，需要保存和到HashMap里

## 代码
```Java

    public int subarraySum(int[] nums, int k) {

        //保存预先计算的和，key=和， value = 次数 - 因为同一个和可能不同的子数组
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        
        int sum = 0;
        sumMap.put(0, 1); //放入0，保证 num[i] = k时候可以有正确返回
        int result = 0;//结果

        for (int i = 0; i < nums.length; i++) {
            //从0-i的和
            sum += nums[i];
            //看看 sum - k有没有在预先计算的和里，有的话返回次数，没有0
            result += sumMap.getOrDefault( sum - k , 0);
            //把这个和放入map，如果已经存在了，就把次数+1，否则就是1 （0+1）
            sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);

        }
        

        return result;

    }
```


