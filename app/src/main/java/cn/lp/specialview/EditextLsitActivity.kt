package cn.lp.specialview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View


class EditextLsitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editetext_list)

    }

    fun EditextActivity(view: View) {
        startActivity(Intent(this, EditextActivity::class.java))

    }

    fun Editext2Activity(view: View) {
        startActivity(Intent(this, Editext2Activity::class.java))
    }

}
