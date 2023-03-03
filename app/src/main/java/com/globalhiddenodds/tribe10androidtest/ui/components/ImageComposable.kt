package com.globalhiddenodds.tribe10androidtest.ui.components

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlin.math.roundToInt
import com.globalhiddenodds.tribe10androidtest.R.drawable as AppIco
import com.globalhiddenodds.tribe10androidtest.R.string as AppText

@Composable
fun LoadImageFromBitmap(
    value: Bitmap?,
    size: Dp,
    context: Context
) {
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(value)
            .crossfade(true)
            .build(),
        placeholder = painterResource(AppIco.loading_img),
        error =  painterResource(AppIco.ic_broken_image),
        contentDescription = stringResource(id = AppText.img_description),
        modifier = Modifier
            .size(size)
            .padding(20.dp)
    )
}

@Composable
fun ZoomImage(byteImage: ByteArray, onSendZoomImage: () -> Unit) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var size by remember { mutableStateOf(IntSize.Zero) }

    Box(modifier = Modifier
        .padding(5.dp)
        .clip(RectangleShape)
        .onSizeChanged { size = it }
        .animateContentSize (
            animationSpec = tween(durationMillis = 3000, delayMillis = 300, easing = FastOutSlowInEasing)
        )
        .run {
            this.pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(1f, 3f)
                    val maxX = (size.width * (scale - 1)) / 2
                    val minX = -maxX
                    offsetX = maxOf(minX, minOf(maxX, offsetX + pan.x))
                    val maxY = (size.height * (scale - 1)) / 2
                    val minY = -maxY
                    offsetY = maxOf(minY, minOf(maxY, offsetY + pan.y))
                }
            }
        }
    ) {
        AsyncImage(
            model = byteImage,
            contentDescription = stringResource(id = AppText.img_description),
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt())}
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = maxOf(1f, minOf(3f, scale)),
                    scaleY = maxOf(1f, minOf(3f, scale))
                ).clickable(onClick = onSendZoomImage).size(200.dp))
    }
}