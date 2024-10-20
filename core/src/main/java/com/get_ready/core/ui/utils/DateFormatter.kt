package com.get_ready.core.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateFormatter {
    // Format for "EEEE, MMMM dd"
    fun formatDate(timestamp: Long): String {
        val date = Date(timestamp*1000) // Convert seconds to milliseconds
        val format = SimpleDateFormat("EEEE, MMMM d", Locale.getDefault())
        return format.format(date)
    }
}
