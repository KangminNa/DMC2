package com.monthlycoding.dmc2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TableRow
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.monthlycoding.dmc2.adapters.CategoryRecyclerViewAdapter
import com.monthlycoding.dmc2.adapters.OnItemClickListener
import com.monthlycoding.dmc2.databinding.FragmentCategoryBinding
import com.monthlycoding.dmc2.datas.CategoryData

class CategoryFragment : Fragment(), OnItemClickListener {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    lateinit var mAdapter: CategoryRecyclerViewAdapter
    private val firestore = FirebaseFirestore.getInstance()
    val storage = FirebaseStorage.getInstance()
    val foodAndPlay = mutableListOf<CategoryData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 15개 버튼 중
        var buttonOption = "노래방"

        // 앱을 실행한 후, 딱 한 번만 foodAndPlay 리스트에 모든 정보가 담기도록 함.
        firestore.collection("FoodAndPlay").get().addOnSuccessListener { result ->
            for (snapshot in result) {
                foodAndPlay.add(snapshot.toObject(CategoryData::class.java))
            }

            // 모든 정보가 담긴 후에 바로 첫 페이지 로딩되도록 함.
            mAdapter = CategoryRecyclerViewAdapter(
                requireContext(),
                foodAndPlay.filter { it.foodGroup == buttonOption },
                this
            )
            mAdapter.notifyDataSetChanged()

            binding.categoryRecyclerview.adapter = mAdapter
            binding.categoryRecyclerview.layoutManager = LinearLayoutManager(context)
            binding.categoryRecyclerview.setHasFixedSize(true)
        }

        // 15개 버튼이 눌릴 때 그에 맞는 리사이클러뷰를 출력함.
        getImageButtons()
            .forEach { imageButton ->
                imageButton.setOnClickListener { it ->
                    // 해당 버튼 페이지와 누른 버튼이 동일하다면 작동되지 않도록 함.
                    if (buttonOption == it.contentDescription.toString()) return@setOnClickListener
                    buttonOption = it.contentDescription.toString()
                    binding.recyclerviewMenuName.text = buttonOption

                    mAdapter = CategoryRecyclerViewAdapter(
                        requireContext(),
                        foodAndPlay.filter { it.foodGroup == buttonOption },
                        this
                    )
                    mAdapter.notifyDataSetChanged()

                    binding.categoryRecyclerview.adapter = mAdapter
                    binding.categoryRecyclerview.layoutManager = LinearLayoutManager(context)
                    binding.categoryRecyclerview.setHasFixedSize(true)
                }
            }
    }

    private fun getImageButtons() = binding.categoryTable.children
        .filterIsInstance<TableRow>()
        .flatMap { it.children }
        .filterIsInstance<ImageButton>()
        .toList()

    override fun onItemClick(data : CategoryData) {
        val intent = RecommendDetailActivity.getIntent(requireContext())
        intent.putExtra("info", data)
        startActivity(intent)
    }
}