package com.yuanchik.myapplicationbebe.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuanchik.myapplicationbebe.databinding.FragmentWatchLaterBinding
import com.yuanchik.myapplicationbebe.utils.AnimationHelper

class WatchLaterFragment : Fragment() {

    private var binding4: FragmentWatchLaterBinding? = null
    private val binding get() = binding4!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding4 = FragmentWatchLaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding4 = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.watchLaterFragmentRoot,
            requireActivity(),
            3
        )
    }
}