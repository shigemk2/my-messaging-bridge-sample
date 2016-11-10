package com.example

import akka.actor._

class MessageBridgeException(message: String, cause: Throwable, retry: Boolean)
  extends RuntimeException(message, cause) {

  def this(message: String, cause: Throwable) =
    this(message, cause, false)

  def retry(): Boolean = retry
}

object RabbitMQMessagingBridgeRunner extends CompletableApp(1)