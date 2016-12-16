package itschool.samsung.ru.loadingimages;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends Activity {

    Button bLoad;
    ImageView imageView;
    Bitmap bitmap;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bLoad = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);
        bLoad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                bLoad.setEnabled(false);
                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setMessage(getString(R.string.loading));
                pDialog.show();

                try {
                    URL url = new URL(getString(R.string.url)); // Получаем url по текстовой ссылке
                    InputStream stream = (InputStream) url.getContent(); //
                    bitmap = BitmapFactory.decodeStream(stream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pDialog.dismiss();
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);

                } else {
                    Toast.makeText(MainActivity.this, R.string.errorLoading,
                            Toast.LENGTH_SHORT).show();
                }
                bLoad.setEnabled(true);
            }
        });
    }

    private class LoadImage extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        protected Bitmap doInBackground(String... args) {
            Bitmap bitmap = null;
            //get Bitmap
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

}
