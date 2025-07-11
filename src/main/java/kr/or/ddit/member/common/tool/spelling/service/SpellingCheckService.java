package kr.or.ddit.member.common.tool.spelling.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import kr.or.ddit.member.common.tool.spelling.util.DictionaryLoader;

@Service
public class SpellingCheckService {

	public Map<String, Boolean> checkSentence(String sentence){
		String[] words = sentence.split("\\s+");
		Map<String, Boolean> result = new LinkedHashMap<>();
		for(String word : words) {
			boolean isCorrect = DictionaryLoader.isValid(word);
			result.put(word, isCorrect);
		}
		return result;
	}
	
}
