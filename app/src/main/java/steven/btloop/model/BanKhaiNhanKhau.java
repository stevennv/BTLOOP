package steven.btloop.model;

import java.util.List;

/**
 * Created by TruongNV on 9/18/2017.
 */

public class BanKhaiNhanKhau extends TheCanCuoc {
    private String city;
    private String location;
    private String religion;
    private String academicLevel;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public String getHasLangue() {
        return hasLangue;
    }

    public void setHasLangue(String hasLangue) {
        this.hasLangue = hasLangue;
    }

    public String getLevelLangue() {
        return levelLangue;
    }

    public void setLevelLangue(String levelLangue) {
        this.levelLangue = levelLangue;
    }

    public String getBestWork() {
        return bestWork;
    }

    public void setBestWork(String bestWork) {
        this.bestWork = bestWork;
    }

    public String getCurrentWork() {
        return currentWork;
    }

    public void setCurrentWork(String currentWork) {
        this.currentWork = currentWork;
    }

    public List<HistoryFifteen> getList() {
        return list;
    }

    public void setList(List<HistoryFifteen> list) {
        this.list = list;
    }

    private String hasLangue;
    private String levelLangue;
    private String bestWork;
    private String currentWork;
    private List<HistoryFifteen> list;

}
