package com.artkostm.arrow

import arrow.typeclasses.*
import arrow.effects.*

ForIO extensions {
  binding {
    val file = getFile("/tmp/file.txt").bind()
    val lines = file.readLines().bind()
    val average =
      if (lines.isEmpty()) {
        0
      } else {
        val count = lines.map { it.length }.foldLeft(0) { acc, lineLength -> acc + lineLength }
        count / lines.length
      }
    average
  }
  .fix()
  .attempt()
  .unsafeRunSync()
}
