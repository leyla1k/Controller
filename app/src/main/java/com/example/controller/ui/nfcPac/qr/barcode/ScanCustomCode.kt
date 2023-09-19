package com.example.controller.ui.nfcPac.qr.barcode

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.controller.ui.nfcPac.qr.QRResult
import com.example.controller.ui.nfcPac.qr.QRScannerActivity
import com.example.controller.ui.nfcPac.qr.QRScannerActivity.Companion.EXTRA_CONFIG
import com.example.controller.ui.nfcPac.qr.QRScannerActivity.Companion.RESULT_ERROR
import com.example.controller.ui.nfcPac.qr.QRScannerActivity.Companion.RESULT_MISSING_PERMISSION
import com.example.controller.ui.nfcPac.qr.config.ScannerConfig
import com.example.controller.ui.nfcPac.qr.extensions.getRootException
import com.example.controller.ui.nfcPac.qr.extensions.toQuickieContentType
import com.example.controller.ui.nfcPac.qr.extensions.toParcelableConfig

public class ScanCustomCode : ActivityResultContract<ScannerConfig, QRResult>() {

    override fun createIntent(context: Context, input: ScannerConfig): Intent {
        return Intent(context, QRScannerActivity::class.java).apply {
            putExtra(EXTRA_CONFIG, input.toParcelableConfig())
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): QRResult {
        return when (resultCode) {
            Activity.RESULT_OK -> QRResult.QRSuccess(intent.toQuickieContentType())
            Activity.RESULT_CANCELED -> QRResult.QRUserCanceled
            RESULT_MISSING_PERMISSION -> QRResult.QRMissingPermission
            RESULT_ERROR -> QRResult.QRError(intent.getRootException())
            else -> QRResult.QRError(IllegalStateException("Unknown activity result code $resultCode"))
        }
    }
}
