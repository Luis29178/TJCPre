package com.example.tjcpre

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class CustomsRaidmodeWeb : AppCompatActivity() {

    companion object {
        val WEBVIEW_JS = "WebViewJS"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.customs_raid_mode_web))
        var webView = findViewById<WebView>(R.id.WebRaidModeCustoms)

        webViewSetup(webView)
    }

    private fun webViewSetup(_webView: WebView){
        _webView.webViewClient = WebViewClient()
        _webView.apply {
            loadUrl("https://customs-raidmode.web.app")
            settings.javaScriptEnabled = true
            settings.builtInZoomControls = true
            settings.displayZoomControls = true
            settings.loadWithOverviewMode =true



        }


    }
}