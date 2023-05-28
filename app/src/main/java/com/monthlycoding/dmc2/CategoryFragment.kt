package com.monthlycoding.dmc2

import android.content.Intent
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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.monthlycoding.dmc2.adapters.CategoryRecyclerViewAdapter
import com.monthlycoding.dmc2.databinding.FragmentCategoryBinding
import com.monthlycoding.dmc2.datas.CategoryData

class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    lateinit var mAdapter: CategoryRecyclerViewAdapter
    lateinit var intent : Intent

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

        // 15개 버튼 중
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

        mAdapter.itemClickListener = object : CategoryRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(id: Int) {
                //intent = Intent(activity, RecommandDetailActivity::class.java)
                Log.d("test2", id.toString())
                //intent.putExtra("title", )
                //startActivity(intent)
            }
        }

    }
}