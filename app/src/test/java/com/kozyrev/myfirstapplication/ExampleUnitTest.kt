package com.kozyrev.myfirstapplication

import com.kozyrev.myfirstapplication.kt.ExampleCasting
import com.kozyrev.myfirstapplication.kt.MyCustomException
import org.junit.Assert.assertEquals
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        val r = fibonacci(5)
    }






    fun fibIter(num: Int): Int{
        var ptp = 0 // previous to previous 1 1 2 3
        var p = 1 // previous 1 2 3 5
        var sum = 0// 1 2 3 5 return

        if (num === 0) {
            return 0
        } else if (num === 1) {
            return 1
        } else {
            for (i in 2..num) {
                sum = ptp + p
                ptp = p
                p = sum
            }
        }
        return sum
    }
    fun fibonacci(n: Int): Int {
        if (n == 0) {
            return 0
        } else if (n == 1) {
            return 1
        } else {
            return (fibonacci(n - 1) + fibonacci(n - 2))
        }
    }

    @Test
    fun testDividingMethod() {
        // given
        val exampleCasting = ExampleCasting()
        // when
        val result = exampleCasting.divideTwoNumbers(4,2)
        // then
        assertEquals(result, 2)
    }

    @Test
    fun testDividingMethodCornerCondition() {
        // given
        val exampleCasting = ExampleCasting()
        // when
        try {
            val result = exampleCasting.divideTwoNumbers(4, 0)
        } catch (e: MyCustomException) {
            // then
            assert(e is MyCustomException)
        } catch (e: Exception) {
            assert(false)
        }
    }
}