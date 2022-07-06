package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import java.util.*;
import com.shifamily.dev.*;

public class Leet1101TheEarliestMomentWhenEveryoneBecomeFriends extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(
                CaseParameters.builder()
                        .parameters(new Object[] { new int[][] { { 20190101, 0, 1 }, { 20190104, 3, 4 },
                                { 20190107, 2, 3 }, { 20190211, 1, 5 }, { 20190224, 2, 4 }, { 20190301, 0, 3 },
                                { 20190312, 1, 2 }, { 20190322, 4, 5 } }, 6 })
                        .answer(20190301)
                        .description("Example 1").build());
        return cases;
    }

    @CaseRunner
    public int earliestAcq(int[][] logs, int N) {
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        UF uf = new UF(N);
        for (int[] log: logs){
            int sz = uf.union(log[1], log[2]);
            if (sz == 1)
                return log[0];
        }
        return -1;
    }

    static public class UF {
        private int[] parents;
        private int count;
        public UF(int n){
            parents = new int[n];
            for (int i = 0; i < n; i++) 
                parents[i] = i;
            count = n;
        }

        public int getCount(){
            return count;
        }

        public int union(int a, int b){
            int[] pa = find(a);
            int[] pb = find(b);

            if (pa[0] != pb[0]){
                if (pa[1] < pb[1])
                    parents[pa[0]] = pb[0];
                else
                    parents[pb[0]] = pa[0];
                count--;
            }

            return count;

        }

        private int[] find(int a){
            int size = 1;
            while (parents[a] != a){
                a = parents[a];
                size++;
            }
            return new int[]{a, size};
        }
    }

}
