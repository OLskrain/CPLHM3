package com.example.olskr.cplhm3.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.olskr.cplhm3.mvp.model.ImageConverter;
import com.example.olskr.cplhm3.mvp.view.MainView;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    MainView view;
    Scheduler scheduler;
    private ImageConverter converter;
    Disposable convertationSubscription;

    public MainPresenter(MainView view, Scheduler scheduler, ImageConverter converter) {
        this.view = view;
        this.scheduler = scheduler;
        this.converter = converter;
    }

    public void convertButtonClick() {
        view.pickImage();
    }

    public void pathsSelected(String source, String dest) {
        view.showConvertProgressDialog();
        converter.convertJpegToPng(source, dest)
                .subscribeOn(Schedulers.computation())
                .observeOn(scheduler)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        convertationSubscription = d;
                    }

                    @Override
                    public void onComplete() {
                        view.showConvertationSuccessMessage();
                        view.dismissConvertProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showConvertationFailedMessage();
                        view.dismissConvertProgressDialog();
                    }
                });
    }

    public void onConvertationCanceled() {
        if (convertationSubscription != null && !convertationSubscription.isDisposed()) {
            convertationSubscription.dispose();
            view.dismissConvertProgressDialog();
            view.showConvertationCanceledMessage();
        }
    }
}
