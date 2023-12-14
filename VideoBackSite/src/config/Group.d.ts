/**
 * Group
 */
export interface Group {
    /**
     * 选项图标
     */
    icon?: string;
    /**
     * 一级分类名，一级分类显示名称
     */
    label: string;
    /**
     * 二级分类组，一级分类包含的二级分类
     */
    options?: Option[];
    /**
     * 一级分类值，一级分类的资源标识符
     */
    value: string;
}

/**
 * Option
 */
export interface Option {
    /**
     * 选项图标
     */
    icon?: string;
    /**
     * 二级分类名，二级分类显示名称
     */
    label: string;
    /**
     * 二级分类值，二级选项资源标识符
     */
    value: string;
}