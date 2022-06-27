package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet418SentenceScreenFitting extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        cases.add(
                CaseParameters.builder().parameters(new Object[] { new String[] { "hello", "world" }, 2, 8 }).answer(1)
                        .description("case a").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { new String[] { "a", "bcd", "e" }, 3, 6 }).answer(2)
                .description("case a").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { new String[] { "I", "had", "apple", "pie" }, 4, 5 }).answer(1)
                .description("case a").build());

        return cases;
    }

    // second try 2022/6/26
    @CaseRunner
    public int wordsTyping2(String[] sentence, int rows, int cols) {
        int[] lens = new int[sentence.length];
        for (int i = 0; i < sentence.length; i++) 
            lens[i] = sentence[i].length();
       
        int res = -1;
        int currentRow = 0;
        int currentCol = 0;
        Map<Integer, int[]> cache = new HashMap<>();
        while (currentRow <= rows){
            int[] r = fitInto(lens, currentCol, cols, cache);
            currentCol = r[0];
            currentRow += r[1];
            res++;
        }
        return res;
    }

    private int[] fitInto(int[] lens, int startCol, int col, Map<Integer, int[]> cache) {
        if (cache.containsKey(startCol))
            return cache.get(startCol);

        int row = 0;
        int currCol = startCol;

        int i = 0;
        while (i < lens.length) {
            if (currCol + lens[i] > col) {
                row++;
                currCol = 0;
            } else {
                currCol = currCol + lens[i] + 1;
                i++;
            }
        }
        int[] res = new int[]{currCol, row};
        cache.put(startCol, res);
        return res;
    }

    @CaseRunner
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int[] l = new int[sentence.length];
        for (int i = 0; i < l.length; i++) {
            l[i] = sentence[i].length();
            if (l[i] > cols)
                return 0;
        }
        Map<Integer, int[]> cache = new HashMap<>();
        int count = 0;
        int row = 0;
        int startCol = 0;
        int[] r;
        while (true) {
            if (cache.containsKey(startCol))
                r = cache.get(startCol);
            else
                r = fit(l, cols, startCol, cache);
            if (row + r[0] >= rows)
                return count;
            row += r[0];
            startCol = r[1];
            count++;
        }
    }

    private int[] fit(int[] l, int cols, int startCol, Map<Integer, int[]> cache) {
        int i = 0;
        int colCount = 0;
        int colIndex = startCol;
        while (i < l.length) {
            if (l[i] + colIndex <= cols) {
                colIndex += l[i] + 1;
                i++;
            } else {
                colCount++;
                colIndex = 0;
            }
        }
        int[] r = new int[2];
        r[0] = colCount;
        r[1] = colIndex;
        cache.put(startCol, r);
        return r;
    }
}
