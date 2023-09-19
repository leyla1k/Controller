package com.example.controller.ui.nfcPac.qr.extensions

import com.example.controller.ui.nfcPac.qr.QRScannerActivity

internal object MlKitErrorHandler {

    @Suppress("UNUSED_PARAMETER", "FunctionOnlyReturningConstant")
    fun isResolvableError(activity: QRScannerActivity, exception: Exception) = false // always false when bundled
}
