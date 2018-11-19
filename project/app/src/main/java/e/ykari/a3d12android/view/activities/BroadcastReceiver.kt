package e.ykari.a3d12android.view.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.wifi.p2p.WifiP2pConfig
import android.net.wifi.p2p.WifiP2pDevice
import android.net.wifi.p2p.WifiP2pDeviceList
import android.net.wifi.p2p.WifiP2pManager
import android.os.AsyncTask
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.system.Os.close
import android.widget.TextView
import java.io.File
import java.io.FileOutputStream
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket


/**
 * A BroadcastReceiver that notifies of important Wi-Fi p2p events.
 */
class WiFiDirectBroadcastReceiver(
        private val mManager: WifiP2pManager,
        private val mChannel: WifiP2pManager.Channel,
        private val mActivity: MyWifiActivity
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action: String = intent.action
        when (action) {
            WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION -> {
                // Check to see if Wi-Fi is enabled and notify appropriate activity
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
            WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION -> {
                // Call WifiP2pManager.requestPeers() to get a list of current peers
                mManager?.requestPeers(mChannel) { peers: WifiP2pDeviceList? ->
                    // Handle peers list
            }
            WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION -> {
                // Respond to new connection or disconnections

            }
            WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION -> {
                // Respond to this device's wifi state changing
            }
        }
    }

        val device: WifiP2pDevice = ...
        val config = WifiP2pConfig()
        config.deviceAddress = device.deviceAddress
        mChannel?.also { channel ->
            mManager?.connect(channel, config, object : WifiP2pManager.ActionListener {

                override fun onSuccess() {
                    //success logic
                }

                override fun onFailure(reason: Int) {
                    //failure logic
                }
            }
        })
    /* register the broadcast receiver with the intent values to be matched */
    override fun onResume() {
        super.onResume()
        mReceiver?.also { receiver ->
            registerReceiver(receiver, mIntentFilter)
        }
    }

    /* unregister the broadcast receiver */
    override fun onPause() {
        super.onPause()
        mReceiver?.also { receiver ->
            unregisterReceiver(receiver)
        }
    }


}

    class FileServerAsyncTask(
            private val context: Context,
            private var statusText: TextView
    ) : AsyncTask<Void, Void, String?>() {

        override fun doInBackground(vararg params: Void): String? {
            /**
             * Create a server socket.
             */
            val serverSocket = ServerSocket(8888)
            return serverSocket.use {
                /**
                 * Wait for client connections. This call blocks until a
                 * connection is accepted from a client.
                 */
                val client = serverSocket.accept()
                /**
                 * If this code is reached, a client has connected and transferred data
                 * Save the input stream from the client as a JPEG file
                 */
                val f = File(Environment.getExternalStorageDirectory().absolutePath +
                        "/${context.packageName}/wifip2pshared-${System.currentTimeMillis()}.jpg")
                val dirs = File(f.parent)

                dirs.takeIf { it.doesNotExist() }?.apply {
                    mkdirs()
                }
                f.createNewFile()
                val inputstream = client.getInputStream()
                copyFile(inputstream, FileOutputStream(f))
                serverSocket.close()
                f.absolutePath
            }
        }

        private fun File.doesNotExist(): Boolean = !exists()

        /**
         * Start activity that can handle the JPEG image
         */
        override fun onPostExecute(result: String?) {
            result?.run {
                statusText.text = "File copied - $result"
                val intent = Intent(android.content.Intent.ACTION_VIEW).apply {
                    setDataAndType(Uri.parse("file://$result"), "image/*")
                }
                context.startActivity(intent)
            }
        }
        val context = applicationContext
        val host: String
        val port: Int
        val len: Int
        val socket = Socket()
        val buf = ByteArray(1024)

        try {
            /**
             * Create a client socket with the host,
             * port, and timeout information.
             */
            socket.bind(null)
            socket.connect((InetSocketAddress(host, port)), 500)

            /**
             * Create a byte stream from a JPEG file and pipe it to the output stream
             * of the socket. This data will be retrieved by the server device.
             */
            val outputStream = socket.getOutputStream()
            val cr = context.contentResolver
            val inputStream: InputStream = cr.openInputStream(Uri.parse("path/to/picture.jpg"))
            while (inputStream.read(buf).also { len = it } != -1) {
                outputStream.write(buf, 0, len)
            }
            outputStream.close()
            inputStream.close()
        } catch (e: FileNotFoundException) {
            //catch logic
        } catch (e: IOException) {
            //catch logic
        } finally {
            /**
             * Clean up any open sockets when done
             * transferring or if an exception occurred.
             */
            socket.takeIf { it.isConnected }?.apply {
                close()
            }
        }
    }
