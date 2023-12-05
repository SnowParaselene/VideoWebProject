/**
 * 用户
 */
export type User = {
    /**
     * 登录账号，用于用户登录的账号名
     */
    account: string;
    /**
     * 用户头像，用户头像静态资源路径
     */
    headImage: string;
    /**
     * 用户名，用户昵称
     */
    name: string;
    /**
     * 登录token，用于校验是否登录以及是否登录过期
     */
    token: string;
    /**
     * 用户uid，用户账号唯一标识
     */
    uid: string;
    /**
     * VIP，是否为VIP
     */
    vip: boolean;
}
