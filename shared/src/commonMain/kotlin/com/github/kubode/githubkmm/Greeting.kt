package com.github.kubode.githubkmm

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}