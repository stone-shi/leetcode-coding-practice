# 原题
670 Maximum Swap
Medium

901

64

Add to List

Share
Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
Note:
The given number is in the range [0, 108]
Accepted
62,242
Submissions
145,336
Seen this question in a real interview before?

Yes

No
Contributor
SoumyaroopRoy
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Facebook
|
16

ByteDance
|
2
Create Maximum Number
Hard
# 解法

把int每个dig放到数组，然后从低位往高位扫描，并且记录当前位前的最大dig和index。
如果当前位置的dig 大于 最大，更新最大值和dig。
如果当前位置的dig 小于 最大，把前最大值和位置，记录在待交换位置。
扫描到头后，最后的交换位置交换。

其中，把int拆每个位可以用 n % 10; n / 10；也可以借助字符串。

## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java

        int tmp = num;
        int dig = 0;
        int[] digs = new int[16];
        int maxVal = Integer.MIN_VALUE;
        int maxDig = -1;
        int exchange = -1;
        int exchangeTo = -1;
        while (tmp > 0){
            digs[dig] = tmp % 10;
            tmp = tmp / 10;
            if (digs[dig] > maxVal){
                maxVal = digs[dig];
                maxDig = dig;
            }else if (digs[dig] < maxVal){
                exchange = dig;
                exchangeTo = maxDig;
            }
            dig++;
        }

        if (exchange != -1){
            tmp = digs[exchange];
            digs[exchange] = digs[exchangeTo];
            digs[exchangeTo] = tmp;
        }else
            return num;

        tmp = digs[--dig];
        while (dig > 0)
            tmp = tmp * 10 + digs[--dig];


        return tmp;
```
