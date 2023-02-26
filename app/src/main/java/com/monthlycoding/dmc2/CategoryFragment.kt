package com.monthlycoding.dmc2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.monthlycoding.dmc2.adapters.CategoryRecyclerViewAdapter
import com.monthlycoding.dmc2.databinding.FragmentCategoryBinding
import com.monthlycoding.dmc2.datas.CategoryData


class CategoryFragment : Fragment() {
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    val mCategoryDatas = ArrayList<CategoryData>()
    lateinit var mAdapter: CategoryRecyclerViewAdapter
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
        mAdapter = CategoryRecyclerViewAdapter(requireContext(), mCategoryDatas)
        mAdapter.notifyDataSetChanged()

        binding.categoryRecyclerview.adapter = mAdapter
        binding.categoryRecyclerview.layoutManager = LinearLayoutManager(context)
        //binding.categoryRecyclerview.addItemDecoration(RecommendItemDecoration(requireContext()))
    }
}
