package group3.mindfactory_booking.model;

public class File {
    int fileID;
    String filePath;
    String fileName;

    public File(int fileID, String filePath, String fileName) {
        this.fileID = fileID;
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
