package com.example.kursovikcmp.feature.Device

expect class DeviceService {
    fun isAndroid(): Boolean
    fun isIOS(): Boolean

    fun openUrl(urlString: String)
}