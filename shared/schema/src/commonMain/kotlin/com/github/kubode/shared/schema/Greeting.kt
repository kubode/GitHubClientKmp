package com.github.kubode.shared.schema

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}