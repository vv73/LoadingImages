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

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends Activity {

    Button bLoad;
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bLoad = (Button)findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.imageView);
        bLoad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setMessage("Загрузка ...");
                pDialog.show();
                try {
                    URL url = new URL("http://www.wincore.ru/uploads/posts/2015-04/1428390721_alps.jpg");
                    InputStream stream = (InputStream)url.getContent();
                    bitmap = BitmapFactory.decodeStream(stream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (bitmap != null){
                    imageView.setImageBitmap(bitmap);
                    pDialog.dismiss();
                }else{

                    pDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Ошибка загрузки", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private class LoadImage extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }
        protected Bitmap doInBackground(String... args) {
            try {
                URL url = new URL(args[0]); // Получаем url по текстовой ссылке
                InputStream stream = (InputStream)url.getContent(); //
                bitmap = BitmapFactory.decodeStream(stream);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
