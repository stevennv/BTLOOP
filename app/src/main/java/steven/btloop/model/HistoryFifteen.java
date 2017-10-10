package steven.btloop.model;

/**
 * Created by TruongNV on 9/18/2017.
 */

public class HistoryFifteen {
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    private String fromDate;
    private String address;
    private String work;
}
