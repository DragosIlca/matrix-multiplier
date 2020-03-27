package com.personal.multiplier.utils

object Calculator {
  def multiplyMatrices(matrix1: Array[Array[Int]], matrix2 : Array[Array[Int]]): Array[Int] = {
    matrix1.indices.map(lineIndex =>
      multiplyLines(matrix1(lineIndex), matrix2.map(_(lineIndex)))
    ).toArray
  }

  def multiplyLines(line: Array[Int], column: Array[Int]): Int = {
    line.indices.foldLeft(0)((result, index) => result + line(index) * column(index))
  }
}
