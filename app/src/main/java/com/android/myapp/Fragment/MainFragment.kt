package com.android.myapp.Fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.android.myapp.R
import com.android.myapp.databinding.FragmentMainBinding

open class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var binding: FragmentMainBinding? = null
    private val viewModel: MainFragmentVM by viewModels()
    lateinit var sharedPref: SharedPreferences

    var mail: String? = null
    var pass: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            it.btnsignin.setOnClickListener {
                binding?.let {
                    checkLogin(it.editemail, it.editpass)
                }
            }
            it.btnback.setOnClickListener {
                showDialog()
            }
        }

    }


    override fun onPause() {
        super.onPause()
        saveData()
    }

    private fun saveData() {
        sharedPref = activity?.getSharedPreferences("saveData", Context.MODE_PRIVATE)!!
        binding?.let {
            mail = it.editemail.text.toString()
            pass = it.editpass.text.toString()

        }
        val editor = sharedPref.edit()
        editor.putString("_mail", mail)
        editor.putString("_pass", pass)
        editor.apply()
        Toast.makeText(context, "Data has been saved", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        sharedPref = activity?.getSharedPreferences("saveData", Context.MODE_PRIVATE)!!
        mail = sharedPref.getString("_mail", null)
        pass = sharedPref.getString("_pass", null)

        binding?.let {
            it.editemail.setText(mail)
            it.editpass.setText(pass)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun checkLogin(editemail: EditText, editpass: EditText) {
        if (editemail.text.isNullOrBlank() || editpass.text.isNullOrBlank()) {
            Toast.makeText(activity, "Email or Password is empty", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.loginDelay(Toast.makeText(activity, "Login success", Toast.LENGTH_SHORT))
        }

    }

    private fun showDialog() {
        val dialog = AlertDialog.Builder(context)
        dialog.apply {
            setTitle("CONFIRM")
            setMessage("Do you want exit??")
            setNegativeButton("No") { dialogInterface: DialogInterface,
                                      i: Int ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Yes") { dialogInterface: DialogInterface,
                                       i: Int ->
                activity?.finish()
            }
        }
        dialog.show()
    }
}