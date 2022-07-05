package com.shifamily.dev.leetcode.practice;

import java.util.*;
import com.shifamily.dev.*;

public class Leet1717MaximumScoreFromRemovingSubstrings extends BasicStudy {

    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] { "bab", 4, 5 }).answer(5)
                .description("My case").build());
        cases.add(CaseParameters.builder().parameters(new Object[] {
                "jbabbbaavaabbqfbadzrabbabgjaozabbabhaaabbbaqbbaabcbzlboaabbbabbabaaaaoaaybbbbbanafaaaaabbaaaaxbbabaabbaaabgablbbsaaababjbebaabaabacbbaclbafbbbkublbbnnaaazwxabalbaaxbsabmbbabablfcubasaaababalaaabsbbbbbabbqahfbbbbbpababababaaaabbafahnabbgbbabaabbataabrbabbbaowaabazbbbabphaukxiaajhbisnkboaanoglbahaubbnaeaobabatabeboaibbqbbaaabbajauaaubnibabbjbvyaaabbbabaaxbaabzzvbfabdbabuabakaaabiwarhaabzaabugbagaaafsqajaaxaaabaaapobbakjvrahbavabbbyaabbabgafaalbaaoaakaeaaaabbatvabbabbamaabaaaabdbpaaabtbawbaxebeabbaalaaalbaabbaababsbbbbebbbaababtbrabxarolqvabbbbfybbbdauabbkbaabbbatbabbaambbqaabbwaizbreababafabbebababbdbpfvoaaababbaabbaaaqbbzpadhhbbbaiabrizbarbabuaaaazbzabaaafbbabbavaabwvaaaarbzbaabbhbbbazabbaxaabosbbaabeaaablbawaabubbbkabaaibbbbaaaaxabxabbabaaaabaaabkbbbaaaaaabbabbbtaaababbbaabbhbaaadaaaeazajaalbfababaghbaayabbaawaaaambhbhabbbbbajbbbqweaabqaaabbfbbabmbbbabbaaawaababbaabbaaapbfndaaabawabbabxbabbbxabbbbaebabskaobrbdaafbbaoaldbbabbbbbabgablzaabbbbaaozaaabgabbaabsabaaaaapqbaaaaabybapbabpabpnbaaaxappbabaabbaabbbanbbvabgcabbaibaaiaaawaabajpbabaazaaabbbgataablabrbbueaabbbaalaaabaubamalbataaabobabaabbbavaabaoabcbbbaabaeabbjbbebbbatybapaibbzbbbbotabaofbbsbivbbaazaabmxubaabbzbbbbubnabbkagaybbbngabbtbahbbbebbbmjhbambbbasbnbabasgabbbabbaaabbbasgbbpbbbbahabdbbbmjbbbbabawbmbabbkaabaaqaaqabvababqabuaubaaabaaababagrlbalbbhbaaaakagaabbmbaavawabbahpcobanbbaaavabababbmaaeabababbxbabrbbbgybcagbabbfbnbbqwbrriaakbbvtzbqqabtbbbbababbjpabaaaarabbabbzbstbbabpbbbbzmabcaabbaaababaambayapbabuabhfaacblbaebbbtadbbbbbbbbabbbaaafaabngkabdaiaaaaaaaaaaaaaababanaebcqbbtmvbjaaabaaxdaabbbzoaaaaababbbfaaaazbfhbbabkaabbaababbakaaaagafaaababjabaqbdaaaababxwbbbobaobgbabayaampzbaoqbmaaajbabbabnbalaajbxkubyqaapybabaxalanraaababoaargatbbbbbapsbkkaaaabbbababhwbebkmubaabaafabbsaoaobataababaabtmiibgbbaabaaaxbaaafvmabacbbbvwbcbbbababaagbjbfiyaabaabbybbabaehrabbabxaabbawbbbbbbbaabuuzauabbbvabbadabababtbbbbaabbabaabaxabbzbbbbbjabnvbabahaaabbbbaaabbabbabxjlauuzbbacabzaafjaaagqbbbbbybbalsabbdaaaobxbbampxaabbabbzamiabababuabnaaaabyafaabiabbhbabbakaaabncabaabiaaabdbbbbaapaaaaaaabaabbahbbbabaabooaaadobqabbaubabbbaaaabbbaybaaeabbeaixbabfaybbbakbbabaapjbabvabbjabpazblbaafbaaabhbbbabaabbcwaabbbbbwxaalbebbuakeabbabbbbbbabababquazbaazabkajshauayabmbbnaaaablbbabbubzaahakaabbabavaebbkhaqaababbabfbacbabxbbabbftaarizabaaabaaababbybbabbicjbbhbbbbaababbabaaaaaaykbgjbabwbabgibfbbababbafaaabtbbhbbacababbabaabaadabaaaabbabaabtzcbabaakaboaaaabbpabababbbbzfaribobawjbbvramabbanaaoababaabfbheavjabbabzgkwbartkbbbbbaabaaxrbabtbaazabbaabbbabaaabbvbj",
                3861, 5496 }).answer(2804124)
                .description("Case 0 - TLE").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { "cdbcbbaaabab", 4, 5 }).answer(19)
                .description("Example 1").build());
        cases.add(CaseParameters.builder().parameters(new Object[] { "aabbaaxybbaabb", 5, 4 }).answer(20)
                .description("Example 2").build());
        return cases;
    }

    // right solution for this problem
    @CaseRunner
    public int maximumGain2(String s, int x, int y) {
        StringBuilder sb = new StringBuilder(s);
        if (x > y)
            return remove(sb, x, "ab") + remove(sb, y, "ba");
        else
            return remove(sb, y, "ba") + remove(sb, x, "ab");
    }

    private int remove(StringBuilder s, int point, String p) {
        int i = 0;
        int n = s.length();
        int res = 0;
        for (int j = 0; j < n; j++) {
            s.setCharAt(i, s.charAt(j));
            if (i >= 1 &&  s.charAt(i - 1) == p.charAt(0) && s.charAt(i) == p.charAt(1)) {
                res += point;
                i -= 2;
            }
            i++;
        }
        s.setLength(i);
        return res;
    }

    // this is a brutal force + memory dfs solution and will cause TLE
    @CaseRunner
    public int maximumGain(String s, int x, int y) {
        Map<String, Integer> mem = new HashMap<>();
        String[] keys = new String[] { "ab", "ba" };
        int[] points = new int[] { x, y };
        int res = dfs(s, keys, points, mem);
        return res;
    }

    private int dfs(String s, String[] keys, int[] points, Map<String, Integer> mem) {
        if (mem.containsKey(s))
            return mem.get(s);

        int res = 0;
        for (int i = 0; i < keys.length; i++) {
            int idx = s.indexOf(keys[i]);
            if (idx == -1)
                continue;
            res = Math.max(res,
                    points[i] + dfs(s.substring(0, idx) + s.substring(idx + keys[i].length()), keys, points, mem));
        }
        mem.put(s, res);
        return res;
    }
}
