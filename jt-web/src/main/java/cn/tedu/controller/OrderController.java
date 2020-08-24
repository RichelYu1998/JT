package cn.tedu.controller;

import cn.tedu.pojo.Cart;
import cn.tedu.pojo.Order;
import cn.tedu.pojo.User;
import cn.tedu.service.DubboCartService;
import cn.tedu.service.DubboOrderService;
import cn.tedu.util.UserThreadLocal;
import cn.tedu.vo.SysResult;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//1.业务名称与Controller对应即可
@Controller
@RequestMapping("/order")
public class OrderController {
    //2.添加接口
    @Reference(check = false)
    private DubboCartService cartService;
    @Reference(check = false)
    private DubboOrderService orderService;
    /**
     * 跳转订单确认页面
     * 1.url:http://www.jt.com/order/create.html
     * 2.参数: 没有参数 null
     * 3.返回值: order-cart
     * 4.页面取值  {carts}  需要查询购物车信息.,给页面返回
     */
    @RequestMapping("create")
    public String create(Model model) {
        Long userId = UserThreadLocal.get().getId();
        List<Cart> cartList = cartService.findCartListByUserId(userId);
        model.addAttribute("carts", cartList);
        return "order-cart";
    }

    /**
     * 1.url地址:http://www.jt.com/order/submit
     * 2.参数   form表单提交
     * 3.返回值  SysResult对象  并且包含orderId数据
     *
     * @return
     */
    @RequestMapping("/submit")
    @ResponseBody
    public SysResult saveOrder(Order order, HttpServletRequest request) {
        User user = (User) request.getAttribute("JT_USER");
        Long userId = user.getId();
        order.setUserId(userId);    //将userId进行赋值操作.
        String orderId = orderService.saveOrder(order);
        if (StringUtils.isEmpty(orderId)) {
            //说明:后端服务器异常
            return SysResult.fail();
        }
        return SysResult.success(orderId);
    }
    //http://www.jt.com/order/success.html?id=111595833611692
    //获取order对象信息   ${order.orderId}
    @RequestMapping("success")
    public String findOrderById(String id,Model model) {
        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);
        return "success";
    }
}