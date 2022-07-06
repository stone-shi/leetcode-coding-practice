package com.shifamily.dev.utils;

public class UnionFind {
    private int[] parents;
    private int count;

    public UnionFind(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++)
            parents[i] = i;
        count = n;
    }

    public int getCount() {
        return count;
    }

    public int union(int a, int b) {
        int[] pa = find(a);
        int[] pb = find(b);

        if (pa[0] != pb[0]) {
            if (pa[1] < pb[1])
                parents[pa[0]] = pb[0];
            else
                parents[pb[0]] = pa[0];
            count--;
        }

        return count;

    }

    private int[] find(int a) {
        int size = 1;
        while (parents[a] != a) {
            a = parents[a];
            size++;
        }
        return new int[] { a, size };
    }

}
