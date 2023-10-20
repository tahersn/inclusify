package tn.esprit.feedservice.Configuration;


import org.springframework.boot.context.properties.*;

/**
 * @author Jozef
 */

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
