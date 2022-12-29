package cn.itcast.controller;

import cn.itcast.service.DoctorService;
import cn.itcast.service.DoctorTypeService;
import cn.itcast.bean.Doctor;
import cn.itcast.bean.Page;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;


@Controller
public class DoctorController {

    @Autowired
    public DoctorService doctorService;
    @Autowired
    public DoctorTypeService doctorTypeService;


    @RequestMapping("/recommend_doctors")
    public String showRecommendDoctor(int rtype, int pageNumber, HttpServletRequest request)
    {
        if(pageNumber<=0)
            pageNumber=1;
        Page p= doctorService.queryDoctorByRecommendType(rtype,pageNumber);

        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p = doctorService.queryDoctorByRecommendType(rtype,p.getTotalPage());
            }
        }
        request.setAttribute("p", p);
        request.setAttribute("t", rtype);
        return "recommend_list";
    }

    @RequestMapping("/doctortypes_list")
    public String showDoctorsByDoctorTypeID(int pageNumber, int dtid,HttpServletRequest request)
    {
        String dtname="";
        if(dtid!=0)
        {
            dtname= doctorTypeService.queryDoctorTypeNameByDoctorTypeID(dtid);
        }
        request.setAttribute("t",dtname);

        if(pageNumber<=0)
            pageNumber=1;
        Page p= doctorTypeService.queryDoctorByDoctorTypeID(dtid,pageNumber);

        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p= doctorTypeService.queryDoctorByDoctorTypeID(dtid,p.getTotalPage());
            }
        }

        request.setAttribute("p",p);
        request.setAttribute("dtid",dtid);
        return "doctortypes_list";
    }

    @RequestMapping("/doctor_detail")
    public String showDoctorByID(int did,HttpServletRequest request)
    {
        Doctor doctor = doctorService.queryDoctorByID(did);
        request.setAttribute("doctor", doctor);
        return "doctor_detail";
    }

    @RequestMapping("/search_doctors")
    public String SearchDoctorsByKeyword(int pageNumber,String keyword,HttpServletRequest request) throws UnsupportedEncodingException {

        if(pageNumber<=0)
        {
            pageNumber=1;
        }
        Page p = doctorService.searchDoctorsByKeyword(keyword,pageNumber);

        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p = doctorService.searchDoctorsByKeyword(keyword,p.getTotalPage());
            }
        }
        request.setAttribute("p", p);
        request.setAttribute("keyword", URLEncoder.encode(keyword,"utf-8"));
        return "doctor_search";
    }

    @RequestMapping("/admin/type_list")
    public String ShowDoctorTypes(HttpServletRequest request)
    {
        request.setAttribute("list",doctorTypeService.queryDoctorTypes());
        return "admin/type_list";
    }
    @RequestMapping("/admin/type_add")
    public String CreateDoctorType(String dtname,HttpServletRequest request)
    {
        boolean result= doctorTypeService.addDoctorType(dtname);
        UpdateDoctorType(request);
        return "redirect:type_list.action";
    }
    @RequestMapping("/admin/type_delete")
    public String RemoveDoctorType(int dtid,HttpServletRequest request)
    {
        boolean result= doctorTypeService.removeDoctorType(dtid);
        UpdateDoctorType(request);
        return "redirect:type_list.action";
    }

    @RequestMapping("/admin/type_update")
    public String ModifyDoctorType(int dtid,String dtname,HttpServletRequest request)
    {
        boolean result= doctorTypeService.modifyDoctorType(dtid,dtname);
        UpdateDoctorType(request);
        return "redirect:type_list.action";
    }

    public void UpdateDoctorType(HttpServletRequest request)
    {
        if(request.getServletContext().getAttribute("doctorTypes")==null)
        {
            request.getServletContext().setAttribute("doctorTypes", doctorTypeService.queryDoctorTypes());
        }
        else
        {
            request.getServletContext().removeAttribute("doctorTypes");
            request.getServletContext().setAttribute("doctorTypes", doctorTypeService.queryDoctorTypes());
        }
    }

    @RequestMapping("/admin/doctor_list")
    public String ShowDoctorByRecommend(int pageNumber,int rtype,HttpServletRequest request)
    {
        if(pageNumber<=0)
            pageNumber=1;
        Page p = doctorService.queryDoctorByRecommendType(rtype, pageNumber);
        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p = doctorService.queryDoctorByRecommendType(rtype, p.getTotalPage());
            }
        }
        request.setAttribute("p", p);
        request.setAttribute("rtype", rtype);
        return "/admin/doctor_list";
    }


    @RequestMapping("/admin/doctor_change")
    public String ChangeDoctorRecommend(int did,int rtype,String method,int page)
    {
        boolean result=false;
        if(method.equals("add")) {
             result= doctorService.addRecommend(did,rtype);
        }else {
            result= doctorService.remoteRecommend(did,rtype);
        }
       return  "redirect:doctor_list.action?pageNumber=1&rtype="+page;
    }

    @RequestMapping("/admin/doctor_delete")
    public String DeleteDoctor(int did,int rtype)
    {
        boolean result= doctorService.removeDoctorByDid(did);
        //
        return  "redirect:doctor_list.action?pageNumber=1&rtype="+rtype;
    }

    @RequestMapping("/admin/doctor_add")
    public String uploadimg(HttpServletRequest request) throws Exception{
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> list = upload.parseRequest(request);
            Doctor b = new Doctor();
            for(FileItem item:list) {
                if(item.isFormField()) {
                    switch(item.getFieldName()) {
                        case "dname":
                            b.setDname(item.getString("utf-8"));
                            break;
                        case "dprice":
                            b.setDprice(Double.parseDouble(item.getString("utf-8")));
                            break;
                        case "dmark":
                            b.setDmark(item.getString("utf-8"));
                            break;
                        case "dstock":
                            b.setDstock(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "dtid":
                           b.setDtid(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "dphone":
                            b.setDphone(item.getString("utf-8"));
                            break;
                        case "dqua":
                            b.setDqua(item.getString("utf-8"));
                            break;
                        case "dlocal":
                            b.setDlocal(item.getString("utf-8"));
                            break;
                    }
                }else {
                    if(item.getInputStream().available()<=0)continue;
                    String fileName = item.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("."));
                    fileName = "/"+new Date().getTime()+fileName;
                    String path = request.getServletContext().getRealPath("/images")+fileName;
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(path);
                    byte[] buffer = new byte[1024];
                    int len=0;
                    while( (len=in.read(buffer))>0 ) {
                        out.write(buffer);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    switch(item.getFieldName()) {
                        case "dcover":
                            b.setDcover("images"+fileName);
                            break;
                        case "dimage1":
                            b.setDimage1("images"+fileName);
                            break;
                        case "dimage2":
                            b.setDimage2("images"+fileName);
                            break;
                    }
                }
            }
            doctorService.addDoctor(b);
        } catch (FileUploadException e) {
            System.out.println(e.getMessage());
        }

        return  "redirect:doctor_list.action?pageNumber=1&rtype=0";

    }

    @RequestMapping("/admin/doctor_edit_show")
    public String ShowDoctorByDid(int did,HttpServletRequest request)
    {
        Doctor b = doctorService.queryDoctorByID(did);
        request.setAttribute("g", b);
        return "admin/doctor_edit";
    }

    @RequestMapping("/admin/doctor_update")
    public String ModifyDoctor(HttpServletRequest request) throws Exception {
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> list = upload.parseRequest(request);
            Doctor b=new Doctor();
            for(FileItem item:list) {
                if(item.isFormField()) {
                    switch(item.getFieldName()) {
                        case "did":
                            b.setDid(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "dname":
                            b.setDname(item.getString("utf-8"));
                            break;
                        case "dprice":
                            b.setDprice(Double.parseDouble(item.getString("utf-8")));
                            break;
                        case "dmark":
                            b.setDmark(item.getString("utf-8"));
                            break;
                        case "dstock":
                            b.setDstock(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "btid":
                            b.setDtid(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "dphone":
                            b.setDphone(item.getString("utf-8"));
                            break;
                        case "dqua":
                            b.setDqua(item.getString("utf-8"));
                            break;
                        case "dlocal":
                            b.setDlocal(item.getString("utf-8"));
                            break;
                        case "dcover":
                            b.setDcover(item.getString("utf-8"));
                            break;
                        case "dimage1":
                            b.setDimage1(item.getString("utf-8"));
                            break;
                        case "dimage2":
                            b.setDimage2(item.getString("utf-8"));
                            break;
                    }
                }else {
                    if(item.getInputStream().available()<=0)continue;
                    String fileName = item.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("."));
                    fileName = "/"+new Date().getTime()+fileName;
                    String path = request.getServletContext().getRealPath("/images")+fileName;
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(path);
                    byte[] buffer = new byte[1024];
                    int len=0;
                    while( (len=in.read(buffer))>0 ) {
                        out.write(buffer);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    switch(item.getFieldName()) {
                        case "dcover":
                            b.setDcover("images"+fileName);
                            break;
                        case "dimage1":
                            b.setDimage1("images"+fileName);
                            break;
                        case "dimage2":
                            b.setDimage2("images"+fileName);
                            break;
                    }
                }
            }
            doctorService.modifyDoctor(b);
            //
        } catch (FileUploadException e) {
            System.out.println(e.getMessage());
        }

        return  "redirect:doctor_list.action?pageNumber=1&rtype=0";
    }
}
