package com.monthlycoding.dmc2

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView

class LandingActivity : AppCompatActivity() {
    private val imgLogo: ImageView by lazy { findViewById(R.id.landing_logo) }
    private val imgTitle: ImageView by lazy { findViewById(R.id.landing_title) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        imgAnimation()
    }

    private fun imgAnimation(){
        val imgLogoFadeIn = ObjectAnimator.ofFloat(imgLogo, "alpha", 0f, 1f).apply { duration = 3000 }
        val imgTitleFadeIn = ObjectAnimator.ofFloat(imgTitle, "alpha", 0f, 1f).apply { duration = 3000 }

        AnimatorSet().apply {
            play(imgLogoFadeIn)
            play(imgTitleFadeIn)
            start()
        } // image의 애니메이션화, 점점 나타나게

        Handler().postDelayed({
            val intent = Intent(this@LandingActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000) // 4초후 Landing2 액티비티로 이동

    }
}