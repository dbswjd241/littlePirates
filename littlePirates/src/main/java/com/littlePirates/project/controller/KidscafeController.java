package com.littlePirates.project.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.littlePirates.project.model.KcreviewVO;
import com.littlePirates.project.model.KidscafeVO;
import com.littlePirates.project.service.KcreviewService;
import com.littlePirates.project.service.KidscafeService;

@Controller
public class KidscafeController {
	@Autowired
	private KidscafeService service;
	@Autowired
	private KcreviewService kcservice;
	
	@RequestMapping("/kidscafe_map")
	public String kidscafe_map(Model model) {
		ArrayList<KidscafeVO> voList = service.listKidscafeInfo();
		model.addAttribute("voList", voList);
		
		return "menu/kidscafe/kidscafe_map";
	}

	// 키즈카페 지도(이름, 위도, 경도)불러오기
	@ResponseBody
	@RequestMapping("/kidscafe_map/loaddata")
	public ArrayList<KidscafeVO> loaddata() {
		ArrayList<KidscafeVO> voList = service.listKidscafeInfo();
		
		return voList;
	}
	
	@ResponseBody
	@RequestMapping("/kidscafe_map/search")
	public String search(@RequestParam("searchAddress") String keyword) {
		
		String address = service.searchKidsCafe(keyword);
		
		return address;
	}
	
	// 간편하게 보기 페이지 열기
	@RequestMapping("/kidscafe_sec")
	public String kidscafe_sec(Model model) {
		ArrayList<KidscafeVO> voList = service.listKidscafeInfo();

		model.addAttribute("voList", voList);
		
		return "menu/kidscafe/kidscafe_sec";
	}

	// 시/도 선택시 해당되는 시/도 출력
	@RequestMapping("/kidscafe_sec/search")
	public String kidscafe_sec_search(@RequestParam String sido, Model model) {
		ArrayList<KidscafeVO> voList = service.kidscafeSearchsido(sido);
		
		model.addAttribute("voList", voList);
		
		return "menu/kidscafe/kidscafe_sec_sido";
	}

	// 구/군 선택시 해당되는 구/군 출력
	@RequestMapping("/kidscafe_sec/search_gu")
	public String kidscafe_sec_search_gu(@RequestParam String sido, 
										 @RequestParam String gu, 
										 Model model) {
		ArrayList<KidscafeVO> voList = service.kidscafeSearchgu(sido, gu);
		
		model.addAttribute("voList", voList);
		
		return "menu/kidscafe/kidscafe_sec_gu";
	}
	
	//	키즈카페 키워드 검색 후 해당 관련 정보 나타내기
	@RequestMapping("/kidscafe_map/kidscafeSearch")
	public String kidscafeSearch(@RequestParam String keyword, Model model){
		
		ArrayList<KidscafeVO> voList = service.kidscafeSearchkeyword(keyword);

		model.addAttribute("voList", voList);
		
		return "menu/kidscafe/kidscafeSearch";	
	}
	
	// 키즈카페 후기 목록
	@RequestMapping("/kidscafeReview")
	public String kidscafeReview(Model model) {
		ArrayList<KcreviewVO> revewList = kcservice.listAll();
		model.addAttribute("reviewList", revewList);
		
		return "menu/kidscafe/kidscafeReview";
	}
	
	@RequestMapping("/kidscafeReviewWrite")
	public String kidscafeReviewWrite(Model model) {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
        Date time = new Date();
        String time1 = format1.format(time);
        model.addAttribute("time1",time1);
		return "menu/kidscafe/kidscafeReviewWrite";
	}

	@RequestMapping("/kidscafeReview/Write")
	public String WirteForm(KcreviewVO kcr, HttpSession session) {
		
		return "menu/redirect:/kidscafeReview";
	}
}
