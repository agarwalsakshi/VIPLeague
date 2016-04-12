package com.guptas.android.datahelper;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import com.firebase.client.ValueEventListener;
import com.guptas.android.R;

/**
 * Created by sakshiagarwal on 08/04/16.
 */
public class MatchDataHelper {
    private static MatchDataHelper instance;
    private ValueEventListener mConnectedListener;

    public static MatchDataHelper getInstance() {
        if (instance == null) {
            instance = new MatchDataHelper();
        }
        return instance;
    }


    public Drawable convertNameToLogo(String name, Activity activity) {
        Drawable image = null;
        if(name.equalsIgnoreCase("RCB"))
        {
            image = activity.getResources().getDrawable(R.drawable.bangalore);
        }
        else if(name.equalsIgnoreCase("MI"))
        {
            image = activity.getResources().getDrawable(R.drawable.mumbai);
        }
        else if(name.equalsIgnoreCase("RPS"))
        {
            image = activity.getResources().getDrawable(R.drawable.pune);
        }
        else if(name.equalsIgnoreCase("GL"))
        {
            image = activity.getResources().getDrawable(R.drawable.gujrat);
        }
        else if(name.equalsIgnoreCase("DD"))
        {
            image = activity.getResources().getDrawable(R.drawable.delhi);
        }
        else if(name.equalsIgnoreCase("KKR"))
        {
            image = activity.getResources().getDrawable(R.drawable.kolkata);
        }
        else if(name.equalsIgnoreCase("SRH"))
        {
            image = activity.getResources().getDrawable(R.drawable.hyderabad);
        }
        else if (name.equalsIgnoreCase("KXIP"))
        {
            image = activity.getResources().getDrawable(R.drawable.punjab);
        }
        return image;
    }
}
