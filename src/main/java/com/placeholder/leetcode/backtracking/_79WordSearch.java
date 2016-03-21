package com.placeholder.leetcode.backtracking;

/**
 * @author 阙宇翔
 * @version 2016/2/17
 */
public class _79WordSearch {
    int rows = 0;
    int cols = 0;
    char[][] board;
    char[] word;

    public static char[][] toCharCharArray(String[] strings) {
        char[][] charCharArray = new char[strings.length][strings[0].length()];
        for (int i = 0; i < strings.length; ++i) {
            charCharArray[i] = strings[i].toCharArray();
        }
        return charCharArray;
    }

    public static void main(String[] args) {
        char[][] charCharArray = toCharCharArray(new String[]{"ABCE", "SFCS", "ADEE"});
        System.out.println(new _79WordSearch().exist(charCharArray, "ABCCED"));
        System.out.println(new _79WordSearch().exist(charCharArray, "SEE"));
        System.out.println(!new _79WordSearch().exist(charCharArray, "ABCB"));

        charCharArray = toCharCharArray(new String[]{
                "gplcefsalhprkbatpoinzrmussjdjexpwitanmbztqqftqbjgjwgalcsdlvmsueodxoslrrg",
                "ijodbpmiyloawsubfrqxlulqtajhrcpbodfputzuepuahqcnhukvqwnjyysrbitplagiticy",
                "xzahpewzygwoejaeiswlcpcqpkyksaisblzrpvsqdoekzgohzkudbwtshrdzuntvysoqpggv",
                "wmfwttfugaxjyrcfkhheucavvqlmxthtfmraixuqzuzxlbfviocfqealunatghnnweogdkwc",
                "evapzfnjvrolvqzrfzlngabegrkkbgmgeoxdvmmqddcatdszcfokfppnibxkimsmcpraedqh",
                "gukcxedbjrmqjddredbopvctlttpzmyhgkjgoohzgvrryvifamtrhwkupelqsmyywhglyqme",
                "lexmbhrbtmudiezaklqczodvyliwbvcpbzbcgufcgbhpfgrsthwuybqwnasqvufyvicdcjfl",
                "koasxtksainykfwzfopbkvbhdelinstyhvqgocyqknpwsnvycnboicwnghvwbquilmqzopsa",
                "cjzvwwvyjwntylhhsedtvzeiluiblabqjanfxigievcegjlapqvkpbtcyddlfhbqjqxgyfre",
                "atlieyktpigglbjjgmvltydfpcnpiewkxhvbfhvwpdddemmkajxwkabbcoqmtozsvuudbrzt",
                "ucwyqkktwhpijsjngbabqzulvooyhqtburblcneauvidnrqtssxitrwqfmpocipxcskefqgb",
                "loeyhwtbrqkmhidouuczctwgniksystjizjpycrpvdbcngthaxgftelgmyzkquvzweouhhjc",
                "mmgztbjvbpauwmaikbvcwqbuvsoebzionqpisyetqenmspwerrhpikjfezjgyrwlhlubmawc",
                "glqzbmfuemlozutdtcltvjgcxagjccnjodirpnlvcwjbscgneugzdmeapmlroyaebkxtaiqc",
                "gafycnnjhvknkqnzczstxvxbhvwhfmlomqorgccpxochfqijpdcoacrhznpgcauqtjjzllqk",
                "ctujjesahxqhbiraxggzibqdkbcxptjrodcxiwzrtpbuxswxafyiiomusqrhlayzgayowxhs",
                "niomcllcsknaabustlzhnzitbhjygrsvbgietvinfvngyhasuabkbjddqpbxgvujdenxawmf",
                "tanshqmeqporytwrixqqukayppxpljwhjmbtcpzuennciktrjjjeulclbbaokyvwkyrmnqgr",
                "fuwngrgpbstxdxigakvnisjvtchguqazmyospxhqpdpuczddlyqwsbrnfywzqwaeuowlngec",
                "ltznvcqgcicwjvkowgpmepragpltxpyilxwibmqdxvzgqlxmtmbxeuzkjldhcbppanxbaogz",
                "jifzveoptpmxjohuzmddqvtsiqwkgcjrmrrivhxoylnjzwdaljebeztmrpzyuipgziqxrnnp",
                "bbyazcbklhlrihfzyezunpdoztlrhbgiefkdjnowwbnekselzdfmuicwcqplrvvvcfanvqjr",
                "uywetcpshxgehialarwrotorbpgwhqnbqlgjnxdvujbeudpuuomihcbitiebyucqfkavkdsg",
                "ptkjwadqoravwcgpkmskixaqhcltidaxykiwmnoahoydqescslmbkprrtelbjnahxkfkytmf",
                "jkkaqcelptmziftbjgfstfzssgeqzrxidhkwlphditcsyxvhdabyiaragvshorpsycqlrxob",
                "qrvqqqzuraubcldkjvrzmitmkjxdgmgyfbqvuprnpmotzrdkmwmaffmrplxxzfwejmcdetqv",
                "hgpjxstlphnwnbpemobmuzsfoukuqapxieifzcsqlfoygdcsuggqhzvxtfrjhhjpnrwmvoch",
                "tqfzwktqsaizzdxukoetxpilgexcucjnuqoqahisjqukvrffhlyfaiqgopkkrvxomngovpjg",
                "hdrfwykfjlklvctmuewnzwbnjiegznngsenqcavnlgyikrwextsxptmadqhcdulxayneyjtl",
                "rrwbksiinahevvfzmmbriopkneonnjzgaxkmpswdsfhnbmonaqgkgxuvckjrtkavhkiycgbu",
                "mljnzycaqimwihsmtddnndkwnuurbylnjxciveknoxjwgdkziomxtxtjrqauoohxllhipuvg",
                "tgezmpyufnsamnjfdmarajqnvavmusspyzokoohvbbvpohvstvjvgcidcfqyxknvjdizrrut",
                "ssigzfysckpjmxmqceqzpdwaggczxyupqdwrkujngayuxmkbracifbknhmnflhudmsvxngmv",
                "hkqgwckpdmxkniyxwncjwynjrkigqubzgricwsszgrlubjtyyvhvvwgmiqsbkwaspiwlcomj",
                "iyfjjyhivrfqpldybxzntbhjmduouhxchclqavyvofodsrbtocikervsvrgpaerhieyjzygp",
                "eutwnwsebcofulxrcfgdjzmsdkdflluripnvmhbnmruictzfyfkkgweljhsuuplceayskzfw",
                "tbevwfcxmmhsllduuyqondrrdrkpsrllvrhrwlokyxcliihegxttamkfdwvxqijlaseydvjd",
                "snodvvhbucwuqjavfvtxfekfwpgbkrfeevhctrdpvakmjmhqjaqogawcpegbvlibipddijte",
                "lfswtzmeccsidqnuutyrggtqxwuifpoqwgmrfbvjfpukihhcafuinpymluutlilhqaaydvhi",
                "nbtvkazmhvuwktiypftbpekgglejinsvpnsbpsoyniuadeatltudzejgrpraclvtaqxpilpx",
                "tlzzszsdsoguurcmitmnfjifzfxjqmjmailsifxctewpxabiupvbbficlfmduxrwfcopjlse",
                "rqurrxznnwqqdysoggucdlyjqoyzbsetlamexntmjjeohxdpdzrjkrsafschnicyioehbxwm",
                "gaapxffdeworqitvavepdgpnwtvzqrnyrqorxtwerkvhsofumlkruzfqtaslrfkkxydvtzzk",
                "lwudmbaymkqglvzgxrsoyeaweerxfsjrodwagwbtgtzuoaalrubqydochhbnzlgpqcrxzsqh",
                "lrbbseolypdzvrdcyfrzszoicghdaxkmronlscwstatotxtsemtwmigqqotqndcetsrlwqgr",
                "szilybfepzadjhtzxmrkswrloizmyhdqilehojlgiojtxfuurmelixzygymghpzpddwtokzz",
                "ylsvqnrjzxvjvujbuvjbmiqpnpjczkdzvvwlinxjksufodikysnlcfcsunuvyzwtushffeos",
                "ylzooiapcpaeucwrqsoqrllodstlyjfyueolooaqdaxaevrwofmhsaxysqjtcqtyuijjylbe",
                "lyesuxqkefrxfrvzhgslyomswvduhfaudeoxbfhgkbfpscqbkjpixbavygrflscqysqcxzkj",
                "apaurrydcnlbqlwotpwgjaxisnkqmubplelevjhzysaofyfzpbfzbehtttlgnpxatiequlpu",
                "drjkpqjerrfuvopphdxwswwlecbansvsjgdawogpfmjdaysibrgvnfgthjuvbrpmxumtjtiq",
                "fttftnpxgystdbpkkjhoazaztnscgcvnxquqeknmkggnhvztghjigkhzzbehfzucrouvaiik",
                "qqzznzsvgcfooopnptwwuqameujhetrulttasovysaoiodvfztdvjdjpatweoqbajwcdkydc",
                "arkrwhwvccrlhcdhvzomrrocnqfyqicrbpiyygtbkmosprbmtramiorwhwwxhaqiraisjcvt",
                "qjlhdnwwewkpnbluajthjmrdmavveqpwbceepccwypllqyhqhbzsntvbvsxbkoxmqdsifxeg",
                "mrtepbxyeyqtroumgtpthpfaszkawqikhbrycowgmozecwslpiezxlzrmjtlzbvidmghceop",
                "spvxlpibxocwbbqnmlynnuxsifzljpcbfxaqmktmyvkbycpmonbdjyvudwhmmjqtisludfid",
                "ctfdxupljrovrlpviyjwhbppualxhwckphnodeapxqkpebmozvkizzztbksjguvxeimhoowm",
                "gibkjnaikkqjlpencwykttkxdwgrmfdunghxvhhguarfrwuvusgnlqmoovicclwpufopnyxh",
                "apornjnhdvxrllfbgndibbaxhqowomfodviqgxxkswbfiigoylzzozyxpmtgaawdwetcbtmt",
                "ppaxyiowtnxiovhfjbnkbknxojaseolteotewjaqyzamwhtfkhpordlhonzsennkbioxrqpp"});
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; ++i) {
            new _79WordSearch().exist(charCharArray, "rbnbxsleizxnneb");
        }
        long duration = System.currentTimeMillis() - start;
        System.out.println(duration / 10.0 + "ms");


