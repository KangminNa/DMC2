package com.monthlycoding.dmc2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TableRow
import androidx.core.view.children
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

        var buttonOption = "노래방"

        mAdapter = CategoryRecyclerViewAdapter(requireContext(), buttonOption)
        mAdapter.notifyDataSetChanged()

        binding.categoryRecyclerview.adapter = mAdapter
        binding.categoryRecyclerview.layoutManager = LinearLayoutManager(context)

        var recyclerView = binding.bottomSheet.findViewById<RecyclerView>(R.id.category_recyclerview)
        recyclerView.setHasFixedSize(true)

        binding.categoryTable.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<ImageButton>().toList().forEach{ it.setOnClickListener {
                if (buttonOption == it.contentDescription.toString()) return@setOnClickListener
                buttonOption = it.contentDescription.toString()
                binding.recyclerviewMenuName.text = buttonOption

                mAdapter = CategoryRecyclerViewAdapter(requireContext(), buttonOption)
                mAdapter.notifyDataSetChanged()

                binding.categoryRecyclerview.adapter = mAdapter
                binding.categoryRecyclerview.layoutManager = LinearLayoutManager(context)

                var recyclerView = binding.bottomSheet.findViewById<RecyclerView>(R.id.category_recyclerview)
                recyclerView.setHasFixedSize(true)

                Log.d("test1", buttonOption)
            }}
    }
}