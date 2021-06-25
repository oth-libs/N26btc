package io.n26btc.extensions

import java.text.SimpleDateFormat
import java.util.Calendar

fun Calendar.formatDate(): String = SimpleDateFormat.getDateInstance().format(time)