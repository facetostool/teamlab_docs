package teamlabAPI;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by runner on 4/28/14.
 */
public class TeamlabDocumentFile {

    private int id;
    private int folderId;
    private int version;
    private int versionGroup;
    private String contentLength;
    private int fileStatus;
    private String fileExst;
    private String title;
    private int access;
    private boolean sharedByMe;
    private String updatedBy;
    private String createdBy;
    private String updatedTime;

    public TeamlabDocumentFile(int id, int folderId, int version, int versionGroup,
                               String contentLength, int fileStatus, String fileExst, String title, int access,
                               boolean sharedByMe, String updatedBy, String createdBy, String updatedTime) {
        this.id = id;
        this.folderId = folderId;
        this.version = version;
        this.versionGroup = versionGroup;
        this.contentLength = contentLength;
        this.fileExst = fileExst;
        this.fileStatus = fileStatus;
        this.title = title;
        this.access = access;
        this.sharedByMe = sharedByMe;
        this.updatedBy = updatedBy;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
    }

}
