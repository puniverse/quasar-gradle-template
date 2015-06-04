/**
 * Increasing-Echo Quasar-Kotlin Example

 * @author circlespainter
 */

package testgrp

import java.util.concurrent.ExecutionException

import co.paralleluniverse.strands.*
import co.paralleluniverse.strands.channels.*

import co.paralleluniverse.fibers.*

fun doAll(): Int? {
    val increasingToEcho = Channels.newIntChannel(0) // Synchronizing channel (buffer = 0)
    val echoToIncreasing = Channels.newIntChannel(0) // Synchronizing channel (buffer = 0)

    val increasing = Fiber(SuspendableCallable(@Suspendable fun(): Int {
        ////// The following is enough to test instrumentation of synchronizing methods
        // synchronized(new Object()) {}

        var curr = 0
        for (i in 0..9) {
            Fiber.sleep(1000)
            println("INCREASER sending: " + curr)
            increasingToEcho.send(curr)
            curr = echoToIncreasing.receive()
            println("INCREASER received: " + curr)
            curr++
            println("INCREASER now: " + curr)
        }
        println("INCREASER closing channel and exiting")
        increasingToEcho.close()
        return curr;
    })).start()

    val echo = Fiber(SuspendableCallable(@Suspendable fun(): Void? {
        val curr: Int?
        while (true) {
            Fiber.sleep(1000)
            curr = increasingToEcho.receive()
            println("ECHO received: " + curr)

            if (curr != null) {
                println("ECHO sending: " + curr)
                echoToIncreasing.send(curr)
            } else {
                println("ECHO detected closed channel, closing and exiting")
                echoToIncreasing.close()
                return null;
            }
        }
    })).start()

    increasing.join()
    echo.join()

    return increasing.get()
}

public fun main(args: Array<String>) {
    doAll()
}
