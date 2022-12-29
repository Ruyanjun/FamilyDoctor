package cn.itcast.service.impl;

import cn.itcast.bean.Doctor;
import cn.itcast.service.DoctorService;
import cn.itcast.bean.Page;
import cn.itcast.mapper.DoctorMapper;
import cn.itcast.mapper.RecommendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    public DoctorMapper doctorMapper;
    @Autowired
    public RecommendMapper recommendMapper;
    public List<Doctor> queryDoctorByRecommendType(int rtype, int pageNumber, int pageSize)
    {
        return recommendMapper.queryDoctorByRecommendType(rtype,(pageNumber-1)*pageSize,pageSize);
    }

    public Page queryDoctorByRecommendType(int rtype, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            if(rtype==0)
                totalCount = doctorMapper.queryCountOfDoctors();
            else
                totalCount = recommendMapper.queryRecommendCountOfDoctorsByTypeID(rtype);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List<Doctor> list=null;
        try {
            if(rtype==0)
                list= doctorMapper.queryDoctors((pageNumber-1)*8,8);
            else
                list = recommendMapper.queryDoctorByRecommendType(rtype,(pageNumber-1)*8,8);

            for(int i=0;i<list.size();i++)
            {
                Doctor doctor =list.get(i);
                doctor.setScroll(recommendMapper.queryDoctorByRtypeAndDid(1, doctor.getDid())>=1);
                doctor.setHot(recommendMapper.queryDoctorByRtypeAndDid(2, doctor.getDid())>=1);
                doctor.setNew(recommendMapper.queryDoctorByRtypeAndDid(3, doctor.getDid())>=1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        p.setList((List) list);
        return p;
    }

    public Doctor queryDoctorByID(int did)
    {
        return doctorMapper.queryDoctorById(did);

    }

    public Page searchDoctorsByKeyword(String keyword,int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            totalCount = doctorMapper.queryCountOfDoctorsByKeyword(keyword);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list=null;
        try {
            list = doctorMapper.queryDoctorsByKeyword(keyword,(pageNumber-1)*8,8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        p.setList(list);
        return p;
    }

    @Override
    public boolean addRecommend(int did, int rtype) {
        try {
            recommendMapper.addRecommendDoctor(did,rtype);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remoteRecommend(int did, int rtype) {
        try {
            recommendMapper.removeRecommendDoctor(did,rtype);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean removeDoctorByDid(int did) {
        try {
            recommendMapper.removeRecommend(did);
            doctorMapper.deleteDoctorByDtid(did);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public boolean addDoctor(Doctor doctor) {
        try {
            doctorMapper.addDoctor(doctor);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean modifyDoctor(Doctor doctor) {
        try {
            doctorMapper.modifyDoctor(doctor);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
