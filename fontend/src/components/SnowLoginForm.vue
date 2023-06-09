<template>
    <div id="login-form-container">
        <div id="login-form-background">
            <svg viewBox="0 0 320 300" xmlns="http://www.w3.org/2000/svg">
                <defs>
                    <linearGradient inkscape:collect="always" id="linearGradient" x1="13" y1="193.49992" x2="307"
                                    y2="193.49992" gradientUnits="userSpaceOnUse">
                        <stop style="stop-color:#ff00ff;" offset="0" id="stop876"/>
                        <stop style="stop-color:#ff0000;" offset="1" id="stop878"/>
                    </linearGradient>
                </defs>
                <path ref="svgPath"
                    d="m 40,120.00016 239.99984,-3.2e-4 c 0,0 24.99263,0.79932 25.00016,35.00016 0.008,34.20084 -25.00016,35 -25.00016,35 h -239.99984 c 0,-0.0205 -25,4.01348 -25,38.5 0,34.48652 25,38.5 25,38.5 h 215 c 0,0 20,-0.99604 20,-25 0,-24.00396 -20,-25 -20,-25 h -190 c 0,0 -20,1.71033 -20,25 0,24.00396 20,25 20,25 h 168.57143"/>
            </svg>
        </div>
        <div id="login-form-main">
            <h1 class="login__title">用户登录</h1>
            <div class="login__form-content">
                <div class="login__box">
                    <span class="fa fa-user-o login__icon" style="font-size: 16px;"></span>
                    <div class="login__box-input">
                        <input
                            id="login-name"
                            type="text"
                            required
                            class="login__input"
                            placeholder=" "
                            v-model="loginForm.username"
                            @keyup.enter.exact="submitLoginForm"
                            @focus="changePath(['240','1386'],'0')" />
                        <label for="login-name" class="login__label">用户名称</label>
                    </div>
                </div>
                <div class="login__box">
                    <span class="fa fa-lock login__icon" style="font-size: 20px;"></span>
                    <div class="login__box-input">
                        <input
                            id="login-word"
                            ref="login-word"
                            type="password"
                            required
                            class="login__input"
                            placeholder=" "
                            v-model="loginForm.password"
                            @keyup.enter.exact="submitLoginForm"
                            @focus="changePath(['240','1386'],'-336')" />
                        <label for="login-word" class="login__label">用户密码</label>
                        <span ref="login-eye" class="fa fa-eye-slash login__eye" tips="显示您输入的密码" id="login-eye" @click="showHiddenWord"></span>
                    </div>
                </div>
            </div>
            <button
                class="login__button"
                id="login-submit"
                @click="submitLoginForm"
                @focus="changePath(['530','1386'],'-730')">登录</button>
            <div class="login__check">
                <div class="login__check-group">
                    <input type="checkbox" class="login__check-input" id="login-store-info">
                    <label for="login-store-info" class="login__check-label">保存登录信息</label>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "SnowLoginForm",
    props:[],
    data: function (){
        return {
            /*svgPathStyle: {
                array: {"stroke-dasharray": "240px, 1386px"},
                offset: {"stroke-dashoffset": "0px"},
            }*/
            loginForm: {
                username: "",
                password: "",
            }
        }
    },
    methods: {
        // 显示密码输入框隐藏的密码内容
        /*showHiddenWord () {
            const inputWord = document.getElementById("login-word");
            const labelEye = document.getElementById("login-eye");
            if (inputWord.type === "password") {
                // 改变密码输入框的类型 password ==> text
                inputWord.type = "text";
                // 改变labelEye的图标
                labelEye.classList.add("fa-eye");
                labelEye.classList.remove("fa-eye-slash");
                // console.log("if branch");
            } else {
                // 改变密码输入框的类型 text ==> password
                inputWord.type = "password";
                // 改变labelEye的图标
                labelEye.classList.add("fa-eye-slash");
                labelEye.classList.remove("fa-eye");
                // console.log("else branch");
            }
        },*/
        showHiddenWord () {
            if (this.$refs["login-word"].type === "password"){
                // 改变密码输入框的类型 password ==> text
                this.$refs["login-word"].type = "text";
                // 改变labelEye的图标
                this.$refs["login-eye"].classList.add("fa-eye");
                this.$refs["login-eye"].classList.remove("fa-eye-slash");
                // 改变提示文本内容 "显示您输入的密码" ==》 "隐藏您输入的密码"
                this.$refs["login-eye"].setAttribute("tips", "隐藏您输入的密码");
            } else {
                // 改变密码输入框的类型 text ==> password
                this.$refs["login-word"].type = "password";
                // 改变labelEye的图标
                this.$refs["login-eye"].classList.add("fa-eye-slash");
                this.$refs["login-eye"].classList.remove("fa-eye");
                // 改变提示文本内容 "隐藏您输入的密码" ==》 "显示您输入的密码"
                this.$refs["login-eye"].setAttribute("tips", "显示您输入的密码");
            }
        },
        // 设置登录表单的背景可视区域的变化
        /*changePath (focusTagName) {
            if ("login__name-focus" === focusTagName){
                this.svgPathStyle.array = '{"stroke-dasharray": "240px, 1386px"}';
                this.svgPathStyle.offset = '{"stroke-dashoffset": "0px"}';
                console.log(this.svgPathStyle.offset+this.svgPathStyle.array);
            } else if ("login__word-focus" === focusTagName) {
                this.svgPathStyle.array = '{"stroke-dasharray": "240px, 1386px"}';
                this.svgPathStyle.offset = '{"stroke-dashoffset": "-336px"}';
                console.log(this.svgPathStyle.offset+this.svgPathStyle.array);
            } else if ("login__button-focus" === focusTagName){
                this.svgPathStyle.array = '{"stroke-dasharray": "530px, 1386px"}';
                this.svgPathStyle.offset = '{"stroke-dashoffset": "-730px"}';
                console.log(this.svgPathStyle.offset+this.svgPathStyle.array);
            }
        },*/
        changePath (dasharray, dashoffset) {
            // const svgPath = document.getElementsByTagName("path");
            let dasharrayString = "";
            let dashoffsetString = dashoffset + "px";
            // console.log(svgPath);
            // console.log(dasharray.length);
            for (let i = 0; i < dasharray.length; i++) {
                dasharrayString += dasharray[i]+"px";
                if (i < dasharray.length - 1) {
                    dasharrayString += ",";
                }
            }
            // console.log(dasharrayString);
            /*svgPath.setAttribute("stroke-dasharray", dasharrayString);
            svgPath.setAttribute("stroke-dashoffset", dashoffset);*/
            /*this.$refs.svgPath.setAttribute("stroke-dasharray", dasharrayString);
            this.$refs.svgPath.setAttribute("stroke-dashoffset", dashoffsetString);*/
            this.$refs.svgPath.style.strokeDasharray = dasharrayString;
            this.$refs.svgPath.style.strokeDashoffset = dashoffsetString;
        },
        // 封装一个登录时的提示窗口
        alertErrorMessage(message) {
            this.$alert(message, "错误", {
                confirmButtonText: 'OK',
            });
        },
        /*
        * 对登录时的用户名称和用户密码进行校验
        * 1、用户名称和用户密码不能为空
        * 2、用户密码的位数不能少于6位、不能多于16位
        * 3、向后端发送登录的请求
        * */
        submitLoginForm () {
            if(this.loginForm.username.length === 0){
                this.alertErrorMessage("请输入用户名称");
                this.loginForm.username = "";
            } else if(this.loginForm.password.length === 0) {
                this.alertErrorMessage("请输入密码");
                this.loginForm.password = "";
            } else if (this.loginForm.password.length < 6) {
                this.alertErrorMessage("输入的密码长度不能小于6位");
                this.loginForm.password = "";
            } else if (this.loginForm.password.length > 16) {
                this.alertErrorMessage("输入的密码长度不能大于16位");
                this.loginForm.password = "";
            } else {
                this.$http.post("/user/login",{
                    username: this.loginForm.username,
                    password: this.loginForm.password,
                }).then((response) => {
                    console.log(response);
                }).then((error) => {
                    console.log(error);
                });
            }
        }
    },
}
</script>

