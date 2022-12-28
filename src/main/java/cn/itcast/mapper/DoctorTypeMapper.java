package cn.itcast.mapper;

import cn.itcast.bean.Doctor;
import cn.itcast.bean.DoctorType;

import java.util.List;

public interface DoctorTypeMapper {
    public List<DoctorType> queryDoctorTypes();

    public List<Doctor> queryDoctorsByDtid(int dtid, int pageIndex, int pageSize);//按dtid查询医生

    public int queryCountOfDoctorsByTypeID(int dtid);//根据推荐类型查询总数

    public String queryDoctorTypeNameByDoctorTypeID(int dtid);

    public void addDoctorType(String dtname);

    public void updateDoctorType(int dtid, String dtname);

    public void deleteDoctorType(int dtid);

    public int identifyDtname(String dtname);
}