package com.infined.infine;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;

public class SendFeedback extends AppCompatActivity {

    private Toolbar mToolbar;
    EditText sendTo;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Send Me Feedback");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sendTo = (EditText)findViewById(R.id.sendTo);

        txt = (TextView)findViewById(R.id.textView2);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/Montserrat-Regular.ttf",true);



        final EditText to = (EditText) findViewById(R.id.sendTo);
        final EditText subject = (EditText) findViewById(R.id.subject);
        final EditText message = (EditText) findViewById(R.id.EmailText);

        Button sendE = (Button) findViewById(R.id.sendEmail);
        sendE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toS = to.getText().toString();
                String subS = subject.getText().toString();
                String mesS = message.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                //email.putExtra(Intent.EXTRA_EMAIL, toS);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ toS});
                email.putExtra(Intent.EXTRA_SUBJECT, subS);
                email.putExtra(Intent.EXTRA_TEXT, mesS);

                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose app to send mail"));

            }
        });

    }
}
