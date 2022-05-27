# Leetcode #2 Add Two Numbers

## 原题

[2 Add Two Numbers](https://leetcode.com/problems/add-two-numbers)

Medium 18508 3774

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

**Example 1:**
![Example 1](https://assets.leetcode.com/uploads/2020/10/02/addtwonumber1.jpg)

> `Input: l1 = [2,4,3], l2 = [5,6,4]`
`Output: [7,0,8]`
Explanation: 342 + 465 = 807.

**Example 2:**

> `Input: l1 = [0], l2 = [0]`
`Output: [0]`

**Example 3:**

> `Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]`
`Output: [8,9,9,9,0,0,0,1]`
 
**Constraints:**

- The number of nodes in each linked list is in the range [1, 100].
- `0 <= Node.val <= 9`
- It is guaranteed that the list represents a number that does not have leading zeros.

## 解法

就像手工加法，从低到高加，注意进位。进位最后还要检查，要加到结果。

## 复杂度


## 代码


```Java
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode res = null;
        ListNode prev = null;
        int addtion = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;

            int r = v1 + v2 + addtion;
            addtion = r / 10;
            r = r % 10;
            ListNode n = new ListNode(r);
            if (res == null) {
                res = n;
                prev = n;
            } else {
                prev.next = n;
                prev = n;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (addtion != 0){
            prev.next = new ListNode(addtion);
        }

        return res;
    }

```
