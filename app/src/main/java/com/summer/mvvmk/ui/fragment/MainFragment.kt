package com.summer.mvvmk.ui.fragment

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.summer.kbase.common.LoggerUtils
import com.summer.kbase.ext.observe
import com.summer.mvvmk.R
import com.summer.mvvmk.entity.Person
import com.summer.mvvmk.ui.vm.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class MainFragment : Fragment() {

//    @Inject
//    lateinit var jordon: Person
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)
        viewModel.userId.observe(this) {
//            text.text = "${jordon.name} -- ${jordon.age} -- $it"
            text.text = "-- $it"
        }
        text.setOnClickListener {
            viewModel.changeID("xxx")
        }
    }

}