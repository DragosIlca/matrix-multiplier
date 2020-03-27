package com.personal.test.multiplier

import com.personal.multiplier.utils.Calculator
import org.scalatest.funsuite.AsyncFunSuite
import org.slf4j.{Logger, LoggerFactory}

class CalculatorTest extends AsyncFunSuite {
  val logger: Logger = LoggerFactory.getLogger(classOf[CalculatorTest])

  test("Multiply equal length arrays") {
    val result = Calculator.multiplyLines(Array(1,2,3), Array(3,6,4))
    assert(result == 27)
  }

  test("Multiply empty arrays") {
    val result = Calculator.multiplyLines(Array(), Array())
    assert(result == 0)
  }
}
