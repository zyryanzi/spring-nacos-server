package cloud.spring.my.study.algorithm;

import java.util.*;

public class BinaryTree {

    public static void printTree(TreeNode root) {
        Queue<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;
    }

    public static TreeNode buildTree(List<Integer> list) {
        List<TreeNode> nodeList = new ArrayList<>();
        // 数组 node 化
        for (Integer val : list) {
            if (val != null) {
                TreeNode node = new TreeNode(val);
                nodeList.add(node);
            } else {
                nodeList.add(null);
            }
        }
        // 处理最后两个结点之前的
        for (int i = 0; i < nodeList.size() / 2 - 1; i++) {
            nodeList.get(i).left = nodeList.get(2 * i + 1);
            nodeList.get(i).right = nodeList.get(2 * i + 2);
        }
        // 单独处理最后两个，避免单孩子
        int i = nodeList.size() / 2 - 1;
        nodeList.get(i).left = nodeList.get(2 * i + 1);
        if (nodeList.size() % 2 == 1) {
            nodeList.get(i).right = nodeList.get(2 * i + 2);
        }

        return nodeList.get(0);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 9, 20, null, null, 15, 7));
        printTree(buildTree(list));
    }


}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
