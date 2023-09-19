package com.example.controller.ui.nfcPac

import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.controller.App
import com.example.controller.databinding.FragmentNfcBinding
import com.example.controller.localbase.CardsRepository
import com.example.controller.ui.nfcPac.qr.barcode.nfc.Coroutines
import com.example.controller.ui.nfcPac.qr.barcode.nfc.NFCManager
import com.example.controller.ui.nfcPac.qr.barcode.nfc.NFCStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.experimental.and


class NFCFragment : Fragment(), CompoundButton.OnCheckedChangeListener, NfcAdapter.ReaderCallback {

    private var _binding: FragmentNfcBinding? = null
    private val binding get() = _binding!!

    companion object {
        private val TAG: String = NFCFragment::class.java.getSimpleName()
        public fun newInstance(): NFCFragment = NFCFragment()
    }


    lateinit var viewModel: NFCViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNfcBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(
            this, NFCViewModelFactory(
                requireActivity().application,
                (requireActivity().application as App).cardsRepository
            )
        )
            .get(NFCViewModel::class.java)

        Coroutines.main(this@NFCFragment) { scope ->
            scope.launch(block = {
                viewModel.observeToast()?.collectLatest(action = { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                })
            })
           /* scope.launch(block = {
                viewModel.observeTag()?.collectLatest(action = { tag ->
                    _binding?.textView?.setText(tag)
                })
            })*/
        }

        binding.toggleButton?.setOnCheckedChangeListener(this@NFCFragment)
        Coroutines.main(this@NFCFragment) { scope ->
            scope.launch(block = {
                viewModel.observeNFCStatus()?.collectLatest(action = { status ->
                    Log.d(
                        NFCFragment.TAG, "observeNFCStatus $status"
                    )
                    if (status == NFCStatus.NoOperation) NFCManager.disableReaderMode(
                        requireContext(),
                        requireActivity()
                    )
                    else if (status == NFCStatus.Tap) NFCManager.enableReaderMode(
                        requireContext(),
                        requireActivity(),
                        this@NFCFragment,
                        viewModel.getNFCFlags(),
                        viewModel.getExtras()
                    )
                })
            })
            scope.launch(block = {
                viewModel.observeToast()?.collectLatest(action = { message ->
                    Log.d(
                        TAG, "observeToast $message"
                    )
                    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                })
            })
           /* scope.launch(block = {
                viewModel.observeTag()?.collectLatest(action = { tag ->
                    Log.d(
                        TAG, "observeTag $tag"
                    )
                    binding.textView.setText(tag)
                })
            })*/
        }

        binding.btQr.setOnClickListener {
            findNavController().navigate(
                NFCFragmentDirections.actionNavigationNfcToQrActivity()

            )
        }
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (buttonView == this._binding?.toggleButton)
            viewModel.onCheckNFC(isChecked)
    }

    override fun onTagDiscovered(tag: Tag?) {
        viewModel.readTag(tag)
        viewLifecycleOwner.lifecycleScope.launch {
            val card = viewModel.getCardFromDB(getDec(tag!!.id).toString())
            Log.d("getDeccc", getDec(tag!!.id).toString())
            withContext(Dispatchers.Main) {
                if (card != null) {
                    binding.tvAge.text = card.dateOfBirth.toString()
                    binding.tvGender.text = card.gender.toString()
                    binding.tvName.text = card.name
                    binding.tvSurname.text = card.surName
                    binding.tvPatronymic.text = card.patronymic.toString()
                } else {
                    binding.tvName.text = "Нет"
                    binding.tvSurname.text = "Такого"
                    binding.tvPatronymic.text = "Человека"
                }
            }
        }
    }

    private fun getDec(bytes: ByteArray): Long {
        var result: Long = 0
        var factor: Long = 1
        for (i in bytes.indices) {
            val value: Long = bytes[i].and(0xffL.toByte()).toLong()
            result += value * factor
            factor *= 256L
        }
        return result
    }
}



