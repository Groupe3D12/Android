package e.ykari.a3d12android.view.activities

import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.p2p.WifiP2pManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread
import e.ykari.a3d12android.R
import java.nio.channels.Channel

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

        mChannel = mManager?.initialize(this, mainLooper, null)
        mChannel?.also {
            channel -->
                    mReceiver = WiFiDirectBroadcastReceiver(mManager, channel, this)

        }

        val mIntentFilter = IntentFilter().apply {
            addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)
            addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)
            addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)
            addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)
        }


    }
}
