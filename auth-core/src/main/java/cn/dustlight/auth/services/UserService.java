package cn.dustlight.auth.services;

import cn.dustlight.auth.util.QueryResults;
import cn.dustlight.auth.entities.PublicUser;
import cn.dustlight.auth.entities.Role;
import cn.dustlight.auth.entities.User;
import cn.dustlight.auth.entities.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.Date;

/**
 * 用户相关Service
 *
 * @param <T> 用户信息实体
 * @param <V> 用户公开信息实体
 */
public interface UserService<T extends User, V extends PublicUser> extends UserDetailsService {
    /**
     * 创建用户
     *
     * @param username             用户名
     * @param password             密码
     * @param email                注册邮箱
     * @param nickname             昵称
     * @param gender               性别
     * @param roles                角色集合
     * @param accountExpiredAt     账号过期时间
     * @param credentialsExpiredAt 凭证过期时间
     * @param unlockedAt           账号解锁时间
     * @param enabled              账号是否启用
     */
    void createUser(String username, String password, String email, String nickname, int gender,
                    Collection<UserRole> roles, Date accountExpiredAt, Date credentialsExpiredAt, Date unlockedAt, boolean enabled);

    /**
     * 通过uid获取用户
     *
     * @param uid 用户uid
     * @return 用户对象
     */
    T loadUser(Long uid);

    /**
     * 通过用户名或邮箱获取用户
     *
     * @param uoe 用户名或邮箱
     * @return 用户对象
     */
    @Override
    T loadUserByUsername(String uoe);

    /**
     * 通过uid集合获取用户（公开）集合
     *
     * @param uidArray 用户uid集合
     * @return 用户对象集合
     */
    Collection<V> loadPublicUserByUid(Collection<Long> uidArray);

    /**
     * 通过用户名集合获取用户集合
     *
     * @param usernames 用户名集合
     * @return 用户对象集合
     */
    Collection<T> loadUsersByUsername(Collection<String> usernames);

    /**
     * 通过uid集合获取用户集合
     *
     * @param uids 用户uid集合
     * @return 用户对象集合
     */
    Collection<T> loadUsers(Collection<Long> uids);

    /**
     * 列举用户
     *
     * @param orderBy 排序
     * @param offset  偏移
     * @param limit   上限
     * @return 查询结果
     */
    QueryResults<T> listUsers(Collection<String> orderBy, Integer offset, Integer limit);

    /**
     * 搜索用户
     *
     * @param keywords 关键词
     * @param orderBy  排序
     * @param offset   偏移
     * @param limit    本次查询上限
     * @return 查询结果
     */
    QueryResults<T> searchUsers(String keywords, Collection<String> orderBy, Integer offset, Integer limit);

    /**
     * 搜索用户公开信息
     *
     * @param keywords 关键词
     * @param orderBy  排序
     * @param offset   偏移
     * @param limit    本次查询上限
     * @return 查询结果
     */
    QueryResults<V> searchPublicUsers(String keywords, Collection<String> orderBy, Integer offset, Integer limit);

    /**
     * 更新用户密码
     *
     * @param uid      用户uid
     * @param password 新密码
     */
    void updatePassword(Long uid, String password);

    /**
     * 通过邮箱更新密码
     *
     * @param email    用户邮箱
     * @param password 新密码
     */
    void updatePasswordByEmail(String email, String password);

    /**
     * 更新用户昵称
     *
     * @param uid      用户uid
     * @param nickname 用户昵称
     */
    void updateNickname(Long uid, String nickname);

    /**
     * 更新用户性别
     *
     * @param uid    用户uid
     * @param gender 用户性别
     */
    void updateGender(Long uid, int gender);

    /**
     * 更新用户邮箱
     *
     * @param uid   用户uid
     * @param email 用户邮箱
     */
    void updateEmail(Long uid, String email);

    /**
     * 为用户添加角色
     *
     * @param uid   用户uid
     * @param roles 角色集合
     */
    void addRoles(Long uid, Collection<UserRole> roles);

    /**
     * 为用户移除角色
     *
     * @param uid     用户uid
     * @param roleIds 角色id集合
     */
    void removeRoles(Long uid, Collection<Long> roleIds);

    /**
     * 获取用户角色
     *
     * @param uid 用户uid
     * @return 用户角色
     */
    Collection<? extends Role> getRoles(Long uid);

    /**
     * 更新用户解锁时间
     *
     * @param uids       用户uid集合
     * @param unlockedAt 用户解锁时间
     */
    void updateUnlockedAt(Collection<Long> uids, Date unlockedAt);

    /**
     * 更新账号过期时间
     *
     * @param uids      用户uid集合
     * @param expiredAt 账号过期时间
     */
    void updateAccountExpiredAt(Collection<Long> uids, Date expiredAt);

    /**
     * 更新凭证过期时间
     *
     * @param uids      用户uid集合
     * @param expiredAt 凭证过期时间
     */
    void updateCredentialsExpiredAt(Collection<Long> uids, Date expiredAt);

    /**
     * 更新用户是否启用
     *
     * @param uids    用户uid集合
     * @param enabled 用户是否启用
     */
    void updateEnabled(Collection<Long> uids, boolean enabled);

    /**
     * 删除用户
     *
     * @param uids 用户uid集合
     */
    void deleteUsers(Collection<Long> uids);

    /**
     * 检查用户名是否存在
     *
     * @param username 用户名
     * @return 用户名是否存在
     */
    boolean isUsernameExists(String username);

    /**
     * 检查邮箱是否存在
     *
     * @param email 邮箱
     * @return 邮箱是否存在
     */
    boolean isEmailExists(String email);
}
