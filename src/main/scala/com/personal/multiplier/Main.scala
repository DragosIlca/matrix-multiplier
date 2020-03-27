package com.personal.multiplier

import com.personal.multiplier.utils.Calculator
import org.slf4j.{Logger, LoggerFactory}

import scala.util.Try

object Main {
  val logger: Logger = LoggerFactory.getLogger(Main.getClass)

  def main(args: Array[String]): Unit = {
    val (matrix1, matrix2) = readInputFile(args(0))
    validateInputMatrices(matrix1, matrix2)

    val result = multiplyMatrices(matrix1, matrix2)
    logger.info(s"The result of multiplication is: ${result.toList}")
  }

  private def multiplyMatrices(matrix1: Array[Array[Int]], matrix2 : Array[Array[Int]]): Array[Int] = {
    matrix1.indices.map(lineIndex =>
      Calculator.multiplyLines(matrix1(lineIndex), matrix2.map(_(lineIndex)))
    ).toArray
  }

  private def readInputFile(path: String): (Array[Array[Int]], Array[Array[Int]]) = {
    Try {
      val bufferedSource = io.Source.fromResource(path)
      logger.debug(s"readInputFile() - Successfully read file: $path")
      val inputNumbers = bufferedSource.getLines().map { line =>
        if (line.contains(',')) {
          val columns = line.split(',')
          columns.map(_.toInt)
        }
        else
          Array.empty[Int]
      }.filter(_.nonEmpty).toArray

      val (matrix1, matrix2) = inputNumbers.splitAt(inputNumbers.head.length - 1)

      (matrix1, matrix2)
    }.fold(
      e => {
        logger.error(s"readInputFile() - Could not parse file $path: " + e)
        System.exit(-1)
        null
      },
      v => {
        logger.debug(s"readInputFile() - Returning matrices: ${v._1.map(_.toList).toList} & ${v._2.map(_.toList).toList}")
        v
      }
    )
  }

  private def validateInputMatrices(matrix1: Array[Array[Int]], matrix2 : Array[Array[Int]]): Unit = {
    if (!matrix1.forall(_.length == matrix1.head.length)) {
      logger.error(s"Invalid first matrix")
      System.exit(-2)
    }
    if (!matrix2.forall(_.length == matrix2.head.length)) {
      logger.error(s"Invalid second matrix")
      System.exit(-2)
    }
    if (matrix1.head.length != matrix2.length) {
      logger.error(s"First matrix's number of columns has to be equal with the second's number of lines")
      System.exit(-3)
    }
  }
}
