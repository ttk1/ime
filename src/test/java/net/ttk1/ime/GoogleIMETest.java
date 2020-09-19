package net.ttk1.ime;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GoogleIMETest {
    @Test
    public void testTransliterate() {
        String text = "きょうはいいてんきですね。";
        String expected = "今日はいい天気ですね。";
        String actual = GoogleIME.transliterate(text);
        assertThat(actual, is(expected));
    }
}
