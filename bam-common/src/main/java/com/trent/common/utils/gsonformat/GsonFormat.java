package com.trent.common.utils.gsonformat;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Trent
 * @Date: 2022/8/5 11:15
 * @program: BAM
 * @Description: 使用gsonFormat生成demo      快捷键 Alt+s
 */
@NoArgsConstructor
@Data
public class GsonFormat{
	private String code;
	private DataDTO data;
	private String message;
	private PageDTO page;
	private String result;
	
	@NoArgsConstructor
	@Data
	public static class DataDTO{
	}
	
	@NoArgsConstructor
	@Data
	public static class PageDTO{
		private String pageDiv;
		private Integer currentPageDataSize;
		private Integer currentPageNumber;
		private List<DataListDTO> dataList;
		private Integer pageMaxSize;
		private Integer pageSize;
		private Integer totalPageSize;
		private Integer totalSize;
		
		@NoArgsConstructor
		@Data
		public static class DataListDTO{
		}
	}
	
	/**
	 * {
	 *   "code": "string",
	 *   "data": {},
	 *   "message": "string",
	 *   "page": {
	 *     "_page_div": "string",
	 *     "currentPageDataSize": 0,
	 *     "currentPageNumber": 0,
	 *     "dataList": [
	 *       {}
	 *     ],
	 *     "pageMaxSize": 0,
	 *     "pageSize": 0,
	 *     "totalPageSize": 0,
	 *     "totalSize": 0
	 *   },
	 *   "result": "string"
	 * }
	 */
	

}
