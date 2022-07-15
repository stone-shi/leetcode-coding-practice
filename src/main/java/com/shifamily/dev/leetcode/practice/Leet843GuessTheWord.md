# Leetcode 843 Guess the Word

## 原题

[843 Guess the Word](https://leetcode.com/problems/guess-the-word/)

**<span style="color:red">Hard</span>** 273 303 

This is an _**interactive problem**_.

You are given an array of **unique** strings `wordlist` where `wordlist[i]` is 6 letters long, and one word in this list is chosen as `secret`.

You may call `Master.guess(word)` to guess a word. The guessed word should have type `string` and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word. Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have exactly 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or fewer calls to Master.guess and at least one of these guesses was secret, then you pass the test case.

**Example 1:**

> `Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]`
**Explanation:**
`master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.`
`master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.`
`master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.`
`master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.`
`master.guess("abcczz") returns 4, because "abcczz" has 4 matches.`
We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Note:  Any solutions that attempt to circumvent the judge will result in disqualification.

**Example 2:**

> `Input: secret = "hamada", wordlist = ["hamada","khaled"], numguesses = 10`
`Output: You guessed the secret word correctly.`

**Constraints:**

* 1 <= wordlist.length <= 100
* wordlist[i].length == 6
* wordlist[i] consist of lowercase English letters.
* All the strings of wordlist are unique.
* secret exists in wordlist.
* numguesses == 10

# 解法

先取一个元素，进行guess。返回数字n。如果n=6，直接返回，否则在剩余的数组中找到和这个元素有n个字符相符的元素，循环。
取元素可以是随机或者第一个，没有区别。:laughing:**为了pass Leetcode的test case，必须随机。**



## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
    private String[] findMatchNum(String[] wordList, int n, int selected) {
        List<String> candidate = new ArrayList<>();
        for (int i = 0; i < wordList.length; i++) {
            if (selected == i)
                continue;
            int ct = 0;
            for (int j = 0; j < wordList[i].length(); j++) {
                if (wordList[selected].charAt(j) == wordList[i].charAt(j))
                    ct++;
            }
            if (ct == n)
                candidate.add(wordList[i]);
        }
        return candidate.stream().toArray(String[]::new);
    }

    public void findSecretWord(String[] wordlist, Master master) {
        int guessMatch;
        Random r = new Random();
        for (int i = 0; i < 10 && wordlist.length > 0; i++) {
            int selected = r.nextInt(wordlist.length);
            guessMatch = master.guess(wordlist[selected]);
            if (guessMatch == 6)
                return;
            wordlist = findMatchNum(wordlist, guessMatch, selected);
        }
    }

```
