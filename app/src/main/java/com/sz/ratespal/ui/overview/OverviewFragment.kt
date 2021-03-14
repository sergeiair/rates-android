package com.sz.ratespal.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.sz.ratespal.api.sign.SignApiInteractor
import com.sz.ratespal.databinding.FragmentLoginBinding
import com.sz.ratespal.databinding.FragmentOverviewBinding
import com.sz.ratespal.ui.base.BaseFragment
import com.sz.ratespal.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OverviewFragment: BaseFragment() {

    /*@Inject
    lateinit var signApiInteractor: SignApiInteractor*/

    private var binding: FragmentOverviewBinding by autoCleared()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindEvents()
        observe()
    }

    override fun bindEvents() {

    }

    override fun observe() {

    }

    private fun login() {

    }
}