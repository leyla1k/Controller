package com.example.controller.ui.workShiftPac

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.controller.App
import com.example.controller.databinding.FragmentWorkShiftBinding
import com.example.controller.databinding.FragmentWorkShiftStartedBinding
import com.example.controller.ui.workShiftPac.workShiftStarted.WorkShiftStartedViewModel
import com.example.controller.ui.workShiftPac.workShiftStarted.WorkShiftStartedViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WorkShiftFragment : Fragment() {


    private var _binding: FragmentWorkShiftBinding? = null
    private val binding get() = _binding!!
    var isStarted: Boolean? = false

    object UserScheme {
        val FIELD_CHECK_NEW_WORK_SHIFT = preferencesKey<Boolean>("is_started")
    }

    val viewModel: WorkShiftViewModel by viewModels { WorkShiftViewModelFactory((requireActivity().application as App).pathsRepository) }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWorkShiftBinding.inflate(inflater, container, false)



        viewLifecycleOwner.lifecycleScope.launch {
            (requireActivity().application as App).prefDataStore!!.data
                .collect { pref: Preferences ->
                    isStarted = pref[UserScheme.FIELD_CHECK_NEW_WORK_SHIFT]
                    Log.d("onViewCreated: ", "onViewCreated: " + isStarted.toString())
                    Log.d(
                        "onViewCreated: ",
                        "onViewCreatedPref: " + pref[UserScheme.FIELD_CHECK_NEW_WORK_SHIFT].toString()
                    )
                }

        }


        binding.btStartWorkShift.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                (requireActivity().application as App).prefDataStore!!.edit { prefs ->
                    prefs[UserScheme.FIELD_CHECK_NEW_WORK_SHIFT] = true
                }
                viewModel.createNewWorkShift()
                isStarted = true
            }
            findNavController().navigate(
                WorkShiftFragmentDirections.actionNavigationWorkShiftToWorkShiftStartedFragment()
            )
        }
        if (isStarted==false || isStarted==null) {
            binding.btStartWorkShift.isEnabled = true
        }
        return binding.root
    }


    override fun onResume() {
        Log.d("onViewCreated: ", "onResume: " + isStarted.toString())
        if (isStarted == true) {
            findNavController().navigate(
                WorkShiftFragmentDirections.actionNavigationWorkShiftToWorkShiftStartedFragment()
            )
        }
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}