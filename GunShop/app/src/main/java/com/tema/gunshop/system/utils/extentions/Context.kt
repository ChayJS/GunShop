package com.tema.gunshop.system.utils.extentions

import android.content.Context

fun Context.writeToFile(fileName: String, text: String) {
    val fos = openFileOutput(fileName, Context.MODE_PRIVATE)

    fos?.write(text.toByteArray())
    fos?.close()
}
