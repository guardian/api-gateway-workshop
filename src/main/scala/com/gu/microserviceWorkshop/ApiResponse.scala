package com.gu.microserviceWorkshop

import io.circe.Encoder
import io.circe.generic.extras.semiauto._


case class APIResponse(statusCode: Int, headers: Map[String, String], body: String)

object APIResponse {

  implicit val APIResponseEncoder: Encoder[APIResponse] = deriveEncoder

}

case class IsPrimeResult(number: Double, isPrime: Boolean)

object IsPrimeResult {

  implicit val isPrimeEncoder: Encoder[IsPrimeResult] = deriveEncoder

}








