package com.yuanchik.myapplicationbebe.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuanchik.myapplicationbebe.databinding.FragmentSelectionsBinding
import com.yuanchik.myapplicationbebe.utils.AnimationHelper

class SelectionsFragment : Fragment() {
    private var binding2: FragmentSelectionsBinding? = null
    private val binding get() = binding2!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding2 = FragmentSelectionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding2 = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.selectionsFragmentRoot,
            requireActivity(),
            4
        )
    }
}