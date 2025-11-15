package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import android.widget.ImageView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var dotsLayout: LinearLayout
    private lateinit var dots: Array<ImageView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val images = listOf(R.drawable.kids_play, R.drawable.kids_flag, R.drawable.kidsfall)

        viewPager = findViewById(R.id.viewPager)

        dotsLayout = findViewById(R.id.dotsLayout)

        viewPager.adapter = ImageSliderAdapter(images)
        setupDots(images.size)
        setCurrentDot(0)

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setCurrentDot(position)
            }
        })
    }

    private fun setupDots(count: Int) {
        dots = Array(count) { null }
        dotsLayout.removeAllViews()

        for (i in 0 until count) {
            dots[i] = ImageView(this)
            dots[i]?.setImageResource(R.drawable.dot_inactive)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            dotsLayout.addView(dots[i], params)
        }
    }

    private fun setCurrentDot(index: Int) {
        for (i in dots.indices) {
            dots[i]?.setImageResource(if (i == index) R.drawable.dot_active else R.drawable.dot_inactive)
        }
    }
}
