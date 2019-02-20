package com.gu.microserviceWorkshop

import java.io._
import java.nio.charset.StandardCharsets.UTF_8

object Lambda {

  def isPrime(number: Int): Boolean = {
    if (number <= 1) {
      false
    } else if (number == 2) {
      true
    } else {
      !(2 until number).exists(x => number % x == 0)
    }
  }

  def handler(in: InputStream, out: OutputStream): Unit = {
    val jsonPayload = scala.io.Source.fromInputStream(in).mkString("")
    val result = if (isPrime(jsonPayload.toInt)) "this is a prime number" else "this is not a prime number"

    out.write(result.getBytes(UTF_8))
  }

}
