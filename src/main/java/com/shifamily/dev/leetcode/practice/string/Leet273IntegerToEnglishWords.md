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

## 复杂度
时间复杂度 O(N) 
空间复杂度 O(1)


## 代码
```Java
   public String numberToWords(int num) {

        if (num == 0)
            return "Zero";

        String[] LESS_THAN_20 = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
        String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String HUNDRED = "Hundred";
        String[] THOUSANDS = { "Billion",  "Million",  "Thousand", "" };
        String SPACE = " ";

        int[] radix = {1000000000, 1000000, 1000, 1};

        StringBuilder sb = new StringBuilder();

        int leftOver = num;

        //循环处理每千位
        for (int i = 0; i < radix.length; i++) {

            //百位内数字
            int hundredNumbers = leftOver / radix[i];
            leftOver = leftOver % radix[i];

            if (hundredNumbers == 0)
                continue;

            //百的处理
            int h = hundredNumbers / 100;
            if (h > 0) {
                if (sb.length() > 0) sb.append(SPACE);
                sb.append(LESS_THAN_20[h]).append(SPACE).append(HUNDRED);
            }

            int left = hundredNumbers % 100;
            if (left >= 20) {//大于20的处理
                int t = left / 10;
                if (sb.length() > 0) sb.append(SPACE);
                sb.append(TENS[t]);
                left -= t * 10;
            }
            if (left > 0) { //小于20的处理
                if (sb.length() > 0) sb.append(SPACE);
                sb.append(LESS_THAN_20[left]);
            }
            if (sb.length() > 0 && THOUSANDS[i].length() > 0) sb.append(SPACE);
            sb.append(THOUSANDS[i]); //加上千位名字
        }
        return sb.toString();

    }
```
