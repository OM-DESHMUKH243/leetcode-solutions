package Strings;

import java.util.*;

public class PalindromePairs {

    public static List<List<Integer>> palindromePairs(String[] words){

        List<List<Integer>> result = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < words.length; i++){
            map.put(words[i], i);
        }

        for(int i = 0; i < words.length; i++){

            String word = words[i];

            for(int cut = 0; cut <= word.length(); cut++){

                String prefix = word.substring(0, cut);
                String suffix = word.substring(cut);

                // Case 1
                if(isPalindrome(prefix)){

                    String reversed = new StringBuilder(suffix)
                                      .reverse()
                                      .toString();

                    Integer idx = map.get(reversed);

                    if(idx != null && idx != i){

                        result.add(Arrays.asList(idx, i));
                    }
                }

                // Case 2
                if(cut != word.length() &&
                   isPalindrome(suffix)){

                    String reversed = new StringBuilder(prefix)
                                      .reverse()
                                      .toString();

                    Integer idx = map.get(reversed);

                    if(idx != null && idx != i){

                        result.add(Arrays.asList(i, idx));
                    }
                }
            }
        }

        return result;
    }

    private static boolean isPalindrome(String s){

        int left = 0;
        int right = s.length() - 1;

        while(left < right){

            if(s.charAt(left) != s.charAt(right)){
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args){

        String[] words = {
            "bat",
            "tab",
            "cat"
        };

        List<List<Integer>> result =
                palindromePairs(words);

        System.out.println(result);
    }
}
