package com.example.controller.ui.nfcPac.qr

import com.example.controller.ui.nfcPac.qr.content.QRContent

public sealed class QRResult {


    public data class QRSuccess internal constructor(val content: QRContent) : QRResult()



    public object QRUserCanceled : QRResult()



    public object QRMissingPermission : QRResult()



    public data class QRError internal constructor(val exception: Exception) : QRResult()
}
