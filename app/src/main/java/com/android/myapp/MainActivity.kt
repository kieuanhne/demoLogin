package com.android.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.myapp.Fragment.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val frm = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frmmain, frm).commit()

    }
}