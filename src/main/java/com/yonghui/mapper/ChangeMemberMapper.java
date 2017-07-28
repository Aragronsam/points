package com.yonghui.mapper;

import com.yonghui.entity.ChangeMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 功能描述:
 *
 * @Author: Aragron
 * @Create: 2017-06-29-17:06
 * @Home: http://aragron.com
 */
@Mapper
public interface ChangeMemberMapper {
    String insert = "INSERT INTO change_member" +
            "(iid, member_id, integral, create_time, update_time, status_flag, wechat_bonus_flag, last_change_time)" +
            "VALUES" +
            "(#{iid}, #{memberId}, #{integral}, #{createTime}, #{updateTime}, #{statusFlag}, #{wechatBonusFlag}, #{lastChangeTime})";
    String findById = "SELECT * FROM change_member WHERE id = #{id}";
    //String findByMemberId = "SELECT * FROM change_member WHERE member_id = #{memberId}";

    /**
     * 根据会员id查询
     * @param id
     * @return
     */
    @Select({findById})
    ChangeMember findById(@Param("id") Integer id);

    /**
     * 根据会员卡号查询
     * @param memberId
     * @return
     */
    //@Select({findByMemberId})
    ChangeMember findByMemberId(@Param("memberId") String memberId);

    /**
     * 新增记录
     * @param changeMember
     * @return
     */
    //@Insert({insert})
    int insert(ChangeMember changeMember);

    /**
     * 新增记录(批量)
     * @param changeMemberList
     * @return
     */
    int insertBatch(@Param("list") List<ChangeMember> changeMemberList);
}
