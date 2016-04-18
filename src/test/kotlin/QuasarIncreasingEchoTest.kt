/**
 * Increasing-Echo Quasar Kotlin Test

 * @author circlespainter
 */

package testgrp

import org.junit.Assert.*
import org.junit.Test
import org.hamcrest.CoreMatchers.*

class QuasarIncreasingEchoTest {
    @Test fun test() {
        assertThat(doAll(), `is`(10))
    }
}
