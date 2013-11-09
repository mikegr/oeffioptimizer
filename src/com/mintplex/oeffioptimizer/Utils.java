
package com.mintplex.oeffioptimizer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Utils {
    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void unzip(InputStream zipFile, File destDir) throws IOException {
        ZipInputStream zis = null;
        FileOutputStream fos = null;
        try {
            zis = new ZipInputStream(new BufferedInputStream(zipFile));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                Log.v("Unzipping: " + entry.getName());
                File entryFile = new File(destDir, entry.getName());
                if (entry.isDirectory()) {
                    entryFile.mkdirs();
                    continue;
                } else {
                    entryFile.getParentFile().mkdirs();
                }
                int size;
                byte[] buffer = new byte[2048];

                fos = new FileOutputStream(entryFile);
                BufferedOutputStream bos = new BufferedOutputStream(fos,
                        buffer.length);

                while ((size = zis.read(buffer, 0, buffer.length)) > 0) {
                    bos.write(buffer, 0, size);
                }
                bos.flush();
                bos.close();
                fos = null;
            }
        } finally {
            if (zis != null)
                zis.close();
            if (fos != null)
                fos.close();
        }
    }


    public static String toHexString(byte[] digest) {
        String hexStr = "";
        for (int i = 0; i < digest.length; i++) {
            hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
        }
        return hexStr;
    }

    private static InputStream getInputStream(String urlString) throws Exception {
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        // Log.v("Date from Server" + new Date(conn.getDate()));
        // Log.v("Expires" + new Date(conn.getExpiration()));
        return conn.getInputStream();
    }

    public static void createZipFile(ZipOutputStream zos, File[] files) throws IOException {
        byte[] buffer = new byte[4096];
        for (File file : files) {
            zos.putNextEntry(new ZipEntry(file.getName()));
            int count;
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            while ((count = is.read(buffer)) != -1) {
                zos.write(buffer, 0, count);
            }
            is.close();
            zos.closeEntry();
        }
        zos.close();
    }

    public static void download(String url, File cacheFile) throws Exception {
        InputStream is = getInputStream(url);
        download(is, cacheFile);
    }

    public static void download(InputStream is, File cacheFile) throws Exception {
        FileOutputStream fos = new FileOutputStream(cacheFile);
        download(is, fos);
    }

    public static void download(InputStream is, OutputStream os) throws Exception {
        try {
            int rc = 0;
            byte[] buffer = new byte[4096];
            while ((rc = is.read(buffer)) != -1) {
                os.write(buffer, 0, rc);
            }
        } finally {
            os.flush();
            os.close();
            is.close();
        }
    }

    public static void downloadWithTmpFile(InputStream is, File destFile) throws Exception {
        File cacheFile = new File(destFile.getParent(), destFile.getName() + ".tmp");
        download(is, cacheFile);
        cacheFile.renameTo(destFile);
    }

    public static void setGreyForgroundFrameLayout(FrameLayout layout) {
        Bitmap bmp = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setARGB(128, 255, 255, 255);
        canvas.drawPaint(paint);
        layout.setForegroundGravity(Gravity.FILL);
        layout.setForeground(new BitmapDrawable(bmp));
    }

    public static String convertReaderToString(Reader r) throws IOException {
        char[] buf = new char[4096];
        int i = 0;
        Writer writer = new StringWriter();
        while ((i = r.read(buf)) != -1) {
            writer.write(buf, 0, i);
        }
        return writer.toString();
    }

    public static void copy(InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[4096];
        int count = 0;
        while ((count = is.read(buffer)) != -1) {
            os.write(buffer, 0, count);
        }
    }

    public static boolean isToday(Date date) {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        Calendar otherday = Calendar.getInstance();
        otherday.setTime(date);
        return otherday.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                && otherday.get(Calendar.MONTH) == today.get(Calendar.MONTH)
                && otherday.get(Calendar.DAY_OF_MONTH) == today
                        .get(Calendar.DAY_OF_MONTH);
    }

    public static String convertStreamToString(InputStream is)
            throws IOException {
        /*
         * To convert the InputStream to String we use the Reader.read(char[]
         * buffer) method. We iterate until the Reader return -1 which means
         * there's no more data to read. We use the StringWriter class to
         * produce the string.
         */
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is,
                        "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }

    public static void writeStringToFile(String string, File file) throws IOException {
        Writer w = null;
        try {
            w = new BufferedWriter(new FileWriter(file));
            w.write(string);
            w.flush();
        } finally {
            if (w != null)
                w.close();
        }
    }

    public static void delete(File file) {
        File[] listing = file.listFiles();
        if (listing != null) {
            for (File f : listing) {
                if (f.isDirectory()) {
                    delete(f);
                } else if (f.isFile()) {
                    f.delete();
                }
            }
        }
        file.delete();
    }

    /**
     * Given either a Spannable String or a regular String and a token, apply
     * the given CharacterStyle to the span between the tokens, and also remove
     * tokens.
     * <p>
     * For example, {@code setSpanBetweenTokens("Hello ##world##!", "##",
     * new ForegroundColorSpan(0xFFFF0000));} will return a CharSequence
     * {@code "Hello world!"} with {@code world} in red.
     * 
     * @param text The text, with the tokens, to adjust.
     * @param token The token string; there should be at least two instances of
     *            token in text.
     * @param cs The style to apply to the CharSequence. WARNING: You cannot
     *            send the same two instances of this parameter, otherwise the
     *            second call will remove the original span.
     * @return A Spannable CharSequence with the new style applied.
     * @see http 
     *      ://developer.android.com/reference/android/text/style/CharacterStyle
     *      .html
     */
    public static CharSequence setSpanBetweenTokens(CharSequence text,
            String token, CharacterStyle cs) {

        SpannableStringBuilder ssb = new SpannableStringBuilder(text);

        // Start and end refer to the points where the span will apply
        Pattern pattern = Pattern.compile(token + "(.*)" + token);
        Matcher m = pattern.matcher(ssb);
        while (m.find()) {
            m.start();
            m.end();
            ssb.setSpan(cs, m.start(), m.end(), 0);
        }

        Pattern hashes = Pattern.compile(token);
        Matcher hm = hashes.matcher(ssb);
        while (hm.find()) {
            ssb.delete(hm.start(), hm.end());
        }
        return ssb;

    }

    public static byte[] downloadAndGetBytes(String urlString, File cacheFile) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileOutputStream fos = new FileOutputStream(cacheFile);
        InputStream is = Utils.getInputStream(urlString);
        byte[] result;
        try {
            int rc = 0;
            byte[] buffer = new byte[4096];
            while ((rc = is.read(buffer)) != -1) {
                bos.write(buffer, 0, rc);
                fos.write(buffer, 0, rc);
            }
            result = bos.toByteArray();
        } finally {
            fos.flush();
            fos.close();
            bos.close();
            is.close();
        }
        return result;
    }

    //    public static File getCacheDirectory(Context ctx) {
    //
    //        String state = Environment.getExternalStorageState();
    //        if (Environment.MEDIA_MOUNTED.equals(state)) {
    //            File file = Application.getCacheDirectory();
    //            return file;
    //        } else {
    //            return ctx.getCacheDir();
    //        }
    //    }

    /**
     * Returns the online status of the device. Note that a request to a server
     * can still fail or time-out due to network or server problems!
     * 
     * @param context of application
     * @return boolean true if online
     */
    public static boolean isDeviceOnline(Context context) {
        ConnectivityManager cMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cMgr.getActiveNetworkInfo();
        if (netInfo == null || netInfo.getState() == null)
            return false;
        return netInfo.getState().equals(State.CONNECTED);
    }
    
    public static int find(int[] array, int value) {
        for(int i=0; i<array.length; i++) 
             if(array[i] == value)
                 return i;
    	return -1;
    }
    
	public static TextView t(View v, int tvRes, String txt) {
		TextView tv = (TextView) v.findViewById(tvRes);
		if (tv != null) {
			tv.setText(txt);
		}
		return tv;
	}
	
	public static TextView t(View v, int tvRes, Spanned txt) {
		TextView tv = (TextView) v.findViewById(tvRes);
		if (tv != null) {
			tv.setText(txt);
		}
		return tv;
	}
	
	public static EditText e(View v, int editRes, String txt) {
		EditText et = (EditText) v.findViewById(editRes);
		if (et != null) {
			et.setText(txt);
		}
		return et;
	}

	public static Button b(View v, int buttonRes, String txt) {
		Button b= (Button) v.findViewById(buttonRes);
		if (b != null) {
			b.setText(txt);
		}
		return b;
	}
    
    
}
