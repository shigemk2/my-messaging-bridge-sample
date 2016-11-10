package com.example

import akka.actor._
import akka.event.{Logging, LoggingAdapter}

class MessageBridgeException(message: String, cause: Throwable, retry: Boolean)
  extends RuntimeException(message, cause) {

  def this(message: String, cause: Throwable) =
    this(message, cause, false)

  def retry(): Boolean = retry
}

object RabbitMQMessagingBridgeRunner extends CompletableApp(1)

class InventoryProductAllocationBridge(config: RabbitMQBridgeConfig)
  extends RabbitMQBridgeActor(config) {
  private val log: LoggingAdapter = Logging.getLogger(context.system, self)

  def receive = {
    case message: RabbitMQBinaryMessage =>
      log.error("Binary messages not supported.")
    case message: RabbitMQTextMessage =>
      log.error(s"Received text message: ${message.textMessage}")
    case invalid: Any =>
      log.error(s"Don't understand message: $invalid")
  }
}