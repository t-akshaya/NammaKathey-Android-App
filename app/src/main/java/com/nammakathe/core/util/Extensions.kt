package com.nammakathe.core.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun View.show() { visibility = View.VISIBLE }
fun View.hide() { visibility = View.GONE }
fun View.invisible() { visibility = View.INVISIBLE }

fun View.animateBounce() {
    val scaleX = ObjectAnimator.ofFloat(this, "scaleX", 0f, 1.15f, 0.9f, 1f)
    val scaleY = ObjectAnimator.ofFloat(this, "scaleY", 0f, 1.15f, 0.9f, 1f)
    val alpha = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
    AnimatorSet().apply {
        playTogether(scaleX, scaleY, alpha)
        duration = 600
        interpolator = OvershootInterpolator()
        start()
    }
}

fun View.animatePulse() {
    ObjectAnimator.ofFloat(this, "scaleX", 1f, 1.08f, 1f).apply {
        duration = 400; repeatCount = 1; start()
    }
    ObjectAnimator.ofFloat(this, "scaleY", 1f, 1.08f, 1f).apply {
        duration = 400; repeatCount = 1; start()
    }
}

fun View.animateShake() {
    ObjectAnimator.ofFloat(this, "translationX", 0f, -20f, 20f, -15f, 15f, -10f, 10f, 0f).apply {
        duration = 500; start()
    }
}

fun View.setGradientBackground(startColor: String, endColor: String) {
    try {
        val gradient = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(Color.parseColor(startColor), Color.parseColor(endColor))
        )
        background = gradient
    } catch (e: Exception) { /* ignore bad colors */ }
}

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
fun Fragment.toast(message: String) = requireContext().toast(message)

fun Long.toDateString(): String =
    SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(this))

fun Int.toTimeString(): String {
    val m = this / 60; val s = this % 60
    return if (m > 0) "${m}m ${s}s" else "${s}s"
}

fun String.safeColor(fallback: String = "#FF6B35"): Int = try {
    Color.parseColor(this)
} catch (e: Exception) {
    Color.parseColor(fallback)
}
