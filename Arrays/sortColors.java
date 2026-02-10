import java.util.*;

public class sortColors {

    public static void sortColorsArray(int[] nums) {

        int low = 0, mid = 0;
        int high = nums.length - 1;

        while(mid <= high) {
            if(nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;

                low++;
                mid++;
            }
            else if(nums[mid] == 1) {
                mid++;
            }
            else { // nums[mid] == 2
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;

                high--;
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter size: ");
            int n = sc.nextInt();

            int[] nums = new int[n];
            System.out.println("Enter elements (0,1,2): ");
            for(int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }

            sortColorsArray(nums);

            System.out.print("Sorted array: ");
            for(int x : nums) {
                System.out.print(x + " ");
            }
        }
    }
}
