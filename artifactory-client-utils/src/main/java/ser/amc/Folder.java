package ser.amc;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ser
 * @since 03.08.2015 22:25
 */
public class Folder {
    Set<File> files = new HashSet<>();
    Set<Folder> folders = new HashSet<>();
    String name;
    int size = 0;
    Folder parent;

    public Folder(String name) {
        this.name = name;
    }

    public void addFile(File file) {
        files.add(file);
        file.parent = this;
        incSize(file.getSize());
    }

    public void addFolder(Folder folder) {
        folders.add(folder);
        folder.parent = this;
        incSize(folder.size);
    }

    public void incSize(int size) {
        this.size = this.size + size;
        if (parent != null)
            parent.incSize(size);
    }
}
