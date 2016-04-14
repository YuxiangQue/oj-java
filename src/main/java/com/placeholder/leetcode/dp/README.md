## 背包问题
http://www.cnblogs.com/SeaSky0606/p/4743758.html



## LIS 最长递增序列
一维
 - _300LongestIncreasingSubsequence
 - _334IncreasingTripletSubsequence

设dp[i]表示以i为结尾的最长递增子序列的长度
状态转移方程为 dp[i] = max{dp[j]+1}, 1<=j<i,a[j]<a[i]


## 小偷问题
一维
 - _198HouseRobber
 
 设dp[i]表示到第i家偷到的总金钱数_
 dp[i] = max(dp[i-2]+a[i], dp[i-1])
 
 - _213HouseRobber2
 0与n-1家不能同时偷取，所以分两种情况，求其大者
  [0..n-2]
  [1..n-1]
 
 
## 一维

 - _152MaximumProductSubarray
min维持目前最小的乘积
max维持目前最大的乘积
max = max(max*a[i], min*a[i], a[i])
min = min(max*a[i], min*a[i], a[i])


## 二维
_221MaximalSquare
_84LargestRectangleInHistogram
_85MaximalRectangle


## 积分图像
- _303RangeSumQueryImmutable
- _304RangeSumQuery2D