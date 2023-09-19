package com.example.controller.ui.workShiftPac.workShiftStarted

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.controller.App
import com.example.controller.databinding.FragmentWorkShiftStartedBinding
import com.example.controller.ui.workShiftPac.WorkShiftFragment
import com.example.controller.ui.workShiftPac.WorkShiftFragmentDirections
import com.example.controller.ui.workShiftPac.dialogs.NewPathAlertDialog
import com.example.controller.ui.workShiftPac.dialogs.WorkShiftClosureAlertDialog
import com.example.controller.ui.workShiftPac.rv.PathsItemTouchHelperCallback
import com.example.controller.ui.workShiftPac.rv.PathsRVAdapter
import com.example.controller.ui.workShiftPac.rv.PathsVerticalItemDecorator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class WorkShiftStartedFragment : Fragment() {

    private var _bindingStarted: FragmentWorkShiftStartedBinding? = null
    private val bindingStarted get() = _bindingStarted!!

    val viewModel: WorkShiftStartedViewModel by viewModels { WorkShiftStartedViewModelFactory((requireActivity().application as App).pathsRepository) }
    private val projectAdapter = PathsRVAdapter()
    private val callback: PathsItemTouchHelperCallback =
        PathsItemTouchHelperCallback(projectAdapter)
    private val touchHelper = ItemTouchHelper(callback)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingStarted = FragmentWorkShiftStartedBinding.inflate(inflater, container, false)

        bindingStarted.btNewPath.setOnClickListener {
            val newPathAlertDialog = NewPathAlertDialog()
            val manager = childFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            newPathAlertDialog.show(transaction, "dialog")
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pathListFlow.flowWithLifecycle(
                viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED
            ).collect {
                projectAdapter.submit(it, bindingStarted.rvPaths)
            }
        }
        with(bindingStarted.rvPaths) {
            touchHelper.attachToRecyclerView(this)
            adapter = projectAdapter
            layoutManager = LinearLayoutManager(requireContext())
                .apply {
                    addItemDecoration(
                        PathsVerticalItemDecorator(50)
                    )
                }
        }

        bindingStarted.ibCloseWork.setOnClickListener {
            val newWorkShiftClosureAlertDialog = WorkShiftClosureAlertDialog()
            val manager = childFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            newWorkShiftClosureAlertDialog.show(transaction, "dialog")

            viewLifecycleOwner.lifecycleScope.launch {
                (requireActivity().application as App).prefDataStore!!.edit { prefs ->
                    prefs[WorkShiftFragment.UserScheme.FIELD_CHECK_NEW_WORK_SHIFT] = false
                }

            }
        }

        return bindingStarted.root
    }


}