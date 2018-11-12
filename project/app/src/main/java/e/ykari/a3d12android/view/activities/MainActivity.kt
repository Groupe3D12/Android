package e.ykari.a3d12android.view.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread
import e.ykari.a3d12android.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        basket_button.setOnClickListener {
            val intent = Intent(this@MainActivity, BasketActivity::class.java)
            startActivity(intent)


        }

    }
}
