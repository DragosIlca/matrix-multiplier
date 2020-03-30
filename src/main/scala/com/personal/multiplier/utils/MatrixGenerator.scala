package com.personal.multiplier.utils

import scala.util.Random

object MatrixGenerator {
  val random = new Random()
  def generate(lines: Int, columns: Int, max: Int): Array[Array[Int]] = {
    (1 to lines).map(_ => (1 to columns).map(_ => random.nextInt(max)).toArray).toArray
  }
}
