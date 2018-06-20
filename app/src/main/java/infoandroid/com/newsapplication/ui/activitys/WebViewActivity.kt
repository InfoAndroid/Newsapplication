package infoandroid.com.newsapplication.ui.activitys


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import infoandroid.com.newsapplication.R
import org.jetbrains.anko.indeterminateProgressDialog

class WebViewActivity : BaseActivity() {
    var mywebview: WebView? = null
    @SuppressLint("SetJavaScriptEnabled")
    var dialog:ProgressDialog??=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wev_view)
        mywebview = findViewById<WebView>(R.id.webview)
        val value = intent.getStringExtra("url")

         dialog = indeterminateProgressDialog(message = "Please wait a bit…", title = "Fetching data")

        mywebview!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                dialog!!.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                dialog!!.hide()

            }
        }
        mywebview!!.loadUrl(value)
    }

    override fun onPause() {
        super.onPause()
        if (dialog!=null)
        {
            dialog!!.dismiss()
        }

    }
}
