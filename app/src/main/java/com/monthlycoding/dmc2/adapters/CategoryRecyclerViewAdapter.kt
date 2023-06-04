package com.monthlycoding.dmc2.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.datas.CategoryData
import com.monthlycoding.dmc2.recommendation.ImageSliderAdapter
import kotlin.properties.Delegates


class CategoryRecyclerViewAdapter(
    val mContext: Context,
    val foodAndPlay : List<CategoryData>,
    val onItemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(mContext)
                .inflate(R.layout.category_recommend_list_item, parent, false)
        return MyViewHolder(view, onItemClickListener, mContext)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // 실제 출력할 데이터를 관리
        val data: CategoryData = foodAndPlay[position]

        // MyViewHolder도 일종의 클래스 이기 떄문에 멤버변수와 함수를 가지고 있을 수 있다.
        holder.bind(data)
    }

    override fun getItemCount() = foodAndPlay.size // 목록의 갯수가 리턴
}