package cn.itcast.bean;

public class Doctor {
    private Integer did;
    private String dphone;
    private String dname;
    private String dqua;
    private String dlocal;
    private String dcover;
    private String dimage1;
    private String dimage2;
    private Double dprice;
    private Integer dtid;
    private String dtname;
    private Integer dstock;
    private String dmark;

    private boolean isScroll;
    private boolean isHot;
    private boolean isNew;

    public boolean getIsScroll() {
        return isScroll;
    }
    public void setScroll(boolean isScroll) {
        this.isScroll = isScroll;
    }
    public boolean getIsHot() {
        return isHot;
    }
    public void setHot(boolean isHot) {
        this.isHot = isHot;
    }
    public boolean getIsNew() {
        return isNew;
    }
    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public String getDtname() {
        return dtname;
    }

    public void setDtname(String dtname) {
        this.dtname = dtname;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDphone() {
        return dphone;
    }

    public void setDphone(String dphone) {
        this.dphone = dphone;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDqua() {
        return dqua;
    }

    public void setDqua(String dqua) {
        this.dqua = dqua;
    }

    public String getDlocal() {
        return dlocal;
    }

    public void setDlocal(String dlocal) {
        this.dlocal = dlocal;
    }

    public String getDcover() {
        return dcover;
    }

    public void setDcover(String dcover) {
        this.dcover = dcover;
    }

    public String getDimage1() {
        return dimage1;
    }

    public void setDimage1(String dimage1) {
        this.dimage1 = dimage1;
    }

    public String getDimage2() {
        return dimage2;
    }

    public void setDimage2(String dimage2) {
        this.dimage2 = dimage2;
    }

    public Double getDprice() {
        return dprice;
    }

    public void setDprice(Double dprice) {
        this.dprice = dprice;
    }

    public Integer getDtid() {
        return dtid;
    }

    public void setDtid(Integer dtid) {
        this.dtid = dtid;
    }

    public Integer getDstock() {
        return dstock;
    }

    public void setDstock(Integer dstock) {
        this.dstock = dstock;
    }

    public String getDmark() {
        return dmark;
    }

    public void setDmark(String dmark) {
        this.dmark = dmark;
    }

    @Override
    public String toString() {
        return "doctor{" +
                "did=" + did +
                ", dphone='" + dphone + '\'' +
                ", dname='" + dname + '\'' +
                ", dqua='" + dqua + '\'' +
                ", dlocal='" + dlocal + '\'' +
                ", dcover='" + dcover + '\'' +
                ", dimage1='" + dimage1 + '\'' +
                ", dimage2='" + dimage2 + '\'' +
                ", dprice=" + dprice +
                ", dtid=" + dtid +
                ", dtname='" + dtname + '\'' +
                ", dstock=" + dstock +
                ", dmark='" + dmark + '\'' +
                '}';
    }
}
