package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet4MedianTwoSortedArrays extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(CaseParameters.builder().parameters(new Object[] { new int[] { 1, 3 }, new int[] { 2 } }).answer(2.0)
                .description("case a").build());

        cases.add(
                CaseParameters.builder().parameters(new Object[] { new int[] { 1, 2 }, new int[] { 3, 4 } }).answer(2.5)
                        .description("case a").build());
        return cases;
    }

    @CaseRunner
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;

        l = locateK(nums1, 0, nums2, 0, l);
        r = locateK(nums1, 0, nums2, 0, r);

        return (l + r) / 2.0;
    }

    private int locateK(int[] a, int startA, int[] b, int startB, int k) {
        if (startA >= a.length)
            return b[startB + k - 1];
        if (startB >= b.length)
            return a[startA + k - 1];

        if (k == 1)
            return Math.min(a[startA], b[startB]);

        int na = startA + k / 2 > a.length ? Integer.MAX_VALUE : a[startA + k / 2 - 1];
        int nb = startB + k / 2 > b.length ? Integer.MAX_VALUE : b[startB + k / 2 - 1];

        if (na < nb)
            return locateK(a, startA + k / 2, b, startB, k - k / 2); // a right , b left
        else
            return locateK(a, startA, b, startB + k / 2, k - k / 2); // a left, b right

    }

}
