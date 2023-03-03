package com.globalhiddenodds.tribe10androidtest.ui.tools

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.core.content.ContextCompat
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import java.io.ByteArrayOutputStream
import kotlin.math.max

object Transform {
    private const val maxSize = 512

    private suspend fun uriToBitmap(context: Context, uri: Uri?): Bitmap {
        var newHeight = 1
        var newWidth = 1
        val loader = ImageLoader(context)


        val request = ImageRequest.Builder(context)
            .data(uri)
            .allowHardware(false)
            .build()

        val result = (loader.execute(request) as SuccessResult).drawable
        val bitmap = (result as BitmapDrawable).bitmap

        if (max(bitmap.height, bitmap.width) > maxSize && bitmap.width > 0 && bitmap.height > 0) {
            val scaleFactor = (bitmap.width.toDouble() / bitmap.height.toDouble())
            newWidth = maxSize
            newHeight = (newWidth.toDouble() / scaleFactor).toInt()
        } else {
            newWidth = bitmap.width
            newHeight = bitmap.height
        }

        return Bitmap.createScaledBitmap(
            bitmap, newWidth, newHeight, true
        )
    }

    suspend fun uriToByteArray(context: Context, uri: Uri?): ByteArray {
        val bmp = uriToBitmap(context = context, uri = uri)
        val outputStream = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return outputStream.toByteArray()
    }

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return outputStream.toByteArray()

    }

    fun resizeBitmap(bitmap: Bitmap): Bitmap {
        var newHeight = 1
        var newWidth = 1

        if (max(bitmap.height, bitmap.width) > maxSize && bitmap.width > 0 && bitmap.height > 0) {
            val scaleFactor = (bitmap.width.toDouble() / bitmap.height.toDouble())
            newWidth = maxSize
            newHeight = (newWidth.toDouble() / scaleFactor).toInt()
        } else {
            newWidth = bitmap.width
            newHeight = bitmap.height
        }

        return Bitmap.createScaledBitmap(
            bitmap, newWidth, newHeight, true
        )
    }

    fun getBitmapFromDrawable(context: Context, drawable: Int): Bitmap {
        val db = ContextCompat.getDrawable(context, drawable)
        val bit = Bitmap.createBitmap(
            db!!.intrinsicWidth, db.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bit)

        db.setBounds(0, 0, canvas.width, canvas.height)

        db.draw(canvas)

        return bit
    }
}