package com.example.controller.ui.nfcPac.qr.config

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal class ParcelableScannerConfig(
    val formats: IntArray,
    val stringRes: Int,
    val drawableRes: Int?,
    val hapticFeedback: Boolean,
    val showTorchToggle: Boolean,
    val horizontalFrameRatio: Float,
    val useFrontCamera: Boolean,
    val showCloseButton: Boolean,
) : Parcelable
