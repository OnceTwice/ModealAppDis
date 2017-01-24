package com.ff.modealappdis.myapp.ui.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.ff.modealappdis.R;
import com.ff.modealappdis.myapp.core.domain.UserVo;

import java.util.List;

public class UserVoListArrayAdapter extends ArrayAdapter<UserVo> {
    private LayoutInflater layoutInflater;
    int i = 0;

    public UserVoListArrayAdapter(Context context) {
        super(context,  R.layout.uservo_list);
        layoutInflater = layoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            Log.d(++i + "번째로", "실행");
            view = layoutInflater.inflate(R.layout.read_list, parent, false);
        }

        // 내부 ArrayList에서 해당 포지션의 UserVo객체를 받아옴
        UserVo userVo = getItem(position);

        EditText et1 = (EditText) view.findViewById(R.id.etID);
        EditText et2 = (EditText) view.findViewById(R.id.etGender);
        EditText et3 = (EditText) view.findViewById(R.id.etLocation);
        EditText et4 = (EditText) view.findViewById(R.id.etBirth);

        et1.setText(userVo.getId());
        et2.setText(userVo.getGender());
        et3.setText(userVo.getLocation());
        et4.setText(userVo.getBirth());

        return view;
        // return super.getView(position, convertView, parent);
    }

    public void add(List<UserVo> userVo) {
        if(userVo == null || userVo.size()==0) {
            return;
        }

        for(UserVo usersVo : userVo) {
            add(usersVo);
        }
    }
}
