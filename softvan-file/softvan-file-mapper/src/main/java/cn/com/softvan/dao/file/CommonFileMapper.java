/*
 * @ClassName CommonFile
 * @Description
 * @version 1.0
 * @Date 2017/7/11 11:39:2
 */
package cn.com.softvan.dao.file;

import cn.com.softvan.model.file.CommonFile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommonFileMapper {
   @Insert("insert into t_common_file (id,file_name,file_url,file_order,id_upload,size,link_id,keyword,date_created)" +
            " values(#{id},#{fileName},#{fileUrl},#{fileOrder},#{idUpload},#{size},#{linkId},#{keyword},now())")
    int insert(CommonFile record) throws Exception;

    @Delete("delete from t_common_file where link_id = #{linkId} and keyword = #{keyword}")
    void deleteByLinkInfo(CommonFile record) throws Exception;

    @Select("select id as id," +
            "file_name as fileName," +
            "file_url as fileUrl," +
            "file_order as fileOrder," +
            "id_upload as idUpload," +
            "size as size," +
            "link_id as linkId," +
            "keyword as keyword," +
            "date_created as dateCreated," +
            "create_id as createId," +
            "create_ip as createIp," +
            "date_updated as dateUpdated," +
            "update_id as updateId," +
            "update_ip as updateIp " +
            "from t_common_file where link_id = #{linkId} and keyword = #{keyword} and del_flag = '0'")
    List<CommonFile> selectByLinkInfo(CommonFile record);
}
