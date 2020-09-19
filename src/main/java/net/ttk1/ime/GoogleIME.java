package net.ttk1.ime;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
                JsonReader jr = new JsonReader(new InputStreamReader(conn.getInputStream()));
                // [[word1,[candidate1,candidate2,...]],[word2,...]]
                // ↑ みたいな感じで入ってくるので、`List<List<List<String>>>` は本当はよくない
                List<List<List<String>>> words = new ArrayList<>();
                words = gson.fromJson(jr, words.getClass());
                for (List<List<String>> word : words) {
                    List<String> candidates = word.get(1);
                    sb.append(candidates.get(0));
                }
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 変換失敗時は元のtextをそのまま返す
        return text;
    }
}
