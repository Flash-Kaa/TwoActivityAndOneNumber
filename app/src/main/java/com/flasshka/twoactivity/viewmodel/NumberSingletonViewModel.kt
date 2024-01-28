package com.flasshka.twoactivity.viewmodel

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NumberSingletonViewModel private constructor() : ViewModel() {
    private var number = 0

    companion object {
        private var thisClass: NumberSingletonViewModel? = null

        fun factory(owner: ComponentActivity? = null): NumberSingletonViewModel {
            if (thisClass == null) {
                if (owner == null) {
                    throw NullPointerException("Owner is null")
                }

                thisClass = ViewModelProvider(owner)[NumberSingletonViewModel::class.java]
            }

            // can't be null
            return thisClass!!
        }
    }

    fun addOne() {
        number++
    }

    fun getNumber(): Int {
        return number
    }

    fun getSquare(): Int {
        return number * number
    }
}