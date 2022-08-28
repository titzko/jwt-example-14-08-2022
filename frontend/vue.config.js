// eslint-disable-next-line @typescript-eslint/no-var-requires
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true
})

module.exports = {
    devServer: {
        proxy: {
            "^/api/": {
                //https://forum.vuejs.org/t/devserver-proxy-not-working/86616
                target: "http://[::1]:8081",
                changeOrigin: true,
                logLevel: "debug"
            }
        }
    }
};
