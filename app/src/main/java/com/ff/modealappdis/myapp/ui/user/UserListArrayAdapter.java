package com.ff.modealappdis.myapp.ui.user;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ff.modealappdis.R;
import com.ff.modealappdis.myapp.core.domain.User;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class UserListArrayAdapter extends ArrayAdapter<User> {
    private LayoutInflater layoutInflater;
    DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
            // .showImageOnLoading( R.drawable.ic_default_profile ) // resource or drawable
            .showImageForEmptyUri( R.drawable.ic_default_profile )  // resource or drawable
            .showImageOnFail( R.drawable.ic_default_profile )       // resource or drawable
            //.resetViewBeforeLoading( false )  // default
            .delayBeforeLoading( 0 )
            //.cacheInMemory( false )   // default
            .cacheOnDisc( true )        // false is default
            //.preProcessor(...)
            //.postProcessor(...)
            //.extraForDownloader(...)
            //.considerExifParams( false )  // default
            //.imageScaleType( ImageScaleType.IN_SAMPLE_POWER_OF_2 )// default
            //.bitmapConfig( Bitmap.Config.ARGB_8888 )  // default
            //.decodingOptions(...)
            //.displayer( new SimpleBitmapDisplayer() ) // default
            //.handler( new Handler() )     // default
            .build();

    public UserListArrayAdapter(Context context) {
        super(context, R.layout.user_list);                 // user_list.xml에 보내줌
        layoutInflater = layoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            view = layoutInflater.inflate(R.layout.row_user_list, parent, false);
        }

        // 내부 ArrayList에서 해당 포지션의 User객체를 받아옴
        User user = getItem(position);

        // 프로필세팅
        ImageLoader.getInstance().displayImage(user.getProfilePic(), (ImageView)view.findViewById(R.id.profile), displayImageOptions);

        // 이름세팅
        TextView textView1 = (TextView)view.findViewById(R.id.name);
        textView1.setText("이름 : " + user.getName());

        // ID세팅
        TextView textView2 = (TextView)view.findViewById(R.id.id);
        textView2.setText("ID : "+user.getId());

        // 비밀번호세팅
        TextView textView3 = (TextView)view.findViewById(R.id.password);
        textView3.setText("비밀번호 : " + user.getName());



        return view;

        // return super.getView(position, convertView, parent);
    }

    public void add(List<User> users) {
        if(users == null || users.size()==0) {
            return;
        }

        for(User user : users) {
            add(user);
        }

        // notifyDataSetChanged();
    }
}
