package com.personal.multiplier.utils

object Calculator {
  def multiplyMatrices(matrix1: Array[Array[Int]], matrix2 : Array[Array[Int]]): Array[Array[Int]] = {
    matrix1.map(line => {
      matrix2.map(column => {
        multiplyLines(line, column)
      })
    })
  }

  def multiplyLines(line: Array[Int], column: Array[Int]): Int = {
    line.indices.foldLeft(0)((result, index) => result + line(index) * column(index))
  }
}
