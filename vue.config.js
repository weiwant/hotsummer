const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      'users': {
        target: "http://abcds.vaiwan.com:8080/users",
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^users': ''
        }
      }
    }
  }
})
