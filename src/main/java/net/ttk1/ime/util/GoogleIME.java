package net.ttk1.ime.util;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

public class GoogleIME {
    public static String transliterate(String text) throws IOException {
        URL url = new URL("https://www.google.com/transliterate");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        return null;
    }
}
