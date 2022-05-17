# 原题
438. Find All Anagrams in a String
Medium

2937

168

Add to List

Share
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
Accepted
256,310
Submissions
601,049
Seen this question in a real interview before?

Yes

No
Contributor
Stomach_ache
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Facebook
|
39

Google
|
5

Uber
|
4

Amazon
|
4

Microsoft
|
3

Oracle
|
2
Valid Anagram
Easy
Permutation in String
Medium


# 解法

对p建立一个26 size的数组。保存每个字母出现的次数。
对s，从 s[0, p.length)开始计算另外一个26 size数组。和p数组比较，如果一样，返回。
然后不断把这个window向右移动。移除的从数组减去次数，增加的，加上次数。让后和p数组比较。


## 复杂度
时间复杂度 O(N)
空间复杂度 O(N)


## 代码
```Java
   public List<Integer> findAnagrams(String s, String p) {

        int plen = p.length();
        int slen = s.length();
        List<Integer> res = new LinkedList<>();

        if (slen < plen)
            return res;

        int[] pCount = new int[26];
        int[] sCount = new int[26];
        for (int i = 0; i < plen; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(pCount, sCount))
            res.add(0);

        for (int i = 1; i < slen - plen + 1 ; i++) {
            sCount[s.charAt(i - 1) - 'a']--;
            sCount[s.charAt(i + plen - 1) - 'a']++;
            if (Arrays.equals(pCount, sCount))
                res.add(i);
        }

        return res;
    }
```
