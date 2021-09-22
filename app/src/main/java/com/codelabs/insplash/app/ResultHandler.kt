package com.codelabs.insplash.app

import com.codelabs.insplash.app.api.UnsplashApiErrorParser
import com.codelabs.insplash.app.states.RepositoryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.HttpURLConnection

suspend fun <T> repositoryResultHandler(callback: suspend () -> RepositoryState.Success<T>): RepositoryState<T> {
    return try {
        callback.invoke()
    } catch (e: CustomException.Server) {
        when (e.code) {
            HttpURLConnection.HTTP_FORBIDDEN ->
                RepositoryState.Failure(ErrorType.ApiRateLimitExceeded)
            else ->
                RepositoryState.Failure(ErrorType.Failure(
                    e.apiError?.errors?.joinToString(separator = "\n")))
        }
    } catch (e: CustomException) {
        RepositoryState.Failure(ErrorType.Failure(e.message))
    }
}

suspend fun <T> remoteResultHandler(callback: suspend () -> T): T {
    return try {
        callback.invoke()
    } catch (e: HttpException) {
        when (e.code()) {
            HttpURLConnection.HTTP_FORBIDDEN ->
                throw CustomException.Server(e.code())
            else ->
                throw CustomException.Server(e.code(), UnsplashApiErrorParser.parse(e.response()!!))
        }
    } catch (e: Exception) {
        throw CustomException(message = e.message)
    }
}