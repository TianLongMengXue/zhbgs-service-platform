const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    devServer: {
        port: 80, // npm run serve 启动时的端口号
        open: false, // npm run serve 自动启动浏览
    }
});
