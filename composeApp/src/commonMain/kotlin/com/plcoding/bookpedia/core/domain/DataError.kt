package com.plcoding.bookpedia.core.domain

sealed interface DataError: Error {
    enum class RemoteError : DataError {
        REQUEST_TIMEOUT,
        SERVER,
        UNKNOWN,
        SERIALIZATION,
        NO_INTERNET,
        TOO_MANY_REQUESTS
    }

    enum class LocalError : DataError {
        DISK_FULL,
        UNKNOWN
    }
}