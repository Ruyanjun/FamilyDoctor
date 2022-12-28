package cn.itcast.service;

import cn.itcast.bean.Doctor;
import cn.itcast.bean.Page;

import java.util.List;

public interface DoctorService {
    public List<Doctor> queryDoctorByRecommendType(int rtype, int pageNumber, int pageSize);
    public Page queryDoctorByRecommendType(int rtype,int pageNumber);
    public Doctor queryDoctorByID(int did);
    public Page searchDoctorsByKeyword(String keyword, int pageNumber);
    public boolean addRecommend(int did,int rtype);
    public boolean remoteRecommend(int did,int rtype);
    public boolean removeDoctorByDid(int did);
    public boolean addDoctor(Doctor doctor);
    public boolean modifyDoctor(Doctor doctor);
}
