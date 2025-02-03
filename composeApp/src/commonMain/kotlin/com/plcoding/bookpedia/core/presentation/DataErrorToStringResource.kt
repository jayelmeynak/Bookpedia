package com.plcoding.bookpedia.core.presentation

import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.error_disk_full
import cmp_bookpedia.composeapp.generated.resources.error_no_internet
import cmp_bookpedia.composeapp.generated.resources.error_request_timeout
import cmp_bookpedia.composeapp.generated.resources.error_serialization
import cmp_bookpedia.composeapp.generated.resources.error_too_many_requests
import cmp_bookpedia.composeapp.generated.resources.error_unknown
import com.plcoding.bookpedia.core.domain.DataError

fun DataError.toUiText(): UiText {
    val stringRes = when (this) {
        DataError.RemoteError.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        DataError.RemoteError.SERVER -> Res.string.error_unknown
        DataError.RemoteError.UNKNOWN -> Res.string.error_unknown
        DataError.RemoteError.SERIALIZATION -> Res.string.error_serialization
        DataError.RemoteError.NO_INTERNET -> Res.string.error_no_internet
        DataError.RemoteError.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        DataError.LocalError.DISK_FULL -> Res.string.error_disk_full
        DataError.LocalError.UNKNOWN -> Res.string.error_unknown
    }
    return UiText.StringResourceId(stringRes)
}
