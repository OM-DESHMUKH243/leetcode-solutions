package HashMap;

import java.util.*;

public class SubstringWithConcatenationOfAllWords {

    public static List<Integer> findSubstring(String s, String[] words){

        List<Integer> indices = new ArrayList<>();

        if(s.length() == 0 || words.length == 0) return indices;

        int wordLen = words[0].length();
        int totalWords = words.length;

        Map<String, Integer> freq = new HashMap<>();

        for(String w : words){
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }

        for(int i = 0; i < wordLen; i++){

            int left = i;
            int matched = 0;

            Map<String, Integer> window = new HashMap<>();

            for(int j = i; j + wordLen <= s.length(); j += wordLen){

                String curr = s.substring(j, j + wordLen);

                if(freq.containsKey(curr)){

                    window.put(curr, window.getOrDefault(curr, 0) + 1);
                    matched++;

                    while(window.get(curr) > freq.get(curr)){

                        String remove = s.substring(left, left + wordLen);
                        window.put(remove, window.get(remove) - 1);
                        left += wordLen;
                        matched--;
                    }

                    if(matched == totalWords){
                        indices.add(left);
                    }

                } 
                else{
                    window.clear();
                    matched = 0;
                    left = j + wordLen;
                }
            }
        }

        return indices;
    }

    public static void main(String[] args){

        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};

        List<Integer> result = findSubstring(s, words);

        System.out.println("Starting indices: " + result);
    }
}
