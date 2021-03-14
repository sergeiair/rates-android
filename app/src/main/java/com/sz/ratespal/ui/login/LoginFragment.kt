package com.sz.ratespal.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.sz.ratespal.api.sign.SignApiInteractor
import com.sz.ratespal.databinding.FragmentLoginBinding
import com.sz.ratespal.ui.base.BaseFragment
import com.sz.ratespal.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment: BaseFragment() {

    @Inject
    lateinit var signApiInteractor: SignApiInteractor

    private var binding: FragmentLoginBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindEvents()
    }

    override fun bindEvents() {
        binding.loginBtn.setOnClickListener {
            GlobalScope.launch  {



                var res = signApiInteractor.login(
                    binding.loginEmail.text.toString(),
                    binding.loginPassword.text.toString()
                )

                Timber.d(res.toString())

            }
        }
    }
}