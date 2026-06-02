package Trees;

import java.util.*;

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

class Codec {

    public String serialize(TreeNode root) {

        if(root == null) {
            return "";
        }

        StringBuilder sb =
                new StringBuilder();

        Queue<TreeNode> queue =
                new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()) {

            TreeNode node =
                    queue.poll();

            if(node == null) {

                sb.append("null,");

                continue;
            }

            sb.append(node.val)
              .append(",");

            queue.offer(node.left);
            queue.offer(node.right);
        }

        return sb.toString();
    }

    public TreeNode deserialize(String data) {

        if(data == null ||
           data.isEmpty()) {

            return null;
        }

        String[] values =
                data.split(",");

        TreeNode root =
                new TreeNode(
                        Integer.parseInt(
                                values[0]
                        )
                );

        Queue<TreeNode> queue =
                new LinkedList<>();

        queue.offer(root);

        int index = 1;

        while(!queue.isEmpty()
              && index < values.length) {

            TreeNode parent =
                    queue.poll();

            if(index < values.length
               &&
               !values[index]
                    .equals("null")) {

                TreeNode left =
                        new TreeNode(
                                Integer.parseInt(
                                        values[index]
                                )
                        );

                parent.left = left;

                queue.offer(left);
            }

            index++;

            if(index < values.length
               &&
               !values[index]
                    .equals("null")) {

                TreeNode right =
                        new TreeNode(
                                Integer.parseInt(
                                        values[index]
                                )
                        );

                parent.right = right;

                queue.offer(right);
            }

            index++;
        }

        return root;
    }
}

public class SerializeDeserializeTree {

    public static void main(String[] args) {

        TreeNode root =
                new TreeNode(1);

        root.left =
                new TreeNode(2);

        root.right =
                new TreeNode(3);

        root.right.left =
                new TreeNode(4);

        root.right.right =
                new TreeNode(5);

        Codec codec =
                new Codec();

        String serialized =
                codec.serialize(root);

        System.out.println(
                "Serialized: "
                + serialized
        );

        TreeNode rebuilt =
                codec.deserialize(
                        serialized
                );

        System.out.println(
                "Root Value: "
                + rebuilt.val
        );
    }
}
