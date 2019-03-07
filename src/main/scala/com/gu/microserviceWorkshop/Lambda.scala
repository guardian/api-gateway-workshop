package com.gu.microserviceWorkshop

import io.circe.syntax._
import java.io._
import java.nio.charset.StandardCharsets.UTF_8

object Lambda {

  def handler(in: InputStream, out: OutputStream): Unit = {

    val file = AwsS3Get("ss-workshop-bucket", "myfile")

    //  next step: replace IsPrimeResult(13, isPrime = true).asJson.noSpaces with your data that you've just got
    // from S3, this will mean that your api will return the content of the file in ss-workshop-bucket

    val response = APIResponse(200,  Map("Content-Type" -> "application/json"), IsPrimeResult(13, isPrime = true).asJson.noSpaces)

    //no spaces converts json to a string
    out.write(response.asJson.noSpaces.getBytes(UTF_8))

  }

}

