//package kr.or.ddit.member.common.tool.spelling.service;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import org.springframework.stereotype.Service;
//
//import jakarta.annotation.PostConstruct;
//import kr.or.ddit.member.common.tool.spelling.util.DictionaryLoader;
//
//@Service
//public class SpellingCheckService {
//
//	public Map<String, Boolean> checkSentence(String sentence){
//		Map<String, Boolean> result = new LinkedHashMap<>();
//		String[] words = sentence.split("\\s+");
//		
//		
//		for(String word : words) {
//			if(word.isBlank()) continue;
//			
//			String cleanWord = word.replaceAll("[^가-힣a-zA-Z]", "");
//		
//			if(cleanWord.length() < 2) continue;		
//			boolean isCorrect = DictionaryLoader.isValid(cleanWord);
//			if(!isCorrect) {				
//				result.put(word, isCorrect);
//			}
//		}
//		return result;
//	}
//	
//}
