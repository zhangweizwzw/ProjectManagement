package com.bj.yatu.projectmanagement.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	private static Toast toast;

	public static void showToast(Context context, Object object) {
		String msg = "";
	
		if (object instanceof String) {
			msg = (String) object;
		} else if (object instanceof Integer) {
			msg = (Integer) object + "";
		} else if (object instanceof Boolean) {
			msg = (Boolean) object + "";
		} else if (object instanceof Float) {
			msg = (Float) object + "";
		} else if (object instanceof Long) {
			msg = (Long) object + "";
		} else {
		      if (object==null) { 
			        msg= "null" ;
			   }else{
			     	msg = object.toString();
			   }
		}
		if (toast != null) {
			toast.setText(msg);
			toast.setDuration(Toast.LENGTH_SHORT);
		} else {
			toast = Toast.makeText(context , msg, Toast.LENGTH_SHORT);
		}
		toast.show();
	}
}
