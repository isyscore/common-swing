@file:Suppress("unused")
package com.isyscore.kotlin.swing.exception

class ResourceNotFoundException: Exception {
    constructor(): super()
    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable?): super(message, cause)
    constructor(cause: Throwable?): super(cause)
}