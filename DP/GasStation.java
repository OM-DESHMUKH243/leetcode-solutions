package DP;

public class GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int total = 0;
        int current = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {

            int balance = gas[i] - cost[i];

            total += balance;
            current += balance;

            if (current < 0) {
                start = i + 1;
                current = 0;
            }
        }

        return total >= 0 ? start : -1;
    }

    public static void main(String[] args) {

        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};

        System.out.println(canCompleteCircuit(gas, cost));
    }
}