<style scoped>
/* 控制整个登录页面的大小为 320px * 320px */
#login-form-container{
    width: 320px;
    height: 320px;
    position: relative;
}
/* 登录表单部分,由于添加的背景的关系,需要将整个登录表达完全位于整个背景之上,不能被背景遮挡 */
#login-form-main{
    position: absolute;
    top: 0;
    left: 0;
    width: 320px;
    height: 320px;
    padding: 0 2.5rem;
}
/* 设置登录表达部分显示的文本大小和字体颜色等内容 */
#login-form-main h1,
#login-form-main label,
#login-form-main button
{
    font-family: "华文行楷","Sim sun",serif;
    color: rgb(102,102,102);
    user-select: none;
    text-align: center;
}
.login__title{
    font-weight: 900;
}
/* 登录表单中的用户名称和密码的输入部分 */
.login__form-content{
    display: grid;
    grid-template-columns: 240px;
    grid-auto-rows: 70px;
    margin-top: 40px;
    z-index: 2;
}
/* 背景的大小是 320px * 320px 但是可视部分的宽度只有 240px 要确保整个登录表单部分是在背景可视部分之上 */
.login__box{
    width: 240px;
    position: relative;
}
/* 用户名称和用户密码部分的图标icon需要固定在输入框的起始位置 */
.login__box>.login__icon{
    position: absolute;
    left: 0;
}
/* 用户名称和用户密码输入框由于需要位于用户名称icon的后面,且不能遮挡 */
.login__box-input{
    width: 220px;
    height: fit-content;
    white-space:nowrap;
    position: absolute;
    left: 20px;
}
.login__input{
    width: 220px;
    padding-block: 1rem;
    vertical-align: middle;
    position: absolute;
    top: -18px;
    font-size: 18px;
    background-color: transparent;
}
.login__input:hover{
    cursor: text;
}
/* 用户密码输入框之后还有一个显示密码的icon图标,所以需要控制输入框的内容不能遮挡这个icon */
#login-word{
    padding-right: 25px;
}
/* 设置用户名称和用户密码在发生上移和字体大小变化的时候的过渡动画 */
.login__label{
    position: absolute;
    left: 0;
    top: -5px;
    transition: top .3s, font-size .3s;
}
.login__label:hover{
    cursor: text;
}
/* 设置当用户进行用户名称或者用户密码输入的时候,用户名称和用户密码这两个用于标识的文本发生上移以及字体缩小 */
.login__input:focus + .login__label{
    top:-30px;
    font-size: smaller;
    font-weight: normal;
}
/* 设置当用户名称或者用户密码已有输入不为空的时候,用户名称和用户密码这两个用于标识的文本保持上移以及字体缩小 */
.login__input:not(:placeholder-shown).login__input:not(:focus) + .login__label {
    top:-30px;
    font-size: smaller;
    font-weight: normal;
}
/* 用户显示用户密码输入框内容的图标icon的位置 */
.login__eye{
    position: absolute;
    right: 0;
    z-index: 9;
    cursor: pointer;
}
.login__eye:hover::after{
    content: attr(tips);
    display: inline-block;
    padding: 10px;
    border: 2px solid rgba(135, 206, 235, .3);
    border-radius: 5px;
    color: rgb(102,102,102);
    background-color: #e3e4e5;
    width: 135px;
    text-align: center;
    font-family: "华文行楷","Sim sun",serif;
    line-height: 1.6;
    white-space: pre-wrap;
    position: fixed;
}
/* 登录表单的按钮部分 */
.login__button{
    z-index: 3;
    width: 230px;
    height: 45px;
    position: absolute;
    left: 45px;
    bottom: 55px;
    border-radius: 50px;
    background-color: transparent;
    font-size: 35px;
    transition: all 300ms;
}
.login__button:hover{
    color: rgba(36,36,36,.8) !important;
}
.login__button:active{
    font-size: 30px;
}
/* 登录表单中的记住用户登录信息的按钮部分 */
.login__check{
    position: absolute;
    bottom: 10px;
}
.login__check-group{
    text-align: center;
}
.login__check-input{
    vertical-align: middle;
}
/* 当鼠标悬停的时候,显示详细信息的描述 */
.login__label{
    position: relative;
}
.login__check-label:hover::after{
    content: "保存您的登录信息,之后30天内,您可以无需再次输入用户名称和用户密码";
    display: inline-block;
    padding: 10px;
    border: 2px solid rgba(135, 206, 235, .3);
    border-radius: 5px;
    color: rgb(102,102,102);
    background-color: #e3e4e5;
    width: 200px;
    line-height: 1.6;
    white-space: pre-wrap;
    position: fixed;
}
/* 整个登录页面的背景设置 */
#login-form-background{
    width: 320px;
    height: 300px;
    position: relative;
}
#login-form-background>svg{
    position: absolute;
    top: 0;
    left: 0;
    width: 320px;
    height: 300px;
}
#login-form-background>svg>path{
    fill: none;
    stroke: url(#linearGradient);
    stroke-width: 4;
    stroke-dashoffset: 0;
    stroke-dasharray: 240 1386;
    transition: stroke-dashoffset 700ms, stroke-dasharray 700ms;
}
/* 设置背景可视区域,由于用户名称和用户密码的点击事情,而发生变化*/
/*#login-form-background>svg>.login__name-focus*/
/*#login-name:focus + #svg-path
{
    stroke-dashoffset: 0;
    stroke-dasharray: 240px, 1386px;
}*/
/*#login-form-background>svg>.login__word-focus*/
/*#login-word:focus + #svg-path
{
    stroke-dashoffset: -336px;
    stroke-dasharray: 240px, 1386px;
}*/
/*#login-form-background>svg>.login__button-focus*/
/*#login-submit:focus + #svg-path
{
    stroke-dashoffset: -730px;
    stroke-dasharray: 530px, 1386px;
}*/
</style>
