package com.placeholder.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _133CloneGraph {
    private Map<UndirectedGraphNode, UndirectedGraphNode> clonedMap = new HashMap<>();

    public static void main(String[] args) {
        UndirectedGraphNode node0 = new UndirectedGraphNode(0);
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        node0.neighbors = new ArrayList<>();
        node0.neighbors.add(node1);
        node0.neighbors.add(node2);

        node1.neighbors = new ArrayList<>();
        node1.neighbors.add(node0);
        node1.neighbors.add(node2);

        node2.neighbors = new ArrayList<>();
        node2.neighbors.add(node0);
        node2.neighbors.add(node1);
        node2.neighbors.add(node2);

        UndirectedGraphNode clonedGraph = new _133CloneGraph().cloneGraph(node0);
        return;
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        return innerCloneGraph(node);
    }

    public UndirectedGraphNode innerCloneGraph(UndirectedGraphNode node) {
        if (clonedMap.containsKey(node)) {
            return clonedMap.get(node);
        }
        UndirectedGraphNode clonedNode = new UndirectedGraphNode(node.label);
        clonedMap.put(node, clonedNode);
        clonedNode.neighbors = new ArrayList<>();
        for (UndirectedGraphNode neighbor : node.neighbors) {
            clonedNode.neighbors.add(cloneGraph(neighbor));
        }
        return clonedNode;
    }

    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }
}
