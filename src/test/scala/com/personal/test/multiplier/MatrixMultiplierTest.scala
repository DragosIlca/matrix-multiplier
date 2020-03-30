package com.personal.test.multiplier

import java.util.concurrent.Executors

import com.personal.multiplier.utils.{Calculator, MatrixGenerator}
import org.scalatest.funsuite.AsyncFunSuite
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.{ExecutionContext, Future}

class MatrixMultiplierTest extends AsyncFunSuite {
  val logger: Logger = LoggerFactory.getLogger(classOf[MatrixMultiplierTest])

  test("1000 x 500 matrices test") {
    val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

    val matrix1 = MatrixGenerator.generate(500, 1000, 10)
    val matrix2 = MatrixGenerator.generate(1000, 500, 10)

    for {
      beforeTime <- Future.successful(System.currentTimeMillis())
      _          <- Calculator.multiplyMatrices(matrix1, matrix2)(ec)
      afterTime  <- Future.successful(System.currentTimeMillis())
      _          <- Future.successful(logger.debug(s"Computation lasted: ${afterTime - beforeTime} milliseconds"))
    } yield succeed
  }
}
