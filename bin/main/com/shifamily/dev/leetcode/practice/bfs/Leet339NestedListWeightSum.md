# 原题
339. Nested List Weight Sum
Easy

422

94

Add to List

Share
Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 10 
Explanation: Four 1's at depth 2, one 2 at depth 1.
Example 2:

Input: [1,[4,[6]]]
Output: 27 
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.
# 解法
dfs 解，加上level信息。

## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)

## 代码
```Java
    public int depthSum(List<NestedInteger> nestedList) {

        return dfs(nestedList, 1);

    }

    public int dfs( List<NestedInteger> nestedList, int level ){

        int sum = 0;
        for (NestedInteger ni: nestedList
             ) {
            if (ni.isInteger())
                sum = sum +  ni.getInteger() * level;
            else
                sum = sum + dfs(ni.getList(), level + 1);
        }
        return sum;

    }

```
