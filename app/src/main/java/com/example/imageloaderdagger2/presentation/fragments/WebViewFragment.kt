package com.example.imageloaderdagger2.presentation.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.imageloaderdagger2.presentation.viewmodels.WebViewViewModel
import dagger.android.support.DaggerFragment
import dev.ronnie.imageloaderdagger2.R
import dev.ronnie.imageloaderdagger2.databinding.WebViewBinding
import java.net.URISyntaxException
import javax.inject.Inject

class WebViewFragment : DaggerFragment(R.layout.web_view) {

    private var binding: WebViewBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: WebViewViewModel by viewModels {  Log.i("Inject", "viewModel")
        viewModelFactory
    }

    private var url: String = "https://www.pinterest.com/search/pins/?q=adnroid&rs=typed"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WebViewBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.webView.loadUrl(url)

        // JavaScript
        val webSettings: WebSettings = binding!!.webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true

        binding!!.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: android.webkit.WebView, url: String): Boolean {
                return handleUrlLoading(url)
            }
        }
    }

    private fun handleUrlLoading(url: String): Boolean {
        var currentUrl = url
        if (url.startsWith("http") || url.startsWith("https")) {
            return false
        } else if (url.startsWith("intent")) {
            try {
                val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                val fallbackUrl = intent.getStringExtra("browser_fallback_url")
                if (fallbackUrl != null) {
                    binding!!.webView.loadUrl(fallbackUrl)
                    return true
                }
            } catch (e: URISyntaxException) {
            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun onBackPressed(): Boolean {
        if (binding!!.webView.canGoBack()) {
            binding!!.webView.goBack()
            return true
        }
        return false
    }
}
