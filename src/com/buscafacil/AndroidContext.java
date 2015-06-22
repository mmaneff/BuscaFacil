package com.buscafacil;

import android.app.Application;
import android.content.Context;

public class AndroidContext extends Application {

	private Context context;

    private AndroidContext(){}

    public void init(Context context){
        if(this.context == null){
        	this.context = context;
        }
    }

    private Context getContext(){
        return context;
    }

    public static Context currentContext(){
        return getInstance().getContext();
    }

    private static AndroidContext instance;

    public static AndroidContext getInstance(){
        return instance == null ? (instance = new AndroidContext()) : instance;
    }
    
}
