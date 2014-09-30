package com.yeahmobi.yunit.sample.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.h2.schema.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import sun.security.krb5.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeahmobi.yunit.sample.service.PersonService;

/**
 * @author YCodegen
 */
@Controller
public class PersonController {

	private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	/**
	 * 首页，使用说明
	 */
	@RequestMapping(value = { "/readme" })
	public ModelAndView readme() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("readmeActive", "active");
		map.put("path", "readme");
		return new ModelAndView("share/container", map);
	}

	/**
	 * 首页，使用说明
	 */
	@RequestMapping(value = { "/readme2" })
	public MappingJackson2JsonView readme2() {

		MappingJackson2JsonView view = new MappingJackson2JsonView();

		ObjectMapper mapper = view.getObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();

		mapper.put("readmeActive", "active");
		map.put("path", "readme");
		return view;
	}

	@RequestMapping(value = "/safe/switch", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object saveSwitchOpen(HttpSession session, Boolean switchOpen) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String aliUsername = (String) session.getAttribute(Config.ALI_USERNAME);
			if (switchOpen != null) {
				this.repubService.doSwitchOpen(aliUsername, switchOpen);
			}

			map.put("success", true);
		} catch (IllegalArgumentException e) {
			map.put("success", false);
			map.put("errorMsg", e.getMessage());
		} catch (Exception e) {
			map.put("success", false);
			map.put("errorMsg", Constant.ERROR_MSG);
			LOG.error(e.getMessage(), e);
		}
		return GsonUtil.toJson(map);
	}

}
