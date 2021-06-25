package io.n26btc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.n26btc.presentation.BaseViewModel

abstract class BaseFragment<BINDING : ViewDataBinding>(
  private val layoutId: Int
) : Fragment() {

  protected lateinit var binding: BINDING
  abstract val viewModel: BaseViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
    binding.lifecycleOwner = viewLifecycleOwner

    setupBinding()

    return binding.root
  }

  abstract fun setupBinding()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    lifecycle.addObserver(viewModel)
  }
}
