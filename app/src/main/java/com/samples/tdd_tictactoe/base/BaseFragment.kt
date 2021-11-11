package com.samples.tdd_tictactoe.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.samples.tdd_tictactoe.common.delegate.FragmentBinding

abstract class BaseFragment<DataBinding : ViewDataBinding>(layoutId: Int) : Fragment() {

    private val dataBindings
            by FragmentBinding<DataBinding>(layoutId)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return dataBindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBindings.apply {
            lifecycleOwner = this@BaseFragment
        }
    }

    fun getDataBinding() = dataBindings

}