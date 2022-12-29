package cn.itcast.bean;

public class OrderItem {
    private Integer oiid;
    private Double oiprice;
    private Integer oiamount;
    private Integer did;
    private String oid;
    private Doctor doctor;
    private Order order;
    public Integer getOiid() {
        return oiid;
    }

    public void setOiid(Integer oiid) {
        this.oiid = oiid;
    }

    public Double getOiprice() {
        return oiprice;
    }

    public void setOiprice(Double oiprice) {
        this.oiprice = oiprice;
    }

    public Integer getOiamount() {
        return oiamount;
    }

    public void setOiamount(Integer oiamount) {
        this.oiamount = oiamount;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
