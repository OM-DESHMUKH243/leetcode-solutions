package DP;

import java.util.*;

public class WordBreakIIMemoization {

    public static List<String> wordBreak(
            String s,
            List<String> wordDict){

        Set<String> set =
                new HashSet<>(wordDict);

        Map<Integer, List<String>> memo =
                new HashMap<>();

        return dfs(s, 0, set, memo);
    }

    private static List<String> dfs(
            String s,
            int start,
            Set<String> set,
            Map<Integer, List<String>> memo){

        // Memo hit
        if(memo.containsKey(start)){
            return memo.get(start);
        }

        List<String> result =
                new ArrayList<>();

        // Reached end
        if(start == s.length()){

            result.add("");

            return result;
        }

        for(int end = start + 1;
            end <= s.length();
            end++){

            String word =
                    s.substring(start, end);

            if(set.contains(word)){

                List<String> suffixes =
                        dfs(s, end, set, memo);

                for(String suffix : suffixes){

                    String sentence =
                            word +
                            (suffix.isEmpty()
                             ? ""
                             : " " + suffix);

                    result.add(sentence);
                }
            }
        }

        memo.put(start, result);

        return result;
    }

    public static void main(String[] args){

        String s = "catsanddog";

        List<String> dict =
                Arrays.asList(
                    "cat",
                    "cats",
                    "and",
                    "sand",
                    "dog"
                );

        List<String> result =
                wordBreak(s, dict);

        System.out.println(result);
    }
}
