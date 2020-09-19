package net.ttk1.ime;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GoogleIMETest {
    @Test
    public void testTransliterate() throws IOException {
        String text = "きょうはいいてんきですね。";
        String expected = "今日はいい天気ですね。";
        String actual = GoogleIME.transliterate(text);
        assertThat(actual, is(expected));
    }
}
