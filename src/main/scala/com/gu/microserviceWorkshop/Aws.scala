package com.gu.microserviceWorkshop

import com.amazonaws.auth.{AWSCredentialsProviderChain, InstanceProfileCredentialsProvider}
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}
import com.amazonaws.services.s3.model.GetObjectRequest

import scala.io.Source
import scala.util.Try

object Aws {

  val ProfileName = "developerPlayground"

  lazy val CredentialsProvider = new AWSCredentialsProviderChain(
    new ProfileCredentialsProvider(ProfileName),
    new InstanceProfileCredentialsProvider(false)
  )

}

object AwsS3 {

  implicit val client: AmazonS3 =
    AmazonS3ClientBuilder
      .standard()
      .withRegion(Regions.EU_WEST_1)
      .withCredentials(Aws.CredentialsProvider)
      .build()

}

object AwsS3Get {

  def apply(bucket: String, key: String): Try[String] = {
    val request = new GetObjectRequest(bucket, key)
    for {
      s3Stream <- Try(AwsS3.client.getObject(request).getObjectContent)
      contentString <- Try(Source.fromInputStream(s3Stream).mkString)
      _ <- Try(s3Stream.close())
    } yield contentString
  }

}
