package com.personal.test.multiplier

import com.personal.multiplier.utils.{Calculator, MatrixGenerator}
import org.scalatest.funsuite.AsyncFunSuite
import org.slf4j.{Logger, LoggerFactory}

class MatrixMultiplierTest extends AsyncFunSuite {
  val logger: Logger = LoggerFactory.getLogger(classOf[MatrixMultiplierTest])

  test("10000x10000 matrices test") {
    val matrix1 = MatrixGenerator.generate(10000, 10000, 10)
    val matrix2 = MatrixGenerator.generate(10000, 10000, 10)

    val beforeTime = System.currentTimeMillis()
    Calculator.multiplyMatrices(matrix1, matrix2)
    val afterTime = System.currentTimeMillis()

    logger.debug(s"Computation lasted: ${afterTime - beforeTime} milliseconds")
    succeed
  }
}
