package cn.tedu.controller;


import cn.tedu.pojo.Cart;
import cn.tedu.service.DubboCartService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Reference(check = false)
    private DubboCartService cartService;
    /**
     * 业务思路: 当用户点击购物车按钮时,应该根据userId查询购物车信息,之后在列表页面中展现.
     * 页面数据展现:  利用${cartList}展现数据
     * @return
     */
    @RequestMapping("/show")
    public String show(Model model){
        //1.获取userId  利用单点登录方式动态获取userID  暂时定死
        Long userId=7L;
        //2.根据userId查询购物车数据
        List<Cart> cartList=cartService.findCartListByUserId(userId);
        //利用model对象将数据填充到域对象中request域
        model.addAttribute("cartList",cartList);
        return "cart";
    }
}
