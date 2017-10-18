package steven.btloop.model;

import java.util.List;

/**
 * Created by Admin on 10/18/2017.
 */

public class ProductList {
    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    private List<Product> list;
}
