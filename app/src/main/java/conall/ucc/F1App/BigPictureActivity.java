package conall.ucc.F1App;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BigPictureActivity extends BaseActivity {


    private ImageView driverImageView = null;
    private Button moreInfoButton=null;
    private TextView driverTextView = null;

    private Driver driverData = null;
    private Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigpicture);



        //get data from intent

        driverData = (Driver) getIntent().getExtras().getSerializable("data");

        //Wire
        driverTextView = findViewById(R.id.driverName);
        driverImageView = findViewById(R.id.imageView);
        moreInfoButton = findViewById(R.id.moreinfo);

        driverTextView.setText(driverData.getName());

        //set the image from data.image
        String imageName = driverData.getImage();
        imageName = imageName.substring(0, imageName.indexOf("."));

        int imageResId = getResources().getIdentifier(
                imageName,
                "drawable",
                getPackageName());

        driverImageView.setImageResource(imageResId);

        moreInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(BigPictureActivity.this, DetailedInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", driverData);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


    }
}
