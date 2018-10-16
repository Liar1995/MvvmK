package com.summer.mvvmk.ui.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.summer.mvvmk.R
import com.summer.mvvmk.entity.Person
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var kobo: Person

//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab.setOnClickListener { Toast.makeText(this, "${kobo.name} -- ${kobo.age}", Toast.LENGTH_LONG).show() }
    }
}
