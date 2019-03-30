package rohitksingh.com.gpsmapdemo;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button currLoc,openMap;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.text);
        currLoc = (Button)findViewById(R.id.currLoc);
        openMap = (Button)findViewById(R.id.openMap);

    }

    private Location getLocation()
    {
        return location;
    }

    private void setOpenMap(Location location)
    {

    }

    public Location getCurrentLocation()
    {
        return null;
    }



}
