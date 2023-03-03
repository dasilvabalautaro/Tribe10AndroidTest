package com.globalhiddenodds.tribe10androidtest

import android.content.Context
import androidx.compose.ui.graphics.asImageBitmap
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.globalhiddenodds.tribe10androidtest.ui.tools.Transform
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import com.globalhiddenodds.tribe10androidtest.R.drawable as APPIco

@RunWith(AndroidJUnit4::class)
class TransformTest {

    @Test
    fun drawableToBitmapTest() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val bitmap = Transform.getBitmapFromDrawable(context = context, APPIco.camera_google)
        Assert.assertEquals(bitmap.asImageBitmap(), true)
    }

    @Test
    fun resizeBitmapTest() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val bitmap = Transform.getBitmapFromDrawable(context = context, APPIco.camera_google)
        val bmpResize = Transform.resizeBitmap(bitmap = bitmap)
        Assert.assertEquals(bmpResize.width, 500)
    }

}