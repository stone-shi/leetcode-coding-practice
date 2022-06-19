package com.shifamily.dev.leetcode.practice;

import java.util.*;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

public class Leet843GuessTheWord extends BasicStudy {

    private String genRandomCase(int count, List<String> cases) {
        HashSet<String> h = new HashSet<>();
        Random r = new Random();
        while (h.size() < count) {
            char[] c = new char[6];
            for (int i = 0; i < 6; i++) {
                c[i] = (char) ('a' + r.nextInt(26));
            }
            h.add(new String(c));
        }
        cases.addAll(h);
        return cases.get(r.nextInt(count));
    }

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();

        // cases.add(CaseParameters.builder()
        // .parameters(new Object[] { new String[] { "acckzz", "ccbazz", "eiowzz",
        // "abcczz" }, "acckzz", 10 })
        // .answer(true)
        // .description("case a").build());
        // cases.add(CaseParameters.builder()
        // .parameters(new Object[] { new String[] { "hamada", "khaled" }, "hamada", 10
        // }).answer(true)
        // .description("case b").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] {
                        new String[] { "wichbx", "oahwep", "tpulot", "eqznzs", "vvmplb", "eywinm", "dqefpt",
                                "kmjmxr", "ihkovg", "trbzyb", "xqulhc", "bcsbfw", "rwzslk", "abpjhw", "mpubps",
                                "viyzbc", "kodlta",
                                "ckfzjh", "phuepp", "rokoro", "nxcwmo", "awvqlr", "uooeon", "hhfuzz", "sajxgr",
                                "oxgaix", "fnugyu",
                                "lkxwru", "mhtrvb", "xxonmg", "tqxlbr", "euxtzg", "tjwvad", "uslult", "rtjosi",
                                "hsygda", "vyuica",
                                "mbnagm", "uinqur", "pikenp", "szgupv", "qpxmsw", "vunxdn", "jahhfn", "kmbeok",
                                "biywow", "yvgwho",
                                "hwzodo", "loffxk", "xavzqd", "vwzpfe", "uairjw", "itufkt", "kaklud", "jjinfa",
                                "kqbttl", "zocgux",
                                "ucwjig", "meesxb", "uysfyc", "kdfvtw", "vizxrv", "rpbdjh", "wynohw", "lhqxvx",
                                "kaadty", "dxxwut",
                                "vjtskm", "yrdswc", "byzjxm", "jeomdc", "saevda", "himevi", "ydltnu", "wrrpoc",
                                "khuopg", "ooxarg",
                                "vcvfry", "thaawc", "bssybb", "ccoyyo", "ajcwbj", "arwfnl", "nafmtm", "xoaumd",
                                "vbejda", "kaefne",
                                "swcrkh", "reeyhj", "vmcwaf", "chxitv", "qkwjna", "vklpkp", "xfnayl", "ktgmfn",
                                "xrmzzm", "fgtuki",
                                "zcffuv", "srxuus", "pydgmq" },
                        "ccoyyo", 10 })
                .answer(true).description("case c").build());
        return cases;
    }

    static class Master {
        char[] sec;
        HashSet<String> wordSet;
        int tryTimes;
        int calledTimes = 0;
        boolean hitAll = false;

        public Master(String[] wordList, String sec, int tryTimes) {
            this.sec = sec.toCharArray();
            this.tryTimes = tryTimes;
            wordSet = new HashSet<>(Arrays.asList(wordList));
        }

        public boolean isPass() {
            return hitAll && calledTimes <= tryTimes;
        }

        public int guess(String word) {
            calledTimes++;
            if (!wordSet.contains(word) || word.length() != sec.length)
                return -1;

            char[] wordChar = word.toCharArray();
            int ct = 0;
            for (int i = 0; i < sec.length; i++) {
                if (sec[i] == wordChar[i])
                    ct++;
            }
            hitAll = hitAll || ct == sec.length;
            return ct;
        }
    }

    private String[] findMatchNum(String[] wordList, int n, int selected) {
        List<String> candidate = new ArrayList<>();
        for (int i = 0; i < wordList.length; i++) {
            if (selected == i)
                continue;
            int ct = 0;
            for (int j = 0; j < wordList[i].length(); j++) {
                if (wordList[selected].charAt(j) == wordList[i].charAt(j))
                    ct++;
            }
            if (ct == n)
                candidate.add(wordList[i]);
        }
        return candidate.stream().toArray(String[]::new);
    }

    public void findSecretWord(String[] wordlist, Master master) {
        int guessMatch;
        Random r = new Random();
        for (int i = 0; i < 10 && wordlist.length > 0; i++) {
            int selected = r.nextInt(wordlist.length);
            guessMatch = master.guess(wordlist[selected]);
            if (guessMatch == 6)
                return;
            wordlist = findMatchNum(wordlist, guessMatch, selected);
        }
    }

    @CaseRunner
    public Boolean runCases(String[] wordlist, String sec, int tryTimes) {
        Master m = new Master(wordlist, sec, tryTimes);
        findSecretWord(wordlist, m);
        return m.isPass();
    }

    @CaseRunner
    public Boolean runCases2(String[] wordlist, String sec, int tryTimes) {
        Master m = new Master(wordlist, sec, tryTimes);
        findSecretWord2(wordlist, m);
        return m.isPass();
    }

    // second try - 2022/06/19
    public void findSecretWord2(String[] wordlist, Master master) {
        int guessMatch;
        Random r = new Random();
        for (int i = 0; i < 10 && wordlist.length > 0; i++) {
            int selected = r.nextInt(wordlist.length);
            String s = wordlist[selected];
            guessMatch = master.guess(s);
            if (guessMatch == s.length())
                return;
            wordlist = findMatchedNumStrings(wordlist, s, guessMatch);
        }

    }

    private String[] findMatchedNumStrings(String[] wordList, String s, int num) {
        List<String> res = new LinkedList<>();
        for (String w : wordList) {
            int n = w.length();
            int matched = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == w.charAt(i))
                    matched++;
            }
            if (matched == num)
                res.add(w);
        }
        return res.stream().toArray(String[]::new);
    }
}
