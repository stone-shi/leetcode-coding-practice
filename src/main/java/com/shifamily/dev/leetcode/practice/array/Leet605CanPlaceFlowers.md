# 原题
605 Can Place Flowers
Easy

630

319

Add to List

Share
Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True
Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False
Note:
The input array won't violate no-adjacent-flowers rule.
The input array size is in the range of [1, 20000].
n is a non-negative integer which won't exceed the input array size.
# 解法
scan下来就可以了。注意边界检查。另外可以有优化是当N已经check了，直接返回，不用扫描全部数据。


## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        for (int i = 0; i < flowerbed.length; i++) {
            if ( (i == 0 || flowerbed[i - 1] == 0) && flowerbed[i] == 0  && ( i == flowerbed.length - 1 || flowerbed[i+1] ==0 )  ) {
                flowerbed[i] = 1;
                n--;
            }

        }

        return n <= 0;

    }
```
