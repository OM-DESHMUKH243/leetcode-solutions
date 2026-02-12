public class ProductExceptSelf {

    public static int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];

        // prefix product
        result[0] = 1;
        for(int i = 1; i < n; i++){
            result[i] = result[i-1] * nums[i-1];
        }

        // suffix product
        int right = 1;
        for(int i = n-1; i >= 0; i--){
            result[i] = result[i] * right;
            right *= nums[i];
        }

        return result;
    }

    
    public static void main(String[] args) {

        int[] nums = {1,2,3,4};
        int[] ans = productExceptSelf(nums);

        System.out.print("Output: ");
        for(int x : ans){
            System.out.print(x + " ");
        }
    }
}
