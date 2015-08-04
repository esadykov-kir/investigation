package ser.amc;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ser
 * @since 03.08.2015 22:42
 */

public class FFTest {

    @Test
    public void testSizes() {
        Folder root = new Folder("root");
        Folder ch1 = new Folder("ch1");
        root.addFolder(ch1);
        ch1.addFile(new File("f1", 1));

        Assert.assertEquals(1, root.size);
        Assert.assertEquals(1, ch1.size);

        ch1.addFile(new File("f2", 3));

        Assert.assertEquals(4, root.size);
        Assert.assertEquals(4, ch1.size);

        Folder ch2 = new Folder("ch2");
        root.addFolder(ch2);
        ch2.addFile(new File("f3", 5));

        Assert.assertEquals(9, root.size);
        Assert.assertEquals(5, ch2.size);
        Assert.assertEquals(4, ch1.size);
    }
}
