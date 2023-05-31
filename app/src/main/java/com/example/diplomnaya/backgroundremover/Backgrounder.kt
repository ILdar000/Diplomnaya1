package com.example.diplomnaya.backgroundremover

import android.graphics.Bitmap
import android.widget.ImageView

class Backgrounder {
     fun removeBg(bitmap: Bitmap,imageView: ImageView) {
        BackgroundRemover.bitmapForProcessing(
            bitmap,
            true,
            object : OnBackgroundChangeListener {
                override fun onSuccess(bitmap: Bitmap) {
                    imageView.setImageBitmap(bitmap)
                }
                override fun onFailed(exception: Exception) {
                }
            }
        )
    }
}
