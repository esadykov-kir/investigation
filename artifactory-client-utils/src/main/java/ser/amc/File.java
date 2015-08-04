package ser.amc;

/**
 * @author ser
 * @since 03.08.2015 22:27
 */
public class File {
    String name;
    int size;
    Folder parent;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
