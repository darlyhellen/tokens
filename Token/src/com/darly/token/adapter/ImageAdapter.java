package com.darly.token.adapter;

 
 import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
 
 public class ImageAdapter extends PagerAdapter {
     private List<View> list;
 
     public ImageAdapter(List<View> list) {
         this.list = list;
     }
 
     @Override
     public int getCount() {
         if (list.size() != 0) {
             return list.size();
         }
         return 0;
     }
 
     @Override
     public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
     }
     
     @Override
     public Object instantiateItem(ViewGroup container, int position) {
         container.addView(list.get(position));
         return list.get(position);
     }
     
     @Override
     public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView(list.get(position));
     }
 
 }