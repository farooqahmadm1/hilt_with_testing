package com.example.hilt_with_testing.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hilt_with_testing.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment(
    private val something: String
) : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: $something")
    }

    companion object {
        private const val TAG = "appDebug"
    }
}