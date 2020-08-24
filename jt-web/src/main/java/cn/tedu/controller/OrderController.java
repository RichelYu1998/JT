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
     * 1.实现订单入库
     * url:http://www.jt.com/order/submit
     * 参数: 整个form表单    利用order对象接收
     * 返回值: SysResult对象    返回orderId
     * 业务: 订单入库时应该入库3张表记录. order  orderShipping  orderItems
     *       orderId由登录用户id+当前时间戳手动 拼接.
     *       并且要求三个对象的主键值相同.
     */
    @RequestMapping("/submit")
    @ResponseBody
    public SysResult submit(Order order){
        //利用拦截器的方式赋值.
        Long userId = UserThreadLocal.get().getId();
        order.setUserId(userId);
        //1.完成订单入库,并且返回orderId
        String orderId = orderService.saveOrder(order);
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