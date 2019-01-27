package com.example.olskr.cplhm3.ui.convert;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.olskr.cplhm3.mvp.model.ImageConverter;

import io.reactivex.Completable;
import timber.log.Timber;

public class ImageConverterImpl implements ImageConverter {
    Context context;

    public ImageConverterImpl(Context context) {
        this.context = context;
    }

    @Override
    public Completable convertJpegToPng(String source, String dest) {
        return Completable.fromAction(() -> {
            Thread.sleep(5000);
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(source));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, context.getContentResolver().openOutputStream(Uri.parse(dest)));
            Timber.d("CONVERTED");
        });
    }
}
