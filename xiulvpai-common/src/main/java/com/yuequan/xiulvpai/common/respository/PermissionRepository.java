package com.yuequan.xiulvpai.common.respository;

import com.yuequan.xiulvpai.common.domain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 权限持久化操作类
 * @author yuequan
 * @since 1.0
 **/
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}