package com.monthlycoding.dmc2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.monthlycoding.dmc2.adapters.CategoryRecyclerViewAdapter
import com.monthlycoding.dmc2.databinding.FragmentCategoryBinding
import com.monthlycoding.dmc2.datas.CategoryData

class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    val mCategoryDatas = ArrayList<CategoryData>()
    lateinit var mAdapter: CategoryRecyclerViewAdapter

    var firestore : FirebaseFirestore? = null

    //lateinit var load : ImageButton
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore
        val docRef = db.collection("FoodAndPlay").document("0")
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                Log.d("CategoryFragment", "DocumentSnapshot data: ${document.data}")
            } else {
                Log.d("CategoryFragment", "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d("CategoryFragment", "get failed with ", exception)
        }

        firestore = FirebaseFirestore.getInstance()

        /*load = binding.menuCategoryLine1.findViewById(R.id.category_line1_1)
        var storage = FirebaseStorage.getInstance()
        var storageReference = storage.getReference()
        var submitProfile = storageReference.child("테스트.jpg"); // 폴더 위치부터 파일 명까지 정확하게 기입해야 함.

        submitProfile.downloadUrl.addOnSuccessListener { url ->
            Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(load)

        }.addOnFailureListener {exception ->
            Log.w("CategoryFragment", "Error getting documments: $exception" )
        }*/

        repeat(10) {
            mCategoryDatas.add(
                CategoryData(
                    "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k",
                    "최고당 돈까스",
                    "02-1234-1234",
                    "서울시 구로구 xxxx"
                )
            )
        }
        //mAdapter = CategoryRecyclerViewAdapter(requireContext(), mCategoryDatas)
        mAdapter = CategoryRecyclerViewAdapter(requireContext())
        mAdapter.notifyDataSetChanged()

        binding.categoryRecyclerview.adapter = mAdapter
        binding.categoryRecyclerview.layoutManager = LinearLayoutManager(context)

        var recyclerView = binding.bottomSheet.findViewById<RecyclerView>(R.id.category_recyclerview)

        recyclerView.setHasFixedSize(true)

        //binding.categoryRecyclerview.addItemDecoration(RecommendItemDecoration(requireContext()))
    }
}