package com.mydemoapps.frgmnt;

/**
 * Created by Ravi on 29/07/15.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import eu.janmuller.android.simplecropimage.CropImage;
import com.mydemoapps.R;
import com.mydemoapps.cnstnt.Constant;
import com.mydemoapps.cnstnt.InternalStorageContentProvider;
import com.mydemoapps.cnstnt.NetworkAvailablity;


public class CameraImageUpload extends Fragment {

    public ProgressDialog progressDialog;
    String server_responce = null;

    final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
    //For Image crope

    public static final int REQUEST_CODE_GALLERY = 0x1;
    public static final int REQUEST_CODE_TAKE_PICTURE = 0x2;
    public static final int REQUEST_CODE_CROP_IMAGE = 0x3;
    private File mFileTemp;

    EditText first_name;
    EditText last_name;
    EditText email;
    EditText password;
    EditText c_password;
    EditText gener;
    EditText country;
    Button sign_up;
    String base_64 = "";

    String _first_name, _last_name, _email, _password, _c_passord, _gender, _country;

    ImageView image;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;



    public CameraImageUpload() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), InternalStorageContentProvider.TEMP_PHOTO_FILE_NAME);
        } else {
            mFileTemp = new File(getActivity().getFilesDir(), InternalStorageContentProvider.TEMP_PHOTO_FILE_NAME);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);

        image = (ImageView) rootView.findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        first_name = (EditText) rootView.findViewById(R.id.first_name);

        last_name = (EditText) rootView.findViewById(R.id.last_name);

        email = (EditText) rootView.findViewById(R.id.email);

        password = (EditText) rootView.findViewById(R.id.password);

        c_password = (EditText) rootView.findViewById(R.id.c_password);

        gener = (EditText) rootView.findViewById(R.id.gener);

        country = (EditText) rootView.findViewById(R.id.country);

        sign_up = (Button) rootView.findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                _first_name = first_name.getText().toString().trim();
                _last_name = last_name.getText().toString().trim();
                _email = email.getText().toString().trim();
                _password = password.getText().toString().trim();
                _c_passord = c_password.getText().toString().trim();
                _gender = gener.getText().toString().trim();
                _country = country.getText().toString().trim();

                if (validateFields()) {
                    GetAllCityUrlConnection getAllCityUrlConnection = new GetAllCityUrlConnection();
                    getAllCityUrlConnection.execute();
                }
            }
        });


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    ///////////////////////


    private boolean validateFields() {
  /*      if (base_64.length() == 0 || base_64 == null || base_64.equalsIgnoreCase(""))
        {
            Constant.showAlertDialog(Constant.errorTitle, "Please select profile picture", MyClass.this, false);
            return false;
        }
        else*/
        if (_first_name.length() == 0 || _first_name == null || _first_name.equalsIgnoreCase("")) {
            Constant.showAlertDialog(Constant.errorTitle, "Please enter your first name.", getActivity(),false);
            return false;
        } else if (_first_name.length() < 3) {
            Constant.showAlertDialog(Constant.errorTitle, "First Name must have min 3 and max 20 characters.",getActivity(), false);
            return false;
        } else if (_last_name.length() == 0 || _last_name == null || _last_name.equalsIgnoreCase("")) {
            Constant.showAlertDialog(Constant.errorTitle, "Please enter your last name.",getActivity(), false);
            return false;
        } else if (_last_name.length() < 3) {
            Constant.showAlertDialog(Constant.errorTitle, "Last Name must have min 3 and max 20 characters.",getActivity(), false);
            return false;
        } else if (_email.length() == 0 || _email == null || _email.equalsIgnoreCase("")) {
            Constant.showAlertDialog(Constant.errorTitle, "Please enter an email address",getActivity(), false);
            return false;
        } else if (!Constant.checkEmail(_email)) {
            Constant.showAlertDialog(Constant.errorTitle, "Please enter a valid email address.",getActivity(), false);
            return false;
        } else if (_password.length() == 0 || _password == null || _password.equalsIgnoreCase("")) {
            Constant.showAlertDialog(Constant.errorTitle, "Please create a password.",getActivity(), false);
            return false;
        } else if (_password.contains(" ")) {
            Constant.showAlertDialog(Constant.errorTitle, "Please remove spaces from the password.", getActivity(), false);
            return false;
        } else if (_password.length() < 3) {
            Constant.showAlertDialog(Constant.errorTitle, "Password must have min 3 and max 25 characters.",getActivity(), false);
            return false;
        } else if (_c_passord.length() == 0 || _c_passord == null || _c_passord.equalsIgnoreCase("")) {
            Constant.showAlertDialog(Constant.errorTitle, "Please confirm your password.",getActivity(), false);
            return false;
        }
        /*else if(userCnfmPassword.contains(" "))
		{
			Constant.showAlertDialog(Constant.errorTitle, "Please remove spaces from the confirm password.", MyClass.this, false);
			return false;
		}*/
        else if (!_password.equals(_c_passord)) {
            Constant.showAlertDialog(Constant.errorTitle, "Password and Confirm Password should be the same.",getActivity(), false);
            return false;
        } else if (_gender.length() == 0 || _gender == null || _gender.equalsIgnoreCase("")) {
            Constant.showAlertDialog(Constant.errorTitle, "Please enter your gender.",getActivity(), false);
            return false;
        } else if (_country.length() == 0 || _country == null || _country.equalsIgnoreCase("")) {
            Constant.showAlertDialog(Constant.errorTitle, "Please enter your country.",getActivity(), false);
            return false;
        }
        return true;

    }


    ////////////////////////////////////////////////////////////
    public class GetAllCityUrlConnection extends AsyncTask<String, Void, String> {
        @Override
        protected void onPostExecute(String result) {

            progressDialog.cancel();
            if (server_responce != null && !server_responce.equalsIgnoreCase("")) {
                try {
                    System.out.println(server_responce);

                    Constant.showAlertDialog(Constant.errorTitle, server_responce, getActivity(), false);
                    Toast.makeText(getActivity(), "POST  " + server_responce, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    //MSG_SERVER_COMMUNICATION_FALIURE
                }
            } else {
                //MSG_SERVER_COMMUNICATION_FALIURE
            }
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(true);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            if (NetworkAvailablity.checkNetworkStatus(getActivity())) {
                try {

                    HttpURLConnection urlConnection;
                    InputStream inputStream = null;


                    try {

                    /*Uri.Builder builder = new Uri.Builder()
					.appendQueryParameter("firstname", _first_name)
                            .appendQueryParameter("lastname", _last_name)
                            .appendQueryParameter("email", _email)
                            .appendQueryParameter("password",_password)
                            .appendQueryParameter("country", _country)
                            .appendQueryParameter("gender",_gender)
                            .appendQueryParameter("profilepicture", "");



                    String data = builder.build().getEncodedQuery();*/


                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("firstname", _first_name);
                        jsonObject.put("lastname", _last_name);
                        jsonObject.put("email", _email);
                        jsonObject.put("password", _password);
                        jsonObject.put("country", _country);
                        jsonObject.put("gender", _gender);
                        jsonObject.put("profilepicture", "");


                        String data = jsonObject.toString();

                   /* try {
                        jsonObject.put("abc","123");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
*/

						/*JSONArray jsonArray = new JSONArray();
						JSONObject jsonObject = new JSONObject();
						try {
							jsonObject.put("abc","123");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						jsonArray.put(jsonObject);
						System.out.println(jsonArray);
                         String dataa =jsonArray.toString();
						System.out.println(dataa);*/
                        //			String temp=URLEncoder.encode(uri, "UTF-8");
                        // URL url = new URL("http://rideversity.co/rideversity_server/index.php/webservices/user/getAllCitiesCorrespondigToState");
                        URL url = new URL("http://services.conceptiondesigns.com/themissionrestservices/SignUp");
                        urlConnection = (HttpURLConnection) ((url.openConnection()));
                        urlConnection.setDoInput(true);
                        urlConnection.setDoOutput(true);
                        urlConnection.setUseCaches(false);
                        urlConnection.setChunkedStreamingMode(1024);
                        urlConnection.setReadTimeout(5000);


                        //// userd for oauth key
                        //	urlConnection.setRequestProperty("oAuthKey",SharedPref.getSharedPrefData(VendorEditProfile.this,SharedPref.PREFRENCE_KEY_OUTH));
                        //	urlConnection.setRequestProperty("Content-Type", "application/json");
                        //	urlConnection.setRequestProperty("Accept", "application/json");


                        urlConnection.setRequestMethod("POST");
                        urlConnection.connect();

                        //Write
                        OutputStream outputStream = urlConnection.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                        writer.write(data);
                        writer.close();
                        outputStream.close();
					/*
						//Write
						DataOutputStream wr = new DataOutputStream (urlConnection.getOutputStream ());
						wr.writeBytes (data);
						wr.flush ();
						wr.close ();*/

                        //Read
                        int statusCode = urlConnection.getResponseCode();

						/* 200 represents HTTP OK */
                        if (statusCode == 200) {
                            inputStream = new BufferedInputStream(urlConnection.getInputStream());
                            server_responce = convertInputStreamToString(inputStream);
                            //   result = 1;  Successful
                        } else {

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(), "network error 200000", Toast.LENGTH_SHORT).show();

                                }
                            });
                            //   result = 0; "Failed to fetch data!";
                        }


                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "exception", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            } else {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "network error", Toast.LENGTH_SHORT).show();

                    }
                });   //MSG_SERVER_COMMUNICATION_FALIURE
            }
            return null;
        }
        ///////////////////////////////

    }

    public String convertInputStreamToString(InputStream in) {
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in));

            StringBuilder str = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        } catch (Exception ex) {
            result = "Error";
        }
        return result;
    }


    ///////////////////////////


    private void selectImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
					/*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, REQUEST_CAMERA);*/
                    Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    try {
                        Uri mImageCaptureUri = null;
                        String state = Environment.getExternalStorageState();
                        if (Environment.MEDIA_MOUNTED.equals(state)) {
                            mImageCaptureUri = Uri.fromFile(mFileTemp);
                        } else {
							/*
							 * The solution is taken from here: http://stackoverflow.com/questions/10042695/how-to-get-camera-result-as-a-uri-in-data-folder
							 */
                            mImageCaptureUri = InternalStorageContentProvider.CONTENT_URI;
                        }
                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                        intent2.putExtra("return-data", true);
                        /*getActivity().*/startActivityForResult(intent2, REQUEST_CODE_TAKE_PICTURE);
                    } catch (ActivityNotFoundException e) {

                        Log.d("TAG", "cannot take picture", e);
                    }
                } else if (items[item].equals("Choose from Library")) {
					/*Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					intent.setType("image/*");
					startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);*/
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    /*getActivity().*/startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    ///////////////////////////////////
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("onActivityResult =requestCode  =" + requestCode);
        if (resultCode != getActivity().RESULT_OK) {
            return;
        }
        Bitmap bitmap;
        switch (requestCode) {
            case REQUEST_CODE_GALLERY:
                System.out.println("i am REQUEST_CODE_GALLERY==================");
                if (resultCode == getActivity().RESULT_OK) {
                    try {

                        InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                        FileOutputStream fileOutputStream = new FileOutputStream(mFileTemp);
                        copyStream(inputStream, fileOutputStream);
                        fileOutputStream.close();
                        inputStream.close();

                        startCropImage();

                    } catch (Exception e) {

                        Log.e("TAG", "Error while creating temp file", e);
                    }


                } else {
                    //Toast.makeText(SignUpActivity.this, "Unable to get Image", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_TAKE_PICTURE:
                System.out.println("i am REQUEST_CODE_TAKE_PICTURE==================");
                if (resultCode == getActivity().RESULT_OK) {
                    startCropImage();


                } else {
                    //Toast.makeText(SignUpActivity.this, "Unable to get Image", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_CROP_IMAGE:

                //			System.out.println("i am REQUEST_CODE_CROP_IMAGE==================");
                String path = data.getStringExtra(CropImage.IMAGE_PATH);
                if (path == null) {

                    return;
                }
                bitmap = BitmapFactory.decodeFile(mFileTemp.getPath());
                fixOrientationBitmap(bitmap);
                //			System.out.println("i am _image.setImageBitmap==================");
                //Toast.makeText(Profile.this,"Done",1).show();
                image.setImageBitmap(bitmap);
                base_64 = Constant.bitmapToBase64(bitmap);

                break;
        }
    }

    /////////////////////////////////
    /////////////////
    private void startCropImage() {

        Intent intent = new Intent(getActivity(), CropImage.class);
        intent.putExtra(CropImage.IMAGE_PATH, mFileTemp.getPath());
        intent.putExtra(CropImage.SCALE, true);
        intent.putExtra(CropImage.ASPECT_X, 1);
        intent.putExtra(CropImage.ASPECT_Y, 1);


        System.out.println("startActivityForResult REQUEST_CODE_CROP_IMAGE");
        /*getActivity().*/startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }

    public static void copyStream(InputStream input, OutputStream output)
            throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    public static String convertBitmapToBase64(Bitmap image) {
        String base64Image = null;
        Bitmap resizedBitmap = getResizedBitmap(image, 200);
        try {
            //	Bitmap immagex=resizedBitmap;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            int i = b.length;
            if (i > 2097152) {

				/*Toast.makeText(Profile.T,"Image size is too large." ,30).show();*/

            } else {
                base64Image = Base64.encodeToString(b, Base64.DEFAULT);
            }

        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return base64Image;
    }

    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public static Bitmap fixOrientationBitmap(Bitmap bitmap) {
        if (bitmap.getWidth() > bitmap.getHeight()) {
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return bitmap;
        }
        return bitmap;
    }

    private String getpath(Uri chosenImageUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(chosenImageUri, projection, null, null, null);
        int column = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column);
    }
}



