package com.artkostm.arrow

import arrow.effects.*
  
fun main(args: Array<String>) {
    println(1.liftIO().attempt().unsafeRunSync())
}
