package com.coolweather.app.util;

import com.coolweather.app.model.City;
import com.coolweather.app.model.CoolWeatherDB;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import android.text.TextUtils;


public class Utility {

	//解析和处理服务器返回的省级数据
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
		//response作为参数传进去。只要这个参数为空或者为“”，都会返回真。
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces=response.split(",");//" ，" 分割
			if(allProvinces!=null&&allProvinces.length>0){
				for(String p:allProvinces){
					String[]array=p.split("\\|");
					Province province=new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					//将解析出来的数据存储到Province表
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;	
	}
	
	//解析和处理服务器返回的市级数据
	public static  boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
		if(!TextUtils.isEmpty(response)){
			String[]allCity=response.split(",");
			if(allCity!=null&&allCity.length>0){
				for(String c:allCity){
					String[] array=c.split("\\|");
					City city=new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					//将解析出来的数据存到City表
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	//解析和处理服务器返回的县级数据
	public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
		if(!TextUtils.isEmpty(response)){
			String[]allCounty=response.split(",");
			if(allCounty!=null&&response.length()>0){
				for(String c:allCounty){
					String[]array=c.split("\\|");
					County county=new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					//将解析出来的数据存到County表
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
			
		}
		return false;
	}
	
}
