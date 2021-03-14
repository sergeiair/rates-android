package com.sz.ratespal.ui.base

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    abstract fun bindEvents()

    abstract fun observe()

}