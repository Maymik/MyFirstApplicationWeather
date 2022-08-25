package com.kozyrev.myfirstapplication.kt

import android.view.View
import com.kozyrev.myfirstapplication.screens.MainActivity
import kotlin.jvm.Throws

class MyCustomException : RuntimeException()

class ExampleCasting {

    var x: Int = 8
    var y: Int? = null

    @Throws(MyCustomException::class)
    fun divideTwoNumbers(x: Int, y: Int): Int{
        if (y==0) throw MyCustomException()
        return x/y
    }


    fun testImplicitCasting(listener: View.OnClickListener) {
        if (listener is MainActivity) {
            listener.onCreateDescription()
        }


        y?.let {
            // do some work
        }
    }

    fun testExplicitCasting(listener: View.OnClickListener) {
        (listener as? MainActivity)?.onCreateDescription()
    }
}