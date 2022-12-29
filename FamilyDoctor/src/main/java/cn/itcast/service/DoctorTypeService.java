package cn.itcast.service;

import cn.itcast.bean.DoctorType;
import cn.itcast.bean.Page;

import java.util.List;

public interface DoctorTypeService {
    public List<DoctorType> queryDoctorTypes();
    public Page queryDoctorByDoctorTypeID(int dtid, int pageNumber);
    public String queryDoctorTypeNameByDoctorTypeID(int dtid);
    public boolean addDoctorType(String dtname);
    public boolean removeDoctorType(int dtid);
    public boolean modifyDoctorType(int dtid,String dtname);
 }
