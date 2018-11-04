package conall.ucc.F1App;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailedInfoActivity extends BaseActivity {


    private TextView nameTextView=null;
    private TextView phoneTextView=null;
    private TextView addressTextView=null;
    private TextView countryTextView=null;
    private TextView pointsTextView=null;


    private Button webInfoButton = null;
    private Driver data;
    private Intent xintent = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedinfoactivity);


        //get data from intent

        Intent intent = getIntent();
        Bundle bundle =intent.getExtras();

        data = (Driver) bundle.getSerializable("data");


        //wire

        nameTextView = findViewById(R.id.nameView);
        phoneTextView = findViewById(R.id.textView2);
        addressTextView = findViewById(R.id.textView3);
        countryTextView = findViewById(R.id.textView4);
        pointsTextView = findViewById(R.id.textView5);

        //Popluating the fiels with data.getName() etc
        nameTextView.setText("Name: " +data.getName());
        phoneTextView.setText("Team: " +data.getTeam());
        addressTextView.setText("Races: " + data.getRaces());
       countryTextView.setText("Country: " + data.getCountry());
        pointsTextView.setText("Points: " + data.getUrl());


        webInfoButton = findViewById(R.id.button2);

        webInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getUrl()));
                xintent = new Intent(DetailedInfoActivity.this, WebActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", data);

                xintent.putExtras(bundle);
                startActivity(xintent);
            }
        });





        //deal with button
    }
}
