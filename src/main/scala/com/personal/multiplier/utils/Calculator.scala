package com.personal.multiplier.utils

import scala.concurrent.{ExecutionContext, Future}

object Calculator {
  def multiplyMatrices(
    matrix1: List[List[Int]],
    matrix2: List[List[Int]]
  )(implicit executionContext: ExecutionContext): Future[List[List[Int]]] = {

    val matrix2Columns = matrix2.head.indices.map(columnIndex => {
      matrix2.map(_(columnIndex))
    }).toList

    Future.traverse(matrix1) (line => {
      Future.traverse(matrix2Columns)(column =>
        Future(multiplyLines(line, column))
      )
    })
  }

  def multiplyLines(line: List[Int], column: List[Int]): Int = {
    line.indices.foldLeft(0)((result, index) => result + line(index) * column(index))
  }
}
