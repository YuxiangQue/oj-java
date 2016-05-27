package com.placeholder.hihoCoder.weekly

import scala.collection.mutable

/**
  * http://hihocoder.com/contest/hiho99/problem/1
  */
object _99Knight {
    def main(args: Array[String]): Unit = {
        var all = for {
            x <- 0 until 8
            y <- 0 until 8
            step0 = bfs((1, 1), (x, y))
            step1 = bfs((3, 2), (x, y))
            step2 = bfs((5, 3), (x, y))
        } yield (x, y, step0 + step1 + step2)
        all = all.sorted(new Ordering[(Int, Int, Int)] {
            override def compare(x: (Int, Int, Int), y: (Int, Int, Int)): Int = {
                val (_, _, step1) = x
                val (_, _, step2) = y
                return step1 - step2
            }
        })
        println(s"${all}")
    }

    def bfs(source: (Int, Int), destination: (Int, Int)): Int = {
        val depthQueen = new mutable.Queue[Int]()
        val queue = new mutable.Queue[(Int, Int)]()
        val visited = new mutable.HashSet[(Int, Int)]()
        queue.enqueue(source)
        depthQueen.enqueue(0)
        while (queue.nonEmpty) {
            val current = queue.dequeue()
            val depth = depthQueen.dequeue()
            visited.add(current)
            if (current == destination) {
                return depth
            }
            move(current)
                .filter(next => !visited.contains(next))
                .foreach(next => {
                    queue.enqueue(next)
                    depthQueen.enqueue(depth + 1)
                })
        }
        return -1
    }

    def move(current: (Int, Int)): List[(Int, Int)] = {
        val (x0, y0) = current
        List((x0 - 2, y0 - 1), (x0 - 2, y0 + 1),
            (x0 - 1, y0 + 2), (x0 + 1, y0 + 2),
            (x0 + 2, y0 - 1), (x0 + 2, y0 + 1),
            (x0 - 1, y0 - 2), (x0 + 1, y0 - 2))
            .filter {
                case (x, y) => if (x < 0 || x >= 8 || y < 0 || y >= 8) false else true
            }
    }
}
