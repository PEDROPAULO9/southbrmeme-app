package com.example.dev.southbrmemes.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev.southbrmemes.view.service.insert.UserInsertDomain
import com.example.dev.southbrmemes.R
import com.example.dev.southbrmemes.databinding.FragmentRegisterBinding
import com.example.dev.southbrmemes.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_register.view.*


/**
 * A simple [Fragment] subclass.
 */
class RegisterUserFragment : Fragment() {

    companion object {
        fun getInstance(): RegisterUserFragment {
            return RegisterUserFragment();
        }
    }

    val user: UserViewModel = UserViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentRegisterBinding.inflate(layoutInflater)
        binding.user = user
        binding.userInsertDomain = UserInsertDomain(activity!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("Cadastro")
        (activity as AppCompatActivity).supportActionBar?.setIcon(null)


        view.textViewTerm.setOnClickListener { v ->
            chengefragment(TermFragment.getInstance(), R.id.flIndex)
        }

        view.btnReturnLogin.setOnClickListener { v ->
            chengefragment(LoginFragment.getInstance(), R.id.flIndex)
        }
    }

    fun chengefragment(fragment: Fragment, id: Int) {
        fragmentManager
                ?.beginTransaction()
                ?.replace(id, fragment, null)
                ?.addToBackStack("Fragment")
                ?.commit()
    }

}

