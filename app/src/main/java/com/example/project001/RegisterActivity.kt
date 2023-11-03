package com.example.project001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import com.example.project001.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener,
    View.OnKeyListener {

    private lateinit var mbinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(mbinding.root)

            mbinding.edtFullname.onFocusChangeListener = this
            mbinding.edtEmail.onFocusChangeListener= this
            mbinding.edtPass.onFocusChangeListener=this
            mbinding.edtConfirmPass.onFocusChangeListener=this


    }


    private fun validateFullname(): Boolean {
        var errorMessage: String? = null
        val value: String = mbinding.edtFullname.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Full name is required!"
        }

        if (errorMessage != null) {
            mbinding.edtFullnameT.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }


    private fun validateEmail(): Boolean {
        var errorMessage: String? = null
        if (mbinding.edtEmail.text.isNullOrBlank()) {
            errorMessage = "Email is required!"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(mbinding.edtEmail.text.toString()).matches()) {
            errorMessage = "Email is invalid!"
        }
        if (errorMessage != null) {
            mbinding.edtEmailT.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validatePass(): Boolean {
        var errorMessage: String? = null
        if (mbinding.edtEmail.text.isNullOrBlank()) {
            errorMessage = "Password is required!"
        } else if (mbinding.edtPass.text.toString().length < 6) {
            errorMessage = "Password must have at least 7 characters!"
        }
        if (errorMessage != null) {
            mbinding.edtPassT.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateConfirmPass(): Boolean {
        var errorMessage: String? = null
        if (mbinding.edtConfirmPass.text.toString().isEmpty()) {
            errorMessage = "Password is required"
        } else if (mbinding.edtConfirmPass.text.toString().length < 6) {
            errorMessage = "Password must have at least 7 characters!"
        }
        if (errorMessage != null) {
            mbinding.edtConfirmPassT.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }


    private fun validatePassAndConfirmPass(): Boolean {
        var errorMessage: String? = null
        val password = mbinding.edtPass.text.toString()
        val cPassword = mbinding.edtConfirmPass.text.toString()

        if (password != cPassword) {
            errorMessage = "Doesn't match"
        }

        if (errorMessage != null) {
            mbinding.edtConfirmPassT.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null

    }


    override fun onClick(view: View?) {
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null) {
            when (view.id) {

                R.id.edtFullname -> {
                    if(hasFocus){
                        if(mbinding.edtFullnameT.isErrorEnabled){
                            mbinding.edtFullnameT.isErrorEnabled = false}
                    }else{validateFullname()}
                }

                R.id.edtEmail -> {
                    if(hasFocus){
                        if(mbinding.edtEmailT.isErrorEnabled){
                            mbinding.edtEmailT.isErrorEnabled = false}
                    }else{validateEmail()}
                }

                R.id.edtPass -> {
                    if(hasFocus){
                        if(mbinding.edtPassT.isErrorEnabled){
                            mbinding.edtPassT.isErrorEnabled = false}
                    }else{validatePass()}
                }

                R.id.edtConfirmPass -> {
                    if(hasFocus){
                        if(mbinding.edtConfirmPassT.isErrorEnabled){
                            mbinding.edtConfirmPassT.isErrorEnabled = false}

                    }else{validateConfirmPass()}
                }
            }
        }
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        return false
    }
}