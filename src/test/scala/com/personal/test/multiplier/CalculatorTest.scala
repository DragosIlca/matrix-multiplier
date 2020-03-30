package com.personal.test.multiplier

import com.personal.multiplier.utils.Calculator
import org.scalatest.funsuite.AsyncFunSuite
import org.slf4j.{Logger, LoggerFactory}

class CalculatorTest extends AsyncFunSuite {
  val logger: Logger = LoggerFactory.getLogger(classOf[CalculatorTest])

  test("Multiply equal length Lists") {
    val result = Calculator.multiplyLines(List(1,2,3), List(3,6,4))
    assert(result == 27)
  }

  test("Multiply empty Lists") {
    val result = Calculator.multiplyLines(List(), List())
    assert(result == 0)
  }
}