        charCharArray = toCharCharArray(new String[]{"aaaa", "aaaa", "aaaa", "aaaa", "aaab"});
        start = System.currentTimeMillis();
        for (int i = 0; i < 10; ++i) {
            new _79WordSearch().exist(charCharArray, "aaaaaaaaaaaaaaaaaaaa");
        }
        duration = System.currentTimeMillis() - start;
        System.out.println(duration / 10.0 + "ms");

        charCharArray = toCharCharArray(new String[]{
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaab"});
        start = System.currentTimeMillis();
        for (int i = 0; i < 10; ++i) {
            new _79WordSearch().exist(charCharArray,
                    "baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        }
        duration = System.currentTimeMillis() - start;
        System.out.println(duration / 10.0 + "ms");
    }

    public boolean exist(char[][] board, String word) {
        rows = board.length;
        cols = board[0].length;
        this.board = board;
        this.word = word.toCharArray();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (helper(i, j, new char[word.length()], 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean helper(
            int row,
            int col,
            char[] sb,
            int index) {
        // 结束，一定要放这，根据或判断的短路原则，不然递归会很深
        if (index == word.length) {
            return true;
        }
        // 越界
        if (row < 0 || row > rows - 1 || col < 0 || col > cols - 1) {
            return false;
        }
        // visited
        if (board[row][col] == '#') {
            return false;
        }
        // 不符合
        if (word[index] != board[row][col]) {
            return false;
        }
        char tmp = sb[index] = board[row][col];
        board[row][col] = '#';  // visited
        boolean result = (helper(row - 1, col, sb, index + 1)) ||
                (helper(row + 1, col, sb, index + 1)) ||
                (helper(row, col - 1, sb, index + 1)) ||
                (helper(row, col + 1, sb, index + 1));
        board[row][col] = tmp;
        return result;
    }
}
