package cn.itcast.controller;

import cn.itcast.service.DoctorService;
import cn.itcast.service.DoctorTypeService;
import cn.itcast.bean.Doctor;
import cn.itcast.bean.DoctorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    public DoctorService doctorService;

    @Autowired
    public DoctorTypeService doctorTypeService;

    @RequestMapping("/index")
    public String Init(HttpServletRequest request)
    {
        List<Doctor> doctors = doctorService.queryDoctorByRecommendType(1,1,6);
        if(doctors.size()>0)
            request.setAttribute("scrollDoctor", doctors.get(0));
        doctors = doctorService.queryDoctorByRecommendType(2,1,6);
        request.setAttribute("hotList", doctors);
        doctors = doctorService.queryDoctorByRecommendType(3,1,8);
        request.setAttribute("newList", doctors);
        List<DoctorType> doctorTypes = doctorTypeService.queryDoctorTypes();
        request.getServletContext().setAttribute("doctorTypes", doctorTypes);
        return "index";
    }
}
