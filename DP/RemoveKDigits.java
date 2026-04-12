package DP;

public class RemoveKDigits {

    public static String removeKdigits(String num, int k){

        StringBuilder stack = new StringBuilder();

        for(char digit : num.toCharArray()){

            while(stack.length() > 0 &&
                  k > 0 &&
                  stack.charAt(stack.length() - 1) > digit){

                stack.deleteCharAt(stack.length() - 1);
                k--;
            }

            stack.append(digit);
        }

        while(k > 0 && stack.length() > 0){
            stack.deleteCharAt(stack.length() - 1);
            k--;
        }

        int index = 0;

        while(index < stack.length() && stack.charAt(index) == '0'){
            index++;
        }

        String result = stack.substring(index);

        return result.isEmpty() ? "0" : result;
    }

    public static void main(String[] args){

        String num = "1432219";
        int k = 3;

        String result = removeKdigits(num, k);

        System.out.println("Smallest Number: " + result);
    }
}
