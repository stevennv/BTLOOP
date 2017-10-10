package steven.btloop.model;

/**
 * Created by TruongNV on 9/21/2017.
 */

public class UserInfo {
    public UserInfo(String name , String urlAvatar , String id){
        this.name = name;
        this.urlAvatar = urlAvatar;
        this.id = id;
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String urlAvatar;
    private String id;
}
