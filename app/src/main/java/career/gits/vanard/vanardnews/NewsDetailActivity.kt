package career.gits.vanard.vanardnews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    lateinit var dialog:SpotsDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        dialog = SpotsDialog(this)
        dialog.show()

        web_news.settings.javaScriptEnabled = true
        web_news.webChromeClient = WebChromeClient()
        web_news.webViewClient = object :WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                dialog.dismiss()
            }
        }

        if (intent != null)
            if (!intent.getStringExtra("webUrl").isEmpty())
                web_news.loadUrl(intent.getStringExtra("webUrl"))
    }
}
