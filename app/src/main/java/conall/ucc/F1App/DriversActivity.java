package conall.ucc.F1App;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DriversActivity extends BaseActivity{


    @Override
    protected void onLeaveThisActivity() {
        // Don't use an exit animation when leaving the main activity!
    }

    private ListView list = null;
    private ArrayAdapter<String> adapter =null;
    private XMLDriversData driversData = null;

    // project + readme(details of technical contribution)




    ListView lst;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);

        //wire
        list = findViewById(R.id.listView);

        driversData = new XMLDriversData(getApplicationContext());

        String[] images = driversData.getImages();
        Integer[] imgid = driversData.getImageIds(images);


        String[] drivername = driversData.getNames();
        String[] team = driversData.getTeams();


        lst = (ListView) findViewById(R.id.listView);
        CustomListview customListview=new CustomListview(this,drivername,team,imgid);
        lst.setAdapter(customListview);


//        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//                //make an intent and prepare data in bundle for it
//                Intent intent = new Intent(DriversActivity.this, BigPictureActivity.class);
//                Bundle bundle = new Bundle();
//
//                bundle.putSerializable("data", driversData.getPersonData(position));
//
//                //Start main activity
//                startActivity(intent);
//            }
//
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //make an intent and prepare data in bundle for it
                Intent intent = new Intent(DriversActivity.this, BigPictureActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("data", driversData.getPersonData(position));
                intent.putExtras(bundle);

                //Start main activity
                startActivity(intent);

            }
        });
    }
}
