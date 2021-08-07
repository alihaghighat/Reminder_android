package com.example.reminder;

import android.app.Application;

import ir.map.sdk_map.Mapir;


public class AppMap extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //TODO Please add your API_KEY
        Mapir.getInstance(this, "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImFkMTk1YTkxZWI0YzlmOTM3ZDU1NWQ3MmYxNDI0MDFjZDU1OWQ3MTM1NzNhMjgwMDZjOTcwYmM1NDQ5M2EwOWRmM2RmNjg4NTgxMjY1YTA4In0.eyJhdWQiOiIxMjYwMiIsImp0aSI6ImFkMTk1YTkxZWI0YzlmOTM3ZDU1NWQ3MmYxNDI0MDFjZDU1OWQ3MTM1NzNhMjgwMDZjOTcwYmM1NDQ5M2EwOWRmM2RmNjg4NTgxMjY1YTA4IiwiaWF0IjoxNjEyMTg1OTI1LCJuYmYiOjE2MTIxODU5MjUsImV4cCI6MTYxNDY5MTUyNSwic3ViIjoiIiwic2NvcGVzIjpbImJhc2ljIl19.aKLZaLeGihNYXEn4Qht7R1bGLULIDzoeDsIRtc8q5j-Lfgs7_s42ocA-tSVSdacQdPSS-WxH_5i609F7n3KWtEzewcv9Oq8LTqXnayfZbjaBL7RxiAE22YxvJAFzgqjZnh_uD9bR5c9v4lyc83zxDoKtI2sgBLCAAqlSK6y0PtEMwCSreVcba2jxeTgLbrXvTLiWfwxnOGnqSiyNSjQa7_w7j8klGpzwrLexF3c_oy4uGjhDXK2REN75WmxL3_bsLpWj-H4cnFUuUWuOk0EuYsDGZSnwM3XBE6cyAFeQU_jC0LTgCaKTdz4BNbCgYakF-nYweZ6ZQUaAMGhU4nSpDQ");
    }

}
