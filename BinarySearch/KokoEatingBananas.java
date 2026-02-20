package BinarySearch;

class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = getMax(piles);

        int ans = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canEat(piles, h, mid)) {
                ans = mid;
                right = mid - 1;   // try smaller speed
            } else {
                left = mid + 1;    // need faster speed
            }
        }

        return ans;
    }

    // Check if Koko can finish with given speed
    private boolean canEat(int[] piles, int h, int speed) {
        long hours = 0;

        for (int bananas : piles) {
            hours += (bananas + speed - 1) / speed; // ceil division
        }

        return hours <= h;
    }

    // Find max pile
    private int getMax(int[] piles) {
        int max = 0;
        for (int x : piles) {
            max = Math.max(max, x);
        }
        return max;
    }
}