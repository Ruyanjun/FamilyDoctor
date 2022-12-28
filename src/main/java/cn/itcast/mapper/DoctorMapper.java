package cn.itcast.mapper;

import cn.itcast.bean.Doctor;

import java.util.List;

public interface DoctorMapper {
    public void addDoctor(Doctor doctor);             //添加医生
    public void removeDoctor(int did);            //删除一添加医生
    public void modifyDoctor(Doctor doctor);          //修改添加医生
    public List<Doctor> queryDoctors(int pageIndex, int pageSize);             //查询全部医生
    public int queryCountOfDoctors();
    public List<Doctor> queryDoctorsByKeyword(String keyword, int pageIndex, int pageSize);             //查询全部书籍
    public int queryCountOfDoctorsByKeyword(String keyword);
    public Doctor queryDoctorById(int did);         //按did查询医生
    public Doctor queryDoctorByPhone(String dphone);  //
    public void deleteDoctorByDtid(int dtid);
}

