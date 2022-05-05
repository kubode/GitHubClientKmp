package com.github.kubode.github.shared.feature.search

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}