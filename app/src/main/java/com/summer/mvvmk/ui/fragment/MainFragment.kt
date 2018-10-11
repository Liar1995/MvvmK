package com.summer.mvvmk.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.summer.mvvmk.R
import com.summer.mvvmk.entity.Person
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


/**
 * Created by sunmeng on 2018/10/11.
 * Email:sunmeng995@gmail.com
 * Description:
 */
class MainFragment : Fragment() {

    @Inject
    lateinit var jordon: Person

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.text = "${jordon.name} -- ${jordon.age}"
    }

}