package cn.itcast.service.impl;

import cn.itcast.service.DoctorTypeService;
import cn.itcast.bean.DoctorType;
import cn.itcast.bean.Page;
import cn.itcast.mapper.DoctorMapper;
import cn.itcast.mapper.DoctorTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorTypeServiceImpl implements DoctorTypeService {

    @Autowired
    public DoctorMapper doctorMapper;
    @Autowired
    public DoctorTypeMapper doctorTypeMapper;

    @Override
    public List<DoctorType> queryDoctorTypes() {
        return doctorTypeMapper.queryDoctorTypes();
    }

    @Override
    public Page queryDoctorByDoctorTypeID(int dtid, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int totalCount = 0;
        try {
            if(dtid==-1)
                totalCount= doctorMapper.queryCountOfDoctors();
            else
                totalCount = doctorTypeMapper.queryCountOfDoctorsByTypeID(dtid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        p.SetPageSizeAndTotalCount(8, totalCount);
        List list=null;
        try {
            if(dtid==-1)
                list= doctorMapper.queryDoctors((pageNumber-1)*8,8);
            else
                list = doctorTypeMapper.queryDoctorsByDtid(dtid,(pageNumber-1)*8,8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        p.setList(list);
        return p;
    }

    @Override
    public String queryDoctorTypeNameByDoctorTypeID(int dtid) {
        return doctorTypeMapper.queryDoctorTypeNameByDoctorTypeID(dtid);
    }

    @Override
    public boolean addDoctorType(String dtname) {
        try {
          int result= doctorTypeMapper.identifyDtname(dtname);
            if(result>=1)
                return false;
            doctorTypeMapper.addDoctorType(dtname);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }


    }

    @Override
    public boolean removeDoctorType(int dtid) {
        try {
            doctorMapper.deleteDoctorByDtid(dtid);
            doctorTypeMapper.deleteDoctorType(dtid);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean modifyDoctorType(int dtid, String dtname) {
        try {
            doctorTypeMapper.updateDoctorType(dtid,dtname);
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
