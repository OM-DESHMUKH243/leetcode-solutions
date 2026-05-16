package Trees;

public class SegmentTreeRangeSum {

    int[] tree;
    int[] nums;
    int n;

    public SegmentTreeRangeSum(int[] nums){

        this.nums = nums;

        n = nums.length;

        tree = new int[4 * n];

        build(0, 0, n - 1);
    }

    private void build(int node,
                       int start,
                       int end){

        if(start == end){

            tree[node] = nums[start];

            return;
        }

        int mid = start + (end - start) / 2;

        build(2 * node + 1,
              start,
              mid);

        build(2 * node + 2,
              mid + 1,
              end);

        tree[node] =
            tree[2 * node + 1]
          + tree[2 * node + 2];
    }

    public void update(int index,
                       int val){

        updateTree(
            0,
            0,
            n - 1,
            index,
            val
        );
    }

    private void updateTree(int node,
                            int start,
                            int end,
                            int idx,
                            int val){

        if(start == end){

            nums[idx] = val;

            tree[node] = val;

            return;
        }

        int mid = start + (end - start) / 2;

        if(idx <= mid){

            updateTree(
                2 * node + 1,
                start,
                mid,
                idx,
                val
            );

        } else {

            updateTree(
                2 * node + 2,
                mid + 1,
                end,
                idx,
                val
            );
        }

        tree[node] =
            tree[2 * node + 1]
          + tree[2 * node + 2];
    }

    public int sumRange(int left,
                        int right){

        return query(
            0,
            0,
            n - 1,
            left,
            right
        );
    }

    private int query(int node,
                      int start,
                      int end,
                      int left,
                      int right){

        // No overlap
        if(right < start ||
           end < left){

            return 0;
        }

        // Complete overlap
        if(left <= start &&
           end <= right){

            return tree[node];
        }

        int mid = start + (end - start) / 2;

        int leftSum = query(
            2 * node + 1,
            start,
            mid,
            left,
            right
        );

        int rightSum = query(
            2 * node + 2,
            mid + 1,
            end,
            left,
            right
        );

        return leftSum + rightSum;
    }

    public static void main(String[] args){

        int[] nums = {1,3,5};

        SegmentTreeRangeSum seg =
            new SegmentTreeRangeSum(nums);

        System.out.println(
            seg.sumRange(0, 2)
        );

        seg.update(1, 2);

        System.out.println(
            seg.sumRange(0, 2)
        );
    }
}