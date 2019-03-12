package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.respository.SiteDao;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {

	@Autowired
	private SiteDao siteDao;
	
//--------------------------------------
	
	public SiteVo main(){			
		return siteDao.get();		
	}
	public void main(SiteVo siteVo){			
		siteDao.update(siteVo);		
	}
}
