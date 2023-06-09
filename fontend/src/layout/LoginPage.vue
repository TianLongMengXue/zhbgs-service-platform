<template>
    <div id="login-container">
        <div id="login-panel">
            <div id="login-panel-title">
                <p>综合办公室</p>
                <p>业务平台</p>
            </div>
            <div id="login-panel-main">
                <SnowLoginForm />
            </div>
        </div>
    </div>
    <div id="login-footer">
        <SnowFooter :content="content" :bgColor="footerBgColor" :textColor="footerTextColor" />
    </div>
</template>

<script>
import SnowFooter from "@/components/SnowFooter";
import SnowLoginForm from "@/components/SnowLoginForm";
import {clickHeart} from "@/utils/clickHeart.js";
import {startSakura} from "@/utils/startSakura.js";

export default {
    name: "LoginPage",
    components: {
        SnowFooter,
        SnowLoginForm,
    },
    mounted() {
        clickHeart();
    },
    data: function () {
        return {
            content: {
                createdFullYear: "2023",
                owner: "综合办公室",
            },
            // footerBgColor: "rgba(135, 206, 235, .3)",
            footerBgColor: "rgba(255,250,250, .3)",
            footerTextColor: "rgb(102, 102, 102)",
        }
    }
}
</script>

<style scoped>
#login-container{
    width: 1280px;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
}
#login-panel {
    width: fit-content;
    height: fit-content;
    display: flex;
    flex-direction: row;
    /*background-color: rgba(135, 206, 235, .1);
    border: 1px solid rgba(135, 206, 235, .3);*/
    background-color: rgba(255,250,250, .2);
    border: 1px solid rgba(255,250,250, .8);
    border-radius: 40px;
    backdrop-filter: blur(10px);
}
#login-panel-title {
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding-left: 40px;
}
#login-panel-title p{
    font-family: "华文行楷","Sim sun",serif;
    font-size: 36px;
    font-weight: 900;
    color: rgb(102,102,102);
    user-select: none;
    text-align: center;
}
#login-panel-main {
    width: 400px;
    height: 400px;
    display: flex;
    align-items: center;
    justify-content: center;
}
#login-footer{
    width: 100%;
    height: fit-content;
}
</style>
