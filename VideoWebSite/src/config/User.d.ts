/**
 * 用户
 */
export type User = {
    /**
     * 登录账号，用于用户登录的账号名
     */
    account: string;
    /**
     * VIP到期时间，用户购买的VIP会员服务过期时间
     */
    expirationTime?: string;
    /**
     * 用户头像，用户头像静态资源路径
     */
    headImage: string;
    /**
     * 用户名，用户昵称
     */
    name: string;
    /**
     * 余额，用户账户余额
     */
    remainingSum: number;
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