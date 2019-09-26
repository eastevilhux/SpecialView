package cn.lp.specialview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_editetext2.*

class Editext2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editetext2)

        passkey.setInputEditTextListener(PWEditText)
    }


}
