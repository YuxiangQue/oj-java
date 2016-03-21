package com.placeholder.jianzhioffer;

import com.placeholder.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuxiangque on 2016/3/18.
 */
public class SerialAndDeserializeBinaryTree {
    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String data = codec.serialize(root);
        System.out.println(data);
        TreeNode root1 = codec.deserialize(data);
        return;
    }

    public static class Codec {

        private int index = 0;

        // 中序
        private void serialize(TreeNode root, List<Integer> vals) {
            if (root == null) {
                vals.add(null);
                return;
            }
            vals.add(root.val);
            serialize(root.left, vals);
            serialize(root.right, vals);
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<Integer> vals = new ArrayList<>();
            serialize(root, vals);
            if (vals.size() == 0)
                return "";
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(vals.get(0) == null ? "null" : vals.get(0));
            for (int i = 1; i < vals.size(); ++i) {
                sb.append(vals.get(i) == null ? ",null" : "," + vals.get(i));
            }
            sb.append("]");
            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            index = 0;
            data = data.substring(1, data.length() - 1);
            String[] vals = data.split(",");
            return deserialize(vals);
        }

        private TreeNode deserialize(String[] vals) {
            if (vals.length == 0)
                return null;
            String val = vals[index++];
            if (val.equals("null")) {
                return null;
            }
            TreeNode treeNode = new TreeNode(Integer.parseInt(val));
            treeNode.left = deserialize(vals);
            treeNode.right = deserialize(vals);
            return treeNode;
        }
    }
}
