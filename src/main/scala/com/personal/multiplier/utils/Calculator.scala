package com.personal.multiplier.utils

object Calculator {
  def multiplyLines(line: Array[Int], column: Array[Int]): Int = {
    line.indices.foldLeft(0)((result, index) => result + line(index) * column(index))
  }
}
