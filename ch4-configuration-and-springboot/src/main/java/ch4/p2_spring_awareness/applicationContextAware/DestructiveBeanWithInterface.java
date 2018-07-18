package ch4.p2_spring_awareness.applicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

public class DestructiveBeanWithInterface {
    private File file;
    private String filePath;

    @PostConstruct
    public void init() throws IOException {
        System.out.println("Initializing Bean");

        if (filePath == null) {
            throw new IllegalArgumentException("You must define the filePath of" + DestructiveBeanWithInterface.class);
        }

        this.file = new File(filePath);
        this.file.createNewFile();

        System.out.println("File exists: " + file.exists());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroying Bean");

        if (!file.delete()) {
            System.out.println("ERROR: failed to delete file");
        }

        System.out.println("File exists: " + file.exists());
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
