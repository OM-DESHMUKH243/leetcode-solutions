package Trees;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val){
        this.val = val;
    }
}

public class SerializeDeserializeBinaryTree {

    public static String serialize(TreeNode root){

        StringBuilder sb = new StringBuilder();
        preorderSerialize(root, sb);

        return sb.toString();
    }

    private static void preorderSerialize(TreeNode node, StringBuilder sb){

        if(node == null){
            sb.append("null,");
            return;
        }

        sb.append(node.val).append(",");

        preorderSerialize(node.left, sb);
        preorderSerialize(node.right, sb);
    }

    public static TreeNode deserialize(String data){

        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));

        return buildTree(queue);
    }

    private static TreeNode buildTree(Queue<String> queue){

        String val = queue.poll();

        if(val.equals("null")){
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(val));

        node.left = buildTree(queue);
        node.right = buildTree(queue);

        return node;
    }

    public static void main(String[] args){

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        String serialized = serialize(root);

        System.out.println("Serialized: " + serialized);

        TreeNode restored = deserialize(serialized);

        System.out.println("Deserialized Root: " + restored.val);
    }
}
