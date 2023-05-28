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
import androidx.recyclerview.widget.RecyclerView
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
    lateinit var storageRef: StorageReference

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

        mAdapter = CategoryRecyclerViewAdapter(requireContext(), buttonOption, foodAndPlay, this)
        mAdapter.notifyDataSetChanged()

        binding.categoryRecyclerview.adapter = mAdapter
        binding.categoryRecyclerview.layoutManager = LinearLayoutManager(context)

        firestore.collection("FoodAndPlay").get().addOnSuccessListener { result ->
            for (snapshot in result) {
                foodAndPlay.add(snapshot.toObject(CategoryData::class.java))
            }
        }

        val recyclerView =
            binding.bottomSheet.findViewById<RecyclerView>(R.id.category_recyclerview)
        recyclerView.setHasFixedSize(true)

        getImageButtons()
            .forEach { imageButton ->
                imageButton.setOnClickListener { it ->
                    if (buttonOption == it.contentDescription.toString()) return@setOnClickListener
                    buttonOption = it.contentDescription.toString()
                    binding.recyclerviewMenuName.text = buttonOption

                    mAdapter = CategoryRecyclerViewAdapter(
                        requireContext(),
                        buttonOption,
                        foodAndPlay.filter { it.foodGroup == buttonOption },
                        this
                    )
                    mAdapter.notifyDataSetChanged()

                    binding.categoryRecyclerview.adapter = mAdapter
                    binding.categoryRecyclerview.layoutManager = LinearLayoutManager(context)

                    val recyclerView = binding.categoryRecyclerview
                    recyclerView.setHasFixedSize(true)

                    Log.d("test1", buttonOption)
                }
            }
    }

    private fun getImageButtons() = binding.categoryTable.children
        .filterIsInstance<TableRow>()
        .flatMap { it.children }
        .filterIsInstance<ImageButton>()
        .toList()

    override fun onItemClick(id: Int) {
        val intent = RecommendDetailActivity.getIntent(requireContext())
        intent.putExtra("id", id)
        startActivity(intent)
    }
}