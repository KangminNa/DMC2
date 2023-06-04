package com.monthlycoding.dmc2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.monthlycoding.dmc2.databinding.ActivityRecommandDetailBinding
import com.monthlycoding.dmc2.datas.CategoryData
import com.monthlycoding.dmc2.util.getSerializableCompat

class RecommendDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecommandDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecommandDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getSerializableCompat<CategoryData>("info") ?: CategoryData(id=0, name="", phone="", location="", foodGroup="", operatingTime="", closed="", imgLink="")
        // ?: 는 엘비스 연산자로 null 이라면 이 다음에 나오는 값을 넣겠다는 의미

        binding.tvStoreName.text = data.name
        binding.tvAddress.text = data.location
        binding.tvTime.text = data.operatingTime
        binding.tvVacationDetail.text = data.closed
        binding.tvCallNumber.text = data.phone

        val storage : FirebaseStorage = FirebaseStorage.getInstance()
        var storageRef = storage.getReference(data.imgLink)
        storageRef.downloadUrl.addOnSuccessListener{ url ->
            Glide.with(binding.root)
                .load(url)
                .into(binding.ivDetailImage)
        }.addOnFailureListener {exception ->
            Log.w("CategoryFragment", "Error getting documents: $exception" )
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, RecommendDetailActivity::class.java)
        }
    }
}