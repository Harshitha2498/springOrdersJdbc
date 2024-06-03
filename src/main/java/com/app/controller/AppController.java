package com.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.app.model.Items;
import com.app.model.OrderItems;
import com.app.model.Orders;
import com.app.service.OrdersService;

/**
 * @author Harshitha D
 * @Created: may 28, 2024
 * @File : AppController.java
 * @Description :
 */
@Controller
@RequestMapping("/")
public class AppController {
	@Autowired
	OrdersService orderService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView welcome(ModelMap model) {
		ModelAndView mv = new ModelAndView();
		List<Orders> list = orderService.listAllOrders();
		model.addAttribute("allorders", list);
		mv.setViewName("allorders");
		return mv;
	}


	@RequestMapping(value = { "/addOrder" }, method = RequestMethod.GET)
	public String addOrder(HttpSession session, Model model) {
		int id=orderService.createNewOrder();
		System.out.println(id+"&&&&&&&&&&&&&&&");
		List<Items> itemsList = orderService.getAllItems();
		model.addAttribute("itemsList", itemsList);
		model.addAttribute("orderId",id); 
		System.out.println(itemsList);
		return "addItems";
	}
	

	@RequestMapping(value = { "/edit-{id}" }, method = RequestMethod.GET)
	public String edit(@PathVariable int id, ModelMap model) {
		List<OrderItems> orderitem = orderService.getOrderItem(id);
		model.addAttribute("list", orderitem);
		model.addAttribute("edit", true);
		model.addAttribute("orderId",id);
		return "viewItems";
	}

	@RequestMapping(value = { "/delete-{id}" }, method = RequestMethod.GET)
	public ModelAndView deleteOrder(@PathVariable int id, ModelMap model) {
		ModelAndView mv = new ModelAndView();
		orderService.delete(id);
		List<Orders> list = orderService.listAllOrders();
		model.addAttribute("allorders", list);
		mv.setViewName("allorders");
		return mv;
	}
	@RequestMapping(value = {"/oidelete"})
	public String deleteOrderItem(@RequestParam(value="id",required=true)int id,@RequestParam("orderitemid")int orderitemid, ModelMap model) {
		orderService.deleteOrderItem(id);
		List<OrderItems> list =orderService.getOrderItem(orderitemid);
		model.addAttribute("orderId",orderitemid);
		model.addAttribute("list", list);
		return "viewItems";
	}

	@GetMapping("/{orderId}")
	public Orders getOrderById(@PathVariable int orderId) {
		return orderService.getOrderId(orderId);
	}

	@RequestMapping(path= { "/addOrderItems"}, method = RequestMethod.POST)
	public String saveItems(@RequestParam("orderId")int orderId,@RequestParam("itemId")int itemId,@RequestParam("quantity")int quantity,Model model) {
		System.out.println("helloo in save items"+itemId+"quantity::::"+quantity+"order id::::"+orderId);
		orderService.save(orderId,itemId,quantity);
		List<OrderItems> list =orderService.getOrderItem(orderId);
		model.addAttribute("orderId",orderId);
		model.addAttribute("list", list);
		return "viewItems";
	}

	
	@RequestMapping(value = { "/oiedit" })
	public String editOrderItem(@RequestParam("id")int id, ModelMap model) {
		System.out.println("hellooooo "+id);
//		List<OrderItems> list=orderService.getOrderItemByid(id);
		List<Items> itemsList = orderService.getAllItems();
		model.addAttribute("itemsList", itemsList);
		model.addAttribute("id",id);
//		System.out.println("idddd:::::"+id);
//		System.out.println(itemsList);
		return "editorder";
	}
	
	@RequestMapping(path= {"/addOrderedItems"},method = RequestMethod.POST)
	public String addOrderedItems(@RequestParam("orderId")int orderId,Model model) {
		System.out.println(orderId+"*****");
		List<Items> itemsList = orderService.getAllItems();
		
//		List<OrderItems> list =orderService.getOrderItem(orderId);
//		model.addAttribute("list", list);
		System.out.println("i am in addorderedItems method:"+itemsList);
		model.addAttribute("itemsList", itemsList);
		model.addAttribute("orderId",orderId);
		return "addOrderedItems";
	}
	@RequestMapping(path= {"/editOrderItems"},method=RequestMethod.POST)
	public String editOrderItems(@RequestParam("orderItemId")int orderItemId,@RequestParam("itemId")int itemId,@RequestParam("quantity")int quantity,ModelMap model) {
		orderService.editOrderItems(orderItemId,itemId,quantity);
		System.out.println("helloo in editOrderItems"+orderItemId);
		int orderId=orderService.fetchOrderIdById(orderItemId);
		List<OrderItems> list =orderService.getOrderItem(orderId);
		model.addAttribute("orderId",orderId);
		model.addAttribute("list", list);
		return "viewItems";
	}
	
//	@RequestMapping(path = {"/static/img/{path}"}, method = RequestMethod.GET)
//    public Resource getResource(@PathVariable String path) {
//		System.out.println(path);
//        return new ClassPathResource(path);
//    }
	
	@RequestMapping(path = {"/img/{path}"}, method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@PathVariable String path) throws IOException {
        // Load image data
        ClassPathResource resource = new ClassPathResource("img/"+path);
		System.out.println(resource.getFile().getAbsolutePath());
        byte[] imageData = Files.readAllBytes(resource.getFile().toPath());

        // Set content type header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        // Return image data as ResponseEntity
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

}
