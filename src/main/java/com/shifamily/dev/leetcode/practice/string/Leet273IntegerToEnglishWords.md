# 原题

273. Integer to English Words
Hard

533

1464

Favorite

Share
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"
Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"


# 解法

一个数，比如 123,456,789,012
对于每千位(逗号分隔)，分为 Billion, Million, Thousand

每个千位内，第一位是百(hundred)，第二位大于2 (20)的，用 Tens ("Twenty", "Thirty", "Forty" ....)
第三位和第二位小于20的，分别用 "Zero", "One", "Two", "Three" ...
