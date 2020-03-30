package com.personal.multiplier

import java.util.concurrent.Executors

import com.personal.multiplier.utils.Calculator
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._

import scala.util.Try

object Main {
  val logger: Logger = LoggerFactory.getLogger(Main.getClass)

  def main(args: List[String]): Unit = {
    if (args.length != 2) {
      logger.error("Usage: resourceName, numberOfThreads")
      System.exit(1)
    }

    val threads = args(1).toInt

    val (matrix1, matrix2) = readInputFile(args(0))
    validateInputMatrices(matrix1, matrix2)

    implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(threads))

    val futureResult = Calculator.multiplyMatrices(matrix1, matrix2)

    val result = Await.result(futureResult, 50 seconds)

    logger.info(s"The result of multiplication is: ${result.toList}")
  }

  private def readInputFile(path: String): (List[List[Int]], List[List[Int]]) = {
    Try {
      val bufferedSource = io.Source.fromResource(path)
      logger.debug(s"readInputFile() - Successfully read file: $path")
      val inputNumbers = bufferedSource.getLines().map { line =>
        if (line.contains(',')) {
          val columns = line.split(',')
          columns.map(_.toInt).toList
        }
        else
          List.empty[Int]
      }.filter(_.nonEmpty).toList

      val (matrix1, matrix2) = inputNumbers.splitAt(inputNumbers.head.length)

      (matrix1, matrix2)
    }.fold(
      e => {
        logger.error(s"readInputFile() - Could not parse file $path: " + e)
        System.exit(-1)
        null
      },
      v => v
    )
  }

  private def validateInputMatrices(matrix1: List[List[Int]], matrix2 : List[List[Int]]): Unit = {
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
