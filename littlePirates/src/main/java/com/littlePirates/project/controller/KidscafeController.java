package com.littlePirates.project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.littlePirates.project.model.KidscafeVO;
import com.littlePirates.project.service.KidscafeService;

@Controller
public class KidscafeController {
	@Autowired
	private KidscafeService service;
	
	@RequestMapping("/kidscafe_map")
	public String kidscafe_map(Model model) {
		ArrayList<KidscafeVO> voList = service.listKidscafeInfo();
		model.addAttribute("voList", voList);
		
		return "kidscafe/kidscafe_map";
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
	
	@RequestMapping("/kidscafe_sec")
	public String kidscafe_sec(Model model) {
		ArrayList<KidscafeVO> voList = service.listKidscafeInfo();

		model.addAttribute("voList", voList);
		
		return "kidscafe/kidscafe_sec";
	}

	@RequestMapping("/kidscafe_sec/search")
	public String kidscafe_sec_search(@RequestParam String sido, Model model) {
		ArrayList<KidscafeVO> voList = service.kidscafeSearchsido(sido);
		
		model.addAttribute("voList", voList);
		
		return "kidscafe/kidscafe_sec_sido";
	}
	
	//	키즈카페 키워드 검색
	@RequestMapping("/kidscafe_map/kidscafeSearch1")
	public String kidscafeSearch1(@RequestParam String keyword, Model model){
		
		System.out.println(keyword);
		ArrayList<KidscafeVO> voList = service.kidscafeSearchkeyword(keyword);

		model.addAttribute("voList", voList);
		
		return "kidscafe/kidscafeSearch1";	
	}
	
	
	
}
