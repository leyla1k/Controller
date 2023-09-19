package com.example.controller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.controller.databinding.ActivityMainBinding
import com.example.controller.entities.Path
import com.example.controller.entities.WorkShift
import com.example.controller.ui.workShiftPac.dialogs.DialogListener
import com.example.controller.ui.workShiftPac.workShiftStarted.WorkShiftStartedFragmentDirections
import com.example.controller.ui.workShiftPac.workShiftStarted.WorkShiftStartedViewModel
import com.example.controller.ui.workShiftPac.workShiftStarted.WorkShiftStartedViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class MainActivity : AppCompatActivity(), DialogListener {

    companion object {
        private val TAG = MainActivity::class.java.getSimpleName()
    }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    val viewModel: WorkShiftStartedViewModel by viewModels { WorkShiftStartedViewModelFactory((this.application as App).pathsRepository) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_nfc, R.id.navigation_work_shift, R.id.navigation_notifications
            )
        )
        // setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun onFinishNewPathAlertDialog(dataGovNumber: String, dataPathNumber: String) {
        Toast.makeText(this, "Hey super!", Toast.LENGTH_SHORT).show()

        val res = lifecycleScope.async(Dispatchers.IO) {
             viewModel.getLastWork()
        }
        lifecycleScope.launch {
            viewModel.addNewPath(
                Path(
                    0,
                    workId = res.await()!!.id,
                    govNumber = dataGovNumber,
                    pathNumber = dataPathNumber,
                    startDate = Date(),
                    endDate = null
                )
            )
        }
    }

    override fun onFinishWorkShiftClosureAlertDialog() {
        //update смены и переход на предыдущий фрагмент
        lifecycleScope.launch {
            viewModel.closeWorkShift()
        }
        findNavController(R.id.nav_host_fragment_activity_main).navigate(
            WorkShiftStartedFragmentDirections.actionWorkShiftStartedFragmentToNavigationWorkShift()
        )

    }


}








