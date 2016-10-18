package id.co.imastudio.sample_intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btnSub1, btnSub2, btnDial;
    private String strIntent;
    private EditText txtIntent;
    SmsManager smsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSub1 = (Button) findViewById(R.id.btn_activity_sub_1);
        btnSub2 = (Button) findViewById(R.id.btn_activity_sub_2);
        btnDial = (Button) findViewById(R.id.btn_activity_dial);

        txtIntent = (EditText) findViewById(R.id.text_Intent);

        smsManager = SmsManager.getDefault();
        btnSub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Sub1Activity.class);
                startActivity(intent);
            }
        });

        btnSub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getEditextContent()) {
                    txtIntent.setError(null);
                    Intent intent = new Intent(MainActivity.this, Sub2Activity.class);
                    intent.putExtra(Sub2Activity.KEY_DATA, strIntent);
                    startActivityForResult(intent, 0);
                }
            }
        });


        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getEditextContent()) {
                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.putExtra("address"  ,"+6282243609178");
                    sendIntent.putExtra("sms_body", strIntent);
                    sendIntent.setType("vnd.android-dir/mms-sms");
                    startActivity(sendIntent);
                }
            }
        });
    }

    private boolean getEditextContent() {
        this.strIntent = "";
        this.strIntent = this.txtIntent.getText().toString();
        if (this.strIntent.matches("")) {
            this.txtIntent.setError("This field is required");
            this.txtIntent.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}

