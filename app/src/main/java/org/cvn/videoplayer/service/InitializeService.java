package org.cvn.videoplayer.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import org.cvn.videoplayer.base.BaseConfig;
import org.cvn.videoplayer.util.LogUtils;

public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "initApplication";

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     * Used to name the worker thread, important only for debugging.
     */
    /*public InitializeService(String name) {
        super(name);
    }*/

    public InitializeService(){
        super("InitializeService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication();
            }
        }
    }

    private void initApplication() {
        BaseConfig.INSTANCE.initConfig();
        LogUtils.logDebug = true;
    }

}
