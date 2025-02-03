package com.plcoding.bookpedia.utils

expect fun getPlatformType(): PlatformType

enum class PlatformType {
    ANDROID,
    IOS,
    DESKTOP
}