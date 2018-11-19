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
            val intent : Intent = Intent(this@MainActivity, BasketActivity::class.java)
            startActivity(intent)


        }
        recette_button.setOnClickListener {
            val intent : Intent = Intent(this@MainActivity, RecetteActivity::class.java)
            startActivity(intent)


        }
        search_button.setOnClickListener {
            val intent : Intent = Intent(this@MainActivity, SearchActivity::class.java)
            startActivity(intent)


        }

    }
}

override fun onReceive(context: Context, intent: Intent) {
    ... 
    val action: String = intent.action
    when (action) {
        WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION -> {
            val state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1)
            when (state) {
                WifiP2pManager.WIFI_P2P_STATE_ENABLED -> {
                    // Wifi P2P is enabled
                }
                else -> {
                    // Wi-Fi P2P is not enabled
                }
            }
        }
    }
}
