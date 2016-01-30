package com.mydemoapps.cnstnt;

import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.util.Base64;

public class Constant {
    public static double Latitude=0.0;
    public static double Longitude=0.0;


    ///////////////////////
    public static AlertDialog.Builder alertbox;
    public static AlertDialog alertDialog;
    /////////////

    public static final String errorTitle="Error";
    public static final String msgTitle="Message";
    //////
    public static void showAlertDialog(final String title, String message,
                                       final Context context, final boolean redirectToPreviousScreen)
    {
        if (alertDialog != null && alertDialog.isShowing()) {
        } else {
            alertbox = new AlertDialog.Builder(context,AlertDialog.THEME_HOLO_LIGHT);
            alertbox.setMessage(message);
            alertbox.setTitle(title);
			/*alertbox.setTitle(Gravity.CENTER);*/
            alertbox.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    alertDialog.dismiss();
                }
            });
            alertDialog = alertbox.create();
            alertDialog.show();
        }
    }
    ////////

    ////////////
    /////////
    public static boolean checkEmail(String email) {
        String expression = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*"
                + "+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        Pattern emailPattern = Pattern.compile(expression);
        return emailPattern.matcher(email).matches();
    }

    // method  to convert bitmap to base64//
    public static  String bitmapToBase64(Bitmap bitmap){
        String base64=null;
        try{
            if(bitmap!=null){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte [] _byteArray = baos.toByteArray();
                base64 = Base64.encodeToString(_byteArray,Base64.DEFAULT);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return base64;
    }






    ///////////////////////////////////////////////////////////////////





}
