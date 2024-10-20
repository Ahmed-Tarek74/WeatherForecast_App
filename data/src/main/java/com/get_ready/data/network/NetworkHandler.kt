package com.get_ready.data.network

import com.get_ready.data.customExceptions.DataNotFoundException
import com.get_ready.data.customExceptions.NetworkException
import com.get_ready.data.customExceptions.ServerErrorException
import com.get_ready.data.customExceptions.UnauthorizedException
import com.get_ready.data.customExceptions.UnknownException
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NetworkHandler @Inject constructor() {
    suspend fun <T> handleNetworkCall(call: suspend () -> T): T {
        return try {
            call()
        } catch (e: IOException) {
            throw NetworkException("Network error: Unable to fetch data. Please check your internet connection.")
        } catch (e: HttpException) {
            when (e.code()) {
                401 -> throw UnauthorizedException("Unauthorized: Invalid API key.")
                404 -> throw DataNotFoundException("Requested data not found.")
                else -> throw ServerErrorException("Server error: Something went wrong. Please try again later.")
            }
        } catch (e: Exception) {
            throw UnknownException("An unknown error occurred: ${e.message}")
        }
    }
}