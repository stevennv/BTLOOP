package steven.btloop.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TruongNV on 10/3/2017.
 */

public class Product implements Serializable {
    protected Product(Parcel in) {
        _id = in.readString();
        name = in.readString();
        artwork = in.readString();
        price = in.readString();
        viewCount = in.readInt();
        os = in.readString();
        fontCamera = in.readString();
        backCamera = in.readString();
        memory = in.readString();
        screen = in.readString();
    }

//    public static final Creator<Product> CREATOR = new Creator<Product>() {
//        @Override
//        public Product createFromParcel(Parcel in) {
//            return new Product(in);
//        }
//
//        @Override
//        public Product[] newArray(int size) {
//            return new Product[size];
//        }
//    };

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtwork() {
        return artwork;
    }

    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }


    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getFontCamera() {
        return fontCamera;
    }

    public void setFontCamera(String fontCamera) {
        this.fontCamera = fontCamera;
    }

    public String getBackCamera() {
        return backCamera;
    }

    public void setBackCamera(String backCamera) {
        this.backCamera = backCamera;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    private String _id;
    private String name;
    private String artwork;
    private String price;
    private List<Category> category;
    private int viewCount;
    private String os;
    private String fontCamera;
    private String backCamera;
    private String memory;
    private String screen;

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(_id);
//        dest.writeString(name);
//        dest.writeString(artwork);
//        dest.writeString(price);
//        dest.writeList(category);
//        dest.writeInt(viewCount);
//        dest.writeString(os);
//        dest.writeString(fontCamera);
//        dest.writeString(backCamera);
//        dest.writeString(memory);
//        dest.writeString(screen);
//    }
}
