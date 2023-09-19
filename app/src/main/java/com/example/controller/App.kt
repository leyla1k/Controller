package com.example.controller

import android.app.Application
import android.os.StrictMode
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import com.example.controller.localbase.MainDB
import com.example.controller.localbase.CardsRepository
import com.example.controller.localbase.PassPhrase
import com.example.controller.localbase.PathsRepository
import com.google.android.datatransport.runtime.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import net.sqlcipher.database.SupportFactory


const val TAG="AppClass"
class App: Application() {


    var prefDataStore = createDataStore(
    name = "work_data_store",
    corruptionHandler = null,
    migrations = emptyList(),
    scope = CoroutineScope(Dispatchers.IO + Job())
    )

    private val cardsDatabase by lazy { MainDB.getDatabase(applicationContext, SupportFactory(
        PassPhrase(applicationContext).getPassphrase())) }


    val cardsRepository by lazy{
        CardsRepository(cardsDatabase.getDao())
    }.also {  Log.d(TAG, " again!") }

    val pathsRepository by lazy{
        PathsRepository(cardsDatabase.getDao())
    }.also {  Log.d(TAG, " again!") }

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }

        super.onCreate()
    }


}