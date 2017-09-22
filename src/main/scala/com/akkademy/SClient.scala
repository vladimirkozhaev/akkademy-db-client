package com.akkademy

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import com.akkademy.messages.{GetRequest, SetRequest, RequestReceived}

import scala.concurrent.Future
import scala.concurrent.duration._

class SClient(remoteAddress: String) {
  private implicit val timeout = Timeout(2 seconds)
  private implicit val system = ActorSystem("LocalSystem")
  private val remoteDb = system.actorSelection(s"akka.tcp://akkademy@$remoteAddress/user/akkademy-db")

  def set(key: String, value: String): Future[RequestReceived] = {
    (remoteDb ? SetRequest(key, value)).mapTo[RequestReceived]
  }

  def get(key: String): Future[String] = {
    (remoteDb ? GetRequest(key)).mapTo[String]
  }
}