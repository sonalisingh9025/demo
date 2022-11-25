package com.finalatixassignment.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.finalatixassignment.R
import com.finalatixassignment.adapters.NameListAdaptor
import com.finalatixassignment.constant.IntentConstant
import com.finalatixassignment.databinding.ActivityMainBinding
import com.finalatixassignment.interfaces.NameSelectListener
import com.finalatixassignment.models.ResponseNameData
import com.finalatixassignment.utils.AppUtility
import com.finalatixassignment.viewmodels.MainActivityViewModel


class MainActivity : AppCompatActivity(), NameSelectListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private var imageList = ArrayList<ResponseNameData>()


    private val adapter by lazy {
        NameListAdaptor(this, imageList, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]


        setObserver()

        getDataFromApi()
    }


    private fun setObserver() {
        viewModel.nameData.observe(this) {
            if (it != null) {
                setData(it)
            }
        }

        viewModel.error.observe(this) {
            binding.progress.visibility = View.GONE
        }
    }

    private fun setData(it: ArrayList<ResponseNameData>) {
        binding.progress.visibility = View.GONE
        imageList.addAll(it)
        binding.rcvList.adapter =adapter
    }

    private fun getDataFromApi() {
        if (AppUtility.isNetworkAvailable(this)) {
            binding.progress.visibility  = View.VISIBLE
            viewModel.getNameListApi()
        }
    }

    override fun selectName(data: ResponseNameData) {
        val intett = Intent(this,DetailActivity::class.java)
        intett.putExtra(IntentConstant.DATA,data)
        startActivity(intett)
    }

}