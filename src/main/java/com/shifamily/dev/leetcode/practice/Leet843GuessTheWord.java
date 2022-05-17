package com.shifamily.dev.leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

/*
843. Guess the Word
Hard

273

303

Favorite

Share
This problem is an interactive problem new to the LeetCode platform.

We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.

Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.

Example 1:
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

Explanation:

master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Note:  Any solutions that attempt to circumvent the judge will result in disqualification.


 */
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

    cases.add(CaseParameters.builder()
        .parameters(new Object[] { new String[] { "acckzz", "ccbazz", "eiowzz", "abcczz" }, "acckzz", 10 }).answer(true)
        .description("case a").build());
    cases.add(CaseParameters.builder()
        .parameters(new Object[] { new String[] { "hamada", "khaled" }, "hamada", 10 }).answer(true)
        .description("case b").build());
    cases.add(CaseParameters.builder()
        .parameters(new Object[] { new String[] { "wichbx", "oahwep", "tpulot", "eqznzs", "vvmplb", "eywinm", "dqefpt",
            "kmjmxr", "ihkovg", "trbzyb", "xqulhc", "bcsbfw", "rwzslk", "abpjhw", "mpubps", "viyzbc", "kodlta",
            "ckfzjh", "phuepp", "rokoro", "nxcwmo", "awvqlr", "uooeon", "hhfuzz", "sajxgr", "oxgaix", "fnugyu",
            "lkxwru", "mhtrvb", "xxonmg", "tqxlbr", "euxtzg", "tjwvad", "uslult", "rtjosi", "hsygda", "vyuica",
            "mbnagm", "uinqur", "pikenp", "szgupv", "qpxmsw", "vunxdn", "jahhfn", "kmbeok", "biywow", "yvgwho",
            "hwzodo", "loffxk", "xavzqd", "vwzpfe", "uairjw", "itufkt", "kaklud", "jjinfa", "kqbttl", "zocgux",
            "ucwjig", "meesxb", "uysfyc", "kdfvtw", "vizxrv", "rpbdjh", "wynohw", "lhqxvx", "kaadty", "dxxwut",
            "vjtskm", "yrdswc", "byzjxm", "jeomdc", "saevda", "himevi", "ydltnu", "wrrpoc", "khuopg", "ooxarg",
            "vcvfry", "thaawc", "bssybb", "ccoyyo", "ajcwbj", "arwfnl", "nafmtm", "xoaumd", "vbejda", "kaefne",
            "swcrkh", "reeyhj", "vmcwaf", "chxitv", "qkwjna", "vklpkp", "xfnayl", "ktgmfn", "xrmzzm", "fgtuki",
            "zcffuv", "srxuus", "pydgmq" }, "ccoyyo", 10 })
        .answer(true).description("case c").build());
    List<String> p1 = new ArrayList<>();
    String p2 = genRandomCase(99, p1);
    // cases.add(CaseParameters.builder()
    //     .parameters(new Object[] { p1.stream().toArray(String[]::new), p2, 10 }).answer(true)
    //     .description("case random").build());
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

}
