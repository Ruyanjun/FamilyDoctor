package cn.itcast.bean;

import cn.itcast.Utils.PriceUtil;

import java.util.*;

public class Order {
    private String oid;
    private Double ototal;
    private Integer oamount;
    private Integer ostatus;
    private Integer opaytype;
    private Integer uid;
    private String orealname;
    private String ophone;
    private String oaddress;
    private String odatetime;
    private Map<Integer,OrderItem> itemMap = new HashMap<Integer,OrderItem>();
    private List<OrderItem> itemList = new ArrayList<OrderItem>();
    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Double getOtotal() {
        return ototal;
    }

    public void setOtotal(Double ototal) {
        this.ototal = ototal;
    }

    public String getOdatetime() {
        return odatetime;
    }

    public void setOdatetime(String odatetime) {
        this.odatetime = odatetime;
    }
    public Integer getOamount() {
        return oamount;
    }

    public void setOamount(Integer oamount) {
        this.oamount = oamount;
    }

    public Integer getOstatus() {
        return ostatus;
    }

    public void setOstatus(Integer ostatus) {
        this.ostatus = ostatus;
    }

    public Integer getOpaytype() {
        return opaytype;
    }

    public void setOpaytype(Integer opaytype) {
        this.opaytype = opaytype;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOrealname() {
        return orealname;
    }

    public void setOrealname(String orealname) {
        this.orealname = orealname;
    }

    public String getOphone() {
        return ophone;
    }

    public void setOphone(String ophone) {
        this.ophone = ophone;
    }

    public String getOaddress() {
        return oaddress;
    }

    public void setOaddress(String oaddress) {
        this.oaddress = oaddress;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public Map<Integer, OrderItem> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<Integer, OrderItem> itemMap) {
        this.itemMap = itemMap;
    }

    public void addGoods(Doctor doctor) {
        if(itemMap.containsKey(doctor.getDid())) {
            OrderItem item = itemMap.get(doctor.getDid());
            item.setOiamount(item.getOiamount()+1);
        }else {
            OrderItem item = new OrderItem();
            item.setOiprice(doctor.getDprice());
            item.setOiamount(1);
            item.setDoctor(doctor);
            item.setOrder(this);
            item.setDid(doctor.getDid());
            itemMap.put(doctor.getDid(), item);
        }
        oamount++;

        ototal = PriceUtil.add(ototal, doctor.getDprice());
    }

    public void lessen(int did) {
        if(itemMap.containsKey(did)) {
            OrderItem item = itemMap.get(did);
            item.setOiamount(item.getOiamount()-1);
            oamount--;
            ototal = PriceUtil.subtract(ototal,item.getOiprice());
            if(item.getOiamount()<=0) {
                itemMap.remove(did);
            }
        }
    }
    public void delete(int did)
    {
        if(itemMap.containsKey(did)) {
            OrderItem item = itemMap.get(did);
            ototal = PriceUtil.subtract(ototal,item.getOiamount()*item.getOiprice());
            oamount-=item.getOiamount();
            itemMap.remove(did);
        }
    }
}
