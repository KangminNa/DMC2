package com.monthlycoding.dmc2.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.datas.CategoryData
import com.monthlycoding.dmc2.recommendation.ImageSliderAdapter


class CategoryRecyclerViewAdapter(
    val mContext: Context,
    val buttonOption: String
) : RecyclerView.Adapter<CategoryRecyclerViewAdapter.MyViewHolder>() {
    var foodAndPlay = arrayListOf<CategoryData>()
    val firestore = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()
    lateinit var storageRef : StorageReference
    init {
        firestore?.collection("FoodAndPlay")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            foodAndPlay.clear()

            for (snapshot in querySnapshot!!.documents) {
                if (snapshot.getString("categoryGroup")!!.contains(buttonOption)) {
                    var item = snapshot.toObject(CategoryData::class.java)
                    foodAndPlay.add(item!!)
                }
            }
            notifyDataSetChanged()
        }
    }

    //    클래스 내부의 클래스 제작
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // 멤버 변수로, view 변수 내부에서 실제 사용할 UI들을 가져와서 담아두자.

        val categoryImage = view.findViewById<ImageView>(R.id.category_image)
        val categoryName = view.findViewById<TextView>(R.id.category_name)
        val categoryTel = view.findViewById<TextView>(R.id.category_tel)
        val categoryAddress = view.findViewById<TextView>(R.id.category_address)

        // 함수로, 실 데이터를 넘겨받아서, 멤벼변수의 UI 태그들과 결합하는 기능 추가.
        fun bind(data: CategoryData) {
            storageRef = storage.getReference().child(data.categoryImage);
            storageRef.downloadUrl.addOnSuccessListener{ url ->
                Glide.with(mContext)
                    .load(url)
                    .into(categoryImage)

            }.addOnFailureListener {exception ->
                Log.w("CategoryFragment", "Error getting documments: $exception" )
            }
            //Glide.with(mContext).load(storageRef).into(categoryImage)
            categoryName.text = data.categoryName
            categoryTel.text = data.categoryTel
            categoryAddress.text = data.categoryAddress
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view =
            LayoutInflater.from(mContext)
                .inflate(R.layout.category_recommend_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // 실제 출력할 데이터를 관리

        val data = foodAndPlay[position]

        // MyViewHolder도 일종의 클래스 이기 떄문에 멤버변수와 함수를 가지고 있을 수 있다.

        holder.bind(data)
    }

    override fun getItemCount() = foodAndPlay.size // 목록의 갯수가 리턴
}