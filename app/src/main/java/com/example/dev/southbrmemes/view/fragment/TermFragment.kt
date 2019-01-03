package com.example.dev.southbrmemes.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev.southbrmemes.R


/**
 * A simple [Fragment] subclass.
 */
class TermFragment : Fragment() {

    companion object {
        fun getInstance(): TermFragment {
            return TermFragment();
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_term, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle("Termos de uso")
        (activity as AppCompatActivity).supportActionBar?.setIcon(null)

    }
}// Required empty public constructor
