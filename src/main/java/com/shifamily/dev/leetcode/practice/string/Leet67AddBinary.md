# 原题

67. Add Binary
Easy

970

189

Favorite

Share
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"

# 解法

就像手工做加法一样，末尾对齐，从后往前，一位一位加，记上进位。
每位和：
和只有3种情况, 0 (0+0), 1 (0+1)，2（1+1）,模 2后分别为 0， 1， 0，符合进位规则

每位进位：
根据和三种情况，carry = sum /2。只有和为2的时候需要进位，进1.

