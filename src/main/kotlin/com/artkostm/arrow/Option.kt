package com.artkostm.arrow

import arrow.core.*
import arrow.typeclasses.binding

object Nuke
object Target
object Impacted

fun armO(): Option<Nuke> = Some(Nuke)
fun aimO(): Option<Target> = Some(Target)
fun launchO(target: Target, nuke: Nuke): Option<Impacted> = Some(Impacted)

fun attackOption(): Option<Impacted> =
        Option.monad().binding {
            val nuke = armO().bind()
            val target = aimO().bind()
            val impact = launchO(target, nuke).bind()
            impact
        }.ev()

fun main(args: Array<String>) {
    println(attackOption())
}
