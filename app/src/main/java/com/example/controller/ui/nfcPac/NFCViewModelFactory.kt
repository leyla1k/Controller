package com.example.controller.ui.nfcPac

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.controller.localbase.CardsRepository


class NFCViewModelFactory(val application: Application, val cardsRepository: CardsRepository
): ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return NFCViewModel(application,cardsRepository) as T
    }
}


