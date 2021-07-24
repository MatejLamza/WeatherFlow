package com.example.weatherapp.common.state

sealed class State<out T : Any?>

/**
 * Indicates that there is an action running, such as content fetching
 */
object Loading : State<Nothing>()

/**
 * Indicates that the action has resulted in success
 */
data class Done<out T : Any>(val hasData: Boolean? = null) : State<T>()

/**
 * Indicates that the action has failed and contains error message resource ID and optional
 * exception
 */
data class Error<out T : Any>(val throwable: Throwable? = null) : State<T>()
