//深拷贝
export const copyObject = (object: any) => {
    return JSON.parse(JSON.stringify(object));
}