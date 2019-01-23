package com.example.olskr.cplhm3.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.olskr.cplhm3.mvp.model.Model;
import com.example.olskr.cplhm3.mvp.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    Model model;

    public MainPresenter() {
        this.model = new Model();
    }
}
