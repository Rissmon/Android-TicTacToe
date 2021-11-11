package com.samples.tdd_tictactoe.view.game.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samples.tdd_tictactoe.databinding.CellItemLayoutBinding
import com.samples.tdd_tictactoe.model.Cell

class GameAdapter(val onItemClicked: (cellValue: Cell) -> Unit) :
    ListAdapter<Cell, GameAdapter.MyViewHolder>(CellListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView = CellItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MyViewHolder(private val binding: CellItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cellValue: Cell) {
            binding.cell = cellValue
            binding.cellItemView.setOnClickListener {
                onItemClicked(cellValue)
            }
        }
    }
}

class CellListDiffCallback : DiffUtil.ItemCallback<Cell>() {
    override fun areItemsTheSame(oldItem: Cell, newItem: Cell): Boolean =
        oldItem.state == newItem.state

    override fun areContentsTheSame(oldItem: Cell, newItem: Cell): Boolean =
        oldItem == newItem
}
