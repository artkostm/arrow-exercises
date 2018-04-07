package com.artkostm.arrow

import arrow.data.Try
import arrow.data.ev
import arrow.data.monad
import arrow.typeclasses.binding

fun armT(): Try<Nuke> =
        Try { throw RuntimeException("SystemOffline") }

fun aimT(): Try<Target> =
        Try { throw RuntimeException("RotationNeedsOil") }

fun launchT(target: Target, nuke: Nuke): Try<Impacted> =
        Try { throw RuntimeException("MissedByMeters") }

fun attackTry(): Try<Impacted> =
        Try.monad().binding {
            val nuke = armT().bind()
            val target = aimT().bind()
            val impact = launchT(target, nuke).bind()
            impact
        }.ev()

fun main(args: Array<String>) {
    println(armT())
    println(aimT())

    val result = armT()
    println(result.fold({ex -> "BOOM!: $ex"}, {"Got: $it"}))

    println(attackTry())
}