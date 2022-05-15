package com.github.kubode.githubclient.shared.feature.search

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
