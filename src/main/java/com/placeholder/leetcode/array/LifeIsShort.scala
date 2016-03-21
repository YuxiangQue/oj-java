package array

import com.placeholder.leetcode.array._118PascalsTriangle

/**
  * @author 阙宇翔
  * @version 2016/2/19
  */


object LifeIsShort extends App {

  val pt = new _118PascalsTriangle() with Test {
    override def test(): Unit = {
      println(this.generate(3))
    }
  }

  trait Test {
    def test()
  }

  pt.test()

}