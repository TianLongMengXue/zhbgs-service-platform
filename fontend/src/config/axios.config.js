import axios from "axios";
// 配置请求的根路径
axios.defaults.baseURL="http://localhost:8080";
// 为请求头添加跨域访问权限
axios.defaults.headers={"Access-Control-Allow-Origin": "*"};
export default axios;