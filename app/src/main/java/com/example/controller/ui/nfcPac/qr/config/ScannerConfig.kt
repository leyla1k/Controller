package com.example.controller.ui.nfcPac.qr.config

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.controller.ui.nfcPac.qr.barcode.BarcodeFormat

@Suppress("LongParameterList")
public class ScannerConfig internal constructor(
    internal val formats: IntArray,
    internal val stringRes: Int,
    internal val drawableRes: Int?,
    internal val hapticFeedback: Boolean,
    internal val showTorchToggle: Boolean,
    internal val horizontalFrameRatio: Float,
    internal val useFrontCamera: Boolean,
    internal val showCloseButton: Boolean,
) {

    public class Builder {
        private var barcodeFormats: List<BarcodeFormat> = listOf(BarcodeFormat.FORMAT_ALL_FORMATS)
        private var overlayStringRes: Int = 0
        private var overlayDrawableRes: Int? = 0
        private var hapticSuccessFeedback: Boolean = true
        private var showTorchToggle: Boolean = false
        private var horizontalFrameRatio: Float = 1f
        private var useFrontCamera: Boolean = false
        private var showCloseButton: Boolean = false




        public fun setBarcodeFormats(formats: List<BarcodeFormat>): Builder = apply { barcodeFormats = formats }




        public fun setOverlayStringRes(@StringRes stringRes: Int): Builder = apply { overlayStringRes = stringRes }



        public fun setOverlayDrawableRes(@DrawableRes drawableRes: Int?): Builder =
            apply { overlayDrawableRes = drawableRes }



        public fun setHorizontalFrameRatio(ratio: Float): Builder = apply { horizontalFrameRatio = ratio }



        public fun setHapticSuccessFeedback(enable: Boolean): Builder = apply { hapticSuccessFeedback = enable }



        public fun setShowTorchToggle(enable: Boolean): Builder = apply { showTorchToggle = enable }



        public fun setUseFrontCamera(enable: Boolean): Builder = apply { useFrontCamera = enable }


        public fun setShowCloseButton(enable: Boolean): Builder = apply { showCloseButton = enable }



        public fun build(): ScannerConfig =
            ScannerConfig(
                barcodeFormats.map { it.value }.toIntArray(),
                overlayStringRes,
                overlayDrawableRes,
                hapticSuccessFeedback,
                showTorchToggle,
                horizontalFrameRatio,
                useFrontCamera,
                showCloseButton,
            )
    }

    public companion object {

        public fun build(func: Builder.() -> Unit): ScannerConfig = Builder().apply { func() }.build()
    }
}
