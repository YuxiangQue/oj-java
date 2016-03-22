# 树

## 对称树
_101SymmetricTree

## 遍历
_102BinaryTreeLevelOrderTraversal

## 迭代器
_173BinaryTreeIterator
二叉树迭代器算法
http://coolshell.cn/articles/9886.html

## 树的路径
_112PathSum
_113PathSum2

## 公共祖先
_235LowestCommonAncestorOfABinarySearchTree
_236LowestCommonAncestorOfABinaryTree

## 唯一BST
_95UniqueBinarySearchTree2
    
    // 0 n-1
    // 1 n-2
    // 2 n-3
    // ...
    // n-1 0
    // T(j) = T(0)*T(n-1) + ... + T(n-1)*T(0)

_96UniqueBinarySearchTree
https://leetcode.com/discuss/10254/a-simple-recursive-solution