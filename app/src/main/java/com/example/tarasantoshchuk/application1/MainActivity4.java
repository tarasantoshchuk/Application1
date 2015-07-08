package com.example.tarasantoshchuk.application1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutionException;

public class MainActivity4 extends Activity implements View.OnClickListener {
    private static final String TAG = MainActivity4.class.getSimpleName();

    private final List<BitmapDownloadTask> bmpTasks =
            new ArrayList<BitmapDownloadTask>();
    private final List<Button> buttons = new ArrayList<Button>();

    private ImageView mImageView;
    private BitmapDownloadTask currentTask;
    /**
     * hash map with bitmaps from already accessed urls
     */
    private final WeakHashMap<String, Bitmap> mUrlBitmaps = new WeakHashMap<String, Bitmap>();

    @Override
    public void onClick(View v) {
        BitmapDownloadTask btmTask = bmpTasks.get(buttons.indexOf(v));
        if(btmTask.getStatus() == AsyncTask.Status.FINISHED) {
            try {
                mImageView.setImageBitmap(btmTask.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        } else {
            setCurrentTask(btmTask);
        }
    }

    private static class BitmapDownloadTask extends AsyncTask<String,Void, Bitmap> {
        private Context mContext;
        private ImageView mImageView;
        private WeakHashMap<String, Bitmap> mUrlBitmap;

        public BitmapDownloadTask(Context mContext, ImageView mImageView,
                                  WeakHashMap<String, Bitmap> mUrlBitmap) {
            this.mContext = mContext;
            this.mImageView = mImageView;
            this.mUrlBitmap = mUrlBitmap;
        }

        public boolean ismSetImageBitmap() {
            return mSetImageBitmap;
        }

        public void setmSetImageBitmap(boolean mSetImageBitmap) {
            this.mSetImageBitmap = mSetImageBitmap;
        }

        private boolean mSetImageBitmap = false;

        @Override
        protected Bitmap doInBackground(String... params) {
            InputStream content = null;
            try {
                content = (InputStream) new URL(params[0]).getContent();
                Bitmap bmp = BitmapFactory.decodeStream(content);
                mUrlBitmap.put(params[0], bmp);
                return bmp;
            } catch (IOException e) {
                Log.i(TAG, "Bad url format:" + params[0]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap == null) {
                Toast.makeText(mContext, "Bad url format", Toast.LENGTH_SHORT).show();
            }
            if(mSetImageBitmap) {
                mImageView.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        mImageView = (ImageView) findViewById(R.id.imageView);

        buttons.add((Button)findViewById(R.id.btnPicture1));
        buttons.add((Button)findViewById(R.id.btnPicture2));
        buttons.add((Button)findViewById(R.id.btnPicture3));

        bmpTasks.add((BitmapDownloadTask) new BitmapDownloadTask(this, mImageView, mUrlBitmaps)
                .execute(getString(R.string.url_pict_1)));
        bmpTasks.add((BitmapDownloadTask)new BitmapDownloadTask(this, mImageView, mUrlBitmaps)
                .execute(getString(R.string.url_pict_2)));
        bmpTasks.add((BitmapDownloadTask)new BitmapDownloadTask(this, mImageView, mUrlBitmaps)
                .execute(getString(R.string.url_pict_3)));

        for(Button btn: buttons) {
            btn.setOnClickListener(this);
        }

        bmpTasks.get(0).setmSetImageBitmap(true);
        currentTask = bmpTasks.get(0);

        Button btnCustomPicture = (Button) findViewById(R.id.btnCustomPicture);
        final EditText editPictureUrl = (EditText) findViewById(R.id.editPictureUrl);

        btnCustomPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editPictureUrl.getText().toString();
                if (mUrlBitmaps.containsKey(url)) {

                    mImageView.setImageBitmap(mUrlBitmaps.get(url));
                    Toast.makeText(MainActivity4.this, "from hash map", Toast.LENGTH_SHORT).show();

                } else {

                    BitmapDownloadTask task = (BitmapDownloadTask)
                            new BitmapDownloadTask(getApplicationContext(), mImageView, mUrlBitmaps)
                                    .execute(url);

                    setCurrentTask(task);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        for(BitmapDownloadTask bmpTask: bmpTasks) {
            if(bmpTask.getStatus() != AsyncTask.Status.FINISHED) {
                bmpTask.cancel(false);
            }
        }
        if(currentTask.getStatus() != AsyncTask.Status.FINISHED ) {
            currentTask.cancel(false);
        }
    }

    private void setCurrentTask(BitmapDownloadTask task) {
        currentTask.setmSetImageBitmap(false);
        currentTask = task;
        currentTask.setmSetImageBitmap(true);
    }
}
