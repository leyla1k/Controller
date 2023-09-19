package com.example.controller.ui.nfcPac.qr.extensions

import com.example.controller.ui.nfcPac.qr.config.ParcelableScannerConfig
import com.example.controller.ui.nfcPac.qr.config.ScannerConfig


internal fun ScannerConfig.toParcelableConfig() =
    ParcelableScannerConfig(
        formats = formats,
        stringRes = stringRes,
        drawableRes = drawableRes,
        hapticFeedback = hapticFeedback,
        showTorchToggle = showTorchToggle,
        horizontalFrameRatio = horizontalFrameRatio,
        useFrontCamera = useFrontCamera,
        showCloseButton = showCloseButton,
    )
