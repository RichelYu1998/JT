package cn.tedu.controller;


import cn.tedu.pojo.Cart;
import cn.tedu.service.DubboCartService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    /**
     * 业务需求:  完成购物车更新操作
     * 1.url地址: http://www.jt.com/cart/update/num/562379/13
     * 2.请求参数: 562379-itemId  /13-num
     * 3.返回值结果: void
     *
     */
    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public void updateCart(Cart cart){
        //userId和itemId
        Long userId=7L;
        cart.setUserId(userId);
        cartService.updateCartNum(cart);
    }
    /**
     * 业务:删除购物车操作
     * url地址: http://www.jt.com/cart/delete/562379.html
     * 参数问题: 562379
     * 返回值结果: 重定向到购物车列表页面
     */
    @RequestMapping("delete/{itemId}")
    public String deleteCart(@PathVariable("itemId") Long itemId){
        Long userId=7L;
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setItemId(itemId);
        cartService.deleteCart(cart);
        return "redirect:/cart/show.html"; //维护伪静态策略
    }
    /**
     * url地址:http://www.jt.com/cart/add/562379.html
     * 参数: 表单数据提交  cart
     * 返回值: 重定向到购物车展现页面
     */
    @RequestMapping("/add/{itemId}")
    public String saveCart(Cart cart){
        Long userId=7L;
        cart.setUserId(userId);
        cartService.saveCart(cart);
        return "redirect:/cart/show.html"; //维护伪静态策略
    }
}
