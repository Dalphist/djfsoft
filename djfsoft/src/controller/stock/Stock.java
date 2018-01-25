package controller.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pojo.ResultBean;
import pojo.stock.StockInfo;
import service.stock.StockService;

@Controller
@RequestMapping("stock")
public class Stock {
	@Autowired
	StockService stockService;
	
	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		String url = "stock/index";
		mav.setViewName(url);
		return mav;
	}
	@RequestMapping("stockInfoList")
	public ModelAndView stockInfoList(){
		ModelAndView mav = new ModelAndView();
		List<StockInfo> list = stockService.getStockList();
		mav.addObject("list", list);
		String url = "stock/stockInfoList";
		mav.setViewName(url);
		return mav;
	}
}
