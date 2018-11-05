package conall.ucc.F1App;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListview extends ArrayAdapter<String> {

    private String[] drivername,team;
    private Integer[] imgid;
    private Activity context;


    public CustomListview(Activity context, String drivername[], String[] team, Integer[] imgid) {
        super(context, R.layout.listview_layout, drivername);

        this.context = context;
        this.drivername = drivername;
        this.team = team;
        this.imgid = imgid;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null)
        {

            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.listview_layout, null, true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);

        }

        else {
            viewHolder= (ViewHolder) r.getTag();

        }

        viewHolder.image_driver.setImageResource(imgid[position]);

        viewHolder.text_driver.setText(drivername[position]);
        viewHolder.text_team.setText(team[position]);


        return r;

    }

    class ViewHolder

    {
        TextView text_driver;
        TextView text_team;
        ImageView image_driver;
        ViewHolder(View v)

        {
            text_driver = (TextView) v.findViewById(R.id.text_driver);
            text_team = (TextView) v.findViewById(R.id.text_team);
            image_driver = (ImageView) v.findViewById(R.id.image_driver);

        }


    }
}
