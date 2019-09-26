package cn.lp.specialview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import cn.lp.input_library.BorderPWEditText
import kotlinx.android.synthetic.main.activity_editetext.*

class EditextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editetext)


        BorderPWEditText.setmInputOverListener(object : BorderPWEditText.InputOverListener {
            override fun InputOver(string: String?) {
                Toast.makeText(baseContext, "当前接收的数据为：${string}", Toast.LENGTH_LONG).show()
            }

            override fun InputHint(string: String?) {
                Toast.makeText(baseContext, string, Toast.LENGTH_LONG).show()
            }

        })
        BorderPWEditText.isFocusable = true
        BorderPWEditText.isFocusableInTouchMode = true
    }


}
