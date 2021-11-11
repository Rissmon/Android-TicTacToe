package com.samples.tdd_tictactoe.common

import android.widget.ImageView
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import com.samples.tdd_tictactoe.R
import com.samples.tdd_tictactoe.model.Cell
import com.samples.tdd_tictactoe.model.Clear
import com.samples.tdd_tictactoe.model.OSelected
import com.samples.tdd_tictactoe.model.XSelected

@BindingAdapter("cellItemView")
fun cellItemView(
    cellView: MaterialCardView,
    cell: Cell?
) {
    if (cell != null) {
        val cellState = cell.state
        cellView.cardElevation = cellView.context.resources.getDimension(
            when (cellState) {
                Clear -> R.dimen.clear_cell_elevation
                else -> R.dimen.selected_cell_elevation
            }
        )
        cellView.isClickable = cell.state == Clear
        with(cellView.children.first() as ImageView) {
            setImageResource(
                when (cellState) {
                    OSelected -> R.drawable.ic_o
                    XSelected -> R.drawable.ic_x
                    Clear -> 0
                }
            )
        }
    }
}