# 原题

Leetcode 904

904 Fruit Into Baskets
In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?

 

Example 1:

Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].
Example 2:

Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].
Example 3:

Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].
Example 4:

Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 

Note:

1 <= tree.length <= 40000
0 <= tree[i] < tree.length
Accepted

# 解法
slide window。

left代表左边的树，i代表右边的树。右指针从0到n-1（n=tree.length)扫描。用fruitCount表示篮子，只能放2类水果。
右指针扫过代表把1个水果（tree[i]类型）放入篮子。
然后检查篮子是否超过2类水果。
是的话左指针开始从篮子里拿一个水果（tree[left]类型)，直到篮子里只有2类水果。这个过程可能重复，应为每次只拿一个水果，同一类型可能有多个水果。只有这个类型水果count=0的时候，才算移除。

统计结果，右指针 - 左指针 + 1 代表当前状态下水果总数，用res = max(res, count)来记录最大值。

然后右指针继续往下扫描直到整个数组都扫完。

返回最大结果。

## 复杂度
时间复杂度 O(N)
空间复杂度 O(1)



## 代码
```Java
       int left = 0;
        int res = Integer.MIN_VALUE;
        Map<Integer, Integer> fruitCount = new HashMap<>();
        for (int i = 0; i < tree.length; i++) {
            fruitCount.put(tree[i], fruitCount.getOrDefault(tree[i], 0) + 1 );
            while (fruitCount.size() > 2){
                int ct = fruitCount.get(tree[left]);
                ct--;
                if (ct  == 0)
                    fruitCount.remove(tree[left]);
                else
                    fruitCount.put(tree[left], ct );
                left++;
            }
            res = Math.max(res, i - left + 1);
        }
        return res;
```
