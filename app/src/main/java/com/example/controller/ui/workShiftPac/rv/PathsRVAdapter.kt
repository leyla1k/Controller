package com.example.controller.ui.workShiftPac.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.controller.databinding.ItemPathBinding
import com.example.controller.entities.Path
import java.util.Collections


class PathsRVAdapter() : ListAdapter<Path, PathsViewHolder>(PathsDiffCallback()),
    PathsItemTouchHelperAdapter {

    var onPathsClickListener: ((Path) -> Unit)? = null
    lateinit var paths: MutableList<Path>
    lateinit var itemPathBinding: ItemPathBinding

    fun submit(list:  List<Path>, rv: RecyclerView) {
        paths=list.toMutableList()
        submitList(list){
            rv.invalidateItemDecorations()
        }//иначе добавление нового элемента - проблема
    }

    override fun onItemDismiss(position: Int) {
        paths.removeAt(position)
        //onProjectSwipeListener?.invoke(paths[position])
        notifyItemRemoved(position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(paths, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(paths, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathsViewHolder {
        itemPathBinding = ItemPathBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PathsViewHolder(itemPathBinding)
    }


    override fun onBindViewHolder(holder: PathsViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
        with(holder.binding) {
            containerPath.setOnClickListener() {
                onPathsClickListener?.invoke(item)
            }


        }

    }

}