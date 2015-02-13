package com.dbronnikov.vkchat.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.dbronnikov.vkchat.app.Model.Session;


public class ChatActivity extends ActionBarActivity {
    private final int REQUEST_LOGIN = 1;
    private Session session = new Session();
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        transaction = getSupportFragmentManager().beginTransaction();

        session.restore(this);
        if (session.access_token != null) {
            Log.i("ChatActivity", "token =" + session.access_token + ", user_id = " + session.user_id);
            startChatFragmentWithSession();
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
                session.access_token = data.getStringExtra("token");
                session.user_id = data.getLongExtra("user_id", 0);
                session.save(this);
                startChatFragmentWithSession();
                Log.i("ChatActivity", "token =" + session.access_token + ", user_id = " + session.user_id);
            }
        }
    }

    private void startChatFragmentWithSession() {
        Bundle bundle = new Bundle();
        bundle.putString("token", session.access_token);
        bundle.putLong("user_id", session.user_id);
        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.fragment_container, fragment).commit();
    }

    private void startLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);

    }
}
