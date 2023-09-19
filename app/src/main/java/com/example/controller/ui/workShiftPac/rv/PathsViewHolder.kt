package com.example.controller.ui.workShiftPac.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.controller.databinding.ItemPathBinding
import com.example.controller.entities.Path


class PathsViewHolder(val binding: ItemPathBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Path) {
        with(binding) {
            tvGovNumber.text=item.govNumber
            tvPathNumber.text=item.pathNumber
            tvStartDate.text=item.startDate.toString()
            tvEndDate.text= item.endDate?.let { it.toString() }
        }
    }
}