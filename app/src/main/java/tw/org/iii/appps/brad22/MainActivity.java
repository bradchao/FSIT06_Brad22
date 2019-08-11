package tw.org.iii.appps.brad22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private EditText num;
    private LocationManager lmgr;
    private MyListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    123);
        }

        lmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        listener = new MyListener();

        num = findViewById(R.id.num);
        webView = findViewById(R.id.webview);
        initWebView();
    }

    public class MyJSObject {

        @JavascriptInterface
        public void callFromJS(String urname){
            Log.v("brad", "Hello, " + urname);
        }
    }


    //    @Override
    //@SuppressLint("MissingPermission")
    protected void onStart() {
        super.onStart();
        lmgr.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 1*1000,10, listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lmgr.removeUpdates(listener);
    }

    private class MyListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            Log.v("brad",lat + ", " + lng );
            webView.loadUrl("javascript:moveTo(" + lat + ", " + lng + ")");
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
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

        webView.addJavascriptInterface(new MyJSObject(), "brad");


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
