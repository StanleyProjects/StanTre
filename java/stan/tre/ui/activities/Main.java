package stan.tre.ui.activities;

import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import stan.tre.R;

public class Main
        extends StanActivity
{

    TessBaseAPI baseApi;

    public Main()
    {
        super(R.layout.main, R.id.main_frame);
    }

    @Override
    protected void initFragments()
    {

    }

    @Override
    protected void initViews()
    {

    }

    @Override
    protected void init()
    {
        initTess();
    }

    private void initTess()
    {
        baseApi = new TessBaseAPI();
        String filepath = "tessdata";
        String filename = "rus.traineddata";
        String absolutePath = this.getFilesDir().getAbsolutePath();
        File file = new File(absolutePath, filepath + "/" + filename);
        if(!file.exists())
        {
            try
            {
                InputStream in = this.getApplicationContext().getResources().getAssets().open(filepath + "/" + filename);
                file = new File(this.getFilesDir(), filepath);
                file.mkdir();
                file.mkdirs();
                file = new File(this.getFilesDir(), filepath + "/" + filename);
                file.createNewFile();
                OutputStream out = new FileOutputStream(file);
                byte[] buf = new byte[1024];
                int len;
                while((len = in.read(buf)) > 0)
                {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                in = null;
                out = null;
            }
            catch(IOException e)
            {
                Log.e("tess", e.getMessage());
            }
        }
        baseApi.init(absolutePath, "rus");
    }
}