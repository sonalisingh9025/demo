package com.finalatixassignment.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.finalatixassignment.R
import com.finalatixassignment.constant.IntentConstant
import com.finalatixassignment.databinding.ActivityDetailsBinding
import com.finalatixassignment.models.ResponseNameData


class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    private var data: ResponseNameData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        setData()

    }

    private fun setData() {

        intent.let {
            data = intent.getParcelableExtra(IntentConstant.DATA)
        }

        Glide.with(this).load(data!!.imageurl).placeholder(R.drawable.placeholder)
            .into(binding.imgPhoto)

        binding.tvName.text = data?.name
        binding.tvRealName.text = data?.realname
        binding.tvTeam.text = data?.team
        binding.tvFirstappearance.text = data?.firstappearance
        binding.tvCreatedby.text = data?.createdby
        binding.tvPublisher.text = data?.publisher
        binding.tvbio.text = data?.bio
    }

}