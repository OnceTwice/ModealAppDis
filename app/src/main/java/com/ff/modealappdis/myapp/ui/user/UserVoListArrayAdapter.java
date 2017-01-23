package com.ff.modealappdis.myapp.ui.user;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.ff.modealappdis.myapp.core.domain.UserVo;

public class UserVoListArrayAdapter extends ArrayAdapter<UserVo>{

    public UserVoListArrayAdapter(Context context, int resource) {
        super(context, resource);
    }
}
