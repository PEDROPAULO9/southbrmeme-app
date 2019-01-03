package com.example.dev.southbrmemes.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev.southbrmemes.view.service.data.UserDataDomain
import com.example.dev.southbrmemes.view.service.delete.UserDeleteDomain
import com.example.dev.southbrmemes.view.service.update.UserUpdateDomain
import com.example.dev.southbrmemes.databinding.FragmentEditUserBinding
import com.example.dev.southbrmemes.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_edit_user.view.*


/**
 * A simple [Fragment] subclass.
 */
class EditUserFragment : Fragment() {

    companion object {
        fun getInstance(): EditUserFragment {
            return EditUserFragment();
        }
    }

    val user = UserViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentEditUserBinding.inflate(layoutInflater)
        binding.user = user
        binding.userUpdateDomain = UserUpdateDomain(activity!!)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setTitle("Editar Conta")
        (activity as AppCompatActivity).supportActionBar?.setIcon(null)

        UserDataDomain(activity!!).data(user = user)

        view.btnDeleteAccount.setOnClickListener { v ->
            if(activity != null)
            AlertDialog.Builder(activity!!)
                    .setTitle("Deletar conta")
                    .setMessage("Tem certeza que deseja deletar esta conta ?")
                    .setPositiveButton("Sim"
                    ) { _, _ ->
                        if(activity != null)
                            UserDeleteDomain(activity!!).delete()
                    }
                    .setNegativeButton("Não", null)
                    .show()
        }

    }

}// Required empty public constructor
