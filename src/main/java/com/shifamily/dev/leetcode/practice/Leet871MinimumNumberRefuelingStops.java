package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet871MinimumNumberRefuelingStops extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { 1000, 299,
                        new int[][] { { 13, 21 }, { 26, 115 }, { 100, 47 }, { 225, 99 }, { 299, 141 }, { 444, 198 },
                                { 608, 190 }, { 636, 157 }, { 647, 255 }, { 841, 123 } } })
                .answer(4)
                .description("Case 0").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { 1000, 83,
                        new int[][] { { 47, 220 }, { 65, 1 }, { 98, 113 }, { 126, 196 }, { 186, 218 }, { 320, 205 },
                                { 686, 317 }, { 707, 325 }, { 754, 104 }, { 781, 105 } } })
                .answer(4)
                .description("Case 0").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { 1000, 36, new int[][] { { 7, 13 }, { 10, 11 },
                { 12, 31 }, { 22, 14 }, { 32, 26 }, { 38, 16 }, { 50, 8 }, { 54, 13 }, { 75, 4 }, { 85, 2 }, { 88, 35 },
                { 90, 9 }, { 96, 35 }, { 103, 16 }, { 115, 33 }, { 121, 6 }, { 123, 1 }, { 138, 2 }, { 139, 34 },
                { 145, 30 }, { 149, 14 }, { 160, 21 }, { 167, 14 }, { 188, 7 }, { 196, 27 }, { 248, 4 }, { 256, 35 },
                { 262, 16 }, { 264, 12 }, { 283, 23 }, { 297, 15 }, { 307, 25 }, { 311, 35 }, { 316, 6 }, { 345, 30 },
                { 348, 2 }, { 354, 21 }, { 360, 10 }, { 362, 28 }, { 363, 29 }, { 367, 7 }, { 370, 13 }, { 402, 6 },
                { 410, 32 }, { 447, 20 }, { 453, 13 }, { 454, 27 }, { 468, 1 }, { 470, 8 }, { 471, 11 }, { 474, 34 },
                { 486, 13 }, { 490, 16 }, { 495, 10 }, { 527, 9 }, { 533, 14 }, { 553, 36 }, { 554, 23 }, { 605, 5 },
                { 630, 17 }, { 635, 30 }, { 640, 31 }, { 646, 9 }, { 647, 12 }, { 659, 5 }, { 664, 34 }, { 667, 35 },
                { 676, 6 }, { 690, 19 }, { 709, 10 }, { 721, 28 }, { 734, 2 }, { 742, 6 }, { 772, 22 }, { 777, 32 },
                { 778, 36 }, { 794, 7 }, { 812, 24 }, { 813, 33 }, { 815, 14 }, { 816, 21 }, { 824, 17 }, { 826, 3 },
                { 838, 14 }, { 840, 8 }, { 853, 29 }, { 863, 18 }, { 867, 1 }, { 881, 27 }, { 886, 27 }, { 894, 26 },
                { 917, 3 }, { 953, 6 }, { 956, 3 }, { 957, 28 }, { 962, 33 }, { 967, 35 }, { 972, 34 }, { 984, 8 },
                { 987, 12 } } }).answer(32)
                .description("Case 1").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { 100, 50, new int[][] { { 40, 50 } } }).answer(1)
                .description("Case 2").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { 1, 1, new int[][] {} }).answer(0)
                .description("Example 1").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { 100, 1, new int[][] { { 10, 100 } } })
                .answer(-1)
                .description("Example 2").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { 100, 10,
                        new int[][] { { 10, 60 }, { 20, 30 }, { 30, 30 }, { 60, 40 } } })
                .answer(2)
                .description("Example 3").build());
        return cases;
    }

    // solution 1, O(N log N), priority queue greedy
    @CaseRunner
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int stop = 0;
        int n = stations.length;
        int i = 0;

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int maxDistance = startFuel;
        while (maxDistance < target) {
            while (i < n && stations[i][0] <= maxDistance) {
                pq.offer(stations[i][1]);
                i++;
            }
            if (pq.isEmpty())
                return -1;
            maxDistance += pq.poll();
            stop++;
        }

        return stop;
    }

}
