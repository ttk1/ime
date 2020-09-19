package net.ttk1.ime;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleIME {
    public static String transliterate(String text) throws IOException {
        URL url = new URL(String.format("https://www.google.com/transliterate?langpair=ja-Hira|ja&text=%s", URLEncoder.encode(text, "UTF-8")));
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
            int BUFF_SIZE = 100;
            byte[] buf = new byte[BUFF_SIZE];
            InputStream is = conn.getInputStream();
            StringBuilder sb = new StringBuilder();
            int len;
            while ((len = is.read(buf, 0, BUFF_SIZE)) >= 0) {
                sb.append(new String(buf, 0, len));
            }
            return sb.toString();
        } else {
            return "";
        }
    }
}
