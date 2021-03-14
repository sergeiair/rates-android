package com.sz.ratespal.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sz.ratespal.activities.main.Navigator
import com.sz.ratespal.api.sign.SignApiInteractor
import com.sz.ratespal.databinding.FragmentLoginBinding
import com.sz.ratespal.ui.base.BaseFragment
import com.sz.ratespal.ui.shared.UserViewViewModel
import com.sz.ratespal.utils.Print
import com.sz.ratespal.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment: BaseFragment() {

    @Inject
    lateinit var signApiInteractor: SignApiInteractor

    private var binding: FragmentLoginBinding by autoCleared()
    private val viewModel: UserViewViewModel by viewModels()

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
        observe()
    }

    override fun bindEvents() {
        binding.loginBtn.setOnClickListener {
            login()
        }
    }

    override fun observe() {
        viewModel.user.observe(viewLifecycleOwner, Observer {
            (activity as Navigator?)?.navigateToAuthorizedArea(it)
        })
    }

    private fun login() {
        GlobalScope.launch  {
            signApiInteractor.login(
                binding.loginEmail.text.toString(),
                binding.loginPassword.text.toString()
            ).takeIf { it.data?.data != null }.let {
                viewModel.storeUser(it!!.data!!.data, it.payload)
            }
        }
    }
}