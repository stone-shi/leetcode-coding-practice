# Leetcode #1406 Stone Game III

## 原题

[1406 Stone Game III](https://leetcode.com/problems/stone-game-iii/)

**Hard** 983 22

Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.

Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2, or 3 stones from the first remaining stones in the row.

The score of each player is the sum of the values of the stones taken. The score of each player is 0 initially.

The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.

Assume Alice and Bob play optimally.

Return "Alice" if Alice will win, "Bob" if Bob will win, or "Tie" if they will end the game with the same score.

**Example 1:**

> `Input: values = [1,2,3,7]`
`Output: "Bob"`
**Explanation:** Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.

**Example 2:**

> `Input: values = [1,2,3,-9]`
`Output: "Alice"`
**Explanation:** Alice must choose all the three piles at the first move to win and leave Bob with negative score.
If Alice chooses one pile her score will be 1 and the next move Bob's score becomes 5. In the next move, Alice will take the pile with value = -9 and lose.
If Alice chooses two piles her score will be 3 and the next move Bob's score becomes 3. In the next move, Alice will take the pile with value = -9 and also lose.
Remember that both play optimally so here Alice will choose the scenario that makes her win.

**Example 3:**

> `Input: values = [1,2,3,6]`
`Output: "Tie"`
**Explanation:** Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.
 
**Constraints:**

- 1 <= stoneValue.length <= 5 * 104
- -1000 <= stoneValue[i] <= 1000

## 解法

dp[i] means, if we ignore before A[i],
what's the highest score that Alex can win over the Bob？

There are three option for Alice to choose.
Take A[i], win take - dp[i+1]
Take A[i] + A[i+1], win take - dp[i+2]
Take A[i] + A[i+1] + A[i+2], win take - dp[i+3]
dp[i] equals the best outcome of these three solutions.


## 复杂度

## 代码

```Java
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            int take = 0;
            for (int j = 0; j < 3 && j + i < n; j++) {
                take += stoneValue[j + i];
                dp[i] = Math.max(dp[i], take - dp[j + i + 1]);
            }
        }
        if (dp[0] > 0)
            return "Alice";
        else if (dp[0] < 0)
            return "Bob";
        return "Tie";
    }

```
