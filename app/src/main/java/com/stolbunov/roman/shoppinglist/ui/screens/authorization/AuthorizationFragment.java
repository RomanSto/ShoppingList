package com.stolbunov.roman.shoppinglist.ui.screens.authorization;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stolbunov.roman.shoppinglist.R;
import com.stolbunov.roman.shoppinglist.databinding.FragmentAuthorizationBinding;
import com.stolbunov.roman.shoppinglist.entities.Event;
import com.stolbunov.roman.shoppinglist.entities.Nothing;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;

public class AuthorizationFragment extends DaggerFragment {
    private OnAuthorizationListener listener;

    @Inject
    public AuthorizationFragmentViewModelFactory factory;

    public static AuthorizationFragment newInstance() {
        return new AuthorizationFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnAuthorizationListener) context;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        listener = (OnAuthorizationListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        AuthorizationFragmentViewModel vm = ViewModelProviders
                .of(this, factory)
                .get(AuthorizationFragmentViewModel.class);

        FragmentAuthorizationBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_authorization,
                container,
                false);

        binding.setLifecycleOwner(this);
        binding.setVm(vm);

        subscribeToVM(vm);
        isCurrentUser(vm);
        return binding.getRoot();
    }

    private void subscribeToVM(AuthorizationFragmentViewModel vm) {
        vm.getShowMessageLD().observe(this, this::showMessage);
        vm.getSuccessfulAuthorizationLD().observe(this, this::notifyListener);
    }

    private void isCurrentUser(AuthorizationFragmentViewModel vm) {
        if (vm.isCurrentUser() && listener != null) {
            listener.userExists();
        }
    }

    private void notifyListener(Nothing nothing) {
        if (nothing != null && listener != null) {
            listener.onSuccessfulAuthorization();
        }
    }

    private void showMessage(Event<String> event) {
        if (!event.isHandled() && listener != null) {
            listener.showMessage(event.getValue());
        }
    }

    public interface OnAuthorizationListener {
        void onSuccessfulAuthorization();

        void showMessage(String message);

        void userExists();
    }
}