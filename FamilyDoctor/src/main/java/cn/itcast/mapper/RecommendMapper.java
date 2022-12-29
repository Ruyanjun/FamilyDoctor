package cn.itcast.mapper;

import cn.itcast.bean.Doctor;

import java.util.List;

public interface RecommendMapper {
    public List<Doctor> queryDoctorByRecommendType(int rtype, int pageIndex, int pageSize); //按推荐类型查询医生
    public int queryRecommendCountOfDoctorsByTypeID(int rtype);//根据推荐类型查询总数
    public int queryDoctorByRtypeAndDid(int rtype,int did);
    public void addRecommendDoctor(int did,int rtype);//添加推荐医生
    public void removeRecommendDoctor(int did,int rtype);//删除推荐医生
    public void removeRecommend(int did);
}
