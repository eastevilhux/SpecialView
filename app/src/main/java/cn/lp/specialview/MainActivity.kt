package cn.lp.specialview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import cn.lp.specialview.banner.BannerActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnJumpBanner(view: View) {


        startActivity(Intent(this, BannerActivity::class.java))

    }
}
