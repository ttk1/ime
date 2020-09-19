package net.ttk1.ime;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.google.co.jp/ime/cgiapi.html
 */
public class GoogleIME {
    private static final Gson gson = new Gson();

    public static String transliterate(String text) {
        try {
            URL url = new URL(String.format("https://www.google.com/transliterate?langpair=ja-Hira|ja&text=%s", URLEncoder.encode(text, "UTF-8")));
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                StringBuilder sb = new StringBuilder();
                JsonReader jr = new JsonReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                // メモ:
                // [[phrase1,[candidate1,candidate2,...]],[phrase2,...]]
                // みたいな感じで入ってくるので、`List<List<List<String>>>` は本当はよくない。
                // ただJavaだと複数の型が含まれるListを宣言できないのでこのような形にしてる。
                // 良いやり方思いついた方、PR送ってください。
                List<List<List<String>>> phrases = new ArrayList<>();
                phrases = gson.fromJson(jr, phrases.getClass());
                for (List<List<String>> phrase : phrases) {
                    List<String> candidates = phrase.get(1);
                    // 変換候補の先頭のものを使用する
                    sb.append(candidates.get(0));
                }
                conn.disconnect();
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 変換失敗時は元のtextをそのまま返す
        return text;
    }
}
