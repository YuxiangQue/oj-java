
# 链表

## 快慢指针

## 反转链表

_92ReverseLinkedList2
_206ReverseLinkedList

## 合并两个排序链表
_23MergeKSortedLists

## 有环链表
_141LinkedListCycle 判断有环

_142LinkedListCycle2 环入口
https://leetcode.com/problems/linked-list-cycle-ii/
    
    A和B分别为慢速和快速指针
    设M和N分别为公共路径的长度和环的长度
    相遇时，2xA-M-N = xA-M
    则有，xA =N
    此时A在圆环上的位置为，xA-M = N-M
    设B为起点，A和B同速启动，则相遇点为环交点

## 链表排序

### 插入排序
_147InsertionSortList

    链表排序 divide and conque
    一分为二，分别排序，合并，递归

### 桶排序


## 复制随机链表
_138CopyListWithRandomPointer

- http://www.cnblogs.com/easonliu/p/3647160.html

## 链表与树的转换
_109ConvertSortedListToBST
    
    深度优先搜索
    找出当前链表的中间节点，
    然后再递归左右的子链表，
    开始的时候程序先计算链表总厂，
    然后传入两个前后索引指针，
    最后每次递归找出中间节点即可。
    
## 链表调整顺序
_143ReorderList 
https://leetcode.com/problems/reorder-list/

    Divide list into two parts
    Reverse the part two
    Merge part one and reversed part two
    
## 拆分链表
_86PartitionList
https://leetcode.com/problems/partition-list/
   