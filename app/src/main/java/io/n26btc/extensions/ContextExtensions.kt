package io.n26btc.extensions

import android.content.Context
import android.content.Intent
import android.provider.Settings

fun Context.openWifiSettings() {
  startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
}