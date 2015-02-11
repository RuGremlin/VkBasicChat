package com.dbronnikov.vkchat.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.dbronnikov.vkchat.app.Model.Session;
import com.perm.kate.api.Api;


public class ChatActivity extends ActionBarActivity {
    private final int REQUEST_LOGIN = 1;
    private Session session = new Session();
    static Api vkApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new ChatFragment()).commit();

        session.restore(this);
        if (session.access_token != null) {
            vkApi = new Api(session.access_token, "4774945"); //TODO: exclude as constant
        } else {
            startLoginActivity();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                System.out.println("token = " + data.getStringExtra("token"));
            }
        }
    }

    private void startLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);

    }
}
