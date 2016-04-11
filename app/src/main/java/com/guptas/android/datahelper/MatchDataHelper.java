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
        if(name.equalsIgnoreCase("Bangalore"))
        {
            image = activity.getResources().getDrawable(R.drawable.bangalore);
        }
        else if(name.equalsIgnoreCase("Mumbai"))
        {
            image = activity.getResources().getDrawable(R.drawable.mumbai);
        }
        else if(name.equalsIgnoreCase("Pune"))
        {
            image = activity.getResources().getDrawable(R.drawable.pune);
        }
        else if(name.equalsIgnoreCase("Gujrat"))
        {
            image = activity.getResources().getDrawable(R.drawable.gujrat);
        }
        else if(name.equalsIgnoreCase("Delhi"))
        {
            image = activity.getResources().getDrawable(R.drawable.delhi);
        }
        else if(name.equalsIgnoreCase("Kolkata"))
        {
            image = activity.getResources().getDrawable(R.drawable.kolkata);
        }
        else if(name.equalsIgnoreCase("Hyderabad"))
        {
            image = activity.getResources().getDrawable(R.drawable.hyderabad);
        }
        else if (name.equalsIgnoreCase("Punjab"))
        {
            image = activity.getResources().getDrawable(R.drawable.punjab);
        }
        return image;
    }
}
