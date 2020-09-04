package vision.genesis.clientapp.net;

import android.graphics.Bitmap;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 01/09/2020.
 */
public class CustomWebViewClient extends WebViewClient
{
	public interface Listener
	{
		void onIntercepted(String url);
	}

	private Listener listener;

	private String interceptingUrl;

	private boolean needIntercept = false;

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
		return false;
	}

	public void setUrlToIntercept(String url, Listener listener) {
		this.interceptingUrl = url;
		this.listener = listener;
		this.needIntercept = true;
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		if (needIntercept && url.contains(interceptingUrl)) {
			this.listener.onIntercepted(url);
		}
	}
}
