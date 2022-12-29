package cn.itcast.controller;

import cn.itcast.bean.Doctor;
import cn.itcast.service.DoctorService;
import cn.itcast.service.OrderService;
import cn.itcast.bean.Order;
import cn.itcast.bean.Page;
import cn.itcast.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    public DoctorService doctorService;
    @Autowired
    public OrderService orderService;

    @RequestMapping(value = "/doctors_buy")
    public void AddDoctorToCart(@RequestParam("did") int did, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Order o = null;
        if(request.getSession().getAttribute("order") != null) {
            o = (Order) request.getSession().getAttribute("order");
        }else {
            o = new Order();
            o.setOamount(0);
            o.setOtotal(0.0);
            request.getSession().setAttribute("order", o);
        }
        Doctor doctor = doctorService.queryDoctorByID(did);
        if(doctor.getDstock()>0) {
            o.addGoods(doctor);
            response.getWriter().print("ok");
        }else {
            response.getWriter().print("fail");
        }
    }
    @RequestMapping(value = "/doctors_lessen")
    public void LessenDoctorToCart(@RequestParam("did") int did, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Order o = (Order) request.getSession().getAttribute("order");
        o.lessen(did);
        response.getWriter().print("ok");
    }

    @RequestMapping(value = "/doctors_delete")
    public void DeleteDoctorToCart(@RequestParam("did") int did, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Order o = (Order) request.getSession().getAttribute("order");
        o.delete(did);
        response.getWriter().print("ok");
    }

    @RequestMapping(value = "/order_confirm")
    public String OrderConfirm( int opaytype,HttpServletRequest request)
    {
        Order o = (Order) request.getSession().getAttribute("order");
        o.setOstatus(2);
        o.setOpaytype(opaytype);
        User user=(User)request.getSession().getAttribute("user");
        o.setUid(user.getUid());
        o.setOrealname(user.getUrealname());
        o.setOphone(user.getUphone());
        o.setOaddress(user.getUaddress());
        boolean result=orderService.addOrder(o);
        if(result)
        {
            request.getSession().removeAttribute("order");
            request.setAttribute("msg", "订单支付成功！");
            return "order_result";
        }
        else
        {
            request.setAttribute("failmsg", "订单支付失败！");
            return "order_result";
        }

    }
    @RequestMapping("/order_submit")
    public String OrderSubmit(HttpServletRequest request)
    {
        if(request.getSession().getAttribute("user")!=null) {
            return "order_submit";
        }else {
            request.setAttribute("failMsg", "请登录后，再提交订单！");
            return "redirect:user_login.jsp";
        }
    }

    @RequestMapping("order_list")
    public String ShowOrderByUid(HttpServletRequest request)
    {
        User user=(User)request.getSession().getAttribute("user");
        if(user==null)
        {
            return "redirect:/index.action";
        }
        List<Order> orderList=orderService.queryOrderByUid(user.getUid());
        request.setAttribute("orderList", orderList);
        return "order_list";
    }

    @RequestMapping("/admin/order_list")
    public String ShowOrderList(int pageNumber,int ostatus,HttpServletRequest request)
    {
        request.setAttribute("ostatus", ostatus);
        if(pageNumber<=0)
            pageNumber=1;
        Page p = orderService.queryOrdersByOstatus(ostatus,pageNumber);
        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p = orderService.queryOrdersByOstatus(ostatus,p.getTotalPage());
            }
        }

        request.setAttribute("p", p);
        return "admin/order_list";
    }

    @RequestMapping("/admin/order_status_change")
    public String ChangeOrderStatusByOid(String oid,int ostatus,HttpServletRequest request)
    {
        boolean result= orderService.updateOrderStatus(oid,ostatus);
        return "redirect:order_list.action?pageNumber=1&ostatus="+ostatus;
    }

    @RequestMapping("/admin/order_delete")
    public String DeleteOrderByOid(String oid,int ostatus,HttpServletRequest request)
    {
        boolean result= orderService.deleteOrderByOid(oid);
        return "redirect:order_list.action?pageNumber=1&ostatus="+ostatus;
    }

}
