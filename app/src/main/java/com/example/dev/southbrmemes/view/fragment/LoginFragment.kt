package com.example.dev.southbrmemes.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev.southbrmemes.view.service.auth.UserLoginDomain
import com.example.dev.southbrmemes.R
import com.example.dev.southbrmemes.databinding.FragmentLoginBinding
import com.example.dev.southbrmemes.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.view.*


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    companion object {
        fun getInstance(): LoginFragment {
            return LoginFragment();
        }
    }

    val user = UserViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.user = user
        binding.userLoginDomain = UserLoginDomain(activity = activity!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("South BR Memes")
        (activity as AppCompatActivity).supportActionBar?.setIcon(null)

        view.btnRegister.setOnClickListener { v ->
            chengefragment(RegisterUserFragment.getInstance(), R.id.flIndex)
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
