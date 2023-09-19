package com.example.controller.ui.workShiftPac.rv

import androidx.recyclerview.widget.DiffUtil
import com.example.controller.entities.Path


class PathsDiffCallback : DiffUtil.ItemCallback<Path>() {

    override fun areItemsTheSame(oldItem: Path, newItem: Path) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Path, newItem: Path) =
        oldItem == newItem
}