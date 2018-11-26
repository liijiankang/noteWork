package com.ljk

import scala.util.Random
import scala.math.BigInt.probablePrime

object TestScala {
  def main(args: Array[String]): Unit = {
    print(probablePrime(3,Random))
  }
}
