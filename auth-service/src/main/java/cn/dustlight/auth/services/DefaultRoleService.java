package cn.dustlight.auth.services;

import cn.dustlight.auth.ErrorEnum;
import cn.dustlight.auth.entities.Role;
import cn.dustlight.auth.generator.UniqueGenerator;
import cn.dustlight.auth.mappers.AuthorityMapper;
import cn.dustlight.auth.mappers.RoleMapper;

import java.util.Collection;

public class DefaultRoleService implements RoleService {

    private final UniqueGenerator<Long> idGenerator;
    private final RoleMapper roleMapper;
    private final AuthorityMapper authorityMapper;

    public DefaultRoleService(UniqueGenerator<Long> idGenerator,
                              RoleMapper roleMapper,
                              AuthorityMapper authorityMapper) {
        this.idGenerator = idGenerator;
        this.roleMapper = roleMapper;
        this.authorityMapper = authorityMapper;
    }

    @Override
    public Collection<? extends Role> listRoles() {
        return roleMapper.listRoles();
    }

    @Override
    public Collection<? extends Role> getRoles(Collection<Long> rids) {
        return roleMapper.selectRoles(rids);
    }

    @Override
    public Role getRole(Long rid) {
        return roleMapper.selectRole(rid);
    }

    @Override
    public void createRole(String name, String description) {
        if (!roleMapper.insertRole(idGenerator.generate(), name, description))
            ErrorEnum.CREATE_ROLE_FAIL.throwException();
    }

    @Override
    public void createRoles(Collection<? extends Role> roles) {
        if (!roleMapper.insertRoles(roles))
            ErrorEnum.CREATE_ROLE_FAIL.throwException();
    }

    @Override
    public void removeRole(Long rid) {
        if (!roleMapper.deleteRole(rid))
            ErrorEnum.DELETE_ROLE_FAIL.throwException();
    }

    @Override
    public void removeRoles(Collection<Long> rids) {
        if (!roleMapper.deleteRoles(rids))
            ErrorEnum.DELETE_ROLE_FAIL.throwException();
    }

    @Override
    public Collection<String> listRoleAuthorities(Long rid) {
        return authorityMapper.listRoleAuthorities(rid);
    }

    @Override
    public void createRoleAuthorities(Long rid, Collection<Long> aids) {
        if (!authorityMapper.insertRoleAuthorities(rid, aids))
            ErrorEnum.CREATE_AUTHORITY_FAIL.throwException();
    }

    @Override
    public void removeRoleAuthorities(Long rid, Collection<Long> aids) {
        if (!authorityMapper.deleteRoleAuthorities(rid, aids))
            ErrorEnum.DELETE_AUTHORITY_FAIL.throwException();
    }
}
