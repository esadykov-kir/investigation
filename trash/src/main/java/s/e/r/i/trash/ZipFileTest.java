package s.e.r.i.trash;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * http://bugs.java.com/bugdatabase/view_bug.do?bug_id=8017777
 */
public class ZipFileTest {

    public static void main(String[] args) throws Exception {
        new ZipFileTest().crashReadWrite();
        //new ZipFileTest().crashReadTouch();
    }

    public ZipFileTest() throws Exception {
    }

    public void crashReadTouch() throws InterruptedException, IOException {
        File file = new File(System.getProperty( "java.io.tmpdir" ) +  "/zipfile.zip" );
        new ZipFileWriter(file).run();

        for (int i = 0; i <= 200; i++) {
            ZipFile zip = new ZipFile(System.getProperty( "java.io.tmpdir" ) +  "/zipfile.zip" );
            new Thread(new ZipFileReader(zip)).start();
            Thread.sleep(10);
            file.setLastModified(System.currentTimeMillis());
        }
    }

    public void crashReadWrite() throws InterruptedException, IOException {
        File file = new File(System.getProperty( "java.io.tmpdir" ) +  "/zipfile.zip" );
        new ZipFileWriter(file).run();

        for (int i = 0; i <= 10; i++) {
            ZipFile zip = new ZipFile(System.getProperty( "java.io.tmpdir" ) +  "/zipfile.zip" );
            new Thread(new ZipFileReader(zip)).start();
            file = new File(System.getProperty( "java.io.tmpdir" ) +  "/zipfile.zip" );
            new Thread(new ZipFileWriter(file)).start();
        }
    }

    public class ZipFileReader implements Runnable {
        private ZipFile zipFile;

        public ZipFileReader(ZipFile aZip) {
            zipFile = aZip;
        }

        @Override
        public void run() {
            Enumeration zipEntries = zipFile.entries();
            for (int i=1000; i >= 0; i--) {
                //getEntry will crash vm if zip file was modified
                zipFile.getEntry( " test "  + i);
            }
            while (zipEntries.hasMoreElements()) {
                //getNextEntry will crash vm if zip file was modified
                ZipEntry zipEntry = (ZipEntry) zipEntries.nextElement();
                zipEntry.getCrc();
            }
        }
    }

    public class ZipFileWriter implements Runnable {
        private File file;

        public ZipFileWriter(File aFile) {
            file = aFile;
        }

        @Override
        public void run() {
            int i = 1;
            ZipOutputStream zOut;
            try {
                zOut = new ZipOutputStream(new FileOutputStream(file));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                return;
            }
            try {

                for (int j = 0; j <= 1000; j++) {
                    ZipEntry e = new ZipEntry( " test "  + j);
                    zOut.putNextEntry(e);
                    String content =  " test "  + i++;
                    for (i = 0; i <= 1000; i++) {
                        zOut.write(content.getBytes());
                    }
                    zOut.closeEntry();
                }
            } catch (IOException e) {
                System.err.println( " Caught Exception: "  + e);
                e.printStackTrace();
            } finally {
                try {
                    zOut.close();
                } catch (IOException e) {
                }
            }
        }
    }
}