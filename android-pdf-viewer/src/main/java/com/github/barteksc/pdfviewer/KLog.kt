package com.github.barteksc.pdfviewer

import android.util.Log

/**
 * Created by work on 2018/1/25.
 */
class KLog(){
    companion object {
        @Volatile
        private var index = 0
        private const val tag = "_KLog"
        @JvmStatic fun info(any: Any?) {
            any?.let {
                addOne()
                Log.i(tag, "$index\t${any.toString()}")
            }
        }
        @JvmStatic fun info(vararg list: Any?) {
            addOne()
            val line = buildString {
                for (item in list) {
                    append(if (item == null) "null" else item.toString())
                    append(" ")
                }
                toString()
            }
            Log.i(tag, "$index\t$line")
        }

        @JvmStatic fun assert(result: Boolean) {
            if (!result) {
                Log.i(tag, "$index\tassert false")
            }
        }

        private inline fun addOne(){
            synchronized(::KLog){
                ++index
            }
        }
    }
}