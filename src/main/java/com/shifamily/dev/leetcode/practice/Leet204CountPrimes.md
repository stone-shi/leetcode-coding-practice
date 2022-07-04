# Leetcode #204. Count Primes

## 原题

[204 Count Primes](https://leetcode.com/problems/count-primes/)

**<span style="color:blue">Medium</span>** 044 1032

Given an integer n, return the number of prime numbers that are strictly less than n.

**Example 1:**

> `Input: n = 10`
`Output: 4`
**Explanation:** There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

**Example 2:**

> `Input: n = 0`
`Output: 0`

**Example 3:**

> `Input: n = 1`
`Output: 0`

**Constraints:**

- 0 <= n <= 5 * 10^6^

## 解法

看图。找到一个prime后，把这个prime 乘以 其他数字的标记为非prime。

![Solution Demo](https://leetcode.com/static/images/solutions/Sieve_of_Eratosthenes_animation.gif)

有一个optional优化，可以只检查到 sqrt(n)，因为小于等于 sqrt(n)找不到，必定大于也找不到。
但是，必须扫描notPrime数组得到count.

## 复杂度

时间 O(N)
空间 O(N)

## 代码

```Java
   // solution 1 - this can be further optmized to only check sqrt(n)
    @CaseRunner
    public int countPrimes(int n) {
        if (n <= 2)
            return 0;

        boolean[] notPrime = new boolean[n];

        int res = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                res++;
                for (long j = i; j * i < n; j++) {
                    notPrime[(int) (j * i)] = true;
                }
            }
        }
        return res;
    }

    // solution 2 - only check sqrt(n)
    @CaseRunner
    public int countPrimes2(int n) {
        if (n <= 2)
            return 0;

        boolean[] notPrime = new boolean[n];
        int res = 0;
        long sqn = (long)Math.sqrt(n * 1.0);

        for (int i = 2; i <= sqn; i++ ) {
            if (notPrime[i] == false){
                for (long j = i; j * i < n; j++) {
                    notPrime[(int)(j * i)] = true;
                }
            }
        }

        for (int i = 2; i < notPrime.length; i++) {
            if (notPrime[i] == false)
                res++;
        }
        return res;
    }

```