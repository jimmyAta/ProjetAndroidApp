package emse.lightcontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ContextManagementActivity extends AppCompatActivity {

    private String room;
    RoomContextHttpManager httpManager = new RoomContextHttpManager(this);
    private RoomContextState contextState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_management);


        ((Button) findViewById(R.id.buttonCheck)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                room = ((EditText) findViewById(R.id.editText1))
                        .getText().toString();
                retrieveRoomContextState(room);
            }
        });

        ((Button) findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switchLight(room);
            }
        });

        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switchNoise(room);

            }
        });
    }

    public void onUpdate(RoomContextState context){
        this.contextState = context;
        System.out.println("onupdate");
        updateContextView();

    }

    private void updateContextView() {
        ((TextView) findViewById(R.id.textViewLightValue)).setText(Integer
                .toString(contextState.getLight()));
        ((TextView) findViewById(R.id.textViewNoiseValue)).setText(Float
                .toString(contextState.getNoise()));
        ImageView image=(ImageView)findViewById(R.id.imageView1);
        if (contextState.getStatus().equals("ON"))
            image.setImageResource(R.drawable.ic_bulb_on);
        else
            image.setImageResource(R.drawable.ic_bulb_off);
    }

    private void retrieveRoomContextState(String room) {
        httpManager.retrieveRoomContextState(room,this.getApplicationContext());
    }

    private void switchLight(String room){
        httpManager.switchLight(this.getApplicationContext(), room);
    }

    private void switchNoise(String room){
        httpManager.switchNoise(this.getApplicationContext(), room);
    }


}
