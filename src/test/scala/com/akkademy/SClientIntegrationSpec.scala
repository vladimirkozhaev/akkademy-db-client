package com.akkademy

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import org.scalatest.FunSpecLike
import org.scalatest.Matchers



class SClientIntegrationSpec extends FunSpecLike with Matchers{
  val client = new SClient("192.168.56.1:2552")
  describe("akkademyDb Scala Client") {
        describe("set method") {
            it("should set a value") {
                client.set("123", new Integer(123))
                val futureResult = client.get("123")
                val result = Await.result(futureResult, 60 seconds)
                result should equal(123)
            }
        }
  }
}