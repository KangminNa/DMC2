package com.monthlycoding.dmc2.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.datas.RecommendData

class RecommendRecyclerViewAdapter(
    val mContext: Context,
    val mList: List<RecommendData> // ListView와는 다르게 ViewHolder를 사용하면 resId를 안받아도 됨
) : RecyclerView.Adapter<RecommendRecyclerViewAdapter.MyViewHolder>() {

    //    클래스 내부의 클래스 제작
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

//        멤버 변수로, view 변수 내부에서 실제 사용할 UI들을 가져와서 담아두자.

        val recommendTitle = view.findViewById<TextView>(R.id.recommend_title)
        val recommendImage = view.findViewById<ImageView>(R.id.recommend_image)

        //        함수로, 실 데이터를 넘겨받아서, 멤벼변수의 UI 태그들과 결합하는 기능 추가.
        fun bind(data: RecommendData) {
            recommendTitle.text = data.recommendTitle
            Glide.with(mContext).load(data.recommendImage).into(recommendImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view =
            LayoutInflater.from(mContext).inflate(R.layout.total_recommend_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // 실제 출력할 데이터를 관리

        val data = mList[position]

        // MyViewHolder도 일종의 클래스 이기 떄문에 멤버변수와 함수를 가지고 있을 수 있다.

        holder.bind(data)
    }

    override fun getItemCount() = mList.size // 목록의 갯수가 리턴
}