package teamlabAPI;

/**
 * Created by runner on 4/28/14.
 */
public class TeamlabDocumentFolder {

    private int id;
    private int parentId;
    private int fileCounts;
    private int foldersCounts;
    private boolean isShareable;
    private String title;
    private int access;
    private boolean sharedByMe;
    private String updatedBy;
    private String createdBy;
    private String updatedTime;

    public TeamlabDocumentFolder(int id, int parentId, int fileCounts, int foldersCounts,
                               boolean isShareable, String title, int access,
                               boolean sharedByMe, String updatedBy, String createdBy, String updatedTime) {
        this.id = id;
        this.parentId = parentId;
        this.fileCounts = fileCounts;
        this.foldersCounts = foldersCounts;
        this.isShareable = isShareable;
        this.title = title;
        this.access = access;
        this.sharedByMe = sharedByMe;
        this.updatedBy = updatedBy;
        this.createdBy = createdBy;
        this.updatedTime = updatedTime;
    }

}
