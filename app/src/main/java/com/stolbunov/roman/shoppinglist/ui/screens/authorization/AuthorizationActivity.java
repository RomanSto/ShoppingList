package com.stolbunov.roman.shoppinglist.ui.screens.authorization;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.stolbunov.roman.shoppinglist.R;
import com.stolbunov.roman.shoppinglist.ui.screens.shopping_list.ShoppingListActivity;

import androidx.fragment.app.FragmentTransaction;
import dagger.android.support.DaggerAppCompatActivity;

public class AuthorizationActivity extends DaggerAppCompatActivity
        implements AuthorizationFragment.OnAuthorizationListener {

    public static Intent getIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, AuthorizationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        AuthorizationFragment fragment = AuthorizationFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_auth_fragment, fragment);
        transaction.commit();
    }

    @Override
    public void onSuccessfulAuthorization() {
        startActivity();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userExists() {
        startActivity();
    }


    private void startActivity() {
        startActivity(ShoppingListActivity.getIntent(this));
        finish();
    }
}
