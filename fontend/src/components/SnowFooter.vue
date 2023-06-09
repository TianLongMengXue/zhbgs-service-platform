<template>
    <div id="footer" :style="{backgroundColor:bgColor}">
        <p v-cloak :style="{color: textColor}">Copyright&nbsp;&copy;&nbsp;{{ content.createdFullYear }}
            <span v-show="equalCreatedAndCurrentYear">
                &nbsp;-&nbsp;{{currentFullYear}}
            </span>
            &nbsp;{{ content.owner }}&nbsp;All&nbsp;Right&nbsp;Reserved</p>
    </div>
</template>

<script>
export default {
    name: "SnowFooter",
    props: [
        // 父组件传递的一个对象
        "content",
        // 父组件传递一个背景颜色
        "bgColor",
        // 父组件传递一个文本颜色
        "textColor",
    ],
    data: function () {
        return {
            currentFullYear: new Date().getFullYear(),
        }
    },
    computed: {
        equalCreatedAndCurrentYear () {
            // 注意这里的数据类型,this.currentFullYear和this.content.createdFullYear的值相同但是数据类型不相同
            // console.log(this.currentFullYear.toString() === this.content.createdFullYear);
            return !(this.currentFullYear.toString() === this.content.createdFullYear);
        }
    },
}
</script>

<style scoped>
#footer{
    width: 100%;
    height: fit-content;
    padding: 20px;
    border-radius: 50px;
}
#footer p, #footer p span {
    /*font-size: 14px;*/
    font-weight: normal;
    display: block;
    text-align: center;
    user-select: none;
}
</style>
