package com.android.myapp.Fragment

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragmentVM: ViewModel() {
    fun loginDelay(showToast: Toast){
        viewModelScope.launch() {
            for (i in 1..10){
                delay(200)
                if(i == 10){
                    showToast.show()
                }
            }
        }
    }
}