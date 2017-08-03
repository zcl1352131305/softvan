/*
 * @ClassName CommonFile
 * @Description
 * @version 1.0
 * @Date 2017/7/11 11:39:2
 */
package cn.com.softvan.model.file;

import cn.com.softvan.model.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonFile extends BaseBean {
	/**
	 * @Fields userName 文件原名称
	 */
	private String fileName;
	/**
	 * @Fields userName 文件地址
	 */
	private String fileUrl;
	/**
	 * @Fields userName 文件排序
	 */
	private String fileOrder;
	/**
	 * @Fields userName 上传时内ID
	 */
	private String idUpload;
	/**
	 * @Fields userName 大小
	 */
	private String size;
	/**
	 * @Fields userName 关联ID
	 */
	private String linkId;
	/**
	 * @Fields userName 标志
	 */
	private String keyword;

}
