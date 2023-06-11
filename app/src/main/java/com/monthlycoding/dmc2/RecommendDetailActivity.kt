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
import com.monthlycoding.dmc2.datas.MenuData
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

        if (data.imgLink != "") uploadMenuImage(data.imgLink, binding.ivDetailImage)

        val menu1 = intent.getSerializableCompat<MenuData>("menu1") ?: MenuData(id=0, price=0, menu="", menuImgLink="")
        val menu2 = intent.getSerializableCompat<MenuData>("menu2") ?: MenuData(id=0, price=0, menu="", menuImgLink="")
        val menu3 = intent.getSerializableCompat<MenuData>("menu3") ?: MenuData(id=0, price=0, menu="", menuImgLink="")

        binding.llMenu1Menu.text = menu1.menu
        binding.llMenu2Menu.text = menu2.menu
        binding.llMenu3Menu.text = menu3.menu
        binding.llMenu1Price.text = menu1.price.toString()
        binding.llMenu2Price.text = menu2.price.toString()
        binding.llMenu3Price.text = menu3.price.toString()

        if (menu1.menuImgLink != "") uploadMenuImage(menu1.menuImgLink, binding.llMenu1Image)
        if (menu2.menuImgLink != "") uploadMenuImage(menu2.menuImgLink, binding.llMenu2Image)
        if (menu3.menuImgLink != "") uploadMenuImage(menu3.menuImgLink, binding.llMenu3Image)
    }

    private fun uploadMenuImage(menuImgLink : String, imageView : android.widget.ImageView) {
        val storage : FirebaseStorage = FirebaseStorage.getInstance()
        var storageRef = storage.getReference(menuImgLink)
        storageRef.downloadUrl.addOnSuccessListener{ url ->
            Glide.with(binding.root)
                .load(url)
                .into(imageView)
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