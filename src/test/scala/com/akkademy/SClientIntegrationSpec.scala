package com.akkademy

import org.scalatest.concurrent.PatienceConfiguration.Timeout
import org.scalatest.{FunSpecLike, Matchers}
import org.scalatest.concurrent.ScalaFutures
import scala.concurrent.duration._

class SClientIntegrationSpec extends FunSpecLike with Matchers with ScalaFutures {
  val client = new SClient("127.0.0.1:2552")
  describe("akkademyDb Scala Client") {
    describe("set method") {
      it("should set a value") {
        whenReady(client.set("123", "123"), Timeout(1 second)) {
          r =>
            whenReady(client.get("123")) {
              f =>
                f shouldBe "123"
            }
        }
      }
    }
  }
}