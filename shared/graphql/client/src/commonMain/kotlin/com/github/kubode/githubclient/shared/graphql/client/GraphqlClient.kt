package com.github.kubode.githubclient.shared.graphql.client

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain
import com.apollographql.apollo3.network.http.HttpNetworkTransport
import com.apollographql.apollo3.network.http.LoggingInterceptor

private class AuthorizationInterceptor : HttpInterceptor {
    override suspend fun intercept(
        request: HttpRequest,
        chain: HttpInterceptorChain
    ): HttpResponse {
        return chain.proceed(
            request.newBuilder()
                .addHeader("Authorization", "bearer $GITHUB_TOKEN")
                .build()
        )
    }
}

val apolloClient: ApolloClient = ApolloClient.Builder()
    .networkTransport(
        HttpNetworkTransport.Builder()
            .serverUrl("https://api.github.com/graphql")
            .addInterceptor(AuthorizationInterceptor())
            .addInterceptor(LoggingInterceptor())
            .build()
    )
    .build()
