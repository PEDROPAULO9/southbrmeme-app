package com.example.dev.southbrmemes.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev.southbrmemes.view.service.reload.MyMemeListDomain
import com.example.dev.southbrmemes.R
import kotlinx.android.synthetic.main.fragment_my_meme.*


/**
 * A simple [Fragment] subclass.
 */
class MyMemeFragment : Fragment() {


    companion object {
        fun getInstance(): MyMemeFragment {
            return MyMemeFragment();
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_meme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("Meus Memes")
        (activity as AppCompatActivity).supportActionBar?.setIcon(null)

        if(activity != null)
        MyMemeListDomain(activity!!).list(listMyMeme)

        refreshMyMeme.setOnRefreshListener {
            refreshMyMeme.isRefreshing = false
            if(activity != null)
            MyMemeListDomain(activity!!).list(listMyMeme)
        }

        fabRegisterMyMeme.setOnClickListener { v ->
            chengefragment(RegisterMemeFragment.getInstance(), R.id.flLogged)
        }
    }

    fun chengefragment(fragment: Fragment, id: Int) {
        fragmentManager
                ?.beginTransaction()
                ?.replace(id, fragment, null)
                ?.addToBackStack("Fragment")
                ?.commit()
    }

}// Required empty public constructor
