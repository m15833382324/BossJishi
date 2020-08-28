package com.dist.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapSortUtil {
	
	private MapSortUtil() {
	}
	
	/**
	 *  正序排序integer
	 * @param mapList
	 * @param sortStr
	 * @return
	 */
	public static <K,V> List<Map<K, V>> sortMapAscInteger(List<Map<K,V>> mapList,Object sortStr){
		
		//这里将map.entrySet()转换成list
        //然后通过比较器来实现排序
        Collections.sort(mapList,new Comparator<Map>() {
			@Override
			public int compare(Map o1, Map o2) {
				// TODO Auto-generated method stub
				return ((Integer) o1.get(sortStr)).compareTo((Integer)o2.get(sortStr));
			}
        });
		
		return mapList;
	}
	/**
	 *  正序排序string
	 * @param mapList
	 * @param sortStr
	 * @return
	 */
	public static <K,V> List<Map<K, V>> sortMapAscString(List<Map<K,V>> mapList,Object sortStr){
		
		//这里将map.entrySet()转换成list
		//然后通过比较器来实现排序
		Collections.sort(mapList,new Comparator<Map>() {
			@Override
			public int compare(Map o1, Map o2) {
				// TODO Auto-generated method stub
				return ((String) o1.get(sortStr)).compareTo((String)o2.get(sortStr));
			}
		});
		
		return mapList;
	}
	
	
	/**
	 *  倒序排序integer
	 * @param mapList
	 * @param sortStr
	 * @return
	 */
	public static <K,V> List<Map<K, V>> sortMapDescInteger(List<Map<K,V>> mapList,Object sortStr){
		
		//这里将map.entrySet()转换成list
        //然后通过比较器来实现排序
        Collections.sort(mapList,new Comparator<Map>() {
			@Override
			public int compare(Map o1, Map o2) {
				// TODO Auto-generated method stub
				return ((Integer) o2.get(sortStr)).compareTo((Integer)o1.get(sortStr));
			}
        });
		
		return mapList;
	}
	/**
	 *   倒序排序string
	 * @param mapList
	 * @param sortStr
	 * @return
	 */
	public static <K,V> List<Map<K, V>> sortMapDescString(List<Map<K,V>> mapList,Object sortStr){
		
		//这里将map.entrySet()转换成list
		//然后通过比较器来实现排序
		Collections.sort(mapList,new Comparator<Map>() {
			@Override
			public int compare(Map o1, Map o2) {
				// TODO Auto-generated method stub
				return ((String) o2.get(sortStr)).compareTo((String)o1.get(sortStr));
			}
		});
		
		return mapList;
	}
}
