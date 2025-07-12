package kr.or.ddit.member.common.tool.spelling.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SpellingTest {

    @Value("${naver.speller.client-id}")
    private String clientId;

    @Value("${naver.speller.client-secret}")
    private String clientSecret;

    /**
     * 네이버 NCP Speller API 호출해서 맞춤법 검사 결과(html) 반환
     */
    public String checkSpelling(String text) throws IOException, JSONException {
        String apiUrl = "https://naveropenapi.apigw.ntruss.com/recog/v1/kor-spell";
        HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
        conn.setRequestProperty("X-NCP-APIGW-API-KEY",    clientSecret);

        conn.setDoOutput(true);
        try (OutputStream os = conn.getOutputStream()) {
            String body = "text=" + URLEncoder.encode(text, StandardCharsets.UTF_8);
            os.write(body.getBytes(StandardCharsets.UTF_8));
        }

        int status = conn.getResponseCode();
        StringBuilder sb = new StringBuilder();
        
        
        
        try (BufferedReader br = new BufferedReader(
                 new InputStreamReader(
                     status == 200 ? conn.getInputStream() : conn.getErrorStream(),
                     StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } finally {
            conn.disconnect();
        }
        
     // ▶ 파싱 시작
        String raw = sb.toString();
        System.out.println("▶▶▶ Speller raw response: " + raw);
        JSONObject root    = new JSONObject(raw);
        JSONObject message = root.getJSONObject("message");
        JSONObject result  = message.getJSONObject("result");
        String html        = result.getString("html");
        // ▶ 파싱 끝


        // **디버깅 로그**
        System.out.println("▶▶▶ Speller raw response: " + sb.toString());

        // 최상위 JSONArray 파싱
        JSONArray arr = new JSONArray(sb.toString());
        if (arr.length() == 0) return "";

        JSONObject first = arr.getJSONObject(0);
        return first.optString("html", "");
    }
}
