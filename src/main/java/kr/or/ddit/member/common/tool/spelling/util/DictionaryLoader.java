//package kr.or.ddit.member.common.tool.spelling.util;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.HashSet;
//import java.util.Set;
//
//public class DictionaryLoader {
//
//	private static Set<String> dictionary;
//
//	// static initializer
//	static {
//		try {
//			dictionary = loadFromClasspath("dictionary.txt");
//		} catch (IOException e) {
//			dictionary = new HashSet<>();
//		}
//	}
//
//	private static Set<String> loadFromClasspath(String filename) throws IOException {
//		Set<String> set = new HashSet<>();
//		try (InputStream is = DictionaryLoader.class.getClassLoader().getResourceAsStream(filename);
//				BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
//			String line;
//			while ((line = br.readLine()) != null) {
//				line = line.trim();
//				if (!line.matches("^[0-9]+.*$") && !line.isEmpty()) {
//					set.add(line);
//				}
//			}
//		}
//		return set;
//	}
//
//	public static boolean isValid(String word) {
//		return dictionary.contains(word);
//	}
//
//}
