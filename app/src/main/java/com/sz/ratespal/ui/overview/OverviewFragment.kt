package com.sz.ratespal.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sz.ratespal.databinding.FragmentOverviewBinding
import com.sz.ratespal.entities.RatesItem
import com.sz.ratespal.ui.base.BaseFragment
import com.sz.ratespal.utils.LineChartUtil
import com.sz.ratespal.utils.Print
import com.sz.ratespal.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment: BaseFragment() {

    private val viewModel: OverviewViewModel by viewModels()

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
        prepareView()
    }

    override fun bindEvents() {}

    override fun prepareView() {}

    override fun observe() {
        viewModel.rates.observe(viewLifecycleOwner, Observer {
            prepareChart(it)
        })
    }

    private fun prepareChart(data: List<RatesItem>) {
        binding.ratesChart.data = LineChartUtil.prepare("EURUSD", data)
        binding.ratesChart.setVisibleXRangeMaximum(10F)


        binding.ratesChart.apply {
            LineChartUtil.decorate()
            LineChartUtil.withDateXAxis(this)
            invalidate()
        }

        binding.ratesChart.data.notifyDataChanged()
        binding.ratesChart.notifyDataSetChanged()
        binding.ratesChart.invalidate()
    }

}