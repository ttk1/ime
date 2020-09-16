package net.ttk1.ime.hiragana;

import net.ttk1.ime.util.Trie;

public class HiraganaDictionary {
    public static final Trie<Character, String> dict = new Trie<>(null);

    private static void addHiragana(String spell, String hiragana) {
        try {
            Trie<Character, String> current = dict;
            for (int i = 0; i < spell.length(); i++) {
                Character key = spell.charAt(i);
                if (current.hasKey(key)) {
                    current = current.getChild(key);
                } else if (i == spell.length() - 1) {
                    current = current.createChild(key, hiragana);
                } else {
                    current = current.createChild(key, null);
                }
            }
        } catch (Trie.KeyAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    static {
        addHiragana("a", "あ");
        addHiragana("i", "い");
        addHiragana("u", "う");
        addHiragana("e", "え");
        addHiragana("o", "お");

        addHiragana("ka", "か");
        addHiragana("ki", "き");
        addHiragana("ku", "く");
        addHiragana("ke", "け");
        addHiragana("ko", "こ");

        addHiragana("sa", "さ");
        addHiragana("si", "し");
        addHiragana("su", "す");
        addHiragana("se", "せ");
        addHiragana("so", "そ");

        addHiragana("ta", "た");
        addHiragana("ti", "ち");
        addHiragana("tu", "つ");
        addHiragana("te", "て");
        addHiragana("to", "と");

        addHiragana("na", "な");
        addHiragana("ni", "に");
        addHiragana("nu", "ぬ");
        addHiragana("ne", "ね");
        addHiragana("no", "の");

        addHiragana("ha", "は");
        addHiragana("hi", "ひ");
        addHiragana("hu", "ふ");
        addHiragana("he", "へ");
        addHiragana("ho", "ほ");

        addHiragana("ma", "ま");
        addHiragana("mi", "み");
        addHiragana("mu", "む");
        addHiragana("me", "め");
        addHiragana("mo", "も");

        addHiragana("ya", "や");
        addHiragana("yu", "ゆ");
        addHiragana("yo", "よ");

        addHiragana("wa", "わ");
        addHiragana("wo", "を");
        addHiragana("nn", "ん");
    }
}
