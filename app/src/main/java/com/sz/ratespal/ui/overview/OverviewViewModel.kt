package com.sz.ratespal.ui.overview;

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sz.ratespal.api.authorized.AuthorizedApiInteractor
import com.sz.ratespal.entities.RatesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OverviewViewModel @ViewModelInject constructor(
    private val authorizedApiInteractor: AuthorizedApiInteractor
) : ViewModel() {

    val rates: MutableLiveData<List<RatesItem>> = MutableLiveData(listOf())

    init {
        fetchRates()
    }

    private fun fetchRates() {
        GlobalScope.launch  {
            authorizedApiInteractor.getHistory(400).data?.data?.rates?.let {
                withContext(Dispatchers.Main) {
                    rates.value = it
                }
            }
        }
    }

}

