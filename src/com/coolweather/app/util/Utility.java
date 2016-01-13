package com.coolweather.app.util;

import com.coolweather.app.model.City;
import com.coolweather.app.model.CoolWeatherDB;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import android.text.TextUtils;


public class Utility {

	//�����ʹ�����������ص�ʡ������
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
		//response��Ϊ��������ȥ��ֻҪ�������Ϊ�ջ���Ϊ���������᷵���档
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces=response.split(",");//" ��" �ָ�
			if(allProvinces!=null&&allProvinces.length>0){
				for(String p:allProvinces){
					String[]array=p.split("\\|");
					Province province=new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					//���������������ݴ洢��Province��
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;	
	}
	
	//�����ʹ�����������ص��м�����
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
					//���������������ݴ浽City��
					coolWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	//�����ʹ�����������ص��ؼ�����
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
					//���������������ݴ浽County��
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
			
		}
		return false;
	}
	
}
