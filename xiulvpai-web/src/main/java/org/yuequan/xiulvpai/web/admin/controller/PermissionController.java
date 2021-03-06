package org.yuequan.xiulvpai.web.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.yuequan.xiulvpai.common.domain.entity.Permission;
import org.yuequan.xiulvpai.web.admin.annotation.AdminController;
import org.yuequan.xiulvpai.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Permission {@link AdminController}
 * @author yuequan
 * @since 1.0
 */
@AdminController(path = "permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public String index(Model model) throws JsonProcessingException {
        model.addAttribute("permissions", permissionService.getPermissions());
        return "admin/permissions/index";
    }

    @PostMapping
    public String create(Permission permission){
        permissionService.save(permission);
        return "redirect:./";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        permissionService.delete(id);
        return "redirect:./";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("permission", permissionService.findById(id));
        return "admin/permissions/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, Permission permission){
        permissionService.save(permission);
        return "redirect:./";
    }
}
