package lk.ijse.CakeBakery.controller;

import lk.ijse.CakeBakery.model.customer;
import lk.ijse.CakeBakery.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class customerCrudController {
    public static ArrayList<String> getCusIDs() throws SQLException, ClassNotFoundException {

        ResultSet result= CrudUtil.execute("SELECT cusId FROM Customer");
        ArrayList<String> ids=new ArrayList<>();

        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }

    public static customer getCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet result=CrudUtil.execute("SELECT * FROM Customer WHERE cusId=?",id);

        if (result.next()){
            return new customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return  null;

    }
}
