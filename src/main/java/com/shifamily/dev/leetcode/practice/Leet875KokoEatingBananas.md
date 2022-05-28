# Leetcode #875 Koko Eating Bananas

## 原题

[875 Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/)

Medium 4188 186

Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

 

**Example 1:**

> `Input: piles = [3,6,7,11], h = 8`
`Output: 4`

**Example 2:**

> `Input: piles = [30,11,23,4,20], h = 5`
`Output: 30`

**Example 3:**

> `Input: piles = [30,11,23,4,20], h = 6`
`Output: 23`
 
**Constraints:**

- `1 <= piles.length <= 104`
- `piles.length <= h <= 109`
- `1 <= piles[i] <= 109`

## 解法

二分查找法查找左侧边界。

k的范围可以从`[1, max(piles)]`，然后对范围进行二分查找法。判断是对于给定的mid是否能够全部吃完，如果是，找左侧（左边界），否则右侧（增加每次吃的数量）。


[二分查找法参考](https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE%E8%AF%A6%E8%A7%A3.md)

## 复杂度


## 代码

```Java
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = Integer.MIN_VALUE;
        for (int i = 0; i < piles.length; i++) {
            maxPile = Math.max(maxPile, piles[i]);
        }
        int l = 1;
        int r = maxPile;

        while ( l <= r ){
            int m = l + (r - l) / 2;
            if ( canEatAll( m, h, piles))
                r = m - 1;
            else 
                l = m + 1;
        }
        return l;
    }

    private boolean canEatAll(int k, int h, int[] piles){
        int time = 0;
        for (int i = 0; i < piles.length; i++) 
            time += (piles[i] / k) + (piles[i] % k > 0 ? 1 :0);
        return h >= time;   
    }


```
