package com.sz.ratespal.ui.overview

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.sz.ratespal.databinding.FragmentOverviewBinding
import com.sz.ratespal.ui.base.BaseFragment
import com.sz.ratespal.utils.Print
import com.sz.ratespal.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

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
        prepareChart()
    }

    override fun bindEvents() {

    }

    override fun observe() {
        viewModel.rates.observe(viewLifecycleOwner, Observer {
            Print.i(it)
        })
    }

    private fun prepareChart() {

        val values = ArrayList<Entry>()
        for (i in 0 until 50) {
            val `val` = (Math.random() * (3 + 1)).toFloat() + 20
            values.add(Entry(i.toFloat(), `val`))
        }
        val set1: LineDataSet
        if (binding.ratesChart.getData() != null &&
            binding.ratesChart.getData().getDataSetCount() > 0
        ) {
            set1 = binding.ratesChart.getData().getDataSetByIndex(0) as LineDataSet
            set1.values = values
            binding.ratesChart.getData().notifyDataChanged()
            binding.ratesChart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "DataSet 1")
            set1.mode = LineDataSet.Mode.CUBIC_BEZIER
            set1.cubicIntensity = 0.2f
            set1.setDrawFilled(true)
            set1.setDrawCircles(false)
            set1.lineWidth = 1.8f
            set1.circleRadius = 4f
            set1.setCircleColor(Color.WHITE)
            set1.highLightColor = Color.rgb(244, 117, 117)
            set1.color = Color.WHITE
            set1.fillColor = Color.WHITE
            set1.fillAlpha = 100
            set1.setDrawHorizontalHighlightIndicator(false)
            set1.fillFormatter =
                IFillFormatter { dataSet, dataProvider -> binding.ratesChart.getAxisLeft().getAxisMinimum() }

            // create a data object with the data sets
            val data = LineData(set1)

            data.setValueTextSize(9f)
            data.setDrawValues(false)

            // set data
            binding.ratesChart.setData(data)
        }
    }

}