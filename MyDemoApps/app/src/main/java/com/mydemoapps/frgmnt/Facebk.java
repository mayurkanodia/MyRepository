package com.mydemoapps.frgmnt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.mydemoapps.R;
import com.mydemoapps.slidertab.TabFgmnt;

import org.json.JSONObject;

import java.util.Arrays;
/**
 * Created by Mayor kanodiya on 04-01-2016.
 */
public class Facebk extends Fragment {


 //   ImageButton verify_btn;

    LoginButton loginButton;
    CallbackManager callbackManager;
    TextView tex;
    Button shr_btn,nstd_fgmnt;
    ShareDialog shareDialog;


    public Facebk() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        shareDialog = new ShareDialog(getActivity());
        callbackManager = CallbackManager.Factory.create();





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frgmnt_fb, container, false);

        //////////////////////
        shr_btn=(Button)rootView.findViewById(R.id.shr_btn);

        shr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shr();
            }
        });


        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                System.out.println("onSuccess");

                if (result.getPostId() != null) {
//                    Constant.showAlertDialog("Message","your post has been shared with Facebook",getActivity(),false);
                    AlertDialog.Builder builder4 = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
                    builder4.setTitle("Alert");
                    builder4.setMessage("your post has been shared with Facebook").setCancelable(false)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alert4 = builder4.create();
                    alert4.setTitle("tital");
                    alert4.show();


                } else {
                    System.out.println("onSuccess BUt Not Share");
                }
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                System.out.println("onError" + error.getMessage());
            }
        });
        ///////////////////

        tex=(TextView)rootView.findViewById(R.id.tex);
        tex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("public_profile, email, " +
                        "user_birthday, user_friends"));
            }
        });

 /////////////
        LoginButton button = (LoginButton) rootView.findViewById(R.id.login_button);
        button.setFragment(this);
        button.setPublishPermissions("publish_actions");
        callfbLogin();


        nstd_fgmnt =(Button)rootView.findViewById(R.id.nstd_fgmnt);
        nstd_fgmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getActivity(),"l,ll;",Toast.LENGTH_SHORT).show();
                NstdFrgmnt fragment = new NstdFrgmnt();
                if (fragment != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.commit();
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
    private void callfbLogin()
    {
        System.out.println("call intent");
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        System.out.println("on success");


                        final GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
/// pDialog.dismiss();
                                        Log.d("Response", response.getJSONObject().toString());
                                        System.out.println("Response from FB : " + response.getJSONObject().toString());

                                        if (response.getError() != null) {
// handle error
                                            System.out.println("Error from FB ");
                                        } else {
                                            try {
                                                JSONObject _jsonObj = new JSONObject(response.getJSONObject().toString());
                                                String userEmail = _jsonObj.getString("email");
                                                String fb_id = _jsonObj.getString("id");
                                                System.out.println(String.valueOf(_jsonObj));
                                                Toast.makeText(getActivity(), String.valueOf(_jsonObj), Toast.LENGTH_SHORT).show();
                                               /* SharedPref.setDataInSharedPrefrence(LoginScreen.this,SharedPref.PREFRENCE_FB_ID,fb_id);
                                                SharedPref.setDataInSharedPrefrence(LoginScreen.this,SharedPref.PREFRENCE_DISABLE_LOGIN,"HideLogin");
                                                callCreateUserFirstTime();*/

                                            } catch (Exception e) {
                                                System.out.println("JSON Error");
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender, birthday");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        System.out.println("on cancel");

                        Toast.makeText(getActivity(), "On Cancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        System.out.println("on Error" + exception.getMessage());

                        Toast.makeText(getActivity(), "On Error", Toast.LENGTH_SHORT).show();
                    }

                });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getActivity(), "Activity result", Toast.LENGTH_SHORT).show();
    }

    public void shr(){

       /* if (ShareDialog.canShow(ShareLinkContent.class)) {
            String eventUrl = "http://www.mypage.com?id=" + event.getId();
            eventUrl = eventUrl.replaceAll(" ", "-");

            ShareLinkContent adShareContent = new ShareLinkContent.Builder()
                    .setContentTitle(event.getTitle())
                    .setContentDescription(message(R.string.fbshareDesc))
                    .setContentUrl(Uri.parse(eventUrl))
                    .setImageUrl(Uri.parse(event.getImages().get(0).getName())).build();

            shareDialog.show(adShareContent);
        }*/

        if (ShareDialog.canShow(ShareLinkContent.class))
        {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Yokal")
                    .setContentDescription(" fjhgf")
                            //    .setImageUrl(Uri.parse(_imgUri))
                    .setContentUrl(Uri.parse("https://www.facebook.com"))
                    .build();

            shareDialog.show(linkContent);

        }
        Toast.makeText(getActivity(),"Share on FB",Toast.LENGTH_SHORT).show();
        // dialog.dismiss();
    }
////
}
