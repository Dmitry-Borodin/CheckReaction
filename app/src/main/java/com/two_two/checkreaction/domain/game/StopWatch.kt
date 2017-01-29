package com.two_two.checkreaction.domain.game

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
class StopWatch(
        var initialTime: Long = System.currentTimeMillis()
) {

    fun reset() {
        initialTime = System.currentTimeMillis()
    }

    fun getCurrentResult(): Long {
        return  System.currentTimeMillis() - initialTime
    }
}