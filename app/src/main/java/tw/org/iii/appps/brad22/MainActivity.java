package tw.org.iii.appps.brad22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private EditText num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = findViewById(R.id.num);
        webView = findViewById(R.id.webview);
        initWebView();
    }

    private void initWebView(){
        WebViewClient webViewClient = new WebViewClient();
        webView.setWebViewClient(webViewClient);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);


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

    public void lottery(View view) {
        webView.loadUrl("javascript:test1(" + num.getText().toString() + ")");
    }

//    @Override
//    public void onBackPressed() {
//        //super.onBackPressed();
//        Log.v("brad", "onBack");
//    }
}
