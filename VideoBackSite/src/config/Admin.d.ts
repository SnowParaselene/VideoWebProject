/**
 * ApifoxModel
 */
export interface Admin {
    /**
     * 管理员账号，登录该账户所用账号
     */
    account: string;
    /**
     * 管理员id，管理员账号所属id
     */
    aid: string;
    /**
     * 管理员头像，账号头像
     */
    headImage: string;
    /**
     * 可操作菜单，根据该管理员权限可操作的菜单列表
     */
    menuMap: string[];
    /**
     * 管理员名称，管理员所设置的账户名称
     */
    name: string;
    /**
     * 登录token，用于校验是否登录以及是否登录过期
     */
    token: string;
}