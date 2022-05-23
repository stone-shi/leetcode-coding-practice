# Leetcode #299 Bulls and Cows

## 原题

[299 Bulls and Cows](https://leetcode.com/problems/bulls-and-cows/)

Medium
1395
1315
Share

You are playing the Bulls and Cows game with your friend.

You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:

- The number of "bulls", which are digits in the guess that are in the correct position.
- The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.

Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.

The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. Note that both secret and guess may contain duplicate digits.

**Example 1:**

> `Input: secret = "1807", guess = "7810"`
`Output: "1A3B"`
Explanation: Bulls are connected with a '|' and cows are underlined:
"1807"
&nbsp;&nbsp;&nbsp;    |
"<u>7</u>8<u>10</u>"

**Example 2:**

> `Input: secret = "1123", guess = "0111"`
`Output: "1A1B"`
Explanation: Bulls are connected with a '|' and cows are underlined:
"1123"        "1123"
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  |&nbsp;&nbsp;&nbsp; or &nbsp;&nbsp;&nbsp;|
"01<u>1</u>1"        "011<u>1</u>"
Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
 
**Constraints:**

- 1 <= secret.len- gth, guess.length <= 1000
- secret.length == guess.length
- secret and guess consist of digits only.

## 解法

2 pass 比较容易理解
1 pass 解释：

> `numbers[secret.charAt(i)-'0']` is negative only if this character appeared in the guess more times then in the secret which means that this character in the secret can be matched with one of the previous characters in the guest. I hope this explanation makes sense.

## 复杂度

O(N)


## 代码


```Java
   // solution 2 - 1 pass - best 
    @CaseRunner
    public String getHint1(String secret, String guess) {
        int[] charCount = new int[10];
        int a = 0;
        int b = 0;

        for (int i = 0; i < secret.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
            if (s == g)
                a++;
            else{
                if (charCount[g] > 0)
                    b++;
                if (charCount[s] < 0)
                    b++;
                charCount[s]++;
                charCount[g]--;
            }
        }
        return String.valueOf(a) + "A" + String.valueOf(b) + "B";
    }

    // solution 1 - require 2 pass
    @CaseRunner
    public String getHint(String secret, String guess) {
        int[] charCount = new int[10];
        int aCount = 0;
        int bCount = 0;
        for (int i = 0; i < secret.length(); i++){
            if (secret.charAt(i) != guess.charAt(i))
                charCount[secret.charAt(i) - '0']++;
            else
                aCount++;
        }

        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) != guess.charAt(i)){
                if (charCount[guess.charAt(i) - '0'] > 0){
                    bCount++;
                    charCount[guess.charAt(i) - '0']--;
                }
            }
        }
        return String.valueOf(aCount) + "A" + String.valueOf(bCount) + "B";
    }

```
