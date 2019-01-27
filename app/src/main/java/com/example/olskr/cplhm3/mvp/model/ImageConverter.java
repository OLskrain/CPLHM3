package com.example.olskr.cplhm3.mvp.model;

import io.reactivex.Completable;

public interface ImageConverter
{
    Completable convertJpegToPng(String source, String dest);
}
