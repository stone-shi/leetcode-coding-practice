package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet1499MaxValueEquation extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { -19, -12 }, { -13, -18 }, { -12, 18 }, { -11, -8 },
                        { -8, 2 }, { -7, 12 }, { -5, 16 }, { -3, 9 }, { 1, -7 }, { 5, -4 }, { 6, -20 }, { 10, 4 },
                        { 16, 4 }, { 19, -9 }, { 20, 19 } }, 6 })
                .answer(35)
                .description("Case 0").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 1, 3 }, { 2, 0 }, { 5, 10 }, { 6, -10 } }, 1 }).answer(4)
                .description("Example 1").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new int[][] { { 0, 0 }, { 3, 0 }, { 9, 2 } }, 3 }).answer(3)
                .description("Example 2").build());
        return cases;
    }

    // monotonic stack solution
    // yi + yj + | xi - xj|, since x is sorted, | xi - xj | -> xj - xi.
    // yi + yj + | xi - xj| -> yi + yj + xj - xi -> xj + yj + yi - xi
    // given j, we know xj + yj, we need find i has max ( yi - xi)
    // we can use priority queue to sort i O(N log N) OR monotonic stack (O(N))
    // this is a monotonic stack solution
    @CaseRunner
    public int findMaxValueOfEquation(int[][] points, int k) {
        int res = Integer.MIN_VALUE;
        Deque<int[]> q = new LinkedList<>();
        for (int[] point : points) { // go throuh all points (j)
            // remove point not qualify xj - xi > k
            while (!q.isEmpty() && point[0] - q.peekFirst()[1] > k)
                q.pollFirst();

            // if any left, first one is biggest of qualifed (monotonic stack to make sure)
            if (!q.isEmpty()) {
                res = Math.max(res, point[0] + point[1] + q.peekFirst()[0]); //  point[0] + point[1] + q.peekFirst()[0] -> xj + yj + (yi - xi)
            }
            // monotonic stack here
            while (!q.isEmpty() && q.peekLast()[0] < (point[1] - point[0]))
                q.removeLast();

            // enqueue two elements first is (yi - xi), second is xi
            q.offerLast(new int[] { point[1] - point[0], point[0] });
        }
        return res;
    }
}
