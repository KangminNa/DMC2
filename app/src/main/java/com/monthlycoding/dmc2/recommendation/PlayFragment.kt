package com.monthlycoding.dmc2.recommendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.monthlycoding.dmc2.adapters.RecommendRecyclerViewAdapter
import com.monthlycoding.dmc2.databinding.FragmentPlayBinding
import com.monthlycoding.dmc2.datas.RecommendData

class PlayFragment : Fragment() {
    private var _binding: FragmentPlayBinding? = null
    private val binding: FragmentPlayBinding get() = _binding!!

    private val mRecommendDatas = listOf(
        RecommendData(
            "반 회식 장소 어디가 좋을까?",
            "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
        ),
        RecommendData(
            "5인큐 하기 좋은 피시방은?",
            "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
        ),
        RecommendData(
            "시설 좋은 당구장은 어디없나?",
            "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
        ),
        RecommendData(
            "클라이밍 하러 가볼래?",
            "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
        ),
        RecommendData(
            "나는 혼자 놀고 싶어..",
            "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
        ),
        RecommendData(
            "시간 때우기 좋은 카페",
            "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
        ),
        RecommendData(
            "공부 할만한 곳 모음",
            "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
        ),
        RecommendData(
            "교수님 취향 음식점",
            "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
        ),
        RecommendData(
            "꿀 교양 모음",
            "https://fastly.picsum.photos/id/499/600/600.jpg?hmac=8DVR1iJ06_QxDOBazkF1SWTM6fyRennwVQf2ebe27_k"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = RecommendRecyclerViewAdapter(requireContext(), mRecommendDatas)
        mAdapter.notifyDataSetChanged()

        binding.recyclerviewPlayRecommend.adapter = mAdapter
        binding.recyclerviewPlayRecommend.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewPlayRecommend.addItemDecoration(RecommendItemDecoration(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}