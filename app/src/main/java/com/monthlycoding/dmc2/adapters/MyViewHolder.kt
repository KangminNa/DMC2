package com.monthlycoding.dmc2.adapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.StorageReference
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.datas.CategoryData

class MyViewHolder(
    val view: View,
    val onItemClickListener: OnItemClickListener,
//    val storageRef : StorageReference
) : RecyclerView.ViewHolder(view) {
    // 멤버 변수로, view 변수 내부에서 실제 사용할 UI들을 가져와서 담아두자.
    val categoryImage = view.findViewById<ImageView>(R.id.category_image)
    val categoryName = view.findViewById<TextView>(R.id.category_name)
    val categoryTel = view.findViewById<TextView>(R.id.category_tel)
    val categoryAddress = view.findViewById<TextView>(R.id.category_address)
    val categoryItemView = view.findViewById<ConstraintLayout>(R.id.category_itemview)

    // 함수로, 실 데이터를 넘겨받아서, 멤벼변수의 UI 태그들과 결합하는 기능 추가.
    fun bind(data: CategoryData) {
        view.setOnClickListener {
            onItemClickListener.onItemClick(data.id)
        }
//        storageRef = storage.getReference().child(data.imgLink);
//        storageRef.downloadUrl.addOnSuccessListener{ url ->
//            Glide.with(mContext)
//                .load(data.imgLink)
//                .into(categoryImage)
//
//        }.addOnFailureListener {exception ->
//            Log.w("CategoryFragment", "Error getting documents: $exception" )
//        }

        //Glide.with(mContext).load(storageRef).into(categoryImage)
        categoryName.text = data.name
        categoryTel.text = data.phone
        categoryAddress.text = data.location
    }
}