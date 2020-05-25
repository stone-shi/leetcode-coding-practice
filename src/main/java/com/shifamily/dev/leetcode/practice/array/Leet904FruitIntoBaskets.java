package com.shifamily.dev.leetcode.practice.array;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.HashMap;
import java.util.Map;

/*
904. Fruit Into Baskets
Medium
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

 */
public class Leet904FruitIntoBaskets extends BasicStudy {
    public Leet904FruitIntoBaskets() {

        int[][] casesP1 = {{3,3,3,1,2,1,1,2,3,3,4}, {1,0,1,4,1,4,1,2,3}, {14,14,1,1,14, 5,14,1,14,1, 5,5,1,24, 7,7,8,7,7, 12,12,8,23,8, 23,8,20,10,0, 17}, {1,2,1}, {0,1,2,2}, {1,2,3,2,2}} ;
        int[] answers = {5, 5, 5, 3, 3, 4};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = casesP1[i];
            addParameterAndAnswer(p, answers[i]);
        }
    }




    @CaseRunner
    public int totalFruit(int[] tree) {
        
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
        
    }
    public int totalFruit_InitVersion(int[] tree) {

        int left = 0;
        int right = 0;
        int maxPick = Integer.MIN_VALUE;
        int currentPick = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while ( left < tree.length && right < tree.length){
            int fruitAdd = tree[right];
            int fruitRemove = tree[left];
            if ( map.size() < 2 || map.containsKey(fruitAdd)){
                map.put(fruitAdd, map.getOrDefault(fruitAdd, 0) + 1);
                currentPick++;
                maxPick = Math.max(currentPick, maxPick);
                right++;
            }else{
                if (map.containsKey(fruitRemove)) {
                    int fruitCount = map.get(fruitRemove);
                    fruitCount--;
                    currentPick--;
                    if (fruitCount == 0)
                        map.remove(fruitRemove);
                    else
                        map.put(fruitRemove, fruitCount);
                }
                left++;

            }
        }

        return maxPick;

    }
}
