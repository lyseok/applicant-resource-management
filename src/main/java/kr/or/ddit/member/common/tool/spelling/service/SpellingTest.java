package kr.or.ddit.member.common.tool.spelling.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class SpellingTest {

	 public String checkSpelling(String text) throws IOException, JSONException {
	        // 1. 문장 인코딩
	        String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8).replace("+", "%20");

	        // 2. 외부 API URL 생성
	        String apiUrl = "https://m.search.naver.com/p/csearch/ocontent/util/SpellerProxy"
	                + "?where=nexearch&color_blindness=0&q=" + encodedText;

	        System.out.println("apiUrl: " + apiUrl);  // 디버깅

	        // 3. 연결 설정
	        URL url = new URL(apiUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("User-Agent",
	                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
	                        "(KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
	        conn.setRequestProperty("Referer", "https://m.search.naver.com/");
	        conn.setRequestProperty("Accept", "*/*");
	        conn.setRequestProperty("Accept-Language", "ko-KR,ko;q=0.9");

	        // 4. 응답 읽기
	        BufferedReader br = new BufferedReader(
	                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
	        StringBuilder sb = new StringBuilder();
	        String line;

	        while ((line = br.readLine()) != null) {
	            sb.append(line);
	        }
	        br.close();

	        String raw = sb.toString();
	        System.out.println("응답 원문: " + raw);

	        // 5. JSONP 파싱: 콜백 함수 제거
	        int start = raw.indexOf("(");
	        int end = raw.lastIndexOf(")");

	        if (start == -1 || end == -1) {
	            throw new JSONException("JSONP 형식이 아닙니다.");
	        }

	        String pureJson = raw.substring(start + 1, end);

	        // 6. JSON 파싱
	        JSONObject json = new JSONObject(pureJson);
	        JSONObject result = json.getJSONObject("message").getJSONObject("result");

	        if (!result.has("html")) {
	            throw new JSONException("응답에 'html' 키가 없음");
	        }

	        return result.getString("html");
	    }
	
}
