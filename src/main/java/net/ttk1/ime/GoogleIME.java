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
            InputStream is = conn.getInputStream();
            int BUFF_SIZE = 100;
            byte[] buf = new byte[BUFF_SIZE];
            int offset = 0;
            while (true) {
                int len = is.read(buf, offset, BUFF_SIZE - offset);
                if (len < 0) {
                    break;
                }
                offset += len;
                if (offset >= BUFF_SIZE) {
                    break;
                }
            }
            return new String(buf, 0, Math.min(offset, BUFF_SIZE));
        } else {
            return "";
        }
    }
}
