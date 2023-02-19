package com.monthlycoding.dmc2.recommendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.monthlycoding.dmc2.adapters.RecommendRecyclerViewAdapter
import com.monthlycoding.dmc2.databinding.FragmentTotalBinding
import com.monthlycoding.dmc2.datas.RecommendData

class TotalFragment : Fragment() {

    val mRecommendDatas = ArrayList<RecommendData>()

    lateinit var mAdapter: RecommendRecyclerViewAdapter
    private var _binding: FragmentTotalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTotalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecommendDatas.add(
            RecommendData(
                "반 회식 장소 어디가 좋을까?",
                "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
            )
        )
        mRecommendDatas.add(
            RecommendData(
                "5인큐 하기 좋은 피시방은?",
                "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
            )
        )
        mRecommendDatas.add(
            RecommendData(
                "시설 좋은 당구장은 어디없나?",
                "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
            )
        )
        mRecommendDatas.add(
            RecommendData(
                "클라이밍 하러 가볼래?",
                "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
            )
        )
        mRecommendDatas.add(
            RecommendData(
                "나는 혼자 놀고 싶어..",
                "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
            )
        )
        mRecommendDatas.add(
            RecommendData(
                "시간 때우기 좋은 카페",
                "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
            )
        )
        mRecommendDatas.add(
            RecommendData(
                "공부 할만한 곳 모음",
                "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
            )
        )
        mRecommendDatas.add(
            RecommendData(
                "교수님 취향 음식점",
                "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
            )
        )
        mRecommendDatas.add(
            RecommendData(
                "꿀 교양 모음",
                "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
            )
        )
        mAdapter = RecommendRecyclerViewAdapter(requireContext(), mRecommendDatas)
        mAdapter.notifyDataSetChanged()

        binding.recyclerviewTotalRecommend.adapter = mAdapter
        binding.recyclerviewTotalRecommend.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewTotalRecommend.addItemDecoration(RecommendItemDecoration(requireContext()))
    }
}