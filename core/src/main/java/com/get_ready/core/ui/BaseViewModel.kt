package com.get_ready.core.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Base ViewModel for common functionality
open class BaseViewModel : ViewModel() {

    // Common CoroutineExceptionHandler for handling exceptions in child view models
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleException(exception)
    }

    // Function to handle exceptions, can be overridden by child view models for custom behavior
    protected open fun handleException(exception: Throwable){
        // Log the error
        Log.e("BaseViewModel", "Error occurred: ${exception.localizedMessage}", exception)

        //Send the error to Crashlytics or other crash reporting services (for more stability improvements )
    }
    protected fun launchOnIODispatcher(
        block: suspend CoroutineScope.() -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            block()
        }
    }
    protected fun launchOnDefaultDispatcher(
        block: suspend CoroutineScope.() -> Unit,
    ) {
        viewModelScope.launch(coroutineExceptionHandler) {
            block()
        }
    }
    // Switches context to the IO thread
    protected suspend fun <T> withIOContext(block: suspend () -> T): T {
        return withContext(Dispatchers.IO) { block() }
    }
    // Switches context to the Main thread
    protected suspend fun <T> withMainContext(block: suspend () -> T): T {
        return withContext(Dispatchers.Main) { block() }
    }
}