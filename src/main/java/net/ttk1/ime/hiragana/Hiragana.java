package net.ttk1.ime.hiragana;

import net.ttk1.ime.util.Trie;

public class Hiragana {
    public static String fromRomaji(String romaji) {
        StringBuilder hiragana = new StringBuilder();
        Trie<Character, String> current = HiraganaDictionary.dict;
        for (int i = 0; i < romaji.length(); i++) {
            Character key = romaji.charAt(i);
            Trie<Character, String> child = current.getChild(key);
            if (child == null) {
                hiragana.append(current.getValue());
                current = HiraganaDictionary.dict.getChild(key);
            } else {
                current = current.getChild(key);
            }
        }
        hiragana.append(current.getValue());
        return hiragana.toString();
    }
}
