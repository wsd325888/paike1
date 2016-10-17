package com.example.acer.framework;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.acer.framework.util.NetUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class GetpersonInfo extends AppCompatActivity {

    @InjectView(R.id.view1)
    View view1;
    @InjectView(R.id.tv_headimage)
    TextView tvHeadimage;
    @InjectView(R.id.iv_head_image)
    ImageView ivHeadImage;
    @InjectView(R.id.iv_edit_head_image)
    ImageView ivEditHeadImage;
    @InjectView(R.id.rl_edit_head_image)
    RelativeLayout rlEditHeadImage;
    @InjectView(R.id.view2)
    View view2;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_real_name)
    TextView tvRealName;
    @InjectView(R.id.iv_edit_name)
    ImageView ivEditName;
    @InjectView(R.id.rl_edit_name)
    RelativeLayout rlEditName;
    @InjectView(R.id.view3)
    View view3;
    @InjectView(R.id.tv_sex)
    TextView tvSex;
    @InjectView(R.id.tv_real_sex)
    TextView tvRealSex;
    @InjectView(R.id.iv_edit_sex)
    ImageView ivEditSex;
    @InjectView(R.id.rl_edit_sex)
    RelativeLayout rlEditSex;
    @InjectView(R.id.view4)
    View view4;
    @InjectView(R.id.tv_pwd)
    TextView tvPwd;
    @InjectView(R.id.iv_edit_pwd)
    ImageView ivEditPwd;
    @InjectView(R.id.rl_edit_pwd)
    RelativeLayout rlEditPwd;
    //相机拍摄照片和视频的标准目录
    private File file;
    private Uri imageUri;

    String items[]={"相册选择","拍照"};


    public static final int SELECT_PIC=11;
    public static final int TAKE_PHOTO=12;
    public static final int CROP=13;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getperson_info);
        ButterKnife.inject(this);
        //判断sd卡是否存在，存在
        if(Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED) ){
            //目录，文件名Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            file=new File(Environment.getExternalStorageDirectory(),getPhotoFileName());
            imageUri= Uri.fromFile(file);


        }
    }


    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return sdf.format(date) + ".png";
    }

    @OnClick({R.id.rl_edit_head_image, R.id.rl_edit_name, R.id.rl_edit_sex})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_edit_head_image:
                //修改头像
                new AlertDialog.Builder(this).setTitle("选择").setItems(items,new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub

                        switch(which){
                            case 0:

                                //相册选择
                                Intent intent = new Intent(Intent.ACTION_PICK, null);
                                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                        "image/*");
                                startActivityForResult(intent, SELECT_PIC);

                                break;


                            case 1:

                                //拍照:
                                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                                startActivityForResult(intent2,TAKE_PHOTO);

                                break;
                        }
                    }
                }).show();
                break;
            case R.id.rl_edit_name:
                //修改姓名
                Intent intent=new Intent(this,EditInfoActivity.class);
                intent.putExtra("flag","name");
                startActivityForResult(intent,1);//请求码
                break;
            case R.id.rl_edit_sex:
                Intent intent2=new Intent(this,EditInfoActivity.class);
                intent2.putExtra("flag","sex");
                startActivityForResult(intent2,2);//请求码

                break;
        }
    }

    public void crop(Uri uri){
        //  intent.setType("image/*");
        //裁剪
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //
        switch (requestCode){
            case SELECT_PIC:
                //相册选择
                if (data != null) {
                    crop(data.getData());

                }

                break;
            case TAKE_PHOTO:
                crop(Uri.fromFile(file));
                break;


            case CROP:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {

                        Bitmap bitmap = extras.getParcelable("data");
                        showImage(bitmap);
                    }
                }
            case 1:
                //修改姓名返回
                //获取修改的姓名值
                String name=data.getStringExtra("name");
                //显示在tv上
                tvName.setText(name);
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    //显示图片，上传服务器
    public void showImage(Bitmap bitmap){
        ivHeadImage.setImageBitmap(bitmap);//iv显示图片
        saveImage(bitmap);//保存文件
        uploadImage();//上传服务器

    }

    //将bitmap保存在文件中
    public void saveImage(Bitmap bitmap){
        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,fos);
    }

    //上传图片
    public void uploadImage(){

        RequestParams requestParams=new RequestParams(NetUtil.url+"UploadImageServlet");
        requestParams.setMultipart(true);
        requestParams.addBodyParameter("file",file);

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("ModifyPersonInfo", "onSuccess: ");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

}

