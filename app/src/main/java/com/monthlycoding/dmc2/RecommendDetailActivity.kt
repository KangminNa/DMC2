package com.monthlycoding.dmc2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.monthlycoding.dmc2.databinding.ActivityRecommandDetailBinding
import com.monthlycoding.dmc2.datas.StoreInformation
import com.monthlycoding.dmc2.util.getSerializableCompat

class RecommendDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecommandDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecommandDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", 1)
        // val category = intent.getSerializableCompat<StoreInformation>("id") ?: StoreInformation(~~)
        // ?: 는 엘비스 연산자로 null 이라면 이 다음에 나오는 값을 넣겠다는 의미

        binding.tvAddress.text = id.toString()
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, RecommendDetailActivity::class.java)
        }
    }
}