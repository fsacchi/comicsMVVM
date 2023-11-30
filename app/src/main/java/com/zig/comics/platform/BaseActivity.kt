package com.zig.comics.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    abstract val layoutRes: Int
    protected lateinit var binding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)

        init()
    }

    abstract fun init()
}
