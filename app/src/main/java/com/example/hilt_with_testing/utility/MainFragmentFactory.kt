package com.example.hilt_with_testing.utility

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.hilt_with_testing.ui.HomeFragment
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
    private val something : String
) : FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            HomeFragment::class.java.name -> {
                HomeFragment(something)
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}