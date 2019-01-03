package com.example.dev.southbrmemes.model.utils

import android.widget.CheckBox
import android.widget.EditText
import java.io.File

/**
 * Created by dev on 13/05/2018.
 */
object ValidateComponent {

    val message = "Campo obrigatorio"

    fun validadeLogin(login: EditText, password: EditText): Boolean {
        var validate = true

        if (login.valid) {
            login.error = message
            validate = false
        }
        if (password.valid) {
            password.error = message
            validate = false
        }

        return validate
    }

    fun validadeRegisterUser(name: EditText, login: EditText, password: EditText, confirmPassword: EditText, term: CheckBox?): Boolean {
        var validate = true

        if (name.valid) {
            name.error = message
            validate = false
        }
        if (login.valid) {
            login.error = message
            validate = false
        }
        if (password.valid) {
            password.error = message
            validate = false
        }
        if (!password.text.toString().equals(confirmPassword.text.toString())) {
            confirmPassword.error = "As senhas nao coencidem"
            validate = false
        }

        if (term != null)
        if (!term.isChecked) {
            term.error = message
            validate = false
        }

        return validate
    }

    fun validadeUpdateUser(name: EditText, login: EditText, password: EditText, newPassword: EditText): Boolean {
        var validate = true

        if (name.valid) {
            name.error = message
            validate = false
        }
        if (login.valid) {
            login.error = message
            validate = false
        }
        if (password.valid) {
            password.error = message
            validate = false
        }

        if (newPassword.valid) {
            password.error = message
            validate = false
        }

        return validate
    }


    fun validadeRegisterMeme(archive: File?, commit: EditText): Boolean {
        var validate = true

        if (archive == null) {
            commit.error = "Selecione uma foto"
            validate = false
        }
        if (commit.valid) {
            commit.error = message
            validate = false
        }

        return validate
    }
}

private val EditText.valid: Boolean
    get() {
        return text.toString().isNullOrEmpty()
    }
