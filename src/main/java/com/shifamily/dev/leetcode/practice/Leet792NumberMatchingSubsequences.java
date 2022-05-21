package com.shifamily.dev.leetcode.practice;

import com.shifamily.dev.BasicStudy;
import java.util.*;

import com.shifamily.dev.CaseData;
import com.shifamily.dev.CaseParameters;
import com.shifamily.dev.CaseRunner;

public class Leet792NumberMatchingSubsequences extends BasicStudy {
    @CaseData
    public List<CaseParameters> data1() {
        List<CaseParameters> cases = new ArrayList<>();
        cases.add(CaseParameters.builder().parameters(new Object[] {
                "ricogwqznwxxcpueelcobbbkuvxxrvgyehsudccpsnuxpcqobtvwkuvsubiidjtccoqvuahijyefbpqhbejuisksutsowhufsygtwteiqyligsnbqglqblhpdzzeurtdohdcbjvzgjwylmmoiundjscnlhbrhookmioxqighkxfugpeekgtdofwzemelpyjsdeeppapjoliqlhbrbghqjezzaxuwyrbczodtrhsvnaxhcjiyiphbglyolnswlvtlbmkrsurrcsgdzutwgjofowhryrubnxkahocqjzwwagqidjhwbunvlchojtbvnzdzqpvrazfcxtvhkruvuturdicnucvndigovkzrqiyastqpmfmuouycodvsyjajekhvyjyrydhxkdhffyytldcdlxqbaszbuxsacqwqnhrewhagldzhryzdmmrwnxhaqfezeeabuacyswollycgiowuuudrgzmwnxaezuqlsfvchjfloczlwbefksxsbanrektvibbwxnokzkhndmdhweyeycamjeplecewpnpbshhidnzwopdjuwbecarkgapyjfgmanuavzrxricbgagblomyseyvoeurekqjyljosvbneofjzxtaizjypbcxnbfeibrfjwyjqrisuybfxpvqywqjdlyznmojdhbeomyjqptltpugzceyzenflfnhrptuugyfsghluythksqhmxlmggtcbdddeoincygycdpehteiugqbptyqbvokpwovbnplshnzafunqglnpjvwddvdlmjjyzmwwxzjckmaptilrbfpjxiarmwalhbdjiwbaknvcqovwcqiekzfskpbhgxpyomekqvzpqyirelpadooxjhsyxjkfqavbaoqqvvknqryhotjritrkvdveyapjfsfzenfpuazdrfdofhudqbfnzxnvpluwicurrtshyvevkriudayyysepzqfgqwhgobwyhxltligahroyshfndydvffd",
                new String[] { "iowuuudrgzmw", "azfcxtvhkruvuturdicnucvndigovkzrq", "ylmmo", "maptilrbfpjxiarmwalhbd",
                        "oqvuahijyefbpqhbejuisksutsowhufsygtwteiqyligsnbqgl", "ytldcdlxqbaszbuxsacqwqnhrewhagldzhr",
                        "zeeab", "cqie", "pvrazfcxtvhkruvuturdicnucvndigovkzrqiya",
                        "zxnvpluwicurrtshyvevkriudayyysepzq", "wyhxltligahroyshfn", "nhrewhagldzhryzdmmrwn",
                        "yqbvokpwovbnplshnzafunqglnpjvwddvdlmjjyzmw", "nhrptuugyfsghluythksqhmxlmggtcbdd",
                        "yligsnbqglqblhpdzzeurtdohdcbjvzgjwylmmoiundjsc",
                        "zdrfdofhudqbfnzxnvpluwicurrtshyvevkriudayyysepzq", "ncygycdpehteiugqbptyqbvokpwovbnplshnzafun",
                        "gdzutwgjofowhryrubnxkahocqjzww", "eppapjoliqlhbrbgh", "qwhgobwyhxltligahroys",
                        "dzutwgjofowhryrubnxkah", "rydhxkdhffyytldcdlxqbaszbuxs",
                        "tyqbvokpwovbnplshnzafunqglnpjvwddvdlmjjyzmwwxzjc", "khvyjyrydhxkdhffyytldcdlxqbasz",
                        "jajekhvyjyrydhxkdhffyytldcdlxqbaszbuxsacqwqn", "ppapjoliqlhbrbghq",
                        "zmwwxzjckmaptilrbfpjxiarm", "nxkahocqjzwwagqidjhwbunvlchoj",
                        "ybfxpvqywqjdlyznmojdhbeomyjqptltp", "udrgzmwnxae", "nqglnpjvwddvdlmjjyzmww",
                        "swlvtlbmkrsurrcsgdzutwgjofowhryrubn", "hudqbfnzxnvpluwicurr", "xaezuqlsfvchjf",
                        "tvibbwxnokzkhndmdhweyeycamjeplec", "olnswlvtlbmkrsurrcsgdzu",
                        "qiyastqpmfmuouycodvsyjajekhvyjyrydhxkdhffyyt", "eiqyligsnbqglqblhpdzzeurtdohdcbjvzgjwyl",
                        "cgiowuuudrgzmwnxaezuqlsfvchjflocz", "rxric", "cygycdpehteiugqbptyqbvokpwovbnplshnzaf", "g",
                        "surrcsgd", "yzenflfnhrptuugyfsghluythksqh", "gdzutwgjofowhryrubnxkahocqjzwwagqid",
                        "ddeoincygycdpeh", "yznmojdhbeomyjqptltpugzceyzenflfnhrptuug", "ejuisks",
                        "teiqyligsnbqglqblhpdzzeurtdohdcbjvzgjwylmmoi", "mrwnxhaqfezeeabuacyswollycgio",
                        "qfskkpfakjretogrokmxemjjbvgmmqrfdxlkfvycwalbdeumav",
                        "wjgjhlrpvhqozvvkifhftnfqcfjmmzhtxsoqbeduqmnpvimagq",
                        "ibxhtobuolmllbasaxlanjgalgmbjuxmqpadllryaobcucdeqc",
                        "ydlddogzvzttizzzjohfsenatvbpngarutztgdqczkzoenbxzv",
                        "rmsakibpprdrttycxglfgtjlifznnnlkgjqseguijfctrcahbb",
                        "pqquuarnoybphojyoyizhuyjfgwdlzcmkdbdqzatgmabhnpuyh",
                        "akposmzwykwrenlcrqwrrvsfqxzohrramdajwzlseguupjfzvd",
                        "vyldyqpvmnoemzeyxslcoysqfpvvotenkmehqvopynllvwhxzr",
                        "ysyskgrbolixwmffygycvgewxqnxvjsfefpmxrtsqsvpowoctw",
                        "oqjgumitldivceezxgoiwjgozfqcnkergctffspdxdbnmvjago",
                        "bpfgqhlkvevfazcmpdqakonkudniuobhqzypqlyocjdngltywn",
                        "ttucplgotbiceepzfxdebvluioeeitzmesmoxliuwqsftfmvlg",
                        "xhkklcwblyjmdyhfscmeffmmerxdioseybombzxjatkkltrvzq",
                        "qkvvbrgbzzfhzizulssaxupyqwniqradvkjivedckjrinrlxgi",
                        "itjudnlqncbspswkbcwldkwujlshwsgziontsobirsvskmjbrq",
                        "nmfgxfeqgqefxqivxtdrxeelsucufkhivijmzgioxioosmdpwx",
                        "ihygxkykuczvyokuveuchermxceexajilpkcxjjnwmdbwnxccl",
                        "etvcfbmadfxlprevjjnojxwonnnwjnamgrfwohgyhievupsdqd",
                        "ngskodiaxeswtqvjaqyulpedaqcchcuktfjlzyvddfeblnczmh",
                        "vnmntdvhaxqltluzwwwwrbpqwahebgtmhivtkadczpzabgcjzx",
                        "yjqqdvoxxxjbrccoaqqspqlsnxcnderaewsaqpkigtiqoqopth",
                        "wdytqvztzbdzffllbxexxughdvetajclynypnzaokqizfxqrjl",
                        "yvvwkphuzosvvntckxkmvuflrubigexkivyzzaimkxvqitpixo",
                        "lkdgtxmbgsenzmrlccmsunaezbausnsszryztfhjtezssttmsr",
                        "idyybesughzyzfdiibylnkkdeatqjjqqjbertrcactapbcarzb",
                        "ujiajnirancrfdvrfardygbcnzkqsvujkhcegdfibtcuxzbpds",
                        "jjtkmalhmrknaasskjnixzwjgvusbozslrribgazdhaylaxobj",
                        "nizuzttgartfxiwcsqchizlxvvnebqdtkmghtcyzjmgyzszwgi",
                        "egtvislckyltpfogtvfbtxbsssuwvjcduxjnjuvnqyiykvmrxl",
                        "ozvzwalcvaobxbicbwjrububyxlmfcokdxcrkvuehbnokkzala",
                        "azhukctuheiwghkalboxfnuofwopsrutamthzyzlzkrlsefwcz",
                        "yhvjjzsxlescylsnvmcxzcrrzgfhbsdsvdfcykwifzjcjjbmmu",
                        "tspdebnuhrgnmhhuplbzvpkkhfpeilbwkkbgfjiuwrdmkftphk",
                        "jvnbeqzaxecwxspuxhrngmvnkvulmgobvsnqyxdplrnnwfhfqq",
                        "bcbkgwpfmmqwmzjgmflichzhrjdjxbcescfijfztpxpxvbzjch",
                        "bdrkibtxygyicjcfnzigghdekmgoybvfwshxqnjlctcdkiunob",
                        "koctqrqvfftflwsvssnokdotgtxalgegscyeotcrvyywmzescq",
                        "boigqjvosgxpsnklxdjaxtrhqlyvanuvnpldmoknmzugnubfoa",
                        "jjtxbxyazxldpnbxzgslgguvgyevyliywihuqottxuyowrwfar",
                        "zqsacrwcysmkfbpzxoaszgqqsvqglnblmxhxtjqmnectaxntvb",
                        "izcakfitdhgujdborjuhtwubqcoppsgkqtqoqyswjfldsbfcct",
                        "rroiqffqzenlerchkvmjsbmoybisjafcdzgeppyhojoggdlpzq",
                        "xwjqfobmmqomhczwufwlesolvmbtvpdxejzslxrvnijhvevxmc",
                        "ccrubahioyaxuwzloyhqyluwoknxnydbedenrccljoydfxwaxy",
                        "jjoeiuncnvixvhhynaxbkmlurwxcpukredieqlilgkupminjaj",
                        "pdbsbjnrqzrbmewmdkqqhcpzielskcazuliiatmvhcaksrusae",
                        "nizbnxpqbzsihakkadsbtgxovyuebgtzvrvbowxllkzevktkuu",
                        "hklskdbopqjwdrefpgoxaoxzevpdaiubejuaxxbrhzbamdznrr",
                        "uccnuegvmkqtagudujuildlwefbyoywypakjrhiibrxdmsspjl",
                        "awinuyoppufjxgqvcddleqdhbkmolxqyvsqprnwcoehpturicf" }
        }).answer(51)
                .description("case a").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { "abcde", new String[] { "a", "bb", "acd", "ace" } }).answer(3)
                .description("case a").build());
        cases.add(CaseParameters.builder()
                .parameters(new Object[] { "dsahjpjauf", new String[] { "ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax" } })
                .answer(2)
                .description("case b").build());

        return cases;
    }

    // solution 0, should work
    @CaseRunner
    public int numMatchingSubseq2(String s, String[] words) {
        List<StringBuilder>[] waitingList = new List[26];
        for (int i = 0; i < 26; i++) {
            waitingList[i] = new LinkedList<>();
        }
        for (String w : words) {
            waitingList[w.charAt(0) - 'a'].add(new StringBuilder(w));
        }
        char[] ca = s.toCharArray();
        int res = 0;
        for (char c : ca) {
            int idx = c - 'a';
            List<StringBuilder> sbs = waitingList[idx];
            waitingList[idx] = new LinkedList<>();
            for (StringBuilder sb : sbs) {
                sb.deleteCharAt(0);
                if (sb.length() > 0) {
                    waitingList[sb.charAt(0) - 'a'].add(sb);
                } else {
                    res++;
                }
            }
        }
        return res;
    }

}
