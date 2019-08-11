package tw.org.iii.appps.brad22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        initWebView();
    }

    private void initWebView(){
        WebViewClient webViewClient = new WebViewClient();
        webView.setWebViewClient(webViewClient);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);


        //webView.loadUrl("https://www.iii.org.tw");
        webView.loadUrl("file:///android_asset/brad.html");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void goForward(View view) {
        webView.goForward();
    }

    public void reload(View view) {
        webView.reload();
    }

//    @Override
//    public void onBackPressed() {
//        //super.onBackPressed();
//        Log.v("brad", "onBack");
//    }
}
