package conall.ucc.F1App;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
public class WebActivity extends BaseActivity {

    TextView txtMsg;
    Driver data;
    String url = null;
    String name;
    String info2;
    String content;
    WebView myWebView;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        // Log.d("X25","actvity start");

        txtMsg = findViewById(R.id.textView1);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        data = (Driver) bundle.getSerializable("data");

        url = data.getUrl();
        txtMsg.setText("Information about " + data.getName() + " from Wikipedia");
        String driver_name = data.getName();
        name = driver_name.replaceAll(" ", "_");


        try {

            final Document[] doc = new Document[1];

            StringRequest sRequest = new StringRequest(Request.Method.GET, "https://en.wikipedia.org/wiki/" + name, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {

                        Log.d("X25", "code start");

                        doc[0] = Jsoup.parse(response);
                        Elements paragraphs = doc[0].select("p");

                        String firstParagraph = Jsoup.clean(paragraphs.get(1).toString(), Whitelist.simpleText());
                        String secoundParagraph = Jsoup.clean(paragraphs.get(2).toString(), Whitelist.simpleText());
                        String thirdParagraph = Jsoup.clean(paragraphs.get(3).toString(), Whitelist.simpleText());
                        String fourthParagraph = Jsoup.clean(paragraphs.get(4).toString(), Whitelist.simpleText());
                        String fifthParagraph = Jsoup.clean(paragraphs.get(5).toString(), Whitelist.simpleText());
                        String sixthParagraph = Jsoup.clean(paragraphs.get(6).toString(), Whitelist.simpleText());

                        info2 = firstParagraph + "<br/><br/>" + secoundParagraph + "<br/><br/>" + thirdParagraph + "<br/><br/>" + fourthParagraph + "<br/><br/>";

                        int x = info2.length();

                        if (x < 2000) {
                            info2 = info2 + fifthParagraph + "<br/><br/>";
                            x = info2.length();
                            if (x < 1800) {
                                info2 = info2 + sixthParagraph;

                            }

                        }

                        Log.d("X21", "test" + x);

                        info2 = info2.replaceAll("\\[.*?\\] ?", "");
                        info2 = info2.replaceAll("\\(.*?\\) ?", "");


                        try {

                            myWebView = (WebView) findViewById(R.id.webview1);
                            myWebView.setWebChromeClient(new WebChromeClient());
                            myWebView.getSettings().setJavaScriptEnabled(true);
                            // myWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
                            myWebView.setInitialScale(100);
                            String url = "file:///android_asset/my_local_page1.html";
                            String customHtml = "<html><style>\n" +
                                    "\n" +
                                    "body {\n" +
                                    "\n" +
                                    "background-color: #ffadad;\n" +
                                    "width: 95%;\n" +
                                    "font-size: 38px;\n" +
                                    "\n" +
                                    "\n" +
                                    "}\n" +
                                    "\n" +
                                    "</style><head>\n" +
                                    "    <meta charset=\"utf-8\"></meta>\n" +
                                    "    <meta name=\"viewport\" content=\"initial-scale=1.0, maximum-scale=1.0, width=device-width, user-scalable=no\" />\n" +
                                    "    <title>Android WebView</title>\n" +
                                    "\n" +
                                    "</head><body>" + info2 + "<br/><br/>" + "</body></html>";
                            myWebView.loadData(customHtml, "text/html", "UTF-8");


                        } catch (Exception e) {
                            Log.d("X25:", e.toString());

                        }


                    } catch (Exception e) {
                        Log.d("X25:", "testerror: " + e);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("X25:", error.toString());
                }
            });

            VolleySingleton.getInstance(this).addToRequestQueue(sRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_menu, menu);
        return true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String option = item.getTitle().toString();
        if (option.equals("Zoom In"))
            myWebView.zoomIn();
        if (option.equals("Zoom Out"))
            myWebView.zoomOut();
        return true;
    }


    }